package ru.azarov.mergingfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс сливает входящие сортированные файлы в один выходной файл.
 */

public class MergeUtils {

    static public void mergeFiles(String output, String... fileNames) throws IOException {
        InputFiles inputFiles = new InputFiles(fileNames);
        File outputFile = new File(output);
        PriorityQueue<HeapNode> queue = new PriorityQueue<>();
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            for (int i = 0; i < fileNames.length; i++) {
                queue.add(new HeapNode(inputFiles.getFileAsList(i), 0));
            }
            while (!queue.isEmpty()) {
                // Extract min element from the queue
                HeapNode node = queue.poll();
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

    static class HeapNode implements Comparable<HeapNode> {
        List<Integer> list;
        int index;

        public HeapNode(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < this.list.size() - 1;
        }

        @Override
        public int compareTo(HeapNode o) {
            return list.get(index) - o.list.get(o.index);
        }
    }
}
