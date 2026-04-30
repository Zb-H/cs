package util;

public class Tools{

	public static boolean checkString(String str){
		for(char c : str.toCharArray()){
			if(!Character.isLetter(c)){
				return false;
			}
		}
		return true;
	}

	public static boolean checkLength(long number){
		if(number <= 0){
			return false;
		}else if((int)Math.log10(number)+1 == 10){
			return true;
		}
		return false;
	}

	public static boolean checkEmail(String email){
		//check if email is empty or email is in valid form
		return(email != null && email.matches("^[\\w.+%]+@[\\w]+\\.[\\w]{2,}$"));

	}
    
    public static void loop(Scanner sc, int i, Profile p){
        while(true){
            System.out.printf(prompt(i));
            String str = sc.nextLine().trim();
            
            
        }
    }
//	public static void ptr(String...lines){
//		for(String line:lines){
//			System.out.printf("%s",line);
//		}
//	}
}

