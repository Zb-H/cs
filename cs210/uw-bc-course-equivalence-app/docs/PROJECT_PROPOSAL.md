# Project Proposal: UW <-> BC Course Equivalence Converter

## Problem

Students who plan to transfer from Bellevue College to the University of Washington need to check course equivalencies. The official tables are useful, but they can be hard to search quickly when a student is planning multiple quarters.

## Solution

This app loads a course equivalency CSV file and lets students search by UW course, BC course, or keyword. The app can be used in command-line mode or in a simple desktop GUI.

## Main objects

- `CourseEquivalency`: stores one course equivalency record.
- `EquivalencyRepository`: loads records from a CSV file.
- `SearchService`: searches the records.
- `SearchMode`: enum for UW course, BC course, or keyword search.
- `EquivalencyFrame`: GUI window.
- `App`: main class.

## MVP requirements

- Load CSV data successfully.
- Search by UW course.
- Search by BC course.
- Search by keyword.
- Display results clearly.

## Stretch goals

- Add selected courses to a student plan.
- Export a plan to CSV.
- Add filters by subject and credit.
- Add expired-equivalency warnings.
