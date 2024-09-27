package com.immenser.parcelspacking.reading.json;

import java.util.Optional;

public interface JsonReader<T> {
    /**
     * Метод чтения объектов типа &lt;T&gt; из json.
     *
     * @return результат чтения файла
     */
    Optional<T> read();
}
