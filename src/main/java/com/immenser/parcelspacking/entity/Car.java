package com.immenser.parcelspacking.entity;

import com.immenser.parcelspacking.validation.ParcelSuitabilityValidator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.IntStream;

@Getter
@Setter
public class Car {

    private final int width = 6;
    private final int height = 6;

    private Cell[][] body = new Cell[width][height];
    private int[] freeRowCellsCounters = new int[height];

    {
        Arrays.fill(freeRowCellsCounters, width);
        body = Arrays.stream(body)
                .map(cells -> IntStream.range(0, cells.length)
                        .mapToObj(j -> new Cell())
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    public int getFirstFreeCellInRow(int row) {
        int firstFreeCell = width - 1;
        for (int i = 0; i < width; i++) {
            if (body[row][i].isEmpty()) {
                firstFreeCell = i;
                break;
            }
        }
        return firstFreeCell;
    }

    public void fillBody(List<Parcel> parcels) {
        int currRow = fillRow(parcels, height - 1);
        while (currRow >= 0) {
            currRow = fillRow(parcels, currRow);
        }
    }

    private int fillRow(List<Parcel> parcels, int row) {
        Iterator<Parcel> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Parcel parcel = iterator.next();
            int firstFreeCell = getFirstFreeCellInRow(row);
            if (ParcelSuitabilityValidator.isParcelSuitable(this, row, firstFreeCell, parcel)) {
                putParcel(row, firstFreeCell, parcel);
                iterator.remove();
            }
        }
        return --row;
    }

    public void putParcel(int row, int firstFreeCell, Parcel parcel) {
        for (int i = parcel.getHeight() - 1; i >= 0; i--) {
            for (int j = firstFreeCell; j < firstFreeCell + parcel.getRowWidths().get(i); j++) {
                body[row + i - parcel.getHeight() + 1][j].value = parcel.getValue();
                freeRowCellsCounters[row + i - parcel.getHeight() + 1]--;
            }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.deepEquals(body, car.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, Arrays.deepHashCode(body), Arrays.hashCode(freeRowCellsCounters));
    }

    public class Cell{
        private String value;

        public Cell(){
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
