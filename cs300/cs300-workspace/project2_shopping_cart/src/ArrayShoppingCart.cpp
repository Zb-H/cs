#include "ArrayShoppingCart.h"

bool ArrayShoppingCart::addProduct(const Product& product) {
  return products_.add(product);
}

bool ArrayShoppingCart::removeProduct(const Product& product) {
  return products_.remove(product);
}

int ArrayShoppingCart::getItemCount() const {
  return products_.getCurrentSize();
}

bool ArrayShoppingCart::isEmpty() const {
  return products_.isEmpty();
}

double ArrayShoppingCart::getTotalCost() const {
  double total = 0.0;
  for (const Product& product : products_.toVector()) {
    total += product.price;
  }
  return total;
}

std::vector<Product> ArrayShoppingCart::getProducts() const {
  return products_.toVector();
}

void ArrayShoppingCart::clear() {
  products_.clear();
}
