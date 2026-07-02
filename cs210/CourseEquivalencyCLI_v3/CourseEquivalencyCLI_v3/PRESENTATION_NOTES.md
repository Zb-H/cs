# Presentation Notes: Code-Level Points to Mention

## 1. Problem Being Solved

The program helps students search course equivalencies between Bellevue College and other schools. Instead of manually reading multiple CSV files, the user can search by school, course code, abbreviation, or fuzzy keywords.

## 2. Main OOP Design

Mention these classes:

- `Course`: stores one course, including school, course code, title, and credit.
- `CourseEquivalency`: stores the relationship between two courses.
- `CsvEquivalencyReader`: reads and parses CSV files.
- `EquivalencySearchEngine`: performs fuzzy search.
- `ResultExporter`: exports results to a txt file.
- `TextUtil`: centralizes text cleanup and normalization.

This shows encapsulation because each class has a clear job.

## 3. CSV Reading Is More Than `split(",")`

A key technical point is that CSV files can have commas, quotes, and newlines inside one cell. A simple `line.split(",")` can break. This project reads the CSV character by character and tracks whether the current character is inside quotes.

Important variable:

```java
boolean inQuotes = false;
```

When `inQuotes` is true, commas and newlines are treated as normal text instead of separators.

## 4. Fuzzy Search Logic

The program normalizes text before comparing it:

- `CSE 142` and `cse142` both become `CSE142`.
- `UW` becomes `UNIVERSITY OF WASHINGTON`.
- `BC` becomes `BELLEVUE COLLEGE`.

The search engine gives each possible result a score. Stronger matches appear first.

Examples:

- Exact course code match gets a high score.
- Partial course code match gets a medium score.
- Keyword/title match gets a lower score.

## 5. Robustness

The program is designed not to crash from strange user input.

Examples:

- Blank required input asks again.
- Invalid menu choices ask again.
- Missing CSV files are skipped.
- CSV reading errors are caught with `try/catch`.
- Export file names are cleaned before writing.

## 6. Credit Extraction

The CSV sometimes stores credits in parentheses, such as:

```text
CS 210 FUNDAMENTALS OF COMPUTER SCIENCE I (5)
```

The program uses regex to extract `5` and prints it as:

```text
Credit: 5
```

## 7. Output Formatting

The output is formatted into two centered columns:

```text
BELLEVUE COLLEGE                 |              UNIVERSITY OF WASHINGTON
CS 210                           |              CSE 142
Credit: 5                        |              Credit: 4
```

This makes the result easier to read than printing one long line.

## 8. Data Structures

Mention:

- `ArrayList<CourseEquivalency>` stores all records.
- `ArrayList<SearchResult>` stores search results.
- `HashMap<String, Integer>` maps CSV column names to column indexes.
- `HashSet<String>` removes duplicate school names.

## 9. Why This Is CS210 Level

This project uses:

- classes and objects
- encapsulation
- ArrayList and HashSet
- file I/O
- exception handling
- loops and conditionals
- string processing
- regex
- sorting with `Comparable`
- Javadoc documentation
- JUnit tests

## 10. Possible Future Improvements

- Add a GUI.
- Add a web interface.
- Save search history.
- Add more schools automatically.
- Use a database instead of CSV files.
