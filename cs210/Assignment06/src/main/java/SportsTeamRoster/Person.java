package SportsTeamRoster;

public class Person{
    private String name;
    private String phoneNumber;
    private String dateOfBirth;
    private String jerseyNumber;

    public Person(){

    }

    public void setName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        if(phoneNumber == null || !phoneNumber.matches("\\d{10}")){     // regex for phone number
            throw new IllegalArgumentException("Phone number must contain 10 numbers only");
        }
        this.phoneNumber = phoneNumber;
    }
    public void setDateOfBirth(int year, int month, int date){
        if(year < 1 || year > 2026){
            throw new IllegalArgumentException("Year must between 1~2026");
        }
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("Month must between 1~12");
        }
        int maxDay;
        if(month == 2){
            if(isLeapYear(year)){
                maxDay = 29;
            }else{
                maxDay =28;
            }
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            maxDay = 30;
        }else{
            maxDay = 31;
        }
        if(date < 1 || date > maxDay){
            throw new IllegalArgumentException("Date of " + month + " must between 1~" + maxDay);
        }
        this.dateOfBirth = String.format("%02d/%02d/%04d", month, date, year);
    }
    public void setJerseyNumber(String jerseyNumber){
        if(jerseyNumber == null || !jerseyNumber.matches("\\d{2}")){
            throw new IllegalArgumentException("Jersey number must contain 2 numbers only");
        }
        this.jerseyNumber = jerseyNumber;
    }

    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    public String getJerseyNumber(){
        return this.jerseyNumber;
    }

    public boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
