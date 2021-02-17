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

    static public void mergeFiles(boolean isString, String output, String... fileNames) throws IOException {
        InputFiles inputFiles = new InputFiles(fileNames);
        File outputFile = new File(output);
        if (isString) {
            PriorityQueue<StringHeapNode> queue = new PriorityQueue<>();
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                for (int i = 0; i < fileNames.length; i++) {
                    queue.add(new StringHeapNode(inputFiles.getFileAsStringList(i), 0));
                }
                while (!queue.isEmpty()) {
                    // Extract min element from the queue
                    StringHeapNode node = queue.poll();
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
        } else {
            PriorityQueue<IntegerHeapNode> queue = new PriorityQueue<>();
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {
                for (int i = 0; i < fileNames.length; i++) {

                    queue.add(new IntegerHeapNode(inputFiles.getFileAsIntegerList(i), 0));
                }
                while (!queue.isEmpty()) {
                    // Extract min element from the queue
                    IntegerHeapNode node = queue.poll();
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

    static class IntegerHeapNode implements Comparable<IntegerHeapNode> {
        List<Integer> list;
        int index;

        public IntegerHeapNode(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < this.list.size() - 1;
        }

        @Override
        public int compareTo(IntegerHeapNode o) {
            return list.get(index) - o.list.get(o.index);
        }
    }

    static class StringHeapNode implements Comparable<StringHeapNode> {
        List<String> list;
        int index;

        public StringHeapNode(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < this.list.size() - 1;
        }

        @Override
        public int compareTo(StringHeapNode o) {
            return list.get(index).compareTo(o.list.get(o.index));
        }
    }
}



    /*
    static class HeapNode <T> implements Comparable<HeapNode<T>> {
        List<T> list;
        int index;

        public HeapNode(List<T> list, int index) {
            this.list = list;
            this.index = index;
        }

        public boolean hasNext() {
            return this.index < this.list.size() - 1;
        }

        @Override
        public int compareTo(HeapNode o) {
                if (list.get(0) instanceof String){
                    return (String)list.get(index) ompareTo(o.list.get(o.index));
                }
        }


     */
