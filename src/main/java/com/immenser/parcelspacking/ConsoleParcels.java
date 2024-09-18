package com.immenser.parcelspacking;

import com.immenser.parcelspacking.controller.PackingController;
import com.immenser.parcelspacking.entity.Parcel;
import com.immenser.parcelspacking.exception.IncorrectAlgorithmException;

import java.util.*;

public class ConsoleParcels {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Optional<List<Parcel>> parcels = Optional.empty();
        while (parcels.isEmpty()) {
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();
            parcels = PackingController.readParcelsFromFile(fileName);
        }

        boolean isAlgorithmIncorrect = true;
        while (isAlgorithmIncorrect) {
            try {
                System.out.println("""
                Выберите алгоритм (введите 1 или 2):
                1. Упаковывать по порядку
                2. Упаковывать с сортировкой""");

                int algorithm = scanner.nextInt();

                String distribution = PackingController.distributeParcelsByAlgorithm(parcels.get(), algorithm);
                System.out.println(distribution);

                isAlgorithmIncorrect = false;

            } catch (IncorrectAlgorithmException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
