package com.immenser.parcelspacking.controller;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.service.ParcelService;

import java.util.List;

public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    /**
     * Метод чтения посылок из файла.
     *
     * @param fileName имя файла с посылками.
     * @return список прочитанных посылок.
     */
    public List<Parcel> readParcelsFromFile(String fileName) {
        return parcelService.readParcels(fileName);
    }
}
