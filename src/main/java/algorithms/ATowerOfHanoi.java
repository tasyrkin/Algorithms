package algorithms;
import java.util.LinkedList;


public class ATowerOfHanoi {
	
	LinkedList<Integer> peg1;
	LinkedList<Integer> peg2;
	LinkedList<Integer> peg3;

	public ATowerOfHanoi(int n){
		peg1 = new LinkedList<Integer>();
		for(int cnt = 0; cnt < n; cnt++){
			peg1.addFirst(cnt+1);
		}
		peg2 = new LinkedList<Integer>();
		peg3 = new LinkedList<Integer>();
	}
	
	public void moveDisks(Integer n, LinkedList<Integer> peg1, LinkedList<Integer> peg2, LinkedList<Integer> peg3){
		if(n==0){
			return;
		}
		else if(n==1){
			Integer elementToMove = peg1.remove(peg1.size()-1);
			peg3.addLast(elementToMove);
			System.out.println(this.toString());
			return;
		}
		else if(n==2){
			Integer elementToMove1 = peg1.remove(peg1.size()-1);
			peg2.addLast(elementToMove1);
			System.out.println(this.toString());
			Integer elementToMove0 = peg1.remove(peg1.size()-1);
			peg3.addLast(elementToMove0);
			System.out.println(this.toString());
			elementToMove1 = peg2.remove(peg2.size()-1);
			peg3.addLast(elementToMove1);
			System.out.println(this.toString());
			return;
		}
		moveDisks(n-1,peg1,peg3,peg2);
		moveDisks(1,peg1,peg2,peg3);
		moveDisks(n-1, peg2,peg1,peg3);
		System.out.println(this.toString());
	}
	
	public void runAlgorithm(){
		moveDisks(peg1.size(), peg1,peg2,peg3);
	}
	
	public String pegToString(LinkedList<Integer> peg){
		StringBuffer sb = new StringBuffer("{");		
		for(int cnt = 0; cnt < peg.size(); cnt++){
			sb.append(peg.get(cnt) + (cnt < peg.size() - 1 ? "," : ""));
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(pegToString(peg1) + "\n");
		sb.append(pegToString(peg2) + "\n");
		sb.append(pegToString(peg3) + "\n");
		return sb.toString();
	}
	
	public static void main(String[] args){
		ATowerOfHanoi atoh = new ATowerOfHanoi(5);
		System.out.println(atoh.toString());
		atoh.runAlgorithm();
		//System.out.println(atoh.toString());
	}

}
