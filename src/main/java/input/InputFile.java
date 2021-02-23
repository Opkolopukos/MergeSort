package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFile {
    private BufferedReader reader = null;
    private final String fileName;
    private String currentLine;
    private List<String> contents;

    public InputFile(String filename) {
        this.fileName = filename;
        try {
            this.reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println(this + "not found");
            e.printStackTrace();
        }
    }

    private void getFileContents() throws IOException {
        contents = new ArrayList<>();
        while (reader.ready()) {
            contents.add(reader.readLine());
        }
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine() throws IOException {
        this.currentLine = reader.readLine();
    }

    @Override
    public String toString() {
        return "InputFile{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

}
