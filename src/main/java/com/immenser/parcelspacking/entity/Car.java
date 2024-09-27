package com.immenser.parcelspacking.entity;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;
import com.immenser.parcelspacking.util.validation.SuitabilityValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
public class Car {

    private final int width = 6;
    private final int height = 6;

    private Cell[][] body = new Cell[width][height];
    private int[] freeRowCellsCounters = new int[height];
    private Map<ParcelType, Integer> parcelsTypeCounters;
    private int usedCapacity;

    public Car() {
        Arrays.fill(freeRowCellsCounters, width);
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                body[i][j] = new Cell(i, j);
            }
        }
        parcelsTypeCounters = new TreeMap<>();
        usedCapacity = 0;
    }

    /**
     * Метод последовательного заполнения тела машины посылками.
     *
     * @param parcels список посылок для погрузки.
     */
    public void fillBody(List<Parcel> parcels) {
        int currRow = fillRow(getHeight() - 1, parcels);
        while (currRow >= 0) {
            currRow = fillRow(currRow, parcels);
        }
    }

    private int fillRow(int row, List<Parcel> parcels) {
        Iterator<Parcel> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Parcel parcel = iterator.next();
            int firstFreeCell = getFirstFreeCellInRow(row);
            if (SuitabilityValidator.isParcelSuitable(this, body[row][firstFreeCell], parcel)) {
                putParcel(row, firstFreeCell, parcel);
                iterator.remove();
            }
        }
        return --row;
    }

    private int getFirstFreeCellInRow(int row) {
        int firstFreeCell = width - 1;
        for (int i = 0; i < width; i++) {
            if (body[row][i].isEmpty()) {
                firstFreeCell = i;
                break;
            }
        }
        return firstFreeCell;
    }

    /**
     * Метод упаковки посылки в машину, если она подходит по габаритам.
     *
     * @param parcel посылка, которую необходимо погрузить.
     */
    public void putParcelIfSuitable(Parcel parcel) throws ImpossiblePackingException {
        Cell startCell = findBestSuitablePlace(parcel);
        if (startCell != null) {
            putParcel(startCell.row, startCell.column, parcel);
        } else throw new ImpossiblePackingException("Невозможно распределить посылки равномерно.");
    }

    private Cell findBestSuitablePlace(Parcel parcel) {
        for (int i = height - 1; i >= 0; i--) {
            int j = getFirstFreeCellInRow(i);
            if (SuitabilityValidator.isParcelSuitable(this, body[i][j], parcel)) {
                return body[i][j];
            }
        }
        return null;
    }

    private void putParcel(int row, int column, Parcel parcel) {
        for (int i = parcel.getHeight() - 1; i >= 0; i--) {
            for (int j = column; j < column + parcel.getType().getLayers().get(i).getWidth(); j++) {
                body[row + i - parcel.getHeight() + 1][j].value = String.valueOf(parcel.getType().getValue());
                freeRowCellsCounters[row + i - parcel.getHeight() + 1]--;
            }
        }
        increaseTypeCounter(parcel.getType());
        usedCapacity += parcel.getType().getValue();
    }

    private void increaseTypeCounter(ParcelType type) {
        if (!(parcelsTypeCounters.putIfAbsent(type, 1) == null)) {
            parcelsTypeCounters.put(type, parcelsTypeCounters.get(type) + 1);
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("+".repeat(width + 2));
        for (Cell[] row : body) {
            StringBuilder sb = new StringBuilder();
            sb.append("+");
            for (int j = 0; j < body[0].length; j++) {
                sb.append(row[j].value);
            }
            sb.append("+");
            sj.add(sb.toString());
        }
        sj.add("+".repeat(width + 2));
        sj.add("");
        return sj.toString();
    }

    @Getter
    @EqualsAndHashCode
    public static class Cell {
        private int row;
        private int column;
        private String value;

        private Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.value = " ";
        }

        public Cell(String value) {
            this.value = value;
        }

        public boolean isEmpty() {
            return value.equals(" ");
        }
    }
}
