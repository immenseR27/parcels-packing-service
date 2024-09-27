package com.immenser.parcelspacking.reading;

import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.reading.json.CarJsonReader;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CarJsonReaderTest {
    @Test
    public void readCar_givenValidInput_shouldReturnNotEmptyOptional() {
        CarJsonReader carJsonReader = new CarJsonReader("src/test/resources/input/cars/packed_car.json");
        Optional<Car> optionalCar = carJsonReader.read();
        assertThat(optionalCar.isEmpty()).isFalse();
    }

    @Test
    public void readCar_givenInvalidFile_shouldReturnNotEmptyOptional() {
        CarJsonReader carJsonReader = new CarJsonReader("no_such_file.json");
        Optional<Car> optionalCar = carJsonReader.read();
        assertThat(optionalCar.isEmpty()).isTrue();
    }

    @Test
    public void readParcels_givenInvalidJsonFile_shouldReturnEmptyList() {
        CarJsonReader carJsonReader = new CarJsonReader("src/test/resources/input/parcels/init_parcels_01.txt");
        Optional<Car> optionalCar = carJsonReader.read();
        assertThat(optionalCar.isEmpty()).isTrue();
    }
}
