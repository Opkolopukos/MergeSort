package ru.azarov.mergingfiles;

import ru.azarov.mergingfiles.utils.MergeUtils;

import java.io.*;
import java.util.*;

/**
 *  Sorting listOfSortedLists into 1 sorted list and write into file
 */

public class Sorter {
    private static final File output = new File("FileMergeOutput.txt");

    public static void mergeFiles(List<List<Integer>> listOfLists) {
        try (PrintWriter printWriter = new PrintWriter(Sorter.output)) {
            List<Integer> result = MergeUtils.mergeListOfLists(listOfLists);
            System.out.println(result);
            result.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
