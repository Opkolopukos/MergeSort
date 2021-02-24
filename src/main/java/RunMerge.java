import cmdline.CmdLineParser;
// fixme сортировка чисел идёт, даже если в параментрах указана сортировка строк
// fixme сортирует разные типы данных (строки с числами)
// fixme страшный говнокод в mergeUtils и inputFiles
// fixme обработать нестандартные случаи параметров командной строки
// fixme переделать алгоритм мержа

//


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
 *
 *  -i -a out.txt in1.txt in2.txt
 *   -s -d out.txt in1.txt in2.txt in3.txt
 *    -i -d out.txt in1.txt in2.txt in3.txt
 */

public class RunMerge {
    public static void main(String[] args)  {
        new CmdLineParser().parseCmd(args);
    }
}
