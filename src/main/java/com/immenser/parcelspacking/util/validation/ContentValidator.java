package com.immenser.parcelspacking.util.validation;

public class ContentValidator {

    /**
     * Метод, проверяющий, состоит ли строка из одного и того же повторяющегося числа.
     *
     * @param value повторяющееся число.
     * @param line  проверяемая строка.
     * @return true - если строка соответствует шаблону, иначе - false.
     */
    public static boolean isContentValid(int value, String line) {
        String regex = "^(" + value + ")+$";
        return line.matches(regex);
    }
}
