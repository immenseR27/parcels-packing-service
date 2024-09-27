package com.immenser.parcelspacking.entity;

import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.exception.ImpossiblePackingException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarTest {

    @Test
    public void testEqualsCar() {
        Car car1 = new Car();
        car1.getBody()[5][0] = new Car.Cell("1");
        Car car2 = new Car();
        car2.getBody()[5][0] = new Car.Cell("1");
        Car car3 = new Car();
        car3.getBody()[5][0] = new Car.Cell("2");
        car3.getBody()[5][1] = new Car.Cell("2");

        assertThat(car1).isEqualTo(car2);
        assertThat(car1).isNotEqualTo(car3);

    }

    @Test
    public void putParcelIfSuitable_givenSuitableParcel_putParcel() {
        Parcel parcel1 = new Parcel(ParcelType.SEVEN);
        Car car = new Car();
        car.putParcelIfSuitable(parcel1);

        String expectedBody =
                """
                        ++++++++
                        +      +
                        +      +
                        +      +
                        +      +
                        +777   +
                        +7777  +
                        ++++++++""";

        assertThat(car.toString().trim()).isEqualTo(expectedBody);
    }

    @Test
    public void putParcelIfSuitable_givenUnsuitableParcel_throwImpossiblePackingException() {
        Parcel parcel1 = new Parcel(ParcelType.TWO);
        Car car = new Car();
        car.putParcelIfSuitable(parcel1);
        Parcel parcel2 = new Parcel(ParcelType.FIVE);
        assertThatThrownBy(() -> car.putParcelIfSuitable(parcel2)).isInstanceOf(ImpossiblePackingException.class);
    }

}
