package com.immenser.parcelspacking.service;

import com.immenser.parcelspacking.algorithm.ParcelPacker;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;
import com.immenser.parcelspacking.output.PackingOutputter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class PackingService {
    private final ParcelPacker parcelPacker;
    private final PackingOutputter packingOutputter;

    public PackingService(ParcelPacker parcelPacker, PackingOutputter packingOutputter) {
        this.parcelPacker = parcelPacker;
        this.packingOutputter = packingOutputter;
    }

    /**
     * Метод для погрузки посылок по машинам и вывода погрузки.
     *
     * @param cars    список пустых машин.
     * @param parcels список посылок для погрузки.
     */
    public void packParcels(List<Car> cars, List<Parcel> parcels) {
        try {
            parcelPacker.packParcels(cars, parcels);
            packingOutputter.outputAllCarsPacking(cars);
        } catch (ImpossiblePackingException e) {
            log.error(e.getMessage());
            System.exit(1);
        }
    }
}
