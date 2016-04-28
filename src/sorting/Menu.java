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
            int[] dataSet = new int[10];
            for (int i = 0; i < dataSet.length; i++) {
                dataSet[i] = random.nextInt(10);
                System.out.println(dataSet[i]);
            }
            System.out.print("\n");

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
        } catch (ArrayIndexOutOfBoundsException e) {
            exit = 2;
            System.err.print("Error in menu(): " + e + "\nError Code: " + exit);
            System.exit(exit);
        } catch (Exception e) {
            exit = 1;
            System.err.print("Error in menu(): " + e + "\nError Code: " + exit);
            System.exit(exit);
        }
    }

    private static void BubbleSort(int[] input) {
        int[] sortedArray = input;
        long permutations = 0;
        int swap;
        for (int i = 0; i < (sortedArray.length - 1); i++) {
            for (int j = 0; j < sortedArray.length - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    swap = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = swap;
                    permutations++;
                }
            }
        }

        for (int aSortedArray : sortedArray) {
            if (aSortedArray % 2 == 0 && aSortedArray % 5 == 0) {
                System.out.print("\n");
            }
            System.out.print(aSortedArray);
        }
        System.out.println("Permutations: " + permutations);
    }

    private static void InsertionSort(int[] input) {
        System.out.println("\nInsertionSort\n" + input.length);
    }

    private static void SelectionSort(int[] input) {
        System.out.println("\nSelectionSort\n" + input.length);
    }

    private static void MergeSort(int[] input) {
        System.out.println("\nMergeSort\n" + input.length);
    }

    private static void QuickSort(int[] input) {
        System.out.println("\nQuickSort\n" + input.length);
    }
}