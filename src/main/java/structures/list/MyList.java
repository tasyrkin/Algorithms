package structures.list;

public class MyList {

	private MyListElem head = null;
	
	public MyListElem add(Object data){
		MyListElem newElem = new MyListElem(data, head);
		head = newElem;
		return newElem;
	}

	public void print(){
		MyListElem curr = head;
		while(curr!=null){
			System.out.print(curr.data.toString() + ",");
			curr = curr.next;
		}
	}
	
	static class MyListElem{
		Object data = null;
		MyListElem next = null;		
		
		public MyListElem(){
			data = null;
			next = null;
		}
		public MyListElem(Object data, MyListElem next){
			this.data = data;
			this.next = next;
		}
		public Object getData(){
			return data;
		}
		public MyListElem getNext(){
			return next;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyList list = new MyList();
		Integer[] array = new Integer[]{1,2,3,4,5,6,7,8};
		for(int cnt = 0; cnt < array.length; cnt++){
			list.add(array[cnt]);
		}
		list.print();
	}

}
