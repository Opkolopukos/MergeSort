package merging;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputObject<T> implements Supplier<T> {

    private final Scanner scanner;
    private final Function<String, T> functionPars;

    public InputObject(String source, Function<String, T> functionPars) {
        this.scanner = new Scanner(source);
        this.functionPars = functionPars;
    }

    @Override
    public T get() {
        while (scanner.hasNext()) {
            try {
                String s = scanner.next();
                return functionPars.apply(s);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return null;
    }
}
