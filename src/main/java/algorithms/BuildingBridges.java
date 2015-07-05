package algorithms;

public class BuildingBridges {

	
	public int getPairs(int south[], int north[], int south_j, int north_i){
		
		int maxPairs = -1;
		if(south_j >= south.length){
			return 0;
		}		
		for(int cnt = north_i + 1; cnt < north.length; cnt++){
			if(south[south_j] == north[cnt]){				
				int currPairsWithShift = getPairs(south, north, south_j + 1, cnt) + 1;
				int currPairsWithoutShift = getPairs(south, north, south_j + 1, north_i);
				if(Math.max(currPairsWithShift, currPairsWithoutShift) > maxPairs) {
					maxPairs = Math.max(currPairsWithShift, currPairsWithoutShift);
				}
				break;
			}
		}		
		return maxPairs;		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuildingBridges bb = new BuildingBridges();
		int[] south = {1,2,3,4,5};
		int[] north = {5,4,3,2,1};
		int pairs = bb.getPairs(south , north, 0, -1);
		System.out.println("Maximum pairs: " + pairs);
	}

}
