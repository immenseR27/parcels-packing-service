package com.immenser.parcelspacking.validation;

import com.immenser.parcelspacking.util.validation.ContentValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentValidatorTest {

    @Test
    public void isContentValid_givenValidInput_shouldReturnTrue() {
        assertThat(ContentValidator.isContentValid(5, "55555")).isTrue();
    }

    @Test
    public void isContentValid_givenInvalidInput_shouldReturnFalse() {
        assertThat(ContentValidator.isContentValid(5, "55255")).isFalse();
    }
}
