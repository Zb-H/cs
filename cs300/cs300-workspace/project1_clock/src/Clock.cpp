#include "Clock.h"

/**
 * Default constructor.
 * Initializes the clock to midnight: 00:00.
 */
Clock::Clock()
    : hour_(0),
      minute_(0) {}

/**
 * Parameterized constructor.
 * Initializes the clock using the hour and minute provided by the usr.
 */
Clock::Clock(int hour, int minute)
    : hour_(hour),
      minute_(minute) {}

/**
 * Returns the current hour.
 */
int Clock::getHour() const {
  return hour_;
}

/**
 * Returns the current minute.
 */
int Clock::getMinute() const {
  return minute_;
}

/**
 * Adds the specified number of minutes to the current time.
 * The time wraps around after 24 hours.
 */
void Clock::incrementMinutes(int minutes) {
  // Convert the current time to total minutes since midnight,
  // then add the requested increment.
  int totalMinutes = hour_ * 60 + minute_ + minutes;

  // There are 1440 minutes in one 24-hour day.
  totalMinutes %= 24 * 60;

  // Convert total minutes back to hours and minutes.
  hour_ = totalMinutes / 60;
  minute_ = totalMinutes % 60;
}
