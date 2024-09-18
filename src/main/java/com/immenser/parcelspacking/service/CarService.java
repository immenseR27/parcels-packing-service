package com.immenser.parcelspacking.service;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.Parcel;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    public static String distributeParcelsToCars(List<Parcel> parcels) {
        List<Car> cars = new ArrayList<>();
        while (!parcels.isEmpty()) {
            Car car = new Car();
            cars.add(car);
            car.fillBody(parcels);
        }
        return getStringParcelsDistribution(cars);
    }

    private static String getStringParcelsDistribution(List<Car> cars) {
        StringBuilder sb = new StringBuilder();
        cars.forEach(sb::append);
        return sb.toString();
    }
}
