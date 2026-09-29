/**
 * @file stage2_main.cpp
 * @brief Stage 2: Arrays and loops
 *
 * Demonstrates: fixed-size arrays, for loops, index-based access, function
 *               decomposition.
 *
 * Concepts: Arrays (parallel), loops, finding maximum, functions, control flow.
 */

#include <iostream>
#include <string>

const int MAX_SONGS = 10;

/**
 * @brief Reads song information from the user into three parallel arrays.
 *
 * @param[out] titles  Array to store song titles (must have capacity >= count).
 * @param[out] artists Array to store artist names (must have capacity >= count).
 * @param[out] ratings Array to store ratings (must have capacity >= count).
 * @param[in] count    Number of songs to read from user.
 *
 * @pre count <= MAX_SONGS
 */
void readSongsFromUser(std::string titles[], std::string artists[], int ratings[],
                       int count) {
  for (int i = 0; i < count; ++i) {
    std::cout << "\n--- Song " << (i + 1) << " ---\n";

    std::cout << "Enter song title: ";
    std::getline(std::cin, titles[i]);

    std::cout << "Enter artist name: ";
    std::getline(std::cin, artists[i]);

    std::cout << "Enter rating (1-5): ";
    std::cin >> ratings[i];
    std::cin.ignore();  // Clear the newline from the input buffer
  }
}

/**
 * @brief Finds the index of the song with the highest rating.
 *
 * In case of a tie, returns the index of the first highest-rated song.
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
  const int NUM_SONGS = 3;

  // Create arrays to store song information
  std::string titles[MAX_SONGS];
  std::string artists[MAX_SONGS];
  int ratings[MAX_SONGS];

  // Read song information from user
  readSongsFromUser(titles, artists, ratings, NUM_SONGS);

  // Find the song with the highest rating
  int favoriteIndex = findFavoriteSongIndex(ratings, NUM_SONGS);

  // Output all songs and the favorite
  printAllSongs(titles, artists, ratings, NUM_SONGS, favoriteIndex);
  printFavoriteSong(titles, artists, favoriteIndex);

  return 0;
}
