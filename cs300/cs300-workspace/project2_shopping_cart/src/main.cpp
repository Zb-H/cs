#include <cstdlib>
#include <ctime>
#include <iomanip>
#include <iostream>
#include <limits>
#include <string>

#include "ArrayShoppingCart.h"

int main() {
  // Seed std::rand() once for the bag's randomized add operation.
  std::srand(static_cast<unsigned int>(std::time(nullptr)));

  ArrayShoppingCart cart;

  std::cout << "Shopping Cart\n";
  std::cout << "-------------\n";

  while (true) {
    std::string name;
    std::cout << "Enter product name (or done): ";
    std::getline(std::cin, name);

    if (name == "done" || name == "Done" || name == "DONE") {
      break;
    }

    double price = 0.0;
    int quantity = 0;

    std::cout << "Enter price: $";
    while (!(std::cin >> price) || price < 0.0) {
      std::cout << "Please enter a non-negative price: $";
      std::cin.clear();
      std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }

    std::cout << "Enter quantity: ";
    while (!(std::cin >> quantity) || quantity <= 0) {
      std::cout << "Please enter a positive whole-number quantity: ";
      std::cin.clear();
      std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

    const Product product(name, price);
    for (int i = 0; i < quantity; ++i) {
      cart.addProduct(product);
    }
  }

  std::cout << "\nCart contents:\n";
  std::cout << std::fixed << std::setprecision(2);

  if (cart.isEmpty()) {
    std::cout << "(empty)\n";
  } else {
    for (const Product& product : cart.getProducts()) {
      std::cout << "- " << product.name << ": $" << product.price << '\n';
    }
  }

  std::cout << "Total items: " << cart.getItemCount() << '\n';
  std::cout << "Total cost: $" << cart.getTotalCost() << '\n';

  return 0;
}
