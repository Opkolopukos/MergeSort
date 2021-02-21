package merging;

import java.io.*;
import java.util.Iterator;


public class InputFile {
    private boolean stopProcessing;
    private File file;
    private Iterator iterator;
    private String fileName;
    private BufferedReader bufferedReader;

    public InputFile(String fileName) throws IOException {
        this.file = new File(fileName);
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    public void readLine() throws IOException {
        bufferedReader.readLine();
    }
}