package ru.azarov.mergingfiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инициализирует входные файлы и открывает потоки чтения
 */

public class InputFiles {
    private final List<BufferedReader> bufferedReaders = new ArrayList<>();

    public InputFiles(String... filenames) {
        for (String x : filenames) {
            try {
                bufferedReaders.add(new BufferedReader(new FileReader(x)));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getFileAsList(int index) throws IOException {
        List<Integer> result = new ArrayList<>();
        while (bufferedReaders.get(index).ready()) {
            result.add(Integer.valueOf(bufferedReaders.get(index).readLine()));
        }
        return result;
    }
}
