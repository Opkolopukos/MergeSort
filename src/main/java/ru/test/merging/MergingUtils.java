package ru.test.merging;

import ru.test.comparators.MyComparator;
import ru.test.io.InputObject;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Утилитаный класс с алгоритмом слияния.
 * <p>
 * С помощью карты (с ключом в виде входного файлы и значением в виде текущей читаемой строки)
 * алгоритм сравнивает текущии значения из файлов и, на основе логики заданного компаратора,
 * отдаёт подходящее консьюмеру (который пишет в выходной файл).
 *
 */

public class MergingUtils {
    public static <T extends Comparable<T>> void mergeFiles(MyComparator myComparator, Consumer<T> output, Stream<InputObject<T>> inputs) {
        Map<InputObject<T>, T> suppliersMap = new HashMap<>();
        for (InputObject<T> inputObject : inputs.collect(Collectors.toList())) {
            T value = inputObject.get();
            if (Objects.nonNull(value)) {
                suppliersMap.put(inputObject, value);
            }
        }
        if (suppliersMap.isEmpty()) {
            System.out.println("There are no correct data for completing MergeSort");
            return;
        }
        T lastWrittenValue = null;
        while (!suppliersMap.isEmpty()) {

            Map.Entry<InputObject<T>, T> minMaxPair = null;
            for (Iterator<Map.Entry<InputObject<T>, T>> it = suppliersMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<InputObject<T>, T> actualPair = it.next();
                T actualValue = actualPair.getValue();
                if (actualValue == null) {
                    it.remove();
                    continue;
                }
                if (minMaxPair == null || myComparator.compare(minMaxPair.getValue(), actualValue) > 0) {
                    minMaxPair = actualPair;
                }
                if (myComparator.compare(actualValue, minMaxPair.getValue()) < 0) {
                    it.remove();
                }
            }
            if (minMaxPair == null) {
                break;
            }
            T minValue = minMaxPair.getValue();
            if (lastWrittenValue == null || myComparator.compare(minValue, lastWrittenValue) >= 0) {
                output.accept(minValue);
                lastWrittenValue = minValue;
            }  else {
                System.out.println("Attention! Element \"" + minValue + "\" does not correspond with chosen sorting order");
            }
            minMaxPair.setValue(minMaxPair.getKey().get());
        }
    }
}
