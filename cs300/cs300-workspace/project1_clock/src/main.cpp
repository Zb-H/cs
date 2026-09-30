#include <iomanip>
#include <iostream>

#include "Clock.h"

/**
 * @brief Demonstrates use of the Clock ADT.
 *
 * The usr enters an initial hour, initial minute, and a number of
 * minutes to add. The program then displays the updated time.
 */
int main() {
  int hour;
  int minute;
  int increment;

  // Ask the user for the starting time.
  std::cout << "Enter hour (0-23): ";
  std::cin >> hour;

  std::cout << "Enter minute (0-59): ";
  std::cin >> minute;

  // Ask how many minutes to add.
  std::cout << "Enter minutes to increment: ";
  std::cin >> increment;

  // Create and update the Clock object.
  Clock clock(hour, minute);
  clock.incrementMinutes(increment);

  // Display the time in hh:mm format.
  std::cout << "Updated time: "
            << std::setfill('0')
            << std::setw(2) << clock.getHour()
            << ":"
            << std::setw(2) << clock.getMinute()
            << '\n';

  return 0;
}
