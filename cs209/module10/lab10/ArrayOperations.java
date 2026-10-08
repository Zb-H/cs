public class ArrayOperations{

	public static void main(String[] args){
				//0, 1, 2,  3,  4
		int[] numbers = {45, 2, 9, 18, 29};
		int max = numbers[0];
		int min = numbers[0];

		// find the maximum and minimum values in the array
		for(int i=1; i<numbers.length;i++){
			if(numbers[i]>max){
				max = numbers[i];
			}else if(numbers[i]<min){
				min = numbers[i];
			}
		}

        	// print the maximum and minimum values in the array
		System.out.printf("%s%d%s%d","Max: ",max,"Min: ",min);
	}
}

