package com.immenser.parcelspacking.entity.parcel;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ParcelLayer {
    private final int width;

    public ParcelLayer(int width) {
        this.width = width;
    }
}
