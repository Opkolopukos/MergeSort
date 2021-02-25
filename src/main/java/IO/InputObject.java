package IO;

import java.util.function.Supplier;

public interface InputObject<T> extends Supplier<T> {
    @Override
    T get();

}