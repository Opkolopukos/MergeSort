package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputObjectWithIntegers implements OutputObject<Integer> {
    private PrintWriter printWriter;

    public OutputObjectWithIntegers(String outputFilename) {
        try {
            this.printWriter = new PrintWriter(outputFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(Integer value) {
        try {
            printWriter.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        printWriter.close();
    }
}
