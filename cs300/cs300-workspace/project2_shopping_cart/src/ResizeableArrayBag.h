#ifndef RESIZEABLE_ARRAY_BAG_H_
#define RESIZEABLE_ARRAY_BAG_H_

#include <vector>

#include "BagInterface.h"

/**
 * @brief Bag implementation backed by a dynamically resizable array.
 *
 * Storage strategy:
 * - items_ points to an array allocated on the heap.
 * - itemCount_ is the number of active items.
 * - maxItems_ is the current array capacity.
 * - add() randomizes storage by choosing a random insertion index.
 * - getNext() and removeNext() operate on the last active array item.
 */
template <class ItemType>
class ResizeableArrayBag : public BagInterface<ItemType> {
 public:
  ResizeableArrayBag();
  ResizeableArrayBag(const ResizeableArrayBag<ItemType>& other);
  ResizeableArrayBag<ItemType>& operator=(
      const ResizeableArrayBag<ItemType>& other);
  ~ResizeableArrayBag() override;

  int getCurrentSize() const override;
  bool isEmpty() const override;
  int getFrequencyOf(const ItemType& target) const override;
  bool contains(const ItemType& target) const override;
  std::vector<ItemType> toVector() const override;
  ItemType getNext() const override;
  bool add(const ItemType& someItem) override;
  bool remove(const ItemType& target) override;
  bool removeNext() override;
  void clear() override;

 private:
  static const int DEFAULT_CAPACITY = 6;

  ItemType* items_;
  int itemCount_;
  int maxItems_;

  int getIndexOf(const ItemType& target) const;
  void ensureCapacity();
};

#include "ResizeableArrayBag.cpp"

#endif  // RESIZEABLE_ARRAY_BAG_H_
