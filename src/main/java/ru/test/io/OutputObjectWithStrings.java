package ru.test.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputObjectWithStrings implements OutputObject<String> {
    private final PrintWriter printWriter;

    public OutputObjectWithStrings(String outputFilename) throws FileNotFoundException {
        this.printWriter = new PrintWriter(outputFilename);
    }

    @Override
    public void accept(String value) {
        printWriter.println(value);
    }

    @Override
    public void close() {
        printWriter.close();
    }
}
