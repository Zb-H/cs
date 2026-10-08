#ifndef ARRAY_SHOPPING_CART_H_
#define ARRAY_SHOPPING_CART_H_

#include "ResizeableArrayBag.h"
#include "ShoppingCartInterface.h"

/**
 * @brief Shopping cart implementation backed by a ResizeableArrayBag.
 */
class ArrayShoppingCart : public ShoppingCartInterface {
 public:
  bool addProduct(const Product& product) override;
  bool removeProduct(const Product& product) override;
  int getItemCount() const override;
  bool isEmpty() const override;
  double getTotalCost() const override;
  std::vector<Product> getProducts() const override;
  void clear() override;

 private:
  ResizeableArrayBag<Product> products_;
};

#endif  // ARRAY_SHOPPING_CART_H_
