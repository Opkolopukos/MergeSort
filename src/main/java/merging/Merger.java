package merging;

import IO.InputObject;
import comparators.AscendingComparator;
import comparators.Comparator;
import comparators.DescendingComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Merger {

    public static <T extends Comparable<T>> void merge(Comparator comparator, Consumer<T> output, List<InputObject<T>> inputs) {
        Map<InputObject<T>, T> suppliersMap = inputs.stream().collect(Collectors.toMap(input -> input, InputObject::get));
        List<T> utilList = new ArrayList<>(10);
        while (!suppliersMap.isEmpty()) {
            //задаем значение пары null
            Map.Entry<InputObject<T>, T> minPair = null;
            //итерируемся по энтриСету и оставляем самую минимальную пару
            for (Iterator<Map.Entry<InputObject<T>, T>> it = suppliersMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<InputObject<T>, T> actualPair = it.next();
                //текущее значение пары по которой итерируемся
                T actualValue = actualPair.getValue();
                //если значение равно null(значит мы удаляем пару из мапы) и продолжаем цикл for со следующей пары
                if (actualValue == null) {
                    it.remove();
                    continue;
                }
                //если ничего нет, устанавливаем, либо сравниваем через компаратор
                if (minPair == null || comparator.compare(minPair.getValue(), actualValue) > 0) {
                    minPair = actualPair;
                }
            }

            if (minPair == null) {
                break;
            }
            System.out.println(minPair.getValue());
            output.accept(minPair.getValue());
            if (utilList.size() < 10) {
                utilList.add(minPair.getValue());
            }
            if (utilList.size() > 8) {
                if (comparator instanceof AscendingComparator && isAscendingSort(utilList)) {
                    utilList.clear();
                } else if (comparator instanceof DescendingComparator && isDescendingSort(utilList)) {
                    utilList.clear();
                } else {
                    System.out.println("Sorting order settings and input files sorting order do not correspond");
                    System.out.println("Either change [-a|-d] settings or choose appropriate files");
                    System.exit(0);
                }
            }
            minPair.setValue(minPair.getKey().get());
        }
    }

    public static <T extends Comparable<? super T>>
    boolean isAscendingSort(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T t = iterator.next();
        while (iterator.hasNext()) {
            T t2 = iterator.next();
            if (t.compareTo(t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }
    public static <T extends Comparable<? super T>>
    boolean isDescendingSort(Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        T t = iterator.next();
        while (iterator.hasNext()) {
            T t2 = iterator.next();
            if (t.compareTo(t2) < 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }
}


