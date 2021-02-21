package merging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * Класс сливает входящие сортированные файлы в один выходной файл.
 */

public class MergeUtils {
    static public void mergeFiles(ContentsType type, String output, String... fileNames) throws IOException {
        InputFiles inputFiles = new InputFiles(fileNames);
        File outputFile = new File(output);
        if (type == ContentsType.STRING) {
            PriorityQueue<Nodes.StringHeapNode> queue = new PriorityQueue<>();
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                for (int i = 0; i < fileNames.length; i++) {
                    queue.add(new Nodes.StringHeapNode(inputFiles.getFileAsStringList(i), 0));
                }
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
        if (type == ContentsType.INTEGER) {
            PriorityQueue<Nodes.IntegerHeapNode> queue = new PriorityQueue<>();
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                for (int i = 0; i < fileNames.length; i++) {
                    queue.add(new Nodes.IntegerHeapNode(inputFiles.getFileAsIntegerList(i), 0));
                }
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