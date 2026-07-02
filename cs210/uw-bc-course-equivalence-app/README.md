# UW <-> Bellevue College Course Equivalence App

A simple Java app that searches course equivalencies between University of Washington and Bellevue College.

This is designed as a practical CS210 final project idea because it solves a real student problem: checking whether a Bellevue College class can match a UW course requirement.

## Data included

The uploaded spreadsheet was converted into:

```text
data/course_equivalencies.csv
```

Current record count: **210** course equivalency rows.

## Features

- Loads real course equivalency data from CSV
- Searches by UW course, BC course, or general keyword
- Shows course title, credits, comments, and date range when available
- Has both command-line mode and Swing GUI mode
- Uses Java OOP: model, repository, service, enum, GUI class
- Uses `ArrayList`, file reading, CSV parsing, `switch`, encapsulation, and separation of responsibilities

## How to run

### CLI mode

```bash
mvn clean compile
mvn exec:java
```

### GUI mode

```bash
mvn clean compile
mvn exec:java -Dexec.args="gui"
```

## Example searches

Try searching:

- `CSE`
- `MATH 124`
- `MATH124`
- `CS 210`
- `calculus`
- `anthropology`

## Project structure

```text
uw-bc-course-equivalence-app/
├── data/
│   └── course_equivalencies.csv
├── docs/
│   └── PROJECT_PROPOSAL.md
├── pom.xml
└── src/main/java/com/zh/courseeq/
    ├── App.java
    ├── gui/EquivalencyFrame.java
    ├── model/CourseEquivalency.java
    ├── repository/EquivalencyRepository.java
    └── service/
        ├── SearchMode.java
        └── SearchService.java
```

## CS210 concepts shown

- **Encapsulation**: `CourseEquivalency` keeps fields private and exposes getters.
- **Abstraction**: `EquivalencyRepository` hides file-loading details.
- **Enum**: `SearchMode` controls how searching works.
- **Collections**: `ArrayList<CourseEquivalency>` stores all records.
- **File I/O**: `BufferedReader` reads the CSV file.
- **GUI**: Swing `JFrame`, `JTable`, `JTextField`, and buttons.
- **Robustness**: the CSV parser supports commas inside quoted fields.

## Possible final-project upgrades

1. Add a saved course plan: let students add searched courses into a personal transfer plan.
2. Add filter by subject: CSE, MATH, ENGL, PHYS, etc.
3. Add export: save selected courses to a new CSV.
4. Add warning messages for expired equivalencies based on end date.
5. Add a web scraper/importer later, but keep this version CSV-based for reliability.
