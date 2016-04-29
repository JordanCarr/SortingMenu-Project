/*
 * Copyright 2016 Jordan Carr
 */

package sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

class Menu {
    private static int exit = -1;

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

    private static void MergeSort(int[] input) {
        double start = System.nanoTime();

        long iterations = 0;

        iterations = sortMergeSort(input, 0, (input.length - 1), iterations);

        double end = System.nanoTime();

        printArray(input);

        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    private static long sortMergeSort(int a[], int left, int right, long iterations) {

        if (right == left) return 1;
        int middle = (left + right) / 2; //salient feature

        iterations++;
        sortMergeSort(a, left, middle, iterations); //salient feature #1 (recursion)
        sortMergeSort(a, middle + 1, right, iterations); //salient feature #2
        mergeMergeSort(a, left, middle, right); //salient feature #3
        return iterations;
    }

    private static void mergeMergeSort(int a[], int left, int middle, int right) {
        //This temporary array will be used to build the merged list
        int tmpArray[] = new int[right - left + 1];
        //This creation of a temporary array is a BIG feature of the merge sort.
        int index1 = left;
        int index2 = middle + 1;
        int indx = 0;
        //Loop until one of the sublists is finished, adding the smaller of the first
        //elements of each sublist to the merged list.
        while (index1 <= middle && index2 <= right) {
            if (a[index1] < a[index2]) {
                tmpArray[indx] = a[index1];
                index1++;
            } else {
                tmpArray[indx] = a[index2];
                index2++;
            }
            indx++;
        }
        //Add to the merged list the remaining elements of whichever sublist is
        //not yet finished
        while (index1 <= middle) {
            tmpArray[indx] = a[index1];
            index1++;
            indx++;
        }
        while (index2 <= right) {
            tmpArray[indx] = a[index2];
            index2++;
            indx++;
        }
//Copy the merged list from the tmpArray array into the a array
        for (indx = 0; indx < tmpArray.length; indx++) {
            a[left + indx] = tmpArray[indx];
        }
    }

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

    private static long sortQuickSort(int[] input, int left, int right, long iterations) {
        if (left >= right) {
            return iterations;
        }

        int k = left;
        int j = right;
        int pivotValue = input[(left + right) / 2]; // salient feature
        while (k < j) {
            while (input[k] < pivotValue) //salient feature (pivot point)
            {
                k++;
            }
            while (pivotValue < input[j]) {
                j--;
            }
            if (k <= j) {
                int temp = input[k]; //swap a[k] and a[j]
                input[k] = input[j];
                input[j] = temp;
                k++;
                j--;
                iterations++;
            }
        }
        sortQuickSort(input, left, j, iterations); //salient feature (recursion)
        sortQuickSort(input, k, right, iterations);
        return iterations;
    }

    private static void printArray(int[] arrayToPrint) {
        for (int n = 0; n < arrayToPrint.length; n++) {
            if (n % 10 == 0) {
                System.out.print("\n");
            }
            System.out.print(arrayToPrint[n] + "\t");
        }
    }
}