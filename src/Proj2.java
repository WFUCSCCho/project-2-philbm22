/********************************************************************************
 ∗ @file: Proj2.java
 ∗ @description: Main driver of the AVL vs BST tree comparison, populates 4 array lists with mountains and inserts into
 sorted and shuffled AVL and BST trees and compares the runtime of each while outputting to output.txt
 ∗ @author: Benton Phillips
 ∗ @date: October 24 , 2024
 *********∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //Create an ArrayList to store all mountain objects
        ArrayList<Mountain> mountains = new ArrayList<>();

        //Create separate ArrayLists for each type of comparison we need to make
        AvlTree<Mountain> sortedAvlTree = new AvlTree<>();
        AvlTree<Mountain> shufffledAvlTree = new AvlTree<>();
        BST<Mountain> sortedbst = new BST<>();
        BST<Mountain> shuffledbst = new BST<>();

        //Populate the ArrayList with mountain objects in their correct indices
        for(int i = 0; i < numLines; i++) {
            String line = inputFileNameScanner.nextLine().strip();

            if (!line.isEmpty()) {
                String[] totalCommand = line.split(",");
                if(totalCommand.length >= 5) {
                    Mountain mountain = new Mountain(
                        totalCommand[0].trim(),
                        Integer.parseInt(totalCommand[1].trim()),
                        Integer.parseInt(totalCommand[2].trim()),
                        totalCommand[3].trim(),
                        totalCommand[4].trim()
                        );

                    mountains.add(mountain);
                    }
                }

           }
        inputFileNameScanner.close();

        //Creates a FileWriter object to output our results to "output.txt" in append mode
        FileWriter fileWriter = new FileWriter("output.txt",true);

        //Puts a copy of the mountains ArrayList into each of our comparison ArrayLists
        ArrayList<Mountain> sortedAvl = new ArrayList<>(mountains);
        ArrayList<Mountain> shuffledAvl = new ArrayList<>(mountains);
        ArrayList<Mountain> sortedBST = new ArrayList<>(mountains);
        ArrayList<Mountain> shuffleddBST = new ArrayList<>(mountains);

        //Sorts and shuffled accordingly
        Collections.sort(sortedAvl);
        Collections.shuffle(shuffledAvl);
        Collections.sort(sortedBST);
        Collections.shuffle(shuffleddBST);

        //Sorted BST insertion
        long startTime1 = System.nanoTime();
        for (Mountain mountain : sortedBST) {
            sortedbst.insert(mountain);
        }
        long endTime1 = System.nanoTime();

        double runtime1 = (endTime1 - startTime1) / 1e9;
        fileWriter.write(numLines + "," +  runtime1 + "\n");
        System.out.println("Sorted BST Insertion, " + numLines + " Lines Running Time: " + (endTime1 - startTime1) / 1e9 + " sec");
        System.out.println("Sorted BST Insertion, " + numLines + " Lines Running Rate: " + ((endTime1 - startTime1) / 1e9)/numLines + " sec");
        System.out.println();

        //Shuffled BST insertion
        long startTime2 = System.nanoTime();
        for (Mountain mountain : shuffleddBST) {
            shuffledbst.insert(mountain);
        }
        long endTime2 = System.nanoTime();

        double runtime2 = (endTime2 - startTime2) / 1e9;
        fileWriter.write(numLines + "," +  runtime2 + "\n");
        System.out.println("Shuffled BST Insertion, " + numLines + " Lines Running Time: " + (endTime2 - startTime2) / 1e9 + " sec");
        System.out.println("Shuffled BST Insertion, " + numLines + " Lines Running Rate: " + ((endTime2 - startTime2) / 1e9)/numLines + " sec");
        System.out.println();

        //Sorted AVL insertion
        long startTime3 = System.nanoTime();
        for(Mountain mountain : sortedAvl) {
            sortedAvlTree.insert(mountain);
        }
        long endTime3 = System.nanoTime();

        double runtime3 = (endTime3 - startTime3) / 1e9;
        fileWriter.write(numLines + "," +  runtime3 + "\n");
        System.out.println("Sorted AVL Insertion, " + numLines + " Lines Running Time: " + (endTime3 - startTime3) / 1e9 + " sec");
        System.out.println("Sorted AVL Insertion, " + numLines + " Lines Running Rate: " + ((endTime3 - startTime3) / 1e9)/numLines + " sec");
        System.out.println();

        //Shuffled AVL insertion
        long startTime4 = System.nanoTime();
        for(Mountain mountain : shuffledAvl) {
            shufffledAvlTree.insert(mountain);
        }
        long endTime4 = System.nanoTime();

        double runtime4 = (endTime4 - startTime4) / 1e9;
        fileWriter.write(numLines + "," +  runtime4 + "\n");
        System.out.println("Shuffled AVL Insertion, " + numLines + " Lines Running Time: " + (endTime4 - startTime4) / 1e9 + " sec");
        System.out.println("Shuffled AVL Insertion, " + numLines + " Lines Running Rate: " + ((endTime4 - startTime4) / 1e9)/numLines + " sec");
        System.out.println();

        //Sorted BST search
        long startTime5 = System.nanoTime();
        for (Mountain mountain : sortedBST) {
            sortedbst.search(mountain);
        }
        long endTime5 = System.nanoTime();

        double runtime5 = (endTime5 - startTime5) / 1e9;
        fileWriter.write(numLines + "," +  runtime5 + "\n");
        System.out.println("Sorted BST Search, " + numLines + " Lines Running Time: " + (endTime5 - startTime5) / 1e9 + " sec");
        System.out.println("Sorted BST Search, " + numLines + " Lines Running Rate: " + ((endTime5 - startTime5) / 1e9)/numLines + " sec");
        System.out.println();

        //Shuffled BST search
        long startTime6 = System.nanoTime();
        for (Mountain mountain : shuffleddBST) {
            shuffledbst.search(mountain);
        }
        long endTime6 = System.nanoTime();

        double runtime6 = (endTime6 - startTime6) / 1e9;
        fileWriter.write(numLines + "," +  runtime6 + "\n");
        System.out.println("Shuffled BST Search, " + numLines + " Lines Running Time: " + (endTime6 - startTime6) / 1e9 + " sec");
        System.out.println("Shuffled BST Search, " + numLines + " Lines Running Rate: " + ((endTime6 - startTime6) / 1e9)/numLines + " sec");
        System.out.println();

        //Sorted AVL search
        long startTime7 = System.nanoTime();
        for(Mountain mountain : sortedAvl) {
            sortedAvlTree.contains(mountain);
        }
        long endTime7 = System.nanoTime();

        double runtime7 = (endTime7 - startTime7) / 1e9;
        fileWriter.write(numLines + "," + runtime7 + "\n");
        System.out.println("Sorted AVL Search, " + numLines + " Lines Running Time: " + (endTime7 - startTime7) / 1e9 + " sec");
        System.out.println("Sorted AVL Search, " + numLines + " Lines Running Rate: " + ((endTime7 - startTime7) / 1e9)/numLines + " sec");
        System.out.println();

        //Shuffled AVL search
        long startTime8 = System.nanoTime();
        for(Mountain mountain : shuffledAvl) {
            shufffledAvlTree.contains(mountain);
        }
        long endTime8 = System.nanoTime();

        double runtime8 = (endTime8 - startTime8) / 1e9;
        fileWriter.write(numLines + "," +  runtime8 + "\n");
        System.out.println("Shuffled AVL Search, " + numLines + " Lines Running Time: " + (endTime8 - startTime8) / 1e9 + " sec");
        System.out.println("Shuffled AVL Search, " + numLines + " Lines Running Rate: " + ((endTime8 - startTime8) / 1e9)/numLines + " sec");
        System.out.println();

        //Closes fileWriter for output
        fileWriter.close();

       }
        }