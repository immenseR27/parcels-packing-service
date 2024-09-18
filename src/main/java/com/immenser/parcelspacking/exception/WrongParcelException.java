package com.immenser.parcelspacking.exception;

import lombok.Getter;

@Getter
public class WrongParcelException extends RuntimeException {
    public WrongParcelException(String message) {
        super(message);
    }
}
