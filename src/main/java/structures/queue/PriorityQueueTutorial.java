package structures.queue;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueTutorial {

	public static class HeapElement implements Comparable{
		Integer size;
		Integer i;
		Integer j;
		
		public HeapElement(int size, int i, int j){
			this.size = size;
			this.i = i;
			this.j = j;
		}
		
		public String toString(){
			return "[" + this.size + "," + this.i + "," + this.j + "]";
		}

		@Override
		public int compareTo(Object o) {
			HeapElement he = (HeapElement)o;
			if(!this.size.equals(he.size))return (this.size.compareTo(he.size));
			else{
				if(!this.i.equals(he.i))return this.i.compareTo(he.i);
				else{
					if(!this.j.equals(he.j))return this.j.compareTo(he.j);
				}
			}
			return 0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][]board = {
							{1,0,1,0,1,1,1,1}
						   ,{0,1,0,1,0,0,0,1}
						   ,{1,0,1,1,1,1,1,1}
						   ,{0,0,0,1,0,1,0,1}
					   };
		printArray(board);
		System.out.println();
		
		int[][]larg = new int[board.length][board[0].length];
		
		for(int i=0; i<larg.length;i++){
			for(int j=0; j<larg[0].length;j++){
				larg[i][j] = 1;
			}
		}

		printArray(larg);
		
		System.out.println();
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[0].length; j++){
				if(i==0||j==0)continue;
				if(board[i-1][j]!=board[i][j]&&
						board[i][j-1]!=board[i][j]&&
						board[i-1][j-1]==board[i][j]){
					larg[i][j] = 1 + Math.min(larg[i-1][j], Math.min(larg[i][j-1], larg[i-1][j-1]));
				}
			}
		}
		
		printArray(larg);

		PriorityQueue<HeapElement> pq = new PriorityQueue<HeapElement>();
		for(int i=0; i<larg.length; i++){
			for(int j=0; j<larg[0].length; j++){
				pq.add(new HeapElement(-1*larg[i][j], i, j));
			}
		}
		HeapElement he = null;
		while((he = pq.poll())!=null)
			System.out.print(he.toString() + " ");
	}

	private static void printArray(int[][] array) {
		for(int i=0; i<array.length; i++){
			for(int j=0; j<array[0].length; j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

}
