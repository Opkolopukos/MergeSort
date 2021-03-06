package ru.test.io;

import java.io.BufferedReader;
import java.io.FileReader;

public class InputObjectWithStrings implements InputObject<String> {
    private final BufferedReader reader;

    public InputObjectWithStrings(FileReader reader) {
        this.reader = new BufferedReader(reader);
    }

    @Override
    public String get() {
        try {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line == null || line.contains(" ")) {
                    System.out.println("Element \"" + line + "\" is skipped due to whitespace");
                    continue;
                }
                return line;
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
