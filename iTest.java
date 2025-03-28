import java.io.*;
import java.util.Arrays;

/**
 * The iTest class is responsible for testing all methods in the
 * iAnalytics class using predefined input files containing integer data.
 * It reads test data from files and runs all analytical functions.
 */
public class iTest {

    // List of four test files with different dataset sizes
    private static final String files[] = {
        "tiny.txt", "small.txt", "medium.txt", "large.txt"
    };

    /**
     * Reads an integer array from a specified file.
     * Each file contains a sequence of space-separated integers.
     *
     * @param filename the name of the file to read
     * @return an array of integers read from the file
     */
    public static int[] readArrayFromFile(String filename) {
        int[] arr = new int[0]; // Default empty array in case of an error
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            if (line != null) {
                String[] numbers = line.split(" ");
                arr = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    arr[i] = Integer.parseInt(numbers[i]);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return arr;
    }

    /**
     * Main test program for the iAnalytics class.
     * It tests all available methods using test files.
     *
     * @param args the command line arguments (unused)
     * @throws java.io.IOException if an error occurs while reading the input files
     */
    public static void main(String[] args) throws IOException {
        // Create an instance of iAnalytics to perform tests
        final iAnalytics sa = new iAnalytics();

        // Iterate over all predefined test files
        for (String file : files) { //
            System.out.println("Testing on " + file + ":");
            int[] arr = readArrayFromFile("data/" + file);

            long start = System.nanoTime();
            long end = System.nanoTime();

            // Test methods that do not require ordered inputs on the test data

            // -------- Test 6: longestAscSubarray --------
            start = System.nanoTime();
            int[] longestAsc = sa.longestAscSubarray(arr);
            end = System.nanoTime();
            System.out.println("Longest ascending subarray: " + Arrays.toString(longestAsc));
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            // -------- Test 7: maxSubarraySum --------
            int kSum = 5; // example k for sum
            start = System.nanoTime();
            int maxSum = sa.maxSubarraySum(arr, kSum);
            end = System.nanoTime();
            System.out.println("Max subarray sum with k=" + kSum + ": " + maxSum);
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            System.out.println();

            Arrays.sort(arr);

            // Test methods that require ordered inputs on the test data

            // -------- Test 1: countUnique --------
            start = System.nanoTime();
            int unique = sa.countUnique(arr);
            end = System.nanoTime();
            System.out.println("Unique elements: " + unique);
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            // -------- Test 2: leastFrequent --------
            start = System.nanoTime();
            int leastFreq = sa.leastFrequent(arr);
            end = System.nanoTime();
            System.out.println("Least frequent element: " + leastFreq);
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            // -------- Test 3: countLess --------
            int num = 10; // example threshold
            start = System.nanoTime();
            int lessThan = sa.countLess(arr, num);
            end = System.nanoTime();
            System.out.println("Elements less than " + num + ": " + lessThan);
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            // -------- Test 4: countBetween --------
            int low = 5, high = 20; // example range
            start = System.nanoTime();
            int between = sa.countBetween(arr, low, high);
            end = System.nanoTime();
            System.out.println("Elements between " + low + " and " + high + ": " + between);
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            // -------- Test 5: topKFrequent --------
            int k = 3; // example k
            start = System.nanoTime();
            int[] topK = sa.topKFrequent(arr, k);
            end = System.nanoTime();
            System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK));
            System.out.println("Time taken: " + (end - start) + " ns" + "\n");

            System.out.println(); // Print a blank line for readability
        }
    }
}
