/**
 * @file main.cpp
 * @brief Playlist demo: reads songs from a file, finds the highest-rated
 *        song, and prints the results.
 *
 * Demonstrates: struct definition in a header, multiple source files,
 *               function declarations vs. implementations, fixed-size
 *               arrays, function decomposition.
 */

#include <iostream>

#include "song.h"

// Defined on the compiler command line by CMake (see src/CMakeLists.txt) as
// the absolute path to this source directory, so playlist.txt is found
// regardless of the current working directory when project0_app is run.
#ifndef PLAYLIST_DATA_DIR
#define PLAYLIST_DATA_DIR "."
#endif

int main() {
  // Create an array to store Song objects
  Song songs[MAX_SONGS];
  int count = 0;

  // Read songs from file
  const std::string playlistPath =
      std::string(PLAYLIST_DATA_DIR) + "/playlist.txt";
  if (!readSongsFromFile(playlistPath, songs, count, MAX_SONGS)) {
    return 1;  // Error message already printed
  }

  // Find the song with the highest rating
  int favoriteIndex = findFavoriteSongIndex(songs, count);

  // Output all songs and the favorite
  printAllSongs(songs, count, favoriteIndex);
  printFavoriteSong(songs[favoriteIndex]);

  return 0;
}
