public class exercise06{
	public static void main(String[] args){
		System.out.println("Welcome to \"Java Basics\":\n");
		frame();
		System.out.println("  1. Introduction to Java\n"
				+ "  2. Data Types\\t\\t\n"
				+ "  3. Operators\n"
				+ "  4. Control Flow\\t\\t\n"
				+ "  5. Arrays\n");
		frame();
	}

	public static void frame(){
		for (int i=0; i<22; i++){
			System.out.print("-");
		}
		System.out.println("\n");
	}
}
