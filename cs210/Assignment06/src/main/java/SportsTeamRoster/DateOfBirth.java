package SportsTeamRoster;
/**
 * Represents a person's date of birth.
 *
 * <p>This class stores the year, month, and date separately.
 * It also validates each field before saving it. The class can
 * format the date in two different ways: one for searching and one
 * for printing.</p>
 */
public class DateOfBirth{

    private int year;
    private int month;
    private int date;
    // Constructs an empty DateOfBirth object.
    public DateOfBirth(){

    }
    // Sets all data fields
    // make sure date of birth input and stores follows real life calender
    public void setYear(int year){
        if(year < 1 || year > 2026){
            throw new IllegalArgumentException("Year must between 1~2026");
        }
        this.year = year;
    }

    public void setMonth(int month){
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("Month must between 1~12");
        }
        this.month = month;
    }

    public void setDate(int date){
        int maxday;
        int year = this.year;
        int month = this.month;
        if(month == 2){
            if(isLeapYear(year)){
                maxday = 29;
            }else{
                maxday = 28;
            }
        }else if(month == 4 || month == 6 || month == 9 || month == 11){
            maxday = 30;
        }else{
            maxday = 31;
        }
        if(date < 1 || date > maxday){
            throw new IllegalArgumentException("Date must between 1~" + maxday + ".");
        }
        this.date = date;
    }
    // gets all data fields
    public int getYear(){
        return this.year;
    }
    public int getMonth(){
        return this.month;
    }
    public int getDate(){
        return this.date;
    }
    // return date of birth as string(patched to fix format search issue)
    public String searchDob(){
        return String.format("%d/%d/%d", this.month, this.date, this.year);
    }
    // return date of birth as string 
    public String printDob(){
        return String.format("%02d/%02d/%04d", this.month, this.date, this.year);
    }
    // check whether a year is leap year
    public boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
