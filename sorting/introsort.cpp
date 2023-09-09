#include <iostream>
#include <vector>
#include <algorithm>

void insertionSort(std::vector<int>& arr, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= left && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

int partition(std::vector<int>& arr, int left, int right) {
    int pivot = arr[right];
    int i = left - 1;
    for (int j = left; j < right; j++) {
        if (arr[j] < pivot) {
            i++;
            std::swap(arr[i], arr[j]);
        }
    }
    std::swap(arr[i + 1], arr[right]);
    return i + 1;
}

void introSortUtil(std::vector<int>& arr, int left, int right, int depthLimit) {
    if (right - left > 16) {
        if (depthLimit == 0) {
            std::make_heap(arr.begin() + left, arr.begin() + right + 1);
            std::sort_heap(arr.begin() + left, arr.begin() + right + 1);
            return;
        }
        int pivotIndex = partition(arr, left, right);
        introSortUtil(arr, left, pivotIndex - 1, depthLimit - 1);
        introSortUtil(arr, pivotIndex + 1, right, depthLimit - 1);
    } else {
        insertionSort(arr, left, right);
    }
}

void introSort(std::vector<int>& arr) {
    int depthLimit = 2 * log2(arr.size());
    introSortUtil(arr, 0, arr.size() - 1, depthLimit);
}

int main() {
    std::vector<int> arr = {5, 2, 9, 1, 5};
    
    std::cout << "Original array: ";
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    introSort(arr);

    std::cout << "Sorted array: ";
    for (int num : arr) {
        std::cout << num << " ";
    }
    std::cout << std::endl;

    return 0;
}
