/*
 * Copyright 2016 Jordan Carr
 * Project Name: SortingMenu-Project
 * Class Name: sorting.Menu
 * Last Modification Date: 01/05/16 4:46 PM
 */

package sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Objectives of this program:
 * - Generate a data set with 10,000 numbers in it.
 * - Use the following five sorts on the data: bubble, insertion, selection, merge, and quick.
 * - Determine how long the sort took to complete.
 * - Count the iteration in each sort.
 * - Output the sorted data along with the time taken to complete and the iterations taken to complete.
 */
class Menu {
    private static int exit = -1;
    /**
     * The main method runs the menu for interfacing with the user and also handles any errors thrown throughout the program.
     * @param args There are no args for this program.
     */
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Random random = new Random();
            int[] dataSet = new int[10000];
            for (int i = 0; i < dataSet.length; i++) {
                dataSet[i] = random.nextInt();
            }
            do {
                System.out.println("Please choose a sorting method from the menu below:");
                System.out.print("[1] Bubble Sort\n[2] Insertion Sort\n[3] Selection Sort\n[4] Merge Sort\n[5] Quick " +
                        "Sort\n[Q] Quit\n");
                System.out.print("Selection: ");
                String sel;
                try {
                    sel = br.readLine();
                } catch (NumberFormatException e) {
                    System.out.println("That is not a valid input");
                    sel = " ";
                }
                switch (sel) {
                    case "one":
                    case "1":
                        BubbleSort(dataSet.clone());
                        break;
                    case "two":
                    case "2":
                        InsertionSort(dataSet.clone());
                        break;
                    case "three":
                    case "3":
                        SelectionSort(dataSet.clone());
                        break;
                    case "four":
                    case "4":
                        MergeSort(dataSet.clone());
                        break;
                    case "five":
                    case "5":
                        QuickSort(dataSet.clone());
                        break;
                    case "q":
                    case "Q":
                        System.out.print("Exiting...");
                        exit = 0;
                        break;
                    default:
                        System.out.println("Improper input received");
                        break;
                }
            } while (exit == -1);
            System.exit(exit);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("\nThe cause was: " + e.getCause() + "\n");
            exit = 1;
            System.exit(exit);
        }
    }

    /**
     * BubbleSort, sorts the data from input using the bubble sort algorithm.
     * @param input The input is the 10000 number random integer data set.
     */
    private static void BubbleSort(int[] input) {
        double start = System.nanoTime();
        long iterations = 0;
        int swap;
        for (int i = 0; i < (input.length - 1); i++) {
            for (int j = 0; j < input.length - 1; j++) {
                if (input[j] > input[j + 1]) {
                    swap = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = swap;
                    iterations++;
                }
            }
        }
        double end = System.nanoTime();
        printArray(input);
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    /**
     * InsertionSort, sorts the data from input using the insertion sort algorithm.
     * @param input The input is the 10000 number random integer data set.
     */
    private static void InsertionSort(int[] input) {
        double start = System.nanoTime();
        long iterations = 0;
        int insert;
        int i;
        for (int j = 1; j < input.length; j++) {
            insert = input[j];
            for (i = j - 1; (i >= 0) && (input[i] > insert); i--) {
                input[i + 1] = input[i];
                iterations++;
            }
            input[i + 1] = insert;
        }
        double end = System.nanoTime();
        printArray(input);
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    /**
     * InsertionSort, sorts the data from input using the insertion sort algorithm.
     * @param input The input is the 10000 number random integer data set.
     */
    private static void SelectionSort(int[] input) {
        double start = System.nanoTime();
        long iterations = 0;
        int i, j, f, tmp;
        for (i = input.length - 1; i > 0; i--) {
            f = 0;
            for (j = 1; j <= i; j++) {
                if (input[j] > input[f])
                    f = j;
                iterations++;
            }
            tmp = input[f];
            input[f] = input[i];
            input[i] = tmp;
        }
        double end = System.nanoTime();
        printArray(input);
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    /**
     * SelectionSort, sorts the data from input using the selection sort algorithm.
     * @param input The input is the 10000 number random integer data set.
     */
    private static void MergeSort(int[] input) {
        double start = System.nanoTime();
        long iterations = 0L;
        iterations = sortMergeSort(input, 0, (input.length - 1), iterations);
        double end = System.nanoTime();
        printArray(input);
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    /**
     * Taking input, left, right and, iterations as inputs it sorts the data in the sections defined earlier.
     *
     * @param input The input is the 10000 number random integer data set.
     * @param left  The left side that the sort deals with.
     * @param right The right side that the sort deals with.
     * @return Returns the number of iterations for the sort to complete.
     */
    private static long sortMergeSort(int[] input, int left, int right, long iterations) {
        if (right == left) {
            return iterations;}
        int middle = (left + right) / 2;
        iterations++;
        sortMergeSort(input, left, middle, iterations);
        sortMergeSort(input, middle + 1, right, iterations);
        mergeMergeSort(input, left, middle, right);
        return iterations;
    }

    /**
     * Merges the sorted sections of input.
     * @param input  The input is the 10000 number random integer data set.
     * @param left   The left side that the sort deals with.
     * @param middle To keep track of where the middle is before and during merge.
     * @param right  The right side that the sort deals with.
     */
    private static void mergeMergeSort(int input[], int left, int middle, int right) {

        int tmpArray[] = new int[right - left + 1];

        int index1 = left;
        int index2 = middle + 1;
        int indx = 0;

        while (index1 <= middle && index2 <= right) {
            if (input[index1] < input[index2]) {
                tmpArray[indx] = input[index1];
                index1++;
            } else {
                tmpArray[indx] = input[index2];
                index2++;
            }
            indx++;
        }

        while (index1 <= middle) {
            tmpArray[indx] = input[index1];
            index1++;
            indx++;
        }
        while (index2 <= right) {
            tmpArray[indx] = input[index2];
            index2++;
            indx++;
        }

        for (indx = 0; indx < tmpArray.length; indx++) {
            input[left + indx] = tmpArray[indx];
        }
    }

    /**
     * Takes start and end times for duration as well as passing the proper data onto the sortQuickSort
     * method where the quick sort algorithm is used.
     * @param input The input is the 10000 number random integer data set.
     */
    private static void QuickSort(int[] input) {
        double start = System.nanoTime();
        long iterations = 0;
        int left = 0;
        int right = (input.length - 1);
        iterations = sortQuickSort(input, left, right, iterations);
        double end = System.nanoTime();
        printArray(input);
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    /**
     * Starts the duration and iteration measures and passes the appropriate data to sortQuickSort.
     * @param input The input is the 10000 number random integer data set.
     * @param left The left side that the sort deals with.
     * @param right The right side that the sort deals with.
     * @param iterations Takes in an initial iteration number that is then iterated on each sort algorithm iteration.
     * @return Returns the number of iterations for the sort to complete.
     */
    private static long sortQuickSort(int[] input, int left, int right, long iterations) {
        if (left >= right) {
            return iterations;
        }
        int k = left;
        int j = right;
        int pivotValue = input[(left + right) / 2];
        while (k < j) {
            while (input[k] < pivotValue)
            {
                k++;
            }
            while (pivotValue < input[j]) {
                j--;
            }
            if (k <= j) {
                int temp = input[k];
                input[k] = input[j];
                input[j] = temp;
                k++;
                j--;
                iterations++;
            }
        }
        sortQuickSort(input, left, j, iterations);
        sortQuickSort(input, k, right, iterations);
        return iterations;
    }

    /**
     * Apples the quick sort algorithm with the data passed to it from QuickSort.
     * Takes the array created by one of the sorting methods and prints it to screen as per program specifications.
     * This output is formatted to help legibility.
     * @param arrayToPrint Takes a sorted array and then prints that array to screen.
     */
    private static void printArray(int[] arrayToPrint) {
        for (int n = 0; n < arrayToPrint.length; n++) {
            if (n % 10 == 0) {
                System.out.print("\n");
            }
            System.out.print(arrayToPrint[n] + "\t");
        }
    }
}