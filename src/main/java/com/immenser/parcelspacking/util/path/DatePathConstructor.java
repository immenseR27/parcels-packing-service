package com.immenser.parcelspacking.util.path;

import com.immenser.parcelspacking.exception.IllegalFilePath;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Log4j2
public class DatePathConstructor implements PathConstructor {

    private final File file;
    private final String rootOutPath;
    private final int numOfCars;

    public DatePathConstructor(String inputFileName, String rootOutPath, int numOfCars) {
        this.file = new File(inputFileName);
        this.rootOutPath = rootOutPath;
        this.numOfCars = numOfCars;
    }

    /**
     * Метод создания директории по определенному правилу.
     * <p>
     * Метод создает директорию на основе даты погрузки, имени исходного файла с посылками и количества машин для погрузки.
     *
     * @return Созданная директория.
     */
    @Override
    public Optional<File> constructPath() {
        try {
            File targetDirectory = findTargetDirectory();
            mkdirIfNotExists(targetDirectory);
            return Optional.of(targetDirectory);
        } catch (IllegalFilePath e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        return Optional.empty();
    }

    private File findTargetDirectory() throws IllegalFilePath {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String dateString = sdf.format(Calendar.getInstance().getTime());
        return new File(rootOutPath + "/date_" + dateString + "/from_" + file.getName() + "/to_" + numOfCars + "_cars");
    }

    private void mkdirIfNotExists(File dir) throws IllegalFilePath {
        if (!dir.exists()) {
            log.debug("Создание директории: {}", dir.getPath());
            if (!dir.mkdirs()) {
                throw new IllegalFilePath("Ошибка при создании директории " + dir.getPath());
            }
        }
    }
}
