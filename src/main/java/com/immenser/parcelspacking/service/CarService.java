package com.immenser.parcelspacking.service;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.output.PackingOutputter;
import com.immenser.parcelspacking.reading.json.CarJsonReader;
import com.immenser.parcelspacking.reading.json.JsonReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarService {

    private final PackingOutputter packingOutputter;

    public CarService(PackingOutputter packingOutputter) {
        this.packingOutputter = packingOutputter;
    }

    /**
     * Метод для создания списка пустых машин заданного размера.
     *
     * @param numOfCars размер списка.
     * @return Список пустых машин.
     */
    public static List<Car> prepareEmptyCars(int numOfCars) {
        return IntStream.range(0, numOfCars)
                .mapToObj(i -> new Car())
                .collect(Collectors.toList());
    }

    /**
     * Метод для вывода погрузки посылок в машине из json-файла.
     *
     * @param fileName имя json-файла.
     */
    public void showParcelsByCarFromJsonFile(String fileName) {
        JsonReader<Car> jsonReader = new CarJsonReader(fileName);
        Optional<Car> optionalCar = jsonReader.read();
        optionalCar.ifPresent(car -> {
                    packingOutputter.outputCarContentByTypes(car);
                    packingOutputter.outputCarPacking(car);
                }
        );
    }
}
