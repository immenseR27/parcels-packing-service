package com.immenser.parcelspacking.reading.file;

import java.util.List;

public interface FileReader<T> {
    /**
     * Метод чтения объектов типа &lt;T&gt; из файла.
     *
     * @param fileName имя исходного файла.
     * @return результат чтения файла
     */
    List<T> read(String fileName);
}
