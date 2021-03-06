package ru.test.io;

import java.util.function.Consumer;

public interface OutputObject<T> extends Consumer<T>,AutoCloseable {
    @Override
    void accept(T t);
    void close();
}
