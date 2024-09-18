package com.immenser.parcelspacking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class Parcel {

    private String value;
    private List<Integer> rowWidths = new ArrayList<>();

    public Parcel(int value, List<Integer> rowWidths) {
        this.value = String.valueOf(value);
        this.rowWidths = rowWidths;
    }

    public int getHeight() {
        return rowWidths.size();
    }

    public int getTop() {
        return rowWidths.get(0);
    }

    public int getBottom() {
        return rowWidths.get(rowWidths.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Objects.equals(value, parcel.value) && Objects.equals(rowWidths, parcel.rowWidths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, rowWidths);
    }
}
