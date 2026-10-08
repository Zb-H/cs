public class SumArray{

	public static void main(String[] Args){
		int[] numbers = {3, 6, 9, 12, 15};
		int sum = 0;
        	//TODO: find the sum of integers in the array
		for(int i=0; i<numbers.length; i++){
			sum += numbers[i];
		}

        	//TODO: print the sum 
		System.out.printf("%s%d","Sum: ",sum);
	}
}

