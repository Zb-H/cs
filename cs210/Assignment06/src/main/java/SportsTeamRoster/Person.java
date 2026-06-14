package SportsTeamRoster;
/**
 * Represents a person or player in a sports team roster.
 *
 * <p>A Person object stores the player's name, phone number,
 * date of birth, and jersey number. This class validates user input
 * before saving data into the object.</p>
 */
public class Person{
    private String name;
    private String phoneNumber;
    private DateOfBirth dob;
    private String jerseyNumber;
    // Constructs a Person object
    // A new DateOfBirth object is created automatically so that
    // the person's birthday can be set later.
    public Person(){
        this.dob = new DateOfBirth();
    }
    // setting data fields
    public void setName(String name){
        // input epmty error
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    // phone number must contain exactly 10 digits
    public void setPhoneNumber(String phoneNumber){
        if(phoneNumber == null || !phoneNumber.matches("\\d{10}")){     // regex for phone number
            throw new IllegalArgumentException("Phone number must contain 10 numbers only");
        }
        this.phoneNumber = phoneNumber;
    }
    // The input must follow the month/day/year format.
    // For example: 1/2/2 or 1/02/2000.
    public void setDateOfBirth(String dateOfBirth){
        if(dateOfBirth.matches(REGEX)){
            String[] tokens = dateOfBirth.split("/");
            this.dob.setYear(Integer.parseInt(tokens[2]));
            this.dob.setMonth(Integer.parseInt(tokens[0]));
            this.dob.setDate(Integer.parseInt(tokens[1]));
        }else{
            throw new IllegalArgumentException("Date of birth must follow xx/xx/xxxx format");
        }
    }
    // The jersey number must contain one or two digits.
    public void setJerseyNumber(String jerseyNumber){
        if(jerseyNumber == null || !jerseyNumber.matches("\\d{1,2}")){
            throw new IllegalArgumentException("Jersey number must contain 1 or 2 numbers only");
        }
        this.jerseyNumber = jerseyNumber;
    }
    // get values of each data fields
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public DateOfBirth getDateOfBirth(){
        return this.dob;
    }
    public String getJerseyNumber(){
        return this.jerseyNumber;
    }
    // regex for birth of date.
    private static final String REGEX = "^\\d{1,2}/\\d{1,2}/\\d{1,4}$";
}
