package PetStore;

import java.util.*;


class PetStore {
    public static boolean isStop(String str){
        return str.equalsIgnoreCase("stop");
    }
	public static void main(String args[]) {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		Scanner scanner = new Scanner(System.in);
		String input;
		String name = "";
		Pet aPet = new Pet();
		String coatColor;
		double weight;
        int age;
        double length;

		while (true) {
			System.out.print("Enter 'c' for cat, 'd' for dog,'f' for fish or 's' for snake: ");
			input = scanner.next().toLowerCase();

			if (input.charAt(0) == 'c') {
				System.out.println("Enter the name of the cat (or STOP to quit): ");
				name = scanner.next();
				if (name.equalsIgnoreCase("STOP"))
					break;
				System.out.println("Enter the cat's coat color: ");
				coatColor = scanner.next();
				aPet = new Cat();
				((Cat) aPet).setCoatColor(coatColor);
			} else if (input.charAt(0) == 'd') {
				System.out.println("Enter the name of the dog (or STOP to quit): ");
				name = scanner.next();
				if (name.equalsIgnoreCase("STOP"))
					break;
				System.out.println("Enter the dog's weight: ");
				weight = scanner.nextDouble();
				aPet = new Dog();
				((Dog) aPet).setWeight(weight);
			}else if(input.charAt(0) == 'f'){
                System.out.println("Enter the name of the fish (or STOP to quit): ");
                name = scanner.next();
                if(isStop(name))
                    break;
                System.out.println("Enter the fish's age: ");
                age = scanner.nextInt();
                aPet = new Fish();
                ((Fish) aPet).setAge(age);
            }else if(input.charAt(0) == 's'){
                System.out.println("Enter the name of the snake (or STOP to quit): ");
                name = scanner.next();
                if(isStop(name))
                    break;
                System.out.println("Enter the snake's length: ");
                length = scanner.nextDouble();
                aPet = new Snake();
                ((Snake) aPet).setLength(length);
            }else{
				System.out.println("Invalid choice.");
				continue;
			}

			aPet.setName(name);
			pets.add(aPet);
		}

		for (Pet pet : pets) {
			System.out.print(pet.getName() + " is a");
			if (pet instanceof Cat)
				System.out.println(" " + ((Cat)pet).getCoatColor() + " cat.");
			else if (pet instanceof Dog)
				System.out.println(" dog that weights " + ((Dog)pet).getWeight() + " pounds.");
			else if (pet instanceof Fish)
				System.out.println(" " + ((Fish)pet).getAge() + " years old fish.");
			else if (pet instanceof Snake)
				System.out.println(" snake that is " + ((Snake)pet).getLength() + " feet.");
            else
				System.out.println("n unknown animal.");

            System.out.println(pet.getName() + " says: " + pet.speak());
		}
        scanner.close();
	}
}
