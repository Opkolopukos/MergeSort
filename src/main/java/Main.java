
import ru.azarov.mergingfiles.MergeUtils;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        MergeUtils.mergeFiles(false, "out.txt", "in1.txt", "in2.txt");

    }
}
