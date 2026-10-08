#ifndef PRODUCT_H_
#define PRODUCT_H_

#include <string>

/**
 * @brief Simple product data object used by the shopping cart.
 */
struct Product {
  std::string name;
  double price;

  Product() : name(""), price(0.0) {}
  Product(const std::string& productName, double productPrice)
      : name(productName), price(productPrice) {}

  bool operator==(const Product& other) const {
    return name == other.name && price == other.price;
  }
};

#endif  // PRODUCT_H_
