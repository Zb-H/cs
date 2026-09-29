/**
 * @file song_test.cpp
 * @brief Unit tests for Song struct and utility functions.
 *
 * Uses the Google Test (gtest) framework to test Song construction,
 * finding favorites, and edge cases.
 */

#include <gtest/gtest.h>

#include "song.h"

// ============================================================================
// Stage 1 Tests: Basic types and construction
// ============================================================================

// Test default constructor
TEST(SongConstructorTest, DefaultConstructor) {
  Song song;
  EXPECT_EQ(song.title, "");
  EXPECT_EQ(song.artist, "");
  EXPECT_EQ(song.rating, 0);
}

// Test parameterized constructor
TEST(SongConstructorTest, ParameterizedConstructor) {
  Song song("Imagine", "John Lennon", 5);
  EXPECT_EQ(song.title, "Imagine");
  EXPECT_EQ(song.artist, "John Lennon");
  EXPECT_EQ(song.rating, 5);
}

// Test partial constructor (using defaults)
TEST(SongConstructorTest, PartialConstructor) {
  Song song("Bohemian Rhapsody", "Queen");
  EXPECT_EQ(song.title, "Bohemian Rhapsody");
  EXPECT_EQ(song.artist, "Queen");
  EXPECT_EQ(song.rating, 0);
}

// Test field assignment
TEST(SongFieldAssignmentTest, CanAssignToFields) {
  Song song;
  song.title = "Stairway to Heaven";
  song.artist = "Led Zeppelin";
  song.rating = 5;

  EXPECT_EQ(song.title, "Stairway to Heaven");
  EXPECT_EQ(song.artist, "Led Zeppelin");
  EXPECT_EQ(song.rating, 5);
}

// ============================================================================
// Stage 2 & 3 Tests: Finding favorite in fixed-size arrays
// ============================================================================

// Test finding favorite with single song
TEST(FindFavoriteSongIndexTest, SingleSong) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 3);

  int result = findFavoriteSongIndex(songs, 1);
  EXPECT_EQ(result, 0);
}

// Test finding favorite with multiple songs
TEST(FindFavoriteSongIndexTest, MultipleSongs) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 3);
  songs[1] = Song("Song B", "Artist B", 5);
  songs[2] = Song("Song C", "Artist C", 2);

  int result = findFavoriteSongIndex(songs, 3);
  EXPECT_EQ(result, 1);  // Song B has rating 5 (highest)
}

// Test finding favorite when all songs have same rating
TEST(FindFavoriteSongIndexTest, AllSameRating) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 4);
  songs[1] = Song("Song B", "Artist B", 4);
  songs[2] = Song("Song C", "Artist C", 4);

  int result = findFavoriteSongIndex(songs, 3);
  EXPECT_EQ(result, 0);  // Should return first one when tied
}

// Test finding favorite when favorite is last
TEST(FindFavoriteSongIndexTest, FavoriteIsLast) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 2);
  songs[1] = Song("Song B", "Artist B", 3);
  songs[2] = Song("Song C", "Artist C", 5);

  int result = findFavoriteSongIndex(songs, 3);
  EXPECT_EQ(result, 2);
}

// Test finding favorite when favorite is first
TEST(FindFavoriteSongIndexTest, FavoriteIsFirst) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 5);
  songs[1] = Song("Song B", "Artist B", 3);
  songs[2] = Song("Song C", "Artist C", 2);

  int result = findFavoriteSongIndex(songs, 3);
  EXPECT_EQ(result, 0);
}

// Test with larger collection
TEST(FindFavoriteSongIndexTest, LargeCollection) {
  Song songs[10];
  songs[0] = Song("Song 1", "Artist 1", 2);
  songs[1] = Song("Song 2", "Artist 2", 4);
  songs[2] = Song("Song 3", "Artist 3", 1);
  songs[3] = Song("Song 4", "Artist 4", 5);
  songs[4] = Song("Song 5", "Artist 5", 3);

  int result = findFavoriteSongIndex(songs, 5);
  EXPECT_EQ(result, 3);  // Song 4 has rating 5
}

// ============================================================================
// Edge Case Tests
// ============================================================================

// Test negative ratings (edge case)
TEST(FindFavoriteSongIndexTest, NegativeRatings) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", -1);
  songs[1] = Song("Song B", "Artist B", -5);
  songs[2] = Song("Song C", "Artist C", -2);

  int result = findFavoriteSongIndex(songs, 3);
  EXPECT_EQ(result, 0);  // Song A with -1 is "highest"
}

// Test zero ratings
TEST(FindFavoriteSongIndexTest, ZeroRatings) {
  Song songs[10];
  songs[0] = Song("Song A", "Artist A", 0);
  songs[1] = Song("Song B", "Artist B", 0);

  int result = findFavoriteSongIndex(songs, 2);
  EXPECT_EQ(result, 0);
}

// Test with very long strings
TEST(SongConstructorTest, LongStrings) {
  std::string longTitle(1000, 'A');
  std::string longArtist(1000, 'B');

  Song song(longTitle, longArtist, 5);
  EXPECT_EQ(song.title, longTitle);
  EXPECT_EQ(song.artist, longArtist);
}

// Test rating extremes
TEST(SongConstructorTest, RatingExtremes) {
  Song low("Title", "Artist", -999);   // Very low rating
  Song high("Title", "Artist", 999);   // Very high rating

  EXPECT_EQ(low.rating, -999);
  EXPECT_EQ(high.rating, 999);
}

// Test array of Song objects
TEST(SongArrayTest, CreateAndPopulateArray) {
  Song songs[3];
  songs[0] = Song("Track 1", "Band A", 4);
  songs[1] = Song("Track 2", "Band B", 5);
  songs[2] = Song("Track 3", "Band C", 3);

  EXPECT_EQ(songs[0].title, "Track 1");
  EXPECT_EQ(songs[1].rating, 5);
  EXPECT_EQ(songs[2].artist, "Band C");
}
