package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputObjectWithStrings implements InputObject<String> {
    private BufferedReader reader;

    public InputObjectWithStrings(String filename) {
        try {
            this.reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public String get() {
        while (true) {
            try {
                if (!reader.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return reader.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("Incorrect data");
            }
        }
        return null;
    }
}
