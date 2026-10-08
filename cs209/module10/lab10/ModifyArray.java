public class ModifyArray{
	
	public static void main(String[] args){
		// Declare and initialize an array
      		int[] numbers = {10, 20, 30, 40, 50, 60, 70};

		
      		// modify array elements by duplicating every
      		// array element
		for(int i=0; i<numbers.length;i++){
			numbers[i] += numbers[i];
		}


      		// print the updated array elements
		for(int num : numbers){
			System.out.printf("%d ",num);
		}
	}

}
