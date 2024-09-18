package com.immenser.parcelspacking.service;

import com.immenser.parcelspacking.exception.WrongParcelException;
import com.immenser.parcelspacking.entity.Parcel;
import com.immenser.parcelspacking.validation.ParcelValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ParcelService {

    public static Optional<List<Parcel>> readParcelsFromFile(String fileName) {
        List<Parcel> parcels = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            parcels = new LinkedList<>();
            Parcel parcel = new Parcel();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    addParcelToParcels(parcel, parcels);
                    parcel = new Parcel();
                } else {
                    addParcelLayer(line, parcel);
                }
            }
            addParcelToParcels(parcel, parcels);
        } catch (WrongParcelException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла " + fileName + ". Попробуйте снова.");
        }
        return Optional.ofNullable(parcels);
    }

    private static void addParcelLayer(String line, Parcel parcel) throws WrongParcelException {
        parcel.setValue(line.substring(0, 1));
        if (ParcelValidator.isContentValid(parcel, line)) {
            parcel.getRowWidths().add(line.length());
        }
        else {
            throw new WrongParcelException("Ошибка в исходном файле: Некорректное содержимое посылки. Посылка будет проигнорирована.");
        }
    }

    private static void addParcelToParcels(Parcel parcel, List<Parcel> parcels) throws WrongParcelException {
        if (ParcelValidator.isFormValid(parcel)) {
            parcels.add(parcel);
        } else {
            throw new WrongParcelException("Ошибка в исходном файле: Некорректная форма посылки. Посылка будет проигнорирована.");
        }
    }

    public static void sortParcels(List<Parcel> parcels) {
        parcels.sort(Comparator.comparing(Parcel::getBottom).reversed()
                .thenComparing(Comparator.comparing(Parcel::getTop).reversed()
                        .thenComparing(Comparator.comparing(Parcel::getHeight).reversed())));
    }
}
