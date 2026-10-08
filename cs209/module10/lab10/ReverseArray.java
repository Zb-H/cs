public class ReverseArray {
	public static void main(String[] args) {
        	int[] numbers = {5, 4, 3, 2, 1};

        	// print the elements in the original array
		for(int num : numbers){
			System.out.printf("%d ", num);
		}
		System.out.printf("\n");
        	// print the reverse elements in the array
		for(int i = (numbers.length-1); i>-1;i--){
			System.out.printf("%d ", numbers[i]);
		}
    	}
}
