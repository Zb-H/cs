/**
 * @file stage3_main.cpp
 * @brief Stage 3: File I/O and parsing
 *
 * Demonstrates: std::ifstream, file handling, structured data parsing, error
 *               checking, function decomposition.
 *
 * Concepts: File input, parsing, error handling, functions, fixed-size arrays.
 *
 * File format: title | artist | rating (one song per line)
 */

#include <fstream>
#include <iostream>
#include <string>

const int MAX_SONGS = 10;

/**
 * @brief Removes leading and trailing spaces from a string.
 *
 * @param[in] str The string to trim.
 * @return A copy of str with leading/trailing spaces removed.
 */
std::string trimWhitespace(const std::string& str) {
  size_t first = str.find_first_not_of(' ');
  if (first == std::string::npos) {
    return "";  // The string is all spaces.
  }
  size_t last = str.find_last_not_of(' ');
  return str.substr(first, last - first + 1);
}

/**
 * @brief Reads songs from a file into three parallel arrays.
 *
 * Expected file format: title | artist | rating (one song per line)
 *
 * @param[in] filename       Name of the file to read.
 * @param[out] titles        Array to store song titles.
 * @param[out] artists       Array to store artist names.
 * @param[out] ratings       Array to store ratings.
 * @param[out] count         Number of songs read (output parameter).
 * @param[in] maxCapacity    Maximum number of songs the arrays can hold.
 *
 * @pre maxCapacity > 0
 * @return true if file was successfully read, false otherwise.
 */
bool readSongsFromFile(const std::string& filename, std::string titles[],
                       std::string artists[], int ratings[], int& count,
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

    // Remove leading and trailing whitespace from strings
    title = trimWhitespace(title);
    artist = trimWhitespace(artist);

    titles[count] = title;
    artists[count] = artist;
    ratings[count] = rating;
    ++count;
  }

  inputFile.close();

  if (count == 0) {
    std::cerr << "Error: No songs found in " << filename << "\n";
    return false;
  }

  return true;
}

/**
 * @brief Finds the index of the song with the highest rating.
 *
 * @param[in] ratings Array of ratings.
 * @param[in] count   Number of songs.
 *
 * @pre count > 0
 * @return Index of the favorite song.
 */
int findFavoriteSongIndex(const int ratings[], int count) {
  int favoriteIndex = 0;
  for (int i = 1; i < count; ++i) {
    if (ratings[i] > ratings[favoriteIndex]) {
      favoriteIndex = i;
    }
  }
  return favoriteIndex;
}

/**
 * @brief Outputs all songs, highlighting the favorite.
 *
 * @param[in] titles          Array of song titles.
 * @param[in] artists         Array of artist names.
 * @param[in] ratings         Array of ratings.
 * @param[in] count           Number of songs.
 * @param[in] favoriteIndex   Index of the favorite song.
 */
void printAllSongs(const std::string titles[], const std::string artists[],
                   const int ratings[], int count, int favoriteIndex) {
  std::cout << "\n--- All Songs ---\n";
  for (int i = 0; i < count; ++i) {
    std::cout << titles[i] << " by " << artists[i];
    std::cout << " (Rating: " << ratings[i] << ")";
    if (i == favoriteIndex) {
      std::cout << " <-- FAVORITE";
    }
    std::cout << "\n";
  }
}

/**
 * @brief Outputs information about the favorite song.
 *
 * @param[in] titles         Array of song titles.
 * @param[in] artists        Array of artist names.
 * @param[in] favoriteIndex  Index of the favorite song.
 */
void printFavoriteSong(const std::string titles[], const std::string artists[],
                       int favoriteIndex) {
  std::cout << "\nYour favorite song is: " << titles[favoriteIndex] << " by "
            << artists[favoriteIndex] << "\n";
}

int main() {
  // Create arrays to store song information
  std::string titles[MAX_SONGS];
  std::string artists[MAX_SONGS];
  int ratings[MAX_SONGS];
  int count = 0;

  // PLAYLIST_DATA_DIR is baked in by CMake (see target_compile_definitions
  // in CMakeLists.txt) so this works regardless of the working directory
  // the program is run from.
#ifdef PLAYLIST_DATA_DIR
  std::string filename = std::string(PLAYLIST_DATA_DIR) + "/playlist.txt";
#else
  std::string filename = "playlist.txt";
#endif

  // Read songs from file
  if (!readSongsFromFile(filename, titles, artists, ratings, count,
                         MAX_SONGS)) {
    return 1;  // Error message already printed
  }

  // Find the song with the highest rating
  int favoriteIndex = findFavoriteSongIndex(ratings, count);

  // Output all songs and the favorite
  printAllSongs(titles, artists, ratings, count, favoriteIndex);
  printFavoriteSong(titles, artists, favoriteIndex);

  return 0;
}
