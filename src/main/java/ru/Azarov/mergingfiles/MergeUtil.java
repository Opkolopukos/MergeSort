package ru.azarov.mergingfiles;

import java.io.*;
import java.util.*;

/**
 * Merging two files into one
 */

public class MergeUtil {
    private static final File output = new File("FileMergeOutput.txt");
    private static final List<List<Integer>> inputsList = new ArrayList<>();

    @SafeVarargs
    public static void merge2Files(List<Integer> output, List<Integer>... lists) {
        int n = 0;
        while (n < lists.length) {
            inputsList.add(lists[n++]);
        }
        try (PrintWriter pw = new PrintWriter(MergeUtil.output)) {
            int i = 0, j = 0;
            while (i < inputsList.get(0).size() && j < inputsList.get(1).size()) {
                if (inputsList.get(0).get(i) < inputsList.get(1).get(j)) {
                    pw.write(inputsList.get(0).get(i) + "\n");
                    output.add(inputsList.get(0).get(i++));
                } else {
                    pw.write(inputsList.get(1).get(j) + "\n");
                    output.add(inputsList.get(1).get(j++));
                }
            }
            while (i < inputsList.get(0).size()) {
                pw.write(inputsList.get(0).get(i) + "\n");
                output.add(inputsList.get(0).get(i++));
            }

            while (j < inputsList.get(1).size()) {
                pw.write(inputsList.get(1).get(j) + "\n");
                output.add(inputsList.get(1).get(j++));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
