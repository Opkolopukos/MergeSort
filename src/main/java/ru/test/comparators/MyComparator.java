package ru.test.comparators;

public interface MyComparator {
    <T extends Comparable<T>> int compare(T o1, T o2);
}
