package com.immenser.parcelspacking.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testEqualsCar() {
        Car car1 = new Car();
        car1.getBody()[5][0] = car1.new Cell("1");
        Car car2 = new Car();
        car2.getBody()[5][0] = car2.new Cell("1");
        Car car3 = new Car();
        car3.getBody()[5][0] = car3.new Cell("2");
        car3.getBody()[5][1] = car3.new Cell("2");
        assertAll(
                () -> assertEquals(car1, car2),
                () -> assertNotEquals(car1, car3)
        );
    }

    @Test
    public void testPutParcel() {
        Parcel parcel = new Parcel(7, List.of(3, 4));
        Car car1 = new Car();
        Car.Cell[][] body = {
                {car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" ")},
                {car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" ")},
                {car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" ")},
                {car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" ")},
                {car1.new Cell("7"), car1.new Cell("7"), car1.new Cell("7"), car1.new Cell(" "), car1.new Cell(" "), car1.new Cell(" ")},
                {car1.new Cell("7"), car1.new Cell("7"), car1.new Cell("7"), car1.new Cell("7"), car1.new Cell(" "), car1.new Cell(" ")}
        };
        car1.setBody(body);
        Car car2 = new Car();
        car2.putParcel(6, 1, parcel);
        assertEquals(car1, car2);
    }
}
