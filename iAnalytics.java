/**
 * Class of operations on integer arrays.
 * You MUST NOT change the signatures of the methods supplied.
 */

// IN1002 Introduction to Algorithms
// Coursework 2024/2025
//
// Submission by
// YOUR_NAME_GOES_HERE
// YOUR_EMAIL_GOES_HERE

public class iAnalytics {

    // Task 1: Count unique elements in an ordered array
    // Time Complexity: O(n)
    public int countUnique(int[] arr) { // O(n) time complexity because we iterate through the array once to count unique elements
        if (arr.length == 0) return 0; //
        int count = 1; // at least one unique
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) { // if the current element is not equal to the previous element, then it is unique
                count++;
            }
        }
        return count;
    }

    // Task 2: Find least frequent value in an ordered array
    // Time Complexity: O(n)
    public int leastFrequent(int[] arr) { // O(n) time complexity because we iterate through the array once to find the least frequent element
        if (arr.length == 0) throw new IllegalArgumentException("Array is empty.");

        int minFreq = Integer.MAX_VALUE;
        int minVal = arr[0];
        int currentCount = 1;

        for (int i = 1; i <= arr.length; i++) {
            if (i < arr.length && arr[i] == arr[i - 1]) {
                currentCount++;
            } else {
                if (currentCount < minFreq || (currentCount == minFreq && arr[i - 1] < minVal)) {
                    minFreq = currentCount;
                    minVal = arr[i - 1];
                }
                currentCount = 1;
            }
        }
        return minVal;
    }

    // Task 3: Count elements in an ordered array less than num
    // Time Complexity: O(n) (can be O(log n) if you do binary search, but we must use linear)
    public int countLess(int[] arr, int num) { // O(n) time complexity because we iterate through the array once to count elements less than num
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < num) {
                count++;
            } else {
                break; // because the array is sorted
            }
        }
        return count;
    }

    // Task 4: Count elements in an ordered array between low and high
    // Time Complexity: O(n)
    public int countBetween(int[] arr, int low, int high) { // O(n) time complexity because we iterate through the array once to count elements between low and high
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= low && arr[i] <= high) {
                count++;
            }
            if (arr[i] > high) break; // because array is sorted
        }
        return count;
    }

    // Task 5: Find top K most frequent elements in an ordered array
    // Time Complexity: O(n * k)
    public int[] topKFrequent(int[] arr, int k) { // O(n * k) time complexity because we iterate through the array once to build the frequency array and then iterate through the frequency array k times to find the top k most frequent elements
        // Step 1: Build frequency array
        int[][] freq = new int[arr.length][2]; // [value, frequency]
        int idx = 0;
        int i = 0;
        while (i < arr.length) {
            int val = arr[i];
            int count = 1;
            i++;
            while (i < arr.length && arr[i] == val) {
                count++;
                i++;
            }
            freq[idx][0] = val;
            freq[idx][1] = count;
            idx++;
        }

        // Step 2: Select top K frequencies manually (no PriorityQueue)
        int[] result = new int[Math.min(k, idx)];
        for (int j = 0; j < result.length; j++) { // iterate k times
            int maxFreq = -1;
            int selectedIndex = -1;
            for (int m = 0; m < idx; m++) {
                if (freq[m][1] > maxFreq ||
                        (freq[m][1] == maxFreq && freq[m][0] < freq[selectedIndex][0])) {
                    boolean alreadyChosen = false;
                    for (int n = 0; n < j; n++) {
                        if (result[n] == freq[m][0]) {
                            alreadyChosen = true;
                            break;
                        }
                    }
                    if (!alreadyChosen) {
                        maxFreq = freq[m][1];
                        selectedIndex = m;
                    }
                }
            }
            result[j] = freq[selectedIndex][0];
        }
        return result;
    }

    // Task 6: Find the longest strictly ascending subarray
// Time Complexity: O(n)
    public int[] longestAscSubarray(int[] arr) { // O(n) time complexity because we iterate through the array once to find the longest strictly ascending subarray
        if (arr.length == 0) return new int[0];

        int maxStart = 0, maxLen = 1;
        int currStart = 0, currLen = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                currLen++;
                if (currLen > maxLen) {
                    maxLen = currLen;
                    maxStart = currStart;
                }
            } else {
                currLen = 1;
                currStart = i;
            }
        }

        int[] result = new int[maxLen];
        System.arraycopy(arr, maxStart, result, 0, maxLen);
        return result;
    }

    // Task 7: Find max sum of subarray of size k
// Time Complexity: O(n)
    public int maxSubarraySum(int[] arr, int k) { // O(n) time complexity because we iterate through the array once to find the maximum sum of a subarray of size k
        if (arr.length < k) throw new IllegalArgumentException("Array length is less than k.");

        int windowSum = 0; // sum of the first k elements
        for (int i = 0; i < k; i++) { // calculate the sum of the first k elements
            windowSum += arr[i]; // sum of the first k elements
        }

        int maxSum = windowSum; // maximum sum of a subarray of size k
        for (int i = k; i < arr.length; i++) { // iterate through the array starting from the kth element to find the maximum sum of a subarray of size k
            windowSum += arr[i] - arr[i - k];
            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        return maxSum;
    }

}
