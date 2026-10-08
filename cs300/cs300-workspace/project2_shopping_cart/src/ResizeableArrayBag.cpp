#ifndef RESIZEABLE_ARRAY_BAG_CPP_
#define RESIZEABLE_ARRAY_BAG_CPP_

#include <cstdlib>
#include <stdexcept>

/**
 * Creates an empty bag with the course default capacity.
 */
template <class ItemType>
ResizeableArrayBag<ItemType>::ResizeableArrayBag()
    : items_(new ItemType[DEFAULT_CAPACITY]),
      itemCount_(0),
      maxItems_(DEFAULT_CAPACITY) {}

/**
 * Deep-copy constructor because the bag owns a heap-allocated array.
 */
template <class ItemType>
ResizeableArrayBag<ItemType>::ResizeableArrayBag(
    const ResizeableArrayBag<ItemType>& other)
    : items_(new ItemType[other.maxItems_]),
      itemCount_(other.itemCount_),
      maxItems_(other.maxItems_) {
  for (int i = 0; i < itemCount_; ++i) {
    items_[i] = other.items_[i];
  }
}

/**
 * Deep-copy assignment operator.
 */
template <class ItemType>
ResizeableArrayBag<ItemType>& ResizeableArrayBag<ItemType>::operator=(
    const ResizeableArrayBag<ItemType>& other) {
  if (this != &other) {
    ItemType* newItems = new ItemType[other.maxItems_];
    for (int i = 0; i < other.itemCount_; ++i) {
      newItems[i] = other.items_[i];
    }

    delete[] items_;
    items_ = newItems;
    itemCount_ = other.itemCount_;
    maxItems_ = other.maxItems_;
  }

  return *this;
}

/**
 * Releases the dynamically allocated array.
 */
template <class ItemType>
ResizeableArrayBag<ItemType>::~ResizeableArrayBag() {
  delete[] items_;
}

template <class ItemType>
int ResizeableArrayBag<ItemType>::getCurrentSize() const {
  return itemCount_;
}

template <class ItemType>
bool ResizeableArrayBag<ItemType>::isEmpty() const {
  return itemCount_ == 0;
}

template <class ItemType>
int ResizeableArrayBag<ItemType>::getIndexOf(
    const ItemType& target) const {
  for (int i = 0; i < itemCount_; ++i) {
    if (items_[i] == target) {
      return i;
    }
  }
  return -1;
}

template <class ItemType>
int ResizeableArrayBag<ItemType>::getFrequencyOf(
    const ItemType& target) const {
  int frequency = 0;
  for (int i = 0; i < itemCount_; ++i) {
    if (items_[i] == target) {
      ++frequency;
    }
  }
  return frequency;
}

template <class ItemType>
bool ResizeableArrayBag<ItemType>::contains(
    const ItemType& target) const {
  return getIndexOf(target) >= 0;
}

/**
 * Returns a copy of the active array contents.
 *
 * Because add() randomizes the internal arrangement, this vector is a
 * randomized permutation of the order in which the items were added.
 */
template <class ItemType>
std::vector<ItemType> ResizeableArrayBag<ItemType>::toVector() const {
  std::vector<ItemType> bagContents;
  bagContents.reserve(itemCount_);

  for (int i = 0; i < itemCount_; ++i) {
    bagContents.push_back(items_[i]);
  }

  return bagContents;
}

/**
 * Returns the item at the end of the active array.
 * Consecutive calls return the same item until the bag is modified.
 */
template <class ItemType>
ItemType ResizeableArrayBag<ItemType>::getNext() const {
  if (isEmpty()) {
    throw std::out_of_range("Cannot getNext() from an empty bag.");
  }

  return items_[itemCount_ - 1];
}

/**
 * Doubles the heap array when it is full.
 */
template <class ItemType>
void ResizeableArrayBag<ItemType>::ensureCapacity() {
  if (itemCount_ < maxItems_) {
    return;
  }

  const int newCapacity = maxItems_ * 2;
  ItemType* newItems = new ItemType[newCapacity];

  for (int i = 0; i < itemCount_; ++i) {
    newItems[i] = items_[i];
  }

  delete[] items_;
  items_ = newItems;
  maxItems_ = newCapacity;
}

/**
 * Adds an item using the randomization variation discussed in class.
 *
 * Pick j in [0, itemCount_]. If j is an occupied position, first copy that
 * old value to the free position at the end. Then put the new value at j.
 * getNext()/removeNext() can therefore stay O(1) at the last active index,
 * while the stored order is not simply the insertion order.
 */
template <class ItemType>
bool ResizeableArrayBag<ItemType>::add(const ItemType& someItem) {
  ensureCapacity();

  const int randomIndex = std::rand() % (itemCount_ + 1);

  if (randomIndex < itemCount_) {
    items_[itemCount_] = items_[randomIndex];
  }

  items_[randomIndex] = someItem;
  ++itemCount_;
  return true;
}

/**
 * Removes one occurrence of target. Since a bag has no meaningful order,
 * replace the removed item with the last active item instead of shifting.
 */
template <class ItemType>
bool ResizeableArrayBag<ItemType>::remove(const ItemType& target) {
  const int locatedIndex = getIndexOf(target);
  if (locatedIndex < 0) {
    return false;
  }

  items_[locatedIndex] = items_[itemCount_ - 1];
  --itemCount_;
  return true;
}

/**
 * Removes the same last item that getNext() returns.
 */
template <class ItemType>
bool ResizeableArrayBag<ItemType>::removeNext() {
  if (isEmpty()) {
    return false;
  }

  --itemCount_;
  return true;
}

template <class ItemType>
void ResizeableArrayBag<ItemType>::clear() {
  itemCount_ = 0;
}

#endif  // RESIZEABLE_ARRAY_BAG_CPP_
