package com.immenser.parcelspacking.reading.json;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.immenser.parcelspacking.entity.Car;
import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class CarJsonReader implements JsonReader<Car> {

    private final String fileName;

    public CarJsonReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод чтения объектов типа &lt;T&gt; из json.
     * <p>
     * Метод считывает сериализованную машину из json-файла и создает на ее основе объект класса Car.
     *
     * @return Optional.of() - при успешной десериализации.
     * <p>
     * Optional.empty() - в случае возникновения ошибки при чтении файла или при парсинге json.
     */
    @Override
    public Optional<Car> read() {
        log.debug("Чтение файла {}...", fileName);
        try (FileReader fr = new FileReader(fileName)) {
            Gson gson = new Gson();
            return Optional.of(gson.fromJson(fr, Car.class));
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}", fileName);
            System.exit(1);
        } catch (JsonParseException e) {
            log.error("Невалидный json-файл: {}", fileName);
            System.exit(1);
        }
        log.debug("Файл успешно прочитан.");
        return Optional.empty();
    }
}
