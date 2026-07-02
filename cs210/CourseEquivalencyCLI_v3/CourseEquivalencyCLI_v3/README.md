# Course Equivalency CLI v3

This is a CS210-level Java command-line program for searching course equivalencies from CSV files.

## Main Features

- Reads multiple CSV files from the `data/` folder.
- Supports bidirectional course lookup.
- Supports fuzzy search by school name, school abbreviation, course code, or course keywords.
- Cleans strange school display names such as `UNIVERSITY OF WASHINGTON COURSE DESCRIPTIONS` into `UNIVERSITY OF WASHINGTON`.
- Extracts course credit from course text like `(5)` and displays it as `Credit: 5`.
- Handles weird input safely and asks the user to try again instead of crashing.
- Can export search results to a `.txt` file.
- Includes UML, flowchart, documentation, and presentation notes.

## Project Structure

```text
CourseEquivalencyCLI_v3/
├── data/
│   ├── SEATTLEU_EQUIVALENCY_LIST.csv
│   ├── UW_EQUIVALENCY_LIST.csv
│   ├── UWB_EQUIVALENCY_LIST.csv
│   └── UWT_EQUIVALENCY_LIST.csv
├── pom.xml
├── README.md
├── UML.md
├── FLOWCHART.md
├── PRESENTATION_NOTES.md
└── src/
    ├── main/java/CourseEquivalencyCLI/
    │   ├── App.java
    │   ├── Course.java
    │   ├── CourseEquivalency.java
    │   ├── CsvEquivalencyReader.java
    │   ├── EquivalencySearchEngine.java
    │   ├── ResultExporter.java
    │   ├── SearchResult.java
    │   └── TextUtil.java
    └── test/java/CourseEquivalencyCLI/
        └── AppTest.java
```

## How to Run

Run these commands from the project root folder, where `pom.xml` is located:

```bash
mvn compile
mvn exec:java
```

A quieter version:

```bash
mvn -q exec:java
```

The program automatically loads these files:

```text
data/SEATTLEU_EQUIVALENCY_LIST.csv
data/UW_EQUIVALENCY_LIST.csv
data/UWB_EQUIVALENCY_LIST.csv
data/UWT_EQUIVALENCY_LIST.csv
data/WSU_EQUIVALENCY_LIST.csv
```

If `WSU_EQUIVALENCY_LIST.csv` is not present, the program will skip it instead of crashing.

## Menu

```text
===== Course Equivalency Lookup =====
1. Search by school + course
2. Fuzzy search with one line
3. Show loaded schools
4. Export last search results to txt
5. Exit
```

## Example Searches

Separate school + course search:

```text
School: BC
Course: CS 210
```

```text
School: UW
Course: CSE 142
```

Fuzzy one-line search:

```text
uw cse142
```

```text
bellevue cs210
```

```text
seattleu programming
```

## Main Classes

### `App`

Controls the menu, user input, loading files, printing results, and export option.

### `Course`

Represents one course. It extracts:

- clean school name
- course code
- course title
- credit

### `CourseEquivalency`

Represents one equivalency relationship between two `Course` objects.

### `CsvEquivalencyReader`

Reads CSV files safely. It handles:

- quoted fields
- commas inside quotes
- newlines inside quotes
- missing optional columns
- multiple courses inside one cell

### `EquivalencySearchEngine`

Stores all equivalencies and performs fuzzy searching.

### `TextUtil`

Contains helper methods for text cleanup, school alias expansion, and matching.

### `ResultExporter`

Writes the latest search results into a `.txt` file.

## Why This Fits CS210

This project uses core CS210 concepts:

- classes and objects
- encapsulation
- ArrayList
- HashSet
- basic sorting with `Comparable`
- file reading with `BufferedReader`
- exception handling
- string processing
- regex
- method decomposition
- Javadoc documentation
- JUnit tests
