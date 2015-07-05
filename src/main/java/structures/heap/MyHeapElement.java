package structures.heap;

public class MyHeapElement implements HeapElement{

	private Integer content;
	
	public MyHeapElement(Integer i){
		content = i;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(HeapElement he) {
		if(he instanceof MyHeapElement){
			MyHeapElement elem = (MyHeapElement)he;
			return content.compareTo(elem.content);
		}
		return 1;
	}

	@Override
	public Integer getKey() {
		return content;
	}

	@Override
	public Object getObject() {
		return content;
	}
	
	public String toString(){
		return content.toString();
	}
	
	public boolean equals(Object o){
		if(o!=null && o instanceof MyHeapElement){
			return content.equals(((MyHeapElement)o).content);
		}
		return false;
	}

	@Override
	public void increaseKey() {
		this.content++;		
	}

	@Override
	public void decreaseKey() {
		this.content--;
		
	}

}
