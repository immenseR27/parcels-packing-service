package com.immenser.parcelspacking.output;

import com.immenser.parcelspacking.algorithm.TightParcelPacker;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.service.CarService;
import com.immenser.parcelspacking.service.PackingService;
import com.immenser.parcelspacking.service.ParcelService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsolePackingOutputterTest {

    @Test
    public void outputAllCarsPacking_givenNotEnoughCars_ShouldOutputMessage() {
        PackingService packingService = new PackingService(new TightParcelPacker(), new ConsolePackingOutputter());
        ParcelService parcelService = new ParcelService();
        List<Car> cars = CarService.prepareEmptyCars(1);
        List<Parcel> parcels = parcelService.readParcels("src/test/resources/input/parcels/init_parcels_02.txt");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        packingService.packParcels(cars, parcels);

        String expectedOutput = "Недостаточно машин для погрузки всех посылок";
        String actualOutput = outContent.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void outputAllCarsPacking_givenEnoughCars_ShouldOutputPacking() {
        PackingService packingService = new PackingService(new TightParcelPacker(), new ConsolePackingOutputter());
        ParcelService parcelService = new ParcelService();
        List<Car> cars = CarService.prepareEmptyCars(2);
        List<Parcel> parcels = parcelService.readParcels("src/test/resources/input/parcels/init_parcels_02.txt");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        packingService.packParcels(cars, parcels);

        String expectedOutput =
                """
                        ++++++++
                        +777   +
                        +7777  +
                        +4444  +
                        +8888  +
                        +888822+
                        +555551+
                        ++++++++
                        ++++++++
                        +      +
                        +      +
                        +      +
                        +      +
                        +      +
                        +333333+
                        ++++++++""";

        String actualOutput = outContent.toString().trim();

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
