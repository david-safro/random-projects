#include <iostream>
#include <vector>
#include <algorithm>
#include <random>

bool isSorted(const std::vector<int>& arr) {
    for (size_t i = 1; i < arr.size(); ++i) {
        if (arr[i] < arr[i - 1]) {
            return false;
        }
    }
    return true;
}

void bogoSort(std::vector<int>& arr) {
    std::random_device rd;
    std::mt19937 gen(rd());

    while (!isSorted(arr)) {
        std::shuffle(arr.begin(), arr.end(), gen);
    }
}

int main() {
    std::vector<int> arr = {5, 2, 9, 1, 5};
    
    std::cout << "Original array: ";
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    bogoSort(arr);

    std::cout << "Sorted array: ";
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
