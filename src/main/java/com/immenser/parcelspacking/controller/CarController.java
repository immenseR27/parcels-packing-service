package com.immenser.parcelspacking.controller;

import com.immenser.parcelspacking.service.CarService;

public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Метод вывода погрузки посылок в машине из json-файла.
     *
     * @param fileName имя json-файла с погруженной машиной.
     */
    public void showParcelsByCarFromJsonFile(String fileName) {
        carService.showParcelsByCarFromJsonFile(fileName);
    }
}
