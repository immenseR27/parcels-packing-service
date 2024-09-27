package com.immenser.parcelspacking.controller;

import com.immenser.parcelspacking.algorithm.TightParcelPacker;
import com.immenser.parcelspacking.algorithm.UniformParcelPacker;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.IncorrectAlgorithmException;
import com.immenser.parcelspacking.output.PackingOutputter;
import com.immenser.parcelspacking.service.PackingService;

import java.util.List;

public class PackingController {

    public static final int TIGHT_PACKER = 1;
    public static final int UNIFORM_PACKER = 2;

    private final PackingOutputter packingOutputter;

    public PackingController(PackingOutputter packingOutputter) {
        this.packingOutputter = packingOutputter;
    }

    /**
     * Метод погрузки посылок по заданному алгоритму.
     *
     * @param parcels   список посылок для погрузки.
     * @param cars      список пустых машин.
     * @param algorithm номер алгоритма.
     * @throws IncorrectAlgorithmException если алгоритм погрузки указан неверно.
     */
    public void packParcelsByAlgorithm(List<Parcel> parcels, List<Car> cars, int algorithm) throws IncorrectAlgorithmException {
        PackingService packingService = switch (algorithm) {
            case TIGHT_PACKER -> new PackingService(new TightParcelPacker(), packingOutputter);
            case UNIFORM_PACKER -> new PackingService(new UniformParcelPacker(), packingOutputter);
            default -> throw new IncorrectAlgorithmException("Неверно указан алгоритм. Попробуйте еще раз.\n");
        };
        packingService.packParcels(cars, parcels);
    }

}
