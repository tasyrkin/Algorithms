package algorithms;

public class CyclicBuffer{

	/**
	 * contains history of last allowed requests
	 */
	private long[] array = null;
	/**
	 * cycle buffer start and end. if start=end the buffer is empty
	 */
	private int start = 0, end = 0;
	/**
	 * number of allowed requests
	 */
	private int limit = -1;
	
	/**
	 * Creates a cyclic buffer with defined limit
	 * @param requestsLimitNumber
	 */
	public CyclicBuffer(int requestsLimitNumber) {
		limit = requestsLimitNumber;
		array = new long[requestsLimitNumber+1];
	}	

	/**
	 * Checks if the current request is allowed per second
	 * @return
	 */
	public boolean isRequestAllowedPerSecond(){
		return isRequestAllowed(1000);
	}	

	/**
	 * Checks if the current request is allowed per minute
	 * @return
	 */	
	public boolean isRequestAllowedPerMinute(){
		return isRequestAllowed(60*1000);
	}
	
	/**
	 * Checks if the current request is allowed within @distanceMs milliseconds 
	 * @param distanceMs - distance in ms for which number of requests is required
	 * @return
	 */
	synchronized private boolean isRequestAllowed(int distanceMs) {
		long currTime = System.currentTimeMillis();
		int tmp = start;
		int count = 0;
		while(tmp!=end){
			if((currTime-array[tmp])<distanceMs)count++;
			else{
				end = tmp;
				break;
			}
			tmp = movePrev(tmp);
		}
		if(count>=limit)return false;
		start = moveNext(start);
		array[start] = currTime;
		if(start==end){
			end = moveNext(end);
		}
		return true;
	}
	private int moveNext(int idx){
		int res = (idx+1>=array.length) ? 0 : (idx+1);
		return res;
	}
	private int movePrev(int idx){
		int res = (idx-1<0) ? array.length-1 : (idx-1);
		return res;			
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		CyclicBuffer cq = new CyclicBuffer(2);
		while(true){
			boolean isAllowed = cq.isRequestAllowedPerSecond();
			System.out.println(isAllowed);
			Thread.sleep(450);
		}
	}
}
