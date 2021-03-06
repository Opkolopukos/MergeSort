package ru.test.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputObjectWithIntegers implements OutputObject<Integer> {
    private final PrintWriter printWriter;

    public OutputObjectWithIntegers(String outputFilename) throws FileNotFoundException {
        this.printWriter = new PrintWriter(outputFilename);
    }

    @Override
    public void accept(Integer value) {
        try {
            printWriter.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        printWriter.close();
    }
}
