package com.immenser.parcelspacking.util.path;

import java.io.File;
import java.util.Optional;

public interface PathConstructor {
    /**
     * Метод создания директории по определенному правилу.
     *
     * @return созданная директория.
     */
    Optional<File> constructPath();
}
