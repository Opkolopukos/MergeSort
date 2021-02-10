import ru.azarov.mergingfiles.MergeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметры программы задаются при запуске через аргументы командной строки, по порядку:
 * 1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
 * 2. тип данных (-s или -i), обязательный;
 * 3. имя выходного файла, обязательное;
 * 4. остальные параметры – имена входных файлов, не менее одного.
 * Примеры запуска из командной строки для Windows:
 * sort-it.exe -i -a out.txt in.txt (для целых чисел по возрастанию)
 * sort-it.exe -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)
 * sort-it.exe -d -s out.txt in1.txt in2.txt (для строк по убыванию)
 */


public class Main {
    public static void main(String[] args)  {
        List<Integer> list = List.of(1,4,6,9);
        List<Integer> list2 = List.of(3,4,9,12,15);

        List<Integer> result = new ArrayList<>();
        MergeUtil.merge2Files(result, list, list2);
        System.out.println(result);
    }
}
