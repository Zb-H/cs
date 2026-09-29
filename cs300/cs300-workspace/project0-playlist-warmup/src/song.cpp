/**
 * @file song.cpp
 * @brief Implementation of Song utility functions.
 */

#include "song.h"

#include <fstream>
#include <iostream>

namespace {

// Removes leading and trailing spaces from a string. The pipe-delimited
// format in playlist.txt ("Title | Artist | Rating") leaves a stray space
// on one or both sides of each field once it's split on '|'.
std::string trimWhitespace(const std::string& s) {
  const auto start = s.find_first_not_of(' ');
  if (start == std::string::npos) {
    return "";
  }
  const auto end = s.find_last_not_of(' ');
  return s.substr(start, end - start + 1);
}

}  // namespace

bool readSongsFromFile(const std::string& filename, Song songs[], int& count,
                       int maxCapacity) {
  std::ifstream inputFile(filename);

  if (!inputFile.is_open()) {
    std::cerr << "Error: Could not open " << filename << "\n";
    return false;
  }

  count = 0;
  std::string title;
  std::string artist;
  std::string ratingStr;

  while (std::getline(inputFile, title, '|') && count < maxCapacity) {
    // Parse artist (after the first |)
    std::getline(inputFile, artist, '|');

    // Parse rating (after the second |)
    std::getline(inputFile, ratingStr);

    // Convert rating string to integer
    int rating = std::stoi(ratingStr);

    // Remove stray whitespace left over from splitting on '|'
    title = trimWhitespace(title);
    artist = trimWhitespace(artist);

    songs[count] = Song(title, artist, rating);
    ++count;
  }

  inputFile.close();

  if (count == 0) {
    std::cerr << "Error: No songs found in " << filename << "\n";
    return false;
  }

  return true;
}

int findFavoriteSongIndex(const Song songs[], int count) {
  int favoriteIndex = 0;
  for (int i = 1; i < count; ++i) {
    if (songs[i].rating > songs[favoriteIndex].rating) {
      favoriteIndex = i;
    }
  }
  return favoriteIndex;
}

void printAllSongs(const Song songs[], int count, int favoriteIndex) {
  std::cout << "\n--- All Songs ---\n";
  for (int i = 0; i < count; ++i) {
    std::cout << songs[i].title << " by " << songs[i].artist;
    std::cout << " (Rating: " << songs[i].rating << ")";
    if (i == favoriteIndex) {
      std::cout << " <-- FAVORITE";
    }
    std::cout << "\n";
  }
}

void printFavoriteSong(const Song& favoriteSong) {
  std::cout << "\nYour favorite song is: " << favoriteSong.title << " by "
            << favoriteSong.artist << "\n";
}
