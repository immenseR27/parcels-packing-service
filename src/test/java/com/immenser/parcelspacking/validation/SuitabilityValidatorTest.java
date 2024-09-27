package com.immenser.parcelspacking.validation;

import com.google.gson.Gson;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.util.validation.SuitabilityValidator;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SuitabilityValidatorTest {

    @Test
    public void isParcelSuitable_givenSuitableParcel_shouldReturnTrue() throws IOException {
        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_1.json")) {
            Gson gson = new Gson();
            Car car = gson.fromJson(fr, Car.class);
            Car.Cell cell = car.getBody()[4][4];
            System.out.println(car);
            Parcel parcel = new Parcel(ParcelType.ONE);
            assertThat(SuitabilityValidator.isParcelSuitable(car, cell, parcel)).isTrue();
        }
    }

    @Test
    public void isParcelSuitable_givenUnsuitableParcelBottom_shouldReturnFalse() throws IOException {
        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_1.json")) {
            Gson gson = new Gson();
            Car car = gson.fromJson(fr, Car.class);
            Car.Cell cell = car.getBody()[4][4];
            System.out.println(car);
            Parcel parcel = new Parcel(ParcelType.TWO);
            assertThat(SuitabilityValidator.isParcelSuitable(car, cell, parcel)).isFalse();
        }
    }

    @Test
    public void isParcelSuitable_givenUnsuitableParcelWidth_shouldReturnFalse() throws IOException {
        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_1.json")) {
            Gson gson = new Gson();
            Car car = gson.fromJson(fr, Car.class);
            Car.Cell cell = car.getBody()[4][4];
            System.out.println(car);
            Parcel parcel = new Parcel(ParcelType.THREE);
            assertThat(SuitabilityValidator.isParcelSuitable(car, cell, parcel)).isFalse();
        }
    }

    @Test
    public void isParcelSuitable_givenUnsuitableParcelHeight_shouldReturnFalse() throws IOException {
        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_1.json")) {
            Gson gson = new Gson();
            Car car = gson.fromJson(fr, Car.class);
            Car.Cell cell = car.getBody()[1][0];
            System.out.println(car);
            Parcel parcel = new Parcel(ParcelType.NINE);
            assertThat(SuitabilityValidator.isParcelSuitable(car, cell, parcel)).isFalse();
        }
    }
}
