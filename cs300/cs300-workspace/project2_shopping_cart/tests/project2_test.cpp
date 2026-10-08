#include <algorithm>
#include <cstdlib>
#include <stdexcept>
#include <string>
#include <vector>

#include <gtest/gtest.h>

#include "ArrayShoppingCart.h"
#include "Product.h"
#include "ResizeableArrayBag.h"

TEST(ResizeableArrayBagTest, NewBagIsEmpty) {
  ResizeableArrayBag<std::string> bag;

  EXPECT_TRUE(bag.isEmpty());
  EXPECT_EQ(bag.getCurrentSize(), 0);
  EXPECT_TRUE(bag.toVector().empty());
}

TEST(ResizeableArrayBagTest, AddStoresItemsAndDuplicates) {
  std::srand(1);
  ResizeableArrayBag<std::string> bag;

  EXPECT_TRUE(bag.add("apple"));
  EXPECT_TRUE(bag.add("banana"));
  EXPECT_TRUE(bag.add("apple"));

  EXPECT_EQ(bag.getCurrentSize(), 3);
  EXPECT_EQ(bag.getFrequencyOf("apple"), 2);
  EXPECT_TRUE(bag.contains("banana"));
}

TEST(ResizeableArrayBagTest, ResizesPastDefaultCapacity) {
  std::srand(2);
  ResizeableArrayBag<int> bag;

  for (int i = 0; i < 25; ++i) {
    EXPECT_TRUE(bag.add(i));
  }

  EXPECT_EQ(bag.getCurrentSize(), 25);
  for (int i = 0; i < 25; ++i) {
    EXPECT_TRUE(bag.contains(i));
  }
}

TEST(ResizeableArrayBagTest, ToVectorContainsEveryItem) {
  std::srand(3);
  ResizeableArrayBag<int> bag;
  for (int i = 1; i <= 10; ++i) {
    bag.add(i);
  }

  std::vector<int> contents = bag.toVector();
  std::sort(contents.begin(), contents.end());

  std::vector<int> expected;
  for (int i = 1; i <= 10; ++i) {
    expected.push_back(i);
  }

  EXPECT_EQ(contents, expected);
}

TEST(ResizeableArrayBagTest, GetNextIsStableUntilBagChanges) {
  std::srand(4);
  ResizeableArrayBag<int> bag;
  bag.add(10);
  bag.add(20);
  bag.add(30);

  const int first = bag.getNext();
  const int second = bag.getNext();

  EXPECT_EQ(first, second);
}

TEST(ResizeableArrayBagTest, RemoveNextRemovesGetNextItem) {
  std::srand(5);
  ResizeableArrayBag<int> bag;
  bag.add(10);
  bag.add(20);
  bag.add(30);

  const int next = bag.getNext();
  EXPECT_TRUE(bag.removeNext());

  EXPECT_EQ(bag.getCurrentSize(), 2);
  EXPECT_FALSE(bag.contains(next));
}

TEST(ResizeableArrayBagTest, EmptyGetNextThrows) {
  ResizeableArrayBag<int> bag;
  EXPECT_THROW(bag.getNext(), std::out_of_range);
}

TEST(ResizeableArrayBagTest, EmptyRemoveNextReturnsFalse) {
  ResizeableArrayBag<int> bag;
  EXPECT_FALSE(bag.removeNext());
}

TEST(ResizeableArrayBagTest, RemoveDeletesOneOccurrenceOnly) {
  std::srand(6);
  ResizeableArrayBag<int> bag;
  bag.add(7);
  bag.add(7);
  bag.add(9);

  EXPECT_TRUE(bag.remove(7));
  EXPECT_EQ(bag.getFrequencyOf(7), 1);
  EXPECT_EQ(bag.getCurrentSize(), 2);
  EXPECT_FALSE(bag.remove(100));
}

TEST(ResizeableArrayBagTest, ClearMakesBagEmpty) {
  ResizeableArrayBag<int> bag;
  for (int i = 0; i < 12; ++i) {
    bag.add(i);
  }

  bag.clear();

  EXPECT_TRUE(bag.isEmpty());
  EXPECT_EQ(bag.getCurrentSize(), 0);
}

TEST(ResizeableArrayBagTest, CopyConstructorMakesIndependentCopy) {
  std::srand(7);
  ResizeableArrayBag<int> original;
  original.add(1);
  original.add(2);
  original.add(3);

  ResizeableArrayBag<int> copy(original);
  EXPECT_EQ(copy.getCurrentSize(), 3);

  original.clear();
  EXPECT_EQ(copy.getCurrentSize(), 3);
  EXPECT_TRUE(copy.contains(1));
  EXPECT_TRUE(copy.contains(2));
  EXPECT_TRUE(copy.contains(3));
}

TEST(ArrayShoppingCartTest, NewCartIsEmpty) {
  ArrayShoppingCart cart;

  EXPECT_TRUE(cart.isEmpty());
  EXPECT_EQ(cart.getItemCount(), 0);
  EXPECT_DOUBLE_EQ(cart.getTotalCost(), 0.0);
}

TEST(ArrayShoppingCartTest, AddsQuantityAsDuplicateProducts) {
  std::srand(8);
  ArrayShoppingCart cart;
  const Product apple("Apple", 1.25);

  cart.addProduct(apple);
  cart.addProduct(apple);
  cart.addProduct(apple);

  EXPECT_EQ(cart.getItemCount(), 3);
  EXPECT_DOUBLE_EQ(cart.getTotalCost(), 3.75);
}

TEST(ArrayShoppingCartTest, CalculatesTotalCost) {
  std::srand(9);
  ArrayShoppingCart cart;

  cart.addProduct(Product("Apple", 1.25));
  cart.addProduct(Product("Apple", 1.25));
  cart.addProduct(Product("Bread", 3.50));

  EXPECT_EQ(cart.getItemCount(), 3);
  EXPECT_DOUBLE_EQ(cart.getTotalCost(), 6.00);
}

TEST(ArrayShoppingCartTest, RemoveProductRemovesOneCopy) {
  std::srand(10);
  ArrayShoppingCart cart;
  const Product soda("Soda", 2.00);

  cart.addProduct(soda);
  cart.addProduct(soda);

  EXPECT_TRUE(cart.removeProduct(soda));
  EXPECT_EQ(cart.getItemCount(), 1);
  EXPECT_DOUBLE_EQ(cart.getTotalCost(), 2.00);
}

TEST(ArrayShoppingCartTest, GetProductsReturnsAllProducts) {
  std::srand(11);
  ArrayShoppingCart cart;
  cart.addProduct(Product("Apple", 1.00));
  cart.addProduct(Product("Bread", 3.00));

  const std::vector<Product> products = cart.getProducts();
  EXPECT_EQ(products.size(), 2u);
  EXPECT_NE(std::find(products.begin(), products.end(),
                      Product("Apple", 1.00)),
            products.end());
  EXPECT_NE(std::find(products.begin(), products.end(),
                      Product("Bread", 3.00)),
            products.end());
}

TEST(ArrayShoppingCartTest, ClearEmptiesCart) {
  ArrayShoppingCart cart;
  cart.addProduct(Product("Milk", 4.00));
  cart.addProduct(Product("Eggs", 5.00));

  cart.clear();

  EXPECT_TRUE(cart.isEmpty());
  EXPECT_EQ(cart.getItemCount(), 0);
  EXPECT_DOUBLE_EQ(cart.getTotalCost(), 0.0);
}
