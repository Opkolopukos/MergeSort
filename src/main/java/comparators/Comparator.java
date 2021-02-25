package comparators;

public interface Comparator {
    <T extends Comparable<T>> int compare(T o1, T o2);
}
