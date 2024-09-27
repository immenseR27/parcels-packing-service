package com.immenser.parcelspacking.util.type;

import com.immenser.parcelspacking.dto.ParcelDto;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.exception.WrongParcelException;


public class ParcelTypeDeterminer {

    /**
     * Метод определения типа посылки.
     *
     * @param parcelDto объект с информацией о посылке, подлежащей проверке.
     * @return тип посылки.
     * @throws WrongParcelException если содержимое посылки или ее форма некорректны.
     */
    public static ParcelType determineParcelType(ParcelDto parcelDto) throws WrongParcelException {
        try {
            ParcelType type = ParcelType.values()[parcelDto.getValue() - 1];
            if (parcelDto.getLayers().equals(type.getLayers())) {
                return type;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongParcelException("Ошибка в исходном файле (строка " + parcelDto.getStartLine() +
                    "): Некорректное содержимое посылки. Посылка будет проигнорирована.");
        }
        throw new WrongParcelException("Ошибка в исходном файле (строка " + parcelDto.getStartLine() +
                "): Некорректная форма посылки. Посылка будет проигнорирована.");
    }
}
