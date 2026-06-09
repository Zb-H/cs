package SportsTeamRoster;

import java.util.*;
/**
 * Hello world!
 */
public class Team{
    private String teamName;
    private String coachName;
    private String conferenceName;
    private ArrayList<Person> playerList;

    public Team(){
        this.playerList = new ArrayList<>();
    }

    public void setTeamName(String teamName){
        if(teamName == null || teamName.isEmpty()){
            throw new IllegalArgumentException("Team name cannot be empty");
        }
        this.teamName = teamName;
    }
    public void setCoachName(String coachName){
        if(coachName == null || coachName.isEmpty()){
            throw new IllegalArgumentException("Coach name cannot be empty");
        }
        this.coachName = coachName;
    }
    public void setConferenceName(String conferenceName){
        if(conferenceName == null || conferenceName.isEmpty()){
            throw new IllegalArgumentException("Conference name cannot be empty");
        }
        this.conferenceName = conferenceName;
    }
    public void playerListAdd(Person player){
        this.playerList.add(player);
    }

    public String getTeamName(){
        return this.teamName;
    }
    public String getCoachName(){
        return this.coachName;
    }
    public String getConferenceName(){
        return this.conferenceName;
    }
    public ArrayList<Person> getPlayerList(){
        return this.playerList;
    }

    public void main(String[] args){
        HashMap<String, Team> teamRosters = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        Team team01 = new Team();
        System.out.printf("%80s", "Enter team name: ");
        while(true){
            try{
                team01.setTeamName(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }

        teamRosters.put(team01.getTeamName(), team01);

        System.out.printf("%80s", "Enter coach name: ");
        while(true){
            try{
                team01.setCoachName(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }

        System.out.printf("%80s", "Enter conference name: ");
        while(true){
            try{
                team01.setConferenceName(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }
        
        Person player01 = new Person();
        team01.playerListAdd(player01);
        System.out.printf("%80s", "Enter player name: ");
        while(true){
            try{
                player01.setName(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }
        
        System.out.printf("%80s", "Enter phone number: ");
        while(true){
            try{
                player01.setPhoneNumber(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }

        System.out.printf("%80s", "Enter birth year: ");
        while(true){
            try{
                player01.setPhoneNumber(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }

        System.out.printf("%80s", "Enter jersey number: ");
        while(true){
            try{
                player01.setJerseyNumber(sc.nextLine().trim());
                break;
            }catch(IllegalArgumentException e){
                continue;
            }
        }
        sc.close();
    }
}
