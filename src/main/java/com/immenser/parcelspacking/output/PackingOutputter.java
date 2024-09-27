package com.immenser.parcelspacking.output;

import com.immenser.parcelspacking.entity.Car;

import java.util.List;

public interface PackingOutputter {
    /**
     * Метод вывода погрузки всех посылок по машинам.
     *
     * @param cars список погруженных машин.
     */
    void outputAllCarsPacking(List<Car> cars);

    /**
     * Метод вывода погрузки посылок в определенной машине.
     *
     * @param car машина для вывода содержимого.
     */
    void outputCarPacking(Car car);

    /**
     * Метод вывода количества посылок каждого типа в определенной машине.
     *
     * @param car машина для вывода содержимого.
     */
    void outputCarContentByTypes(Car car);
}
