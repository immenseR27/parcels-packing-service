package com.immenser.parcelspacking.dto;

import com.immenser.parcelspacking.entity.parcel.ParcelLayer;
import lombok.Getter;

import java.util.List;

@Getter
public class ParcelDto {
    private final int startLine;
    private final int value;
    private final List<ParcelLayer> layers;

    public ParcelDto(int startLine, int value, List<ParcelLayer> layers) {
        this.startLine = startLine;
        this.value = value;
        this.layers = layers;
    }
}
