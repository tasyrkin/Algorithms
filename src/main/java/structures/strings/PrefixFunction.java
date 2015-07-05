package structures.strings;

import java.util.Arrays;

public class PrefixFunction {

	public static int[]prefixFunction(String string){
		char[]str = string.toCharArray();
		int[]prefFunc = new int[str.length];
		if(prefFunc.length == 0){
			return prefFunc;
		}
		prefFunc[0] = 0;
	    for (int current = 1; current < str.length; ++current)
	    {
	        int matchedPrefix = current - 1;
	        int candidate = prefFunc[matchedPrefix];
	        while (candidate != 0 && str[current] != str[candidate])
	        {
	            matchedPrefix = prefFunc[matchedPrefix] - 1;
	            candidate = prefFunc[matchedPrefix];
	        }

	        if (candidate == 0)
	        	prefFunc[current] = str[current] == str[0] ? 1 : 0;
	        else
	        	prefFunc[current] = candidate + 1;
	    }		
		
		return prefFunc;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		System.out.println(Arrays.toString(PrefixFunction.prefixFunction("abca$ababcabcacab")));
	}

}
