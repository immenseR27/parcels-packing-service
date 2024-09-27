package com.immenser.parcelspacking.util.validation;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;

public class SuitabilityValidator {

    /**
     * Метод, проверяющий, возможно ли погрузить посылку в определенное место конкретной машины.
     *
     * @param car    машина для погрузки.
     * @param cell   проверяемая ячейка.
     * @param parcel посылка, которую необходимо погрузить.
     * @return true - если посылка подходит, иначе - false.
     */
    public static boolean isParcelSuitable(Car car, Car.Cell cell, Parcel parcel) {
        if (isParcelWidthSuitable(car, cell.getRow(), parcel) && isParcelHeightSuitable(cell.getRow(), parcel)) {
            return isParcelBottomSuitable(car, cell, parcel);
        }
        return false;
    }

    private static boolean isParcelWidthSuitable(Car car, int row, Parcel parcel) {
        return parcel.getBottom() <= car.getFreeRowCellsCounters()[row];
    }

    private static boolean isParcelHeightSuitable(int row, Parcel parcel) {
        return row - parcel.getHeight() >= -1;
    }

    private static boolean isParcelBottomSuitable(Car car, Car.Cell cell, Parcel parcel) {
        if (cell.getRow() == car.getHeight() - 1) {
            return true;
        }
        int bottomCounter = 0;
        for (int i = cell.getColumn(); i < cell.getColumn() + parcel.getBottom(); i++) {
            if (!car.getBody()[cell.getRow() + 1][i].isEmpty()) {
                bottomCounter++;
            }
        }
        return bottomCounter >= parcel.getBottom() / 2 + 1;
    }
}
