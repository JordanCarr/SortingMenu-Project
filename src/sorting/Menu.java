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
        menu();
    }

    private static void menu() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Random random = new Random();
            int[] dataSet = new int[10000];
            for (int i = 0; i < dataSet.length; i++) {
                dataSet[i] = random.nextInt();

                /*if (i % 2 == 0 && i % 5 == 0) {
                    System.out.print("\n");
                }
                System.out.print(dataSet[i]);*/
            }
            //System.out.print("\n");

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
            System.err.println("Error in menu(): ");
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

        printArray(input);

        double end = System.nanoTime();
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

        printArray(input);

        double end = System.nanoTime();
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

        printArray(input);

        double end = System.nanoTime();
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    private static void MergeSort(int[] input) {
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
    }

    private static void QuickSort(int[] input) {
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
    }

    private static void printArray(int[] input) {
        for (int n = 0; n < input.length; n++) {
            if (n % 10 == 0) {
                System.out.print("\n");
            }
            System.out.print(input[n] + "\t");
        }
    }
}