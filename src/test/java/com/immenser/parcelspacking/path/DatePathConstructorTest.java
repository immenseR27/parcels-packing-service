package com.immenser.parcelspacking.path;

import com.immenser.parcelspacking.util.path.DatePathConstructor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DatePathConstructorTest {

    @Test
    public void findTargetDirectory_givenValidInput_shouldReturnOptionalDirectory() {
        DatePathConstructor datePathConstructor = new DatePathConstructor(
                "src/test/resources/input/parcels/init_parcels_02.txt",
                "src/test/resources/output", 2);

        assertThat(datePathConstructor.constructPath()).isEqualTo(Optional.of(
                new File("src/test/resources/output/date_2024_09_26/from_init_parcels_02.txt/to_2_cars")));
    }

    @Test
    public void findTargetDirectory_givenInvalidInput_shouldReturnEmptyOptional() {
        DatePathConstructor datePathConstructor = new DatePathConstructor(
                "zdz*+8zen5-",
                "src/test/resources/output", 2);

        assertThat(datePathConstructor.constructPath()).isEqualTo(Optional.empty());
    }
}
