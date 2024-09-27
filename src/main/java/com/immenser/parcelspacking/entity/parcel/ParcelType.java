package com.immenser.parcelspacking.entity.parcel;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum ParcelType {

    ONE(1, new ArrayList<>(List.of(new ParcelLayer(1)))),
    TWO(2, new ArrayList<>(List.of(new ParcelLayer(2)))),
    THREE(3, new ArrayList<>(List.of(new ParcelLayer(3)))),
    FOUR(4, new ArrayList<>(List.of(new ParcelLayer(4)))),
    FIVE(5, new ArrayList<>(List.of(new ParcelLayer(5)))),
    SIX(6, new ArrayList<>(List.of(new ParcelLayer(3), new ParcelLayer(3)))),
    SEVEN(7, new ArrayList<>(List.of(new ParcelLayer(3), new ParcelLayer(4)))),
    EIGHT(8, new ArrayList<>(List.of(new ParcelLayer(4), new ParcelLayer(4)))),
    NINE(9, new ArrayList<>(List.of(new ParcelLayer(3), new ParcelLayer(3), new ParcelLayer(3)))),
    ;

    private final int value;
    private final List<ParcelLayer> layers;

    ParcelType(int value, List<ParcelLayer> layers) {
        this.value = value;
        this.layers = layers;
    }
}
