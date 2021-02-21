package sort;

public interface Sorter {
    <T extends Comparable<T>> void sort(T[] unsorted);
}
