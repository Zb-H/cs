#ifndef CLOCK_H_
#define CLOCK_H_

/**
 * @class Clock
 * @brief Represents a time of day using a 24-hour clock.
 *
 * A Clock object stores an hour and minute. The time can be read
 * using accessor methods and advanced by minutes.
 */
class Clock {
 public:
  /**
   * @brief Constructs a Clock set to midnight 0:00.
   * 
   * Constructor 1.
   */
  Clock();

  /**
   * @brief Constructs a Clock with a client-specified time.
   *
   * @param hour The hour on a 24-hour clock.
   * @param minute Minute.
   *
   * Constructor 2 overloading.
   */
  Clock(int hour, int minute);

  /**
   * @brief Returns the current hour.
   *
   * @return The current hour.
   */
  int getHour() const;

  /**
   * @brief Returns the current minute.
   *
   * @return The current minute.
   */
  int getMinute() const;

  /**
   * @brief Advances the clock by a specified number of minutes.
   * If the time passes midnight, it wraps around to the beginning
   * of the next 24-hour day.
   *
   * @param minutes Number of minutes to add to the current time.
   */
  void incrementMinutes(int minutes);

 private:
  // Stores the current hour, from 0 through 23.
  int hour_;

  // Stores the current minute, from 0 through 59.
  int minute_;
};

#endif  // CLOCK_H_ include guard.
