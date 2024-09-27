package com.immenser.parcelspacking.algorithm;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;
import com.immenser.parcelspacking.service.ParcelService;
import lombok.extern.log4j.Log4j2;

import java.util.Comparator;
import java.util.List;

@Log4j2
public class UniformParcelPacker implements ParcelPacker {

    /**
     * Метод погрузки посылок.
     * <p>
     * Отсортированные посылки грузятся в машины параллельно. Каждая следующая посылка грузится в наименее загруженную машину.
     *
     * @param cars    список пустых машин.
     * @param parcels список посылок для погрузки.
     * @return список загруженных машин.
     * @throws ImpossiblePackingException если машин недостаточно.
     */
    @Override
    public List<Car> packParcels(List<Car> cars, List<Parcel> parcels) throws ImpossiblePackingException {
        log.trace("Сортировка посылок...");
        ParcelService.sortParcels(parcels);
        log.trace("Посылки отсортированы.");
        log.trace("Идет погрузка...");
        for (Parcel parcel : parcels) {
            cars.sort(Comparator.comparing(Car::getUsedCapacity));
            cars.get(0).putParcelIfSuitable(parcel);
        }
        log.trace("Погрузка завершена");
        return cars;
    }
}
