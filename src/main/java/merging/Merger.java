package merging;

import IO.InputObject;
import comparators.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Утилитаный класс с алгоритмом слияния.
 * <p>
 * С помощью карты (с ключом в виде входного файлы и значением в виде текущей читаемой строки)
 * алгоритм сравнивае текущии значения из файлов и, на основе логики компаратора,
 * отдаёт подходящее консьюмеру (который пишет в выходной файл).
 */

public class Merger {

    public static <T extends Comparable<T>> void merge(Comparator comparator, Consumer<T> output, List<InputObject<T>> inputs) {
        Map<InputObject<T>, T> suppliersMap = inputs.stream().collect(Collectors.toMap(input -> input, InputObject::get));
        while (!suppliersMap.isEmpty()) {
            //задаем значение пары null
            Map.Entry<InputObject<T>, T> minMaxPair = null;
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
                if (minMaxPair == null || comparator.compare(minMaxPair.getValue(), actualValue) > 0) {
                    minMaxPair = actualPair;
                }

                if (comparator.compare(actualValue, minMaxPair.getValue()) < 0) {
                    it.remove();
                }
            }
            if (minMaxPair == null) {
                break;
            }
            T currentMin = minMaxPair.getValue();
            T nextValue = minMaxPair.getKey().get();
            output.accept(currentMin);
            minMaxPair.setValue(nextValue);

            while (nextValue != null && comparator.compare(nextValue, currentMin) < 0) {
                System.out.println("Attention! Element \"" + minMaxPair.getValue() + "\" does not correspond with chosen sorting order");
                System.out.println("Looking for an appropriate element...");
                minMaxPair.setValue(minMaxPair.getKey().get());
                if (minMaxPair.getValue()==null || comparator.compare(minMaxPair.getValue(), currentMin) > 0)
                    break;
            }
        }
    }
}
