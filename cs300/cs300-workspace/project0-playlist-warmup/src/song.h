/**
 * @file song.h
 * @brief Data structure and utility functions for managing songs.
 *
 * This module defines the Song struct and provides functions for reading,
 * finding, and displaying song data.
 */

#ifndef SONG_H_
#define SONG_H_

#include <string>

const int MAX_SONGS = 10;

/**
 * @struct Song
 * @brief Represents a single song with title, artist, and rating.
 *
 * A simple data structure encapsulating the three key attributes of a song.
 */
struct Song {
  std::string title;   ///< The title of the song.
  std::string artist;  ///< The artist/performer of the song.
  int rating;          ///< A numerical rating for the song.

  /**
   * @brief Default constructor initializing all fields.
   *
   * @param t The song title (default: empty string).
   * @param a The artist name (default: empty string).
   * @param r The rating (default: 0).
   */
  Song(const std::string& t = "", const std::string& a = "", int r = 0)
      : title(t), artist(a), rating(r) {}
};

/**
 * @brief Reads songs from a file into an array of Song objects.
 *
 * Parses a pipe-delimited file (format: title | artist | rating) and
 * populates the provided array.
 *
 * @param[in] filename      Name of the file to read.
 * @param[out] songs        Array of Song objects to populate.
 * @param[out] count        Number of songs read (output parameter).
 * @param[in] maxCapacity   Maximum number of songs the array can hold.
 *
 * @pre maxCapacity > 0
 * @return true if file was successfully read, false otherwise.
 */
bool readSongsFromFile(const std::string& filename, Song songs[], int& count,
                       int maxCapacity);

/**
 * @brief Finds the index of the song with the highest rating.
 *
 * In case of a tie, returns the index of the first highest-rated song.
 *
 * @param[in] songs Array of Song objects.
 * @param[in] count Number of songs in the array.
 *
 * @pre count > 0
 * @return Index of the favorite song.
 */
int findFavoriteSongIndex(const Song songs[], int count);

/**
 * @brief Outputs all songs to the console, highlighting the favorite.
 *
 * @param[in] songs           Array of Song objects.
 * @param[in] count           Number of songs.
 * @param[in] favoriteIndex   Index of the favorite song.
 */
void printAllSongs(const Song songs[], int count, int favoriteIndex);

/**
 * @brief Outputs information about the favorite song.
 *
 * @param[in] favoriteSong The Song object to display.
 */
void printFavoriteSong(const Song& favoriteSong);

#endif  // SONG_H_
