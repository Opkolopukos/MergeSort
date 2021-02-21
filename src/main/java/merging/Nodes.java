package merging;


import comparators.DefaultComparator;

import java.util.List;

public class Nodes {

    static class HeapNode <T extends Comparable<T>> implements DefaultComparator {
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
        public <V extends Comparable<V>> int compare(V o1, V o2) {
            return o1.compareTo(o2);
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
