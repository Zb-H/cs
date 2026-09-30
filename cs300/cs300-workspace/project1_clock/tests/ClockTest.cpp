#include <gtest/gtest.h>

#include "Clock.h"

/**
 * Tests that the default constructor initializes the time to midnight.
 */
TEST(ClockTest, DefaultConstructorSetsMidnight) {
  Clock clock;

  EXPECT_EQ(clock.getHour(), 0);
  EXPECT_EQ(clock.getMinute(), 0);
}

/**
 * Tests that the parameterized constructor stores the provided time.
 */
TEST(ClockTest, ParameterizedConstructorSetsTime) {
  Clock clock(10, 30);

  EXPECT_EQ(clock.getHour(), 10);
  EXPECT_EQ(clock.getMinute(), 30);
}

/**
 * Tests the getHour() accessor.
 */
TEST(ClockTest, GetHourReturnsCurrentHour) {
  Clock clock(15, 45);

  EXPECT_EQ(clock.getHour(), 15);
}

/**
 * Tests the getMinute() accessor.
 */
TEST(ClockTest, GetMinuteReturnsCurrentMinute) {
  Clock clock(15, 45);

  EXPECT_EQ(clock.getMinute(), 45);
}

/**
 * Happy-path test: advances normally without crossing midnight.
 */
TEST(ClockTest, IncrementMinutesNormalCase) {
  Clock clock(10, 30);

  clock.incrementMinutes(45);

  EXPECT_EQ(clock.getHour(), 11);
  EXPECT_EQ(clock.getMinute(), 15);
}

/**
 * Tests incrementing within the same hour.
 */
TEST(ClockTest, IncrementMinutesWithinSameHour) {
  Clock clock(8, 10);

  clock.incrementMinutes(20);

  EXPECT_EQ(clock.getHour(), 8);
  EXPECT_EQ(clock.getMinute(), 30);
}

/**
 * Tests carrying minutes into the next hour.
 */
TEST(ClockTest, IncrementMinutesCrossesHour) {
  Clock clock(8, 50);

  clock.incrementMinutes(20);

  EXPECT_EQ(clock.getHour(), 9);
  EXPECT_EQ(clock.getMinute(), 10);
}

/**
 * Edge-case test: advancing past midnight wraps correctly.
 */
TEST(ClockTest, IncrementMinutesWrapsAroundMidnight) {
  Clock clock(23, 50);

  clock.incrementMinutes(20);

  EXPECT_EQ(clock.getHour(), 0);
  EXPECT_EQ(clock.getMinute(), 10);
}

/**
 * Tests adding zero minutes.
 */
TEST(ClockTest, IncrementZeroMinutesDoesNotChangeTime) {
  Clock clock(12, 25);

  clock.incrementMinutes(0);

  EXPECT_EQ(clock.getHour(), 12);
  EXPECT_EQ(clock.getMinute(), 25);
}

/**
 * Tests adding exactly one full day.
 */
TEST(ClockTest, IncrementOneFullDayReturnsSameTime) {
  Clock clock(14, 35);

  clock.incrementMinutes(24 * 60);

  EXPECT_EQ(clock.getHour(), 14);
  EXPECT_EQ(clock.getMinute(), 35);
}

/**
 * Tests adding more than one full day.
 */
TEST(ClockTest, IncrementMoreThanOneDay) {
  Clock clock(8, 15);

  clock.incrementMinutes(24 * 60 + 60);

  EXPECT_EQ(clock.getHour(), 9);
  EXPECT_EQ(clock.getMinute(), 15);
}
