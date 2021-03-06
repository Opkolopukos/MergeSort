package ru.test.io;

import java.util.function.Supplier;

public interface InputObject<T> extends Supplier<T>, AutoCloseable {
}
