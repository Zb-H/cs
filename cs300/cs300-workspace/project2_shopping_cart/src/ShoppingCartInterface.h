#ifndef SHOPPING_CART_INTERFACE_H_
#define SHOPPING_CART_INTERFACE_H_

#include <vector>

#include "Product.h"

/**
 * @brief Abstract interface for the Shopping Cart ADT.
 */
class ShoppingCartInterface {
 public:
  virtual bool addProduct(const Product& product) = 0;
  virtual bool removeProduct(const Product& product) = 0;
  virtual int getItemCount() const = 0;
  virtual bool isEmpty() const = 0;
  virtual double getTotalCost() const = 0;
  virtual std::vector<Product> getProducts() const = 0;
  virtual void clear() = 0;
  virtual ~ShoppingCartInterface() = default;
};

#endif  // SHOPPING_CART_INTERFACE_H_
