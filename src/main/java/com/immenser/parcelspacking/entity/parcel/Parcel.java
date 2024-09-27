package com.immenser.parcelspacking.entity.parcel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Parcel {

    private final ParcelType type;

    /**
     * @return высота посылки.
     */
    public int getHeight() {
        return type.getLayers().size();
    }

    /**
     * @return ширина поверхности посылки.
     */
    public int getTopWidth() {
        return type.getLayers().get(0).getWidth();
    }

    /**
     * @return ширина дна посылки.
     */
    public int getBottom() {
        return type.getLayers().get(type.getLayers().size() - 1).getWidth();
    }

}
