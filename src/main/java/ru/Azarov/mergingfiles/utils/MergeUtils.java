package ru.azarov.mergingfiles.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeUtils {

    static public List<Integer> mergeListOfLists(List<List<Integer>> listOfLists) throws FileNotFoundException {


        PriorityQueue<HeapNode> queue = new PriorityQueue<>();
        // Add first element of each array to the queue
        for (List<Integer> innerList : listOfLists) {
            queue.add(new HeapNode(innerList, 0));

        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            // Extract min element from the queue
            HeapNode node = queue.poll();
            result.add(node.list.get(node.index));

            // Add to queue next element from the array that contains the current visited node
            if (node.hasNext()) {
                queue.add(new HeapNode(node.list, node.index + 1));
            }
        }

        return result;
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
