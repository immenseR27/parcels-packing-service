package com.immenser.parcelspacking.output;

import com.google.gson.Gson;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.util.path.PathConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

@Log4j2
public class FilePackingOutputter implements PackingOutputter {

    private final PathConstructor pathConstructor;

    public FilePackingOutputter(PathConstructor pathConstructor) {
        this.pathConstructor = pathConstructor;
    }

    /**
     * Метод вывода погрузки всех посылок по машинам.
     * <p>
     * Результат погрузки сохраняется в json-файле.
     *
     * @param cars список погруженных машин.
     */
    @Override
    public void outputAllCarsPacking(List<Car> cars) {
        log.debug("Запись погруженных машин в json-файлы...");
        Gson gson = new Gson();
        for (int i = 0; i < cars.size(); i++) {
            int carNum = i + 1;
            Optional<File> optionalDir = pathConstructor.constructPath();
            int finalI = i;
            optionalDir.ifPresent(dir -> {
                String filename = dir.getPath() + "/car_" + carNum + ".json";
                try (Writer writer = new FileWriter(filename)) {
                    gson.toJson(cars.get(finalI), writer);
                } catch (IOException e) {
                    log.error("Ошибка записи в {}", filename);
                    System.exit(1);
                }
            });
        }
        log.debug("Запись успешно завершена.");
    }

    @Override
    public void outputCarPacking(Car car) {

    }

    @Override
    public void outputCarContentByTypes(Car car) {

    }
}
