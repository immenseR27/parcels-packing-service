package com.immenser.parcelspacking.reading;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.reading.file.ParcelTxtReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelTxtReaderTest {

    @Test
    public void readParcels_givenValidInput_shouldReturnFullList() {
        ParcelTxtReader parcelTxtReader = new ParcelTxtReader();
        List<Parcel> redParcels = parcelTxtReader.read("src/test/resources/input/parcels/init_parcels_01.txt");
        List<Parcel> expectedParcels =
                List.of(
                        new Parcel(ParcelType.NINE),
                        new Parcel(ParcelType.SIX),
                        new Parcel(ParcelType.FIVE),
                        new Parcel(ParcelType.ONE),
                        new Parcel(ParcelType.ONE),
                        new Parcel(ParcelType.THREE)
                );

        assertThat(redParcels).isEqualTo(expectedParcels);
    }

    @Test
    public void readParcels_givenInvalidParcels_shouldReturnShortList() {
        ParcelTxtReader parcelTxtReader = new ParcelTxtReader();
        List<Parcel> redParcels = parcelTxtReader.read("src/test/resources/input/parcels/init_parcels_04.txt");
        List<Parcel> expectedParcels =
                List.of(
                        new Parcel(ParcelType.NINE),
                        new Parcel(ParcelType.SIX),
                        new Parcel(ParcelType.FIVE),
                        new Parcel(ParcelType.ONE),
                        new Parcel(ParcelType.ONE),
                        new Parcel(ParcelType.THREE)
                );

        assertThat(redParcels).isNotEqualTo(expectedParcels);
    }

    @Test
    public void readParcels_givenInvalidFile_shouldReturnEmptyList() {
        ParcelTxtReader parcelTxtReader = new ParcelTxtReader();
        List<Parcel> parcels = parcelTxtReader.read("no_such_file.txt");

        assertThat(parcels.isEmpty()).isTrue();
    }
}
