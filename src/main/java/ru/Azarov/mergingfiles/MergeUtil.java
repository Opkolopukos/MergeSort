package ru.Azarov.mergingfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Merge two collections(input) into one (output)
 */

public class MergeUtil {
    private static final File output = new File("FileMergeOutput.txt");

    public static void merge(int[] first, int[] second, List<Integer> output) {

        try (PrintWriter pw = new PrintWriter(MergeUtil.output)) {

        int i = 0, j = 0;
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                pw.write(first[i]+"\n");
                output.add(first[i++]);
            } else {
                pw.write(second[j]+"\n");
                output.add(second[j++]);
            }
        }

        while (i < first.length) {
            pw.write(first[i]+"\n");
            output.add(first[i++]);
        }

        while (j < second.length) {
            pw.write(second[j]+"\n");
            output.add(second[j++]);
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
