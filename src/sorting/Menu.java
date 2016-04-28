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
                        BubbleSort(dataSet);
                        break;
                    case "two":
                    case "2":
                        InsertionSort(dataSet);
                        break;
                    case "three":
                    case "3":
                        SelectionSort(dataSet);
                        break;
                    case "four":
                    case "4":
                        MergeSort(dataSet);
                        break;
                    case "five":
                    case "5":
                        QuickSort(dataSet);
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
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
        long iterations = 0;
        int swap;
        for (int i = 0; i < (manipulatedArray.length - 1); i++) {
            for (int j = 0; j < manipulatedArray.length - 1; j++) {
                if (manipulatedArray[j] > manipulatedArray[j + 1]) {
                    swap = manipulatedArray[j];
                    manipulatedArray[j] = manipulatedArray[j + 1];
                    manipulatedArray[j + 1] = swap;
                    iterations++;
                }
            }
        }

        for (int i = 0; i < manipulatedArray.length; i++) {
            if (i % 10 == 0) {
                System.out.print("\n");
            }
            System.out.print(manipulatedArray[i] + "\t");
        }

        double end = System.nanoTime();
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    private static void InsertionSort(int[] input) {
        double start = System.nanoTime();
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
        long iterations = 0;
        int insert;
        int i;
        for (int j = 1; j < input.length; j++) {
            insert = input[j];
            for (i = j - 1; (i >= 0) && (input[i] < insert); i--) {
                input[i + 1] = input[i];
                iterations++;
            }
            input[i + 1] = insert;
        }

        for (int n = 0; n < manipulatedArray.length; n++) {
            if (n % 10 == 0) {
                System.out.print("\n");
            }
            System.out.print(manipulatedArray[n] + "\t");
        }

        double end = System.nanoTime();
        System.out.println("\nPermutations: " + iterations + "\nDuration: " + ((end - start) / 100000) +
                " milliseconds(s)\n");
    }

    private static void SelectionSort(int[] input) {
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;

    }

    private static void MergeSort(int[] input) {
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
    }

    private static void QuickSort(int[] input) {
        @SuppressWarnings("UnnecessaryLocalVariable") int[] manipulatedArray = input;
    }
}