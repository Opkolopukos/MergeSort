package merging;

import IO.InputObject;
import comparators.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Merger {

    public static <T extends Comparable<T>> void merge(Comparator comparator, Consumer<T> output, List<InputObject<T>> inputs) {
        Map<InputObject<T>, T> suppliersMap = inputs.stream().collect(Collectors.toMap(input -> input, InputObject::get));

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
            output.accept(minPair.getValue());
            System.out.println(minPair.getValue());
            minPair.setValue(minPair.getKey().get());

        }
    }
}


