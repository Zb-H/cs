package SportsTeamRoster;

import java.util.*;
/**
 * The Team class represents a sports team and runs the roster program.
 *
 * Each Team object stores the team name, coach name, conference name,
 * and a list of players. The main method uses a HashMap to allow users
 * to search for a team by team name, coach name, conference name,
 * player name, phone number, date of birth, or jersey number.
 *
 * The program uses an enum called Mode to switch between command mode,
 * input mode, search mode, and exit mode.
 *
 * @author Zhaobi Huang
 * @version 1.1
 * @Git https://github.com/Zb-H/cs/tree/main/cs210/Assignment06
 */
public class Team{

    private String teamName;
    private String coachName;
    private String conferenceName;
    private ArrayList<Person> playerList;
    // construct an empty team object
    public Team(){
        this.playerList = new ArrayList<>();
    }
    // Sets all data fields throws error on empty inputs
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
    // load one player in playerlist
    public void playerListAdd(Person player){
        this.playerList.add(player);
    }
    // gets all datafields
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
    // input checker, catch exception and shows error message if input is empty.
    public static String stringInput(Scanner sc){
        while(true){
            try{
                String input =  sc.nextLine().trim();
                if(input == null || input.isEmpty()){
                    throw new IllegalArgumentException("Input must not be empty.");
                }
                return input;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
    // integer input checker, display error message if input is not integer.
    public static int intInput(Scanner sc){
        while(true){
            try{
                return Integer.parseInt(sc.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Input must be an integer.");
            }
        }
    }
/***************************************** main method ***************************************/
    public static void main(String[] args){
        HashMap<String, Team> teamRosters = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Mode mode = Mode.COMMAND;

        int teamLoopCount = 1;
        while(mode != Mode.EXIT){
            switch(mode){
/****************************************** Command stage ***************************************/
                case COMMAND -> {
                    System.out.print("Enter command: '1' for input mode / '2' for search/ '3' for exit: ");
                    String command = sc.nextLine();

                    if (command.equalsIgnoreCase("1")) {
                        mode = Mode.INPUT;
                    } else if (command.equalsIgnoreCase("2")) {
                        mode = Mode.SEARCH;
                    } else if (command.equalsIgnoreCase("3")) {
                        mode = Mode.EXIT;
                    } else {
                        System.out.println("Invalid command.");
                    }
                }
/********************************************* search stage *************************************/
                case INPUT -> {
                    // team info input loop
                    while(true){
                        Team team = new Team();     // playerList initialized
                        System.out.printf("%40s-%s%d-%n", " ", "Team No.", teamLoopCount);
                        // sets team datafields and l
                        System.out.printf("%-80s", "Enter team name: ");
                        team.setTeamName(stringInput(sc));
                        teamRosters.put(team.getTeamName(), team);

                        System.out.printf("%-80s", "Enter coachName: ");
                        team.setCoachName(stringInput(sc));
                        teamRosters.put(team.getCoachName(), team);

                        System.out.printf("%-80s", "Enter conference name: ");
                        team.setConferenceName(stringInput(sc));
                        teamRosters.put(team.getConferenceName(), team);

                        int personLoopCount = 1;
                        // player info input loop
                        while(true){
                            Person person = new Person();
                            System.out.printf("%-40s-%s%d-%n", " ", "Person No.", personLoopCount);

                            System.out.printf("%-80s", "Enter person name: ");
                            person.setName(stringInput(sc));

                            System.out.printf("%-80s", "Enter phone number: ");
                            while(true){
                                try{
                                    person.setPhoneNumber(sc.nextLine().trim());
                                    break;
                                }catch(IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                }
                            }

                            System.out.printf("%-80s", "Enter date of birth: ");
                            while(true){
                                try{
                                    person.setDateOfBirth(sc.nextLine().trim());
                                    break;
                                }catch(IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                }
                            }

                            System.out.printf("%-80s", "Enter jersey number: ");
                            while(true){
                                try{
                                    person.setJerseyNumber(sc.nextLine().trim());
                                    break;
                                }catch(IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            // load inputs in datafields.
                            team.playerListAdd(person);
                            teamRosters.put(person.getName(), team);
                            teamRosters.put(person.getPhoneNumber(), team);
                            teamRosters.put(person.getDateOfBirth().searchDob(), team);
                            teamRosters.put(person.getDateOfBirth().printDob(),team);
                            teamRosters.put(person.getJerseyNumber(), team);

                            System.out.printf("%-80s", "Put in more players?(Y/n): ");
                            personLoopCount ++;
                            if(sc.nextLine().trim().equalsIgnoreCase("n")){
                                break;
                            }
                        }
                        System.out.printf("%-80s", "Put in more teams?(Y/n): ");
                        teamLoopCount ++;
                        if(sc.nextLine().trim().equalsIgnoreCase("n")){
                            break;
                        }
                    }
                    System.out.print("Enter command: '0' for command mode / '2' for search/ '3' for exit: ");
                    String command = sc.nextLine();

                    if (command.equalsIgnoreCase("0")) {
                        mode = Mode.COMMAND;
                    } else if (command.equalsIgnoreCase("2")) {
                        mode = Mode.SEARCH;
                    } else if (command.equalsIgnoreCase("3")) {
                        mode = Mode.EXIT;
                    } else {
                        System.out.println("Invalid command.");
                    }
                }
/******************************************* search stage ****************************************/
                case SEARCH -> {
                    while(true){
                        String key = null;
                        System.out.printf("%n%-80s", "Enter search words: ");
                        try{
                            key = stringInput(sc);
                        }catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                        if(teamRosters.containsKey(key)){
                            Team targetTeam = teamRosters.get(key);
                            System.out.println(targetTeam.getTeamName());
                            System.out.println(targetTeam.getCoachName());
                            System.out.println(targetTeam.getConferenceName());
                            for(Person targetPlayer : targetTeam.getPlayerList()){
                                System.out.println(targetPlayer.getName());
                                System.out.println(targetPlayer.getJerseyNumber());
                                System.out.println(targetPlayer.getPhoneNumber());
                                System.out.println(targetPlayer.getDateOfBirth().printDob());
                            }
                        }else{
                            System.out.printf("%-80s", "Nothing match with the search word.");
                        }
                        System.out.printf("%n%80s", "Do more searches?(Y/n): ");
                        if(sc.nextLine().trim().equalsIgnoreCase("n")){
                            break;
                        }
                    }
                    System.out.print("Enter command: '0' for command mode / '1' for input/ '3' for exit: ");
                    String command = sc.nextLine();

                    if (command.equalsIgnoreCase("0")) {
                        mode = Mode.COMMAND;
                    } else if (command.equalsIgnoreCase("1")) {
                        mode = Mode.INPUT;
                    } else if (command.equalsIgnoreCase("3")) {
                        mode = Mode.EXIT;
                    } else {
                        System.out.println("Invalid command.");
                    }

                }
/********************************************** exit stage **************************************/
                case EXIT -> {
                    break;
                }
            }
        }
        sc.close();
    }
}
// enum class repesents different stages
enum Mode{
    COMMAND,
    INPUT,
    SEARCH,
    EXIT;
}
