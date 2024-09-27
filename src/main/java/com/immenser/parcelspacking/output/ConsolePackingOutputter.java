package com.immenser.parcelspacking.output;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.ParcelType;

import java.util.List;

public class ConsolePackingOutputter implements PackingOutputter {

    /**
     * Метод вывода погрузки всех посылок по машинам.
     * <p>
     * Результат погрузки выводится на консоль.
     *
     * @param cars список погруженных машин.
     */
    @Override
    public void outputAllCarsPacking(List<Car> cars) {
        StringBuilder sb = new StringBuilder();
        cars.forEach(sb::append);
        System.out.println(sb);
    }

    /**
     * Метод вывода погрузки посылок в определенной машине.
     * <p>
     * Результат погрузки выводится на консоль.
     *
     * @param car машина для вывода содержимого.
     */
    @Override
    public void outputCarPacking(Car car) {
        System.out.println(car);
    }

    /**
     * Метод вывода количества посылок каждого типа в определенной машине.
     * <p>
     * Результат погрузки выводится на консоль.
     *
     * @param car машина для вывода содержимого.
     */
    @Override
    public void outputCarContentByTypes(Car car) {
        for (ParcelType type : car.getParcelsTypeCounters().keySet()) {
            System.out.println("Посылка " + type.getValue() + ": " + car.getParcelsTypeCounters().get(type) + " шт.");
        }
    }
}
