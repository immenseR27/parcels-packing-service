package com.immenser.parcelspacking.entity;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelTest {

    @Test
    public void testParcelHeight() {
        Parcel parcel = new Parcel(ParcelType.EIGHT);
        assertThat(parcel.getHeight()).isEqualTo(2);
    }

    @Test
    public void testParcelTopAndBottom() {
        Parcel parcel = new Parcel(ParcelType.SEVEN);
        assertThat(parcel.getTopWidth()).isEqualTo(3);
        assertThat(parcel.getBottom()).isEqualTo(4);
    }
}
