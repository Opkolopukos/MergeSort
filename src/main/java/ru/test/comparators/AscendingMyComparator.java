package ru.test.comparators;

public class AscendingMyComparator implements MyComparator {
    @Override
    public <T extends Comparable<T>> int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
