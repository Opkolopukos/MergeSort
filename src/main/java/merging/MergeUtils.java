package merging;

import input.InputFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс сливает входящие сортированные файлы в один выходной файл.
 */

public class MergeUtils {
    static public void mergeFiles(boolean isAscendingSort, boolean isStringSort, String output, List<String> inputFileNames) throws IOException {
        InputFiles inputFiles = new InputFiles(inputFileNames);
        File outputFile = new File(output);
        if (isStringSort) {
            PriorityQueue<Nodes.StringHeapNode> queue;
            if (isAscendingSort) {
                queue = new PriorityQueue<>();
                for (int i = 0; i < inputFileNames.size(); i++) {
                    queue.add(new Nodes.StringHeapNode(inputFiles.getFileAsStringList(i), 0));
                }
            } else {
                queue = new PriorityQueue<>(Comparator.reverseOrder());
                for (int i = 0; i < inputFileNames.size(); i++) {
                    queue.add(new Nodes.StringHeapNode(inputFiles.getFileAsReversedStringList(i), 0));
                }
            }

            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                while (!queue.isEmpty()) {
                    // Extract min element from the queue
                    Nodes.StringHeapNode node = queue.poll();
                    printWriter.println(node.list.get(node.index));
                    // Return node with incremented pointer
                    if (node.hasNext()) {
                        node.index++;
                        queue.add(node);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!isStringSort) {
            PriorityQueue<Nodes.IntegerHeapNode> queue;
            if (isAscendingSort) {
                queue = new PriorityQueue<>();
                for (int i = 0; i < inputFileNames.size(); i++) {
                    queue.add(new Nodes.IntegerHeapNode(inputFiles.getFileAsIntegerList(i), 0));
                }
            } else {
                queue = new PriorityQueue<>(Comparator.reverseOrder());
                for (int i = 0; i < inputFileNames.size(); i++) {
                    queue.add(new Nodes.IntegerHeapNode(inputFiles.getFileAsReversedIntegerList(i), 0));
                }
            }
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                while (!queue.isEmpty()) {
                    // Extract min element from the queue
                    Nodes.IntegerHeapNode node = queue.poll();
                    printWriter.println(node.list.get(node.index));
                    // Add to queue next element from the list that contains the current visited node
                    if (node.hasNext()) {
                        node.index++;
                        queue.add(node);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}