package com.immenser.parcelspacking.reading.file;

import com.immenser.parcelspacking.dto.ParcelDto;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.entity.parcel.ParcelLayer;
import com.immenser.parcelspacking.entity.parcel.ParcelType;
import com.immenser.parcelspacking.exception.WrongParcelException;
import com.immenser.parcelspacking.util.type.ParcelTypeDeterminer;
import com.immenser.parcelspacking.util.validation.ContentValidator;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Log4j2
public class ParcelTxtReader implements FileReader<Parcel> {

    private int lineCounter = 0;

    /**
     * Метод чтения объектов типа &lt;T&gt; из файла.
     * <p>
     * Метод считывает посылки из текстового файла и добавляет валидные посылки в список посылок.
     *
     * @param fileName имя исходного файла с посылками.
     * @return список прочитанных посылок.
     */
    @Override
    public List<Parcel> read(String fileName) {
        int parcelCounter = 0;
        List<Parcel> parcels = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            log.debug("Чтение посылок из файла {}...", fileName);
            ParcelDto parcelDto = null;
            String line;
            while ((line = br.readLine()) != null) {
                lineCounter++;
                try {
                    parcelDto = processLine(line, parcelDto, parcels, br);
                } catch (WrongParcelException e) {
                    parcelDto = null;
                    log.warn(e.getMessage());
                }
            }
            addToParcels(parcelDto, parcels);
            log.debug("Файл {} успешно прочитан.", fileName);
            log.debug("Валидных посылок: {} ", parcels.size());
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}.", fileName);
        }
        return parcels;
    }

    private ParcelDto processLine(String line, ParcelDto parcelDto, List<Parcel> parcels, BufferedReader br) throws WrongParcelException, IOException {
        if (line.isEmpty()) {
            addToParcels(parcelDto, parcels);
            parcelDto = null;
        } else {
            if (parcelDto == null) {
                parcelDto = new ParcelDto(lineCounter, Integer.parseInt(line.substring(0, 1)), new ArrayList<>());
            }
            addParcelLayer(line, parcelDto, br);
        }
        return parcelDto;
    }

    private void addParcelLayer(String line, ParcelDto parcelDto, BufferedReader br) throws WrongParcelException, IOException {
        log.trace("Строка {}: {}", lineCounter, line);
        if (ContentValidator.isContentValid(parcelDto.getValue(), line)) {
            parcelDto.getLayers().add(new ParcelLayer(line.length()));
        } else {
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                lineCounter++;
            }
            lineCounter++;
            throw new WrongParcelException("Ошибка в исходном файле (строка " + parcelDto.getStartLine() +
                    "): Некорректное содержимое посылки. Посылка будет проигнорирована.");
        }
    }

    private void addToParcels(ParcelDto parcelDto, List<Parcel> parcels) {
        if (parcelDto != null) {
            try {
                ParcelType type = ParcelTypeDeterminer.determineParcelType(parcelDto);
                parcels.add(new Parcel(type));
            } catch (WrongParcelException e) {
                log.warn(e.getMessage());
            }
        }
    }
}
