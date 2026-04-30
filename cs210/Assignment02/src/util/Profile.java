package util;

public class Profile{
	private String fn;
    private String ln;
	private long number;
	private String email;
    private String school;

	public void setFn(String fn){
		if(!Tools.checkString(fn)){
//			throw new IllegalArgumentException("Name must only contain letters");
			System.out.printf("Name must only contain letters\n");
			return;
		}
		this.fn = fn;
	}

	public void setLn(String ln){
		if(!Tools.checkString(ln)){
//			throw new IllegalArgumentException("Name must only contain letters");
			System.out.printf("Name must only contain letters\n");
			return;
		}
		this.ln = ln;
	}
	public void setNumber(long number){
		if(!Tools.checkLength(number)){
			System.out.printf("Number must be a 10 digit positive number\n");
			return;
		}
		this.number = number;
	}

	public void setEmail(String email){
		if(!Tools.checkEmail(email)){
			System.out.printf("Email must only caontain \"0-9\", \"A-Z\" ,\"a-z\" and \"_\"\n");
			return;
		}
		this.email = email;
	}

    public void setSchool(String school){
        if(Tools.checkString(school)){
            System.out.printf("School must only contain letters\n");
            return;
        }
        this.school = school;
    }

	public String getFn(){
		return fn;
	}

    public String getLn(){
        return ln;
    }

	public long getNumber(){
		return number;
	}

	public String getEmail(){
		return email;
	}
    
    public String getSchool(){
        return school;
    }

}

