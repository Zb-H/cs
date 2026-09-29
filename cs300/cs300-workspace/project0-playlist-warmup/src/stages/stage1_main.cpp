/**
 * @file stage1_main.cpp
 * @brief Stage 1: Basic I/O, types, and expressions
 *
 * Demonstrates: std::string, int, std::cin, std::cout, basic user input/output,
 *               comments, program structure.
 *
 * Concepts: Variables, types, I/O operations, comments.
 */

#include <iostream>
#include <string>

/**
 * @brief Reads a single song's information from the user and outputs it.
 *
 * Demonstrates basic I/O with strings and integers.
 *
 * @return 0 on success.
 */
int main() {
  // Prompt user for song information
  std::cout << "Enter song title: ";
  std::string title;
  std::getline(std::cin, title);

  std::cout << "Enter artist name: ";
  std::string artist;
  std::getline(std::cin, artist);

  std::cout << "Enter rating (1-5): ";
  int rating = 0;
  std::cin >> rating;

  // Output the song information back to the user
  std::cout << "\n--- Song Information ---\n";
  std::cout << "Title:  " << title << "\n";
  std::cout << "Artist: " << artist << "\n";
  std::cout << "Rating: " << rating << "\n";

  return 0;
}
