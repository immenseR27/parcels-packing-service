package com.immenser.parcelspacking.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelTest {

    @Test
    public void testParcelHeight() {
        Parcel parcel = new Parcel(8, List.of(4, 4));
        assertEquals(2, parcel.getHeight());
    }

    @Test
    public void testParcelTopAndBottom() {
        Parcel parcel = new Parcel(7, List.of(3, 4));
        assertAll(
                () -> assertEquals(3, parcel.getTop()),
                () -> assertEquals(4, parcel.getBottom())
        );
    }

    @Test
    public void testParcelBottom() {
        Parcel parcel = new Parcel(7, List.of(3, 4));
        assertEquals(4, parcel.getBottom());
    }

    @Test
    public void testFormValidation() {
        Parcel parcel1 = new Parcel(3, List.of(3));
        Parcel parcel2 = new Parcel(3, List.of(1, 2));
        assertAll(
                () -> assertTrue(Parcel.isValidForm(parcel1)),
                () -> assertFalse(Parcel.isValidForm(parcel2))
        );
    }

    @Test
    public void testEqualsParcels() {
        Parcel parcel1 = new Parcel(7, List.of(3, 4));
        Parcel parcel2 = new Parcel(7, List.of(3, 4));
        Parcel parcel3 = new Parcel(6, List.of(3, 3));
        assertAll(
                () -> assertEquals(parcel1, parcel2),
                () -> assertNotEquals(parcel1, parcel3)
        );
    }
}
