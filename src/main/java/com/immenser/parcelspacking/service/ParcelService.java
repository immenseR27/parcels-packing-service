package com.immenser.parcelspacking.service;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.reading.file.FileReader;
import com.immenser.parcelspacking.reading.file.ParcelTxtReader;

import java.util.Comparator;
import java.util.List;

public class ParcelService {

    /**
     * Метод сортировки посылок по ширине дна, верха и высоте.
     *
     * @param parcels список посылок для сортировки.
     */
    public static void sortParcels(List<Parcel> parcels) {
        parcels.sort(Comparator.comparing(Parcel::getBottom).reversed()
                .thenComparing(Comparator.comparing(Parcel::getTopWidth).reversed()
                        .thenComparing(Comparator.comparing(Parcel::getHeight).reversed())));
    }

    /**
     * Метод чтения посылок из файла.
     *
     * @param fileName имя файла с посылками.
     * @return список прочитанных посылок.
     */
    public List<Parcel> readParcels(String fileName) {
        FileReader<Parcel> parcelFileReader = new ParcelTxtReader();
        return parcelFileReader.read(fileName);
    }
}
