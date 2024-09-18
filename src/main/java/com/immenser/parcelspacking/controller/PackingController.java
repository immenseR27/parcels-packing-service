package com.immenser.parcelspacking.controller;

import com.immenser.parcelspacking.entity.Parcel;
import com.immenser.parcelspacking.exception.IncorrectAlgorithmException;
import com.immenser.parcelspacking.service.CarService;
import com.immenser.parcelspacking.service.ParcelService;

import java.util.List;
import java.util.Optional;

public class PackingController {

    public static final int SIMPLE_ALGORITHM = 1;
    public static final int SORTING_ALGORITHM = 2;

    public static Optional<List<Parcel>> readParcelsFromFile(String fileName) {
        return ParcelService.readParcelsFromFile(fileName);
    }

    public static String distributeParcelsByAlgorithm(List<Parcel> parcels, int algorithm) throws IncorrectAlgorithmException {
        if (algorithm == SIMPLE_ALGORITHM || algorithm == SORTING_ALGORITHM) {
            if (algorithm == SORTING_ALGORITHM) {
                ParcelService.sortParcels(parcels);
            }
            return CarService.distributeParcelsToCars(parcels);
        } else {
            throw new IncorrectAlgorithmException("Неверно указан алгоритм. Попробуйте еще раз.\n");
        }
    }
}
