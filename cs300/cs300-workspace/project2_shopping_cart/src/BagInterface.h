#ifndef BAG_INTERFACE_H_
#define BAG_INTERFACE_H_

#include <vector>

/**
 * @brief Abstract interface for the Bag ADT.
 *
 * A bag is an unordered collection that allows duplicate items.
 */
template <class ItemType>
class BagInterface {
 public:
  virtual int getCurrentSize() const = 0;
  virtual bool isEmpty() const = 0;
  virtual int getFrequencyOf(const ItemType& target) const = 0;
  virtual bool contains(const ItemType& target) const = 0;
  virtual std::vector<ItemType> toVector() const = 0;

  /**
   * @brief Returns the arbitrary item that removeNext() would remove.
   * @throws std::out_of_range if the bag is empty.
   */
  virtual ItemType getNext() const = 0;

  virtual bool add(const ItemType& someItem) = 0;
  virtual bool remove(const ItemType& target) = 0;

  /**
   * @brief Removes the same item getNext() would return.
   * @return true if an item was removed; false if the bag was empty.
   */
  virtual bool removeNext() = 0;

  virtual void clear() = 0;
  virtual ~BagInterface() = default;
};

#endif  // BAG_INTERFACE_H_
