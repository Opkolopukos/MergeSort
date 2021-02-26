package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputObjectWithIntegers implements InputObject<Integer> {
    private BufferedReader reader;

    public InputObjectWithIntegers(String filename) {
        try {
            this.reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public Integer get() {
        while (true) {
            try {
                if (!reader.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("can't parse this value to Integer");
                System.out.println("Either change [-d|-s] settings or choose appropriate input files");
                System.exit(0);
            }
        }
        return null;
    }
}
