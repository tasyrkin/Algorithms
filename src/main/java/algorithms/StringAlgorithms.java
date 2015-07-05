package algorithms;

public class StringAlgorithms {

	public void generateSubstrings(String str){
		int length = str.length();
		for(int i = 0; i < length;i++){
			for(int j = i+1; j <= length; j++){
				System.out.println(str.substring(i, j));
			}
		}
	}
	
	public static void main(String[] args) {
		StringAlgorithms sa = new StringAlgorithms();
		sa.generateSubstrings("abcd");
	}

}
