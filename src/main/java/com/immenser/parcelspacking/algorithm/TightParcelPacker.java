package com.immenser.parcelspacking.algorithm;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;
import com.immenser.parcelspacking.service.ParcelService;
import lombok.extern.log4j.Log4j2;

import java.util.Iterator;
import java.util.List;

@Log4j2
public class TightParcelPacker implements ParcelPacker {

    /**
     * Метод погрузки посылок.
     * <p>
     * Отсортированные посылки грузятся в машины последовательно. Переход к погрузке следующей машины производится только
     * после того как в текущей машине закончится место для оставшихся посылок.
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
        Iterator<Car> iterator = cars.iterator();
        log.trace("Идет погрузка...");
        while (!parcels.isEmpty() && iterator.hasNext()) {
            Car car = iterator.next();
            car.fillBody(parcels);
        }

        if (!parcels.isEmpty()) {
            throw new ImpossiblePackingException("Недостаточно машин для погрузки всех посылок");
        }
        log.trace("Погрузка завершена");
        return cars;
    }
}
