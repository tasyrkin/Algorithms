package algorithms;

import java.util.HashMap;
import java.util.Map;

public class BoxStacking {

	public static final float PRECISION = 0.0001f;
	private Map<MapKey, Float> cache = new HashMap<MapKey, Float>(); 

	public boolean floatsEqual(Float f1, Float f2){
		return Math.abs(f1-f2) <= PRECISION;
	}

	public static class MapKey{
		int boxIdx = -1;
		int areaXY = -1;
		
		public MapKey(int boxIdx, int areaXY){
			this.boxIdx = boxIdx;
			this.areaXY = areaXY;
		}
		
		public boolean equals(Object o){
			if(o != null && o instanceof MapKey){
				MapKey mk = (MapKey)o;
				return this.boxIdx == mk.boxIdx && this.areaXY == mk.areaXY;
			}
			return false;
		}
	
		public int hashCode(){
			return (boxIdx << 2) & (0x3 & areaXY);
		}
	}
	
	public float getTallestBoxStacking(float[][]array){

		float maxHeight = Float.MIN_VALUE;

		for(int box = 0; box < array.length; box++){

			for(int cntXCurr = 0; cntXCurr < array[0].length; cntXCurr++){
				int cntYCurr = cntXCurr + 1;
				if(cntYCurr >= array[0].length){
					cntYCurr = 0;				 
				}

				float width_i = array[box][cntXCurr];
				float length_i = array[box][cntYCurr];
				int heightIndex = getMissingIndex(cntXCurr, cntYCurr);
				float height_i = array[box][heightIndex];

				MapKey mk = new MapKey(box, cntXCurr+cntYCurr);
				
				Float currHeight = null;
				Float cachedValue = cache.get(mk);
				if(cachedValue != null){
					currHeight = cachedValue;
				}
				else{
					currHeight = getTallestBoxStacking(array, width_i, length_i, height_i);
					cache.put(mk, currHeight);
				}
				if(currHeight > maxHeight && !floatsEqual(currHeight, maxHeight)){
					maxHeight = currHeight;
				}

			}			
		}
		return maxHeight;
	}

	public float getTallestBoxStacking(float[][]array, float width_j, float length_j, float height_j){


		float maxHeight = Float.MIN_VALUE;
		boolean recursiveInvokation = false;

		for(int box = 0; box < array.length; box++){

			for(int cntXCurr = 0; cntXCurr < array[0].length; cntXCurr++){
				int cntYCurr = cntXCurr + 1;
				if(cntYCurr >= array[0].length){
					cntYCurr = 0;				 
				}
				
				MapKey mk = new MapKey(box, cntXCurr+cntYCurr);

				float width_i = array[box][cntXCurr];
				float length_i = array[box][cntYCurr];
				//float currArea = array[box][cntXCurr]*array[box][cntYCurr];
				int lengthIndex = getMissingIndex(cntXCurr, cntYCurr);
				float height_i = array[box][lengthIndex];
				if(width_j < width_i 
						&& !floatsEqual(width_j, width_i)
				&& length_j < length_i
						&& !floatsEqual(length_j, length_i)){

					Float currHeight = null;
					Float cachedValue = cache.get(mk);
					if(cachedValue != null){
						currHeight = cachedValue;
					}
					else{
						currHeight = getTallestBoxStacking(array, width_i, length_i, height_i);
						cache.put(mk, currHeight);
					}				
					if(currHeight > maxHeight && !floatsEqual(currHeight, maxHeight)){
						maxHeight = currHeight;
					}
					recursiveInvokation = true;
				}

			}		
		}

		return height_j + (recursiveInvokation ? maxHeight : 0);
	}

	private int getMissingIndex(int cntXCurr, int cntYCurr) {
		return -1*((cntXCurr-1) + (cntYCurr-1)) + 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoxStacking bs = new BoxStacking();
		float[][] boxes = {{0.5f,0.1f,0.1f},{0.1f,2,5}};
		//float[][] boxes = {{0.1f,5f,0.2f}};
		
		float maxLength = bs.getTallestBoxStacking(boxes);
		System.out.println(maxLength);
	}

}
