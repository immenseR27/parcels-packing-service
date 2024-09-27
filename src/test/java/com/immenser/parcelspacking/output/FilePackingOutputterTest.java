package com.immenser.parcelspacking.output;

import com.google.gson.Gson;
import com.immenser.parcelspacking.algorithm.UniformParcelPacker;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.service.CarService;
import com.immenser.parcelspacking.service.PackingService;
import com.immenser.parcelspacking.service.ParcelService;
import com.immenser.parcelspacking.util.path.DatePathConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class FilePackingOutputterTest {

    @Test
    public void outputAllCarsPacking_givenNotEnoughCars_ShouldOutputMessage() {

        PackingService packingService = new PackingService(new UniformParcelPacker(), new ConsolePackingOutputter());
        ParcelService parcelService = new ParcelService();
        List<Car> cars = CarService.prepareEmptyCars(1);
        List<Parcel> parcels = parcelService.readParcels("src/test/resources/input/parcels/init_parcels_02.txt");

        packingService.packParcels(cars, parcels);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String expectedOutput = "Невозможно распределить посылки равномерно";
        String actualOutput = outContent.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void outputAllCarsPacking_givenEnoughCars_ShouldOutputPacking() throws IOException {
        PackingService packingService = new PackingService(new UniformParcelPacker(),
                new FilePackingOutputter(
                        new DatePathConstructor("src/test/resources/input/parcels/init_parcels_02.txt",
                                "src/test/resources/output", 2)));

        ParcelService parcelService = new ParcelService();
        List<Car> cars = CarService.prepareEmptyCars(2);
        List<Parcel> parcels = parcelService.readParcels("src/test/resources/input/parcels/init_parcels_02.txt");

        packingService.packParcels(cars, parcels);

        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_1.json")) {
            Gson gson = new Gson();
            Car car1 = gson.fromJson(fr, Car.class);
            assertThat(car1).isEqualTo(cars.get(0));
        }

        try (FileReader fr = new FileReader("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars/car_2.json")) {
            Gson gson = new Gson();
            Car car2 = gson.fromJson(fr, Car.class);
            assertThat(car2).isEqualTo(cars.get(1));
        }
    }
}
