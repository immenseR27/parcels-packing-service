package com.immenser.parcelspacking.algorithm;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;

import java.util.List;

public interface ParcelPacker {
    /**
     * Метод погрузки посылок.
     *
     * @param cars    список пустых машин.
     * @param parcels список посылок для погрузки.
     * @return список загруженных машин.
     * @throws ImpossiblePackingException если машин недостаточно.
     */
    List<Car> packParcels(List<Car> cars, List<Parcel> parcels) throws ImpossiblePackingException;
}