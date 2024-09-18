package com.immenser.parcelspacking.validation;

import com.immenser.parcelspacking.entity.Parcel;
import com.immenser.parcelspacking.exception.WrongParcelException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParcelValidator {

    private static final Set<Parcel> TEMPLATES = new HashSet<>();

    static {
        TEMPLATES.add(new Parcel(1, new ArrayList<>(List.of(1))));
        TEMPLATES.add(new Parcel(2, new ArrayList<>(List.of(2))));
        TEMPLATES.add(new Parcel(3, new ArrayList<>(List.of(3))));
        TEMPLATES.add(new Parcel(4, new ArrayList<>(List.of(4))));
        TEMPLATES.add(new Parcel(5, new ArrayList<>(List.of(5))));
        TEMPLATES.add(new Parcel(6, new ArrayList<>(List.of(3, 3))));
        TEMPLATES.add(new Parcel(7, new ArrayList<>(List.of(3, 4))));
        TEMPLATES.add(new Parcel(8, new ArrayList<>(List.of(4, 4))));
        TEMPLATES.add(new Parcel(9, new ArrayList<>(List.of(3, 3, 3))));
    }

    public static boolean isFormValid(Parcel parcel) {
        return TEMPLATES.contains(parcel);
    }

    public static boolean isContentValid(Parcel parcel, String line) throws WrongParcelException {
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != parcel.getValue().charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
