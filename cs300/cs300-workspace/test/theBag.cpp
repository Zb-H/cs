#include <vector>
#include <algorithm>

template <typename T>
class Bag {
private:
    std::vector<T> items;

public:
    void add(const T& item) {
        items.push_back(item);
    }

    bool remove(const T& item) {
        auto it = std::find(items.begin(), items.end(), item);

        if (it != items.end()) {
            items.erase(it);
            return true;
        }

        return false;
    }

    bool contains(const T& item) const {
        return std::find(items.begin(), items.end(), item) != items.end();
    }

    int size() const {
        return items.size();
    }

    bool isEmpty() const {
        return items.empty();
    }
};
