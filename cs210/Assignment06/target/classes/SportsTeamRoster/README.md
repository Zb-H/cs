# Sports Team Roster

## Project Description

This project is a command-line sports team roster system written in Java.

The program allows users to enter sports teams and players. Each team stores
a team name, coach name, conference name, and a list of players. Each player
stores a name, phone number, date of birth, and jersey number.

The program also supports search functionality. Users can search for a team
by team name, coach name, conference name, player name, phone number, date of
birth, or jersey number.

## Features

- Add multiple teams
- Add multiple players to each team
- Validate user input
- Validate phone numbers
- Validate dates of birth
- Check leap years
- Search using multiple keywords
- Use HashMap for fast lookup
- Use enum to control program modes

## Main Classes

### DateOfBirth

The DateOfBirth class stores and validates a person's birth year, month,
and date. It also formats the date for searching and printing.

### Person

The Person class stores player information, including name, phone number,
date of birth, and jersey number.

### Team

The Team class stores team information and player lists. It also contains
the main program logic.

### Mode

The Mode enum represents the current state of the program:

- COMMAND
- INPUT
- SEARCH
- EXIT

## How to Run

Compile the program:

```bash
javac SportsTeamRoster/*.java
