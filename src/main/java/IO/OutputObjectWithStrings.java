package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputObjectWithStrings implements OutputObject<String> {
    private PrintWriter printWriter;

    public OutputObjectWithStrings(String outputFilename) {
        try {
            this.printWriter = new PrintWriter(outputFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept(String value) {
        if (!value.contains(" ")) {
            printWriter.println(value);
        } else {
            System.out.print("element is skipped due to whitespace -> ");
        }
    }

    @Override
    public void close() {
        printWriter.close();
    }
}
