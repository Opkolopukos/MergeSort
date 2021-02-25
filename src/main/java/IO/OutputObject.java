package IO;

import java.util.function.Consumer;

public interface OutputObject<T> extends Consumer<T> {
    @Override
    void accept(T t);
    void close();
}
