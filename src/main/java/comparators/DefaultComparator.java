package comparators;

import java.util.Comparator;

public interface DefaultComparator {
    public abstract <T extends Comparable<T>> int compare(T o1, T o2);
}
