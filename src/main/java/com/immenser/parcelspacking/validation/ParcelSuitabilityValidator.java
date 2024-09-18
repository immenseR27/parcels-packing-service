package com.immenser.parcelspacking.validation;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.Parcel;

public class ParcelSuitabilityValidator {

    public static boolean isParcelSuitable(Car car, int row, int firstFreeCell, Parcel parcel) {
        if (isParcelWidthSuitable(car, row, parcel) && isParcelHeightSuitable(row, parcel)) {
            return isParcelBottomSuitable(car, row, firstFreeCell, parcel);
        }
        return false;
    }

    private static boolean isParcelWidthSuitable(Car car, int row, Parcel parcel) {
        return parcel.getBottom() <= car.getFreeRowCellsCounters()[row];
    }

    private static boolean isParcelHeightSuitable(int row, Parcel parcel) {
        return row - parcel.getHeight() >= -1;
    }

    private static boolean isParcelBottomSuitable(Car car, int row, int firstFreeCell, Parcel parcel) {
        if (row == car.getHeight() - 1) {
            return true;
        }
        int bottomCounter = 0;
        for (int i = firstFreeCell; i < firstFreeCell + parcel.getBottom(); i++) {
            if (!car.getBody()[row + 1][i].isEmpty()) {
                bottomCounter++;
            }
        }
        return bottomCounter >= parcel.getBottom() / 2 + 1;
    }
}
