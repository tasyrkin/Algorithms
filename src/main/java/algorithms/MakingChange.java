package algorithms;

import java.util.HashMap;
import java.util.Map;

public class MakingChange {

	int[] coinsForChange = new int[101];
	Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
	
	public int minimalCoins(int[] denominationCoins, int amountOfMoney){
		
		if(amountOfMoney == 0){
			return 0;
		}
		if(amountOfMoney == 1){
			return 1;
		}
		else if (amountOfMoney < 0){
			return Integer.MAX_VALUE;
		}
		
		int minimalAmountOfCoins = Integer.MAX_VALUE;
		
		for(int coin = denominationCoins.length-1; coin >= 0; coin--){
			Integer currentAmountOfCoins = cache.get(amountOfMoney-denominationCoins[coin]);
			if(currentAmountOfCoins==null){
				currentAmountOfCoins = minimalCoins(denominationCoins, amountOfMoney-denominationCoins[coin]);
			}
			if(minimalAmountOfCoins > currentAmountOfCoins){
				minimalAmountOfCoins = currentAmountOfCoins;
			}
			if(minimalAmountOfCoins == 0){
				cache.put(amountOfMoney, 1);
				return 1;
			}
		}
		cache.put(amountOfMoney, minimalAmountOfCoins + 1);
		return minimalAmountOfCoins + 1;		
	}
	
	public void calculareRest(int[] denominationCoins, int k, int rest){
		
		if(k < 0){
			return;
		}
		
		coinsForChange[denominationCoins[k]] = rest/denominationCoins[k];
		int newRest = rest - coinsForChange[denominationCoins[k]]*denominationCoins[k];
		calculareRest(denominationCoins, k-1, newRest);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int money = 11;
		//int[] denominationCoins = {1,2,3,5,10,25,50,100};
		int[] denominationCoins = {50,10,100,5,3,2,25,1};
		MakingChange mc = new MakingChange();
		
		System.out.println(mc.minimalCoins(denominationCoins, money));
		
//		mc.calculareRest(denominationCoins, denominationCoins.length-1, money);
//		
//		for(int cnt = 0; cnt < mc.coinsForChange.length; cnt++){
//			if(mc.coinsForChange[cnt] > 0){
//				System.out.println("coin: " + cnt + " amount: "+mc.coinsForChange[cnt] + " ");
//			}
//		}
	}

}
