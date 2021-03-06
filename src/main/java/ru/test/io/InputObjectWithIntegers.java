package ru.test.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputObjectWithIntegers implements InputObject<Integer> {
    private final BufferedReader reader;

    public InputObjectWithIntegers(FileReader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public Integer get() {
        try {
            while (reader.ready()) {
                try {
                    return Integer.parseInt(reader.readLine());
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Can't parse one of the inputs to Integer, if possible - element will be skipped and MergeSort will resume");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void close() throws Exception {
        reader.close();
    }
}
