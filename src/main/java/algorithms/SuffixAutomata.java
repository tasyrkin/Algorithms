package algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SuffixAutomata {

	public static final int K = 26;
	public static final int MAXLEN = 1000;

	public static class State {
		int length;
		int link;
		Map<Character, Integer> transf = new HashMap<Character, Integer>();
		boolean terminal = false;

		public State(int length, int link, Map<Character, Integer> transf) {
			this.length = length;
			this.link = link;
			this.transf = transf;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("len=" + length);
			sb.append(";link=" + link);
			sb.append(";terminal=" + terminal);
			sb.append(";transf=[");
			Iterator<Entry<Character, Integer>> iterator = transf.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Character, Integer> entry = iterator.next();
				sb.append(entry.getKey() + ":" + entry.getValue() + ",");
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Map<Integer, State> automata = new HashMap<Integer, State>();
		int lastIdx = 0;
		automata.put(lastIdx, new State(0, -1, new HashMap<Character, Integer>()));
		char[] arr = { 'a', 'b', 'c', 'b', 'c' };
		for (int i = 0; i < arr.length; i++) {
			lastIdx = extend(automata, lastIdx, arr[i]);
		}
		int tempIdx = lastIdx;
		do {
			State tempState = automata.get(tempIdx);
			tempState.terminal = true;
			tempIdx = tempState.link;
		} while (tempIdx != -1);
		System.out.println("FINE CONSTRUCTION!");
		char[] arr1 = { 'a', 'a', 'b', 'c', 'b', 'c' };
		tempIdx = 0;
		boolean isSubstring = true;
		for (int i = 0; i < arr1.length; i++) {
			State tempState = automata.get(tempIdx);
			if (null != tempState.transf.get(arr1[i])) {
				tempIdx = tempState.transf.get(arr1[i]);
			} else {
				isSubstring = false;
				break;
			}
		}
		if (isSubstring) {
			System.out.println("A SUBSTRING");
		} else {
			System.out.println("NOT A SUBSTRING");
		}
		System.out.println("FINE!");
	}

	public static int extend(Map<Integer, State> automata, int lastIdx, char symbol) {
		State curState = new State(automata.get(lastIdx).length + 1, -1,
				new HashMap<Character, Integer>());
		int curIdx = lastIdx;
		while (automata.get(++curIdx) != null)
			;
		automata.put(curIdx, curState);
		int tempIdx = lastIdx;
		int pIdx = -1;
		do {
			State considered = automata.get(tempIdx);
			if (considered.transf.get(symbol) == null) {
				considered.transf.put(symbol, curIdx);
			} else {
				pIdx = tempIdx;
				break;
			}
			if (considered.link != -1) {
				tempIdx = considered.link;
				considered = automata.get(considered.link);
			} else {
				tempIdx = considered.link;
			}
		} while (tempIdx != -1);
		if (pIdx == -1) {
			curState.link = 0;
		} else {
			State pState = automata.get(pIdx);
			int qIdx = pState.transf.get(symbol);
			State qState = automata.get(qIdx);
			if (qState.length == pState.length + 1) {
				curState.link = qIdx;
			} else {
				Map<Character, Integer> clonedTransf = new HashMap<Character, Integer>();
				Iterator<Character> iter = qState.transf.keySet().iterator();
				while (iter.hasNext()) {
					char ch = iter.next();
					clonedTransf.put(ch, qState.transf.get(ch));
				}
				State clone = new State(pState.length + 1, qState.link, clonedTransf);
				int cloneIdx = curIdx;
				while (automata.get(++cloneIdx) != null)
					;
				automata.put(cloneIdx, clone);
				curState.link = cloneIdx;
				qState.link = cloneIdx;
				int checkStateIdx = pIdx;
				do {
					if (automata.get(checkStateIdx).transf.get(symbol) == qIdx) {
						automata.get(checkStateIdx).transf.put(symbol, cloneIdx);
					} else {
						break;
					}
					checkStateIdx = automata.get(checkStateIdx).link;
				} while (checkStateIdx != -1);
			}
		}
		return curIdx;
	}
}
