package SportsTeamRoster;

public class DateOfBirth{

    private String year;
    private String month;
    private String date;

    public DateOfBirth(){

    }

    public void setYear(int year){
        if(year < 1 || year > 2026){
            throw new IllegalArgumentException("year must between 1~2026");
        }
        String input;
        this.year = input.format()
    }
}
