package com.immenser.parcelspacking;

import com.immenser.parcelspacking.controller.CarController;
import com.immenser.parcelspacking.controller.PackingController;
import com.immenser.parcelspacking.controller.ParcelController;
import com.immenser.parcelspacking.entity.Car;
import com.immenser.parcelspacking.entity.parcel.Parcel;
import com.immenser.parcelspacking.exception.IncorrectAlgorithmException;
import com.immenser.parcelspacking.output.ConsolePackingOutputter;
import com.immenser.parcelspacking.output.FilePackingOutputter;
import com.immenser.parcelspacking.output.PackingOutputter;
import com.immenser.parcelspacking.service.CarService;
import com.immenser.parcelspacking.service.ParcelService;
import com.immenser.parcelspacking.util.path.DatePathConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class ConsoleParcels {

    public static void main(String[] args) {

        log.info("Приложение запущено.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Выберите действие (введите 1 или 2):
                1. Погрузка посылок по машинам
                2. Просмотр содержимого машины""");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                log.trace("Выбрано действие 'Погрузка посылок по машинам'.");
                parcelPacking(scanner);
                break;
            case 2:
                log.trace("Выбрано выбрал действие 'Просмотр содержимого машины'.");
                carParcelsShowing(scanner);
                break;
            default:
                log.error("Введено некорректное значение действия: {}", action);
                System.exit(1);
        }
        log.info("Приложение завершено.");
    }

    public static void parcelPacking(Scanner scanner) {
        ParcelController parcelController = new ParcelController(new ParcelService());
        List<Parcel> parcels = new LinkedList<>();
        String fileName = "";
        while (fileName.isEmpty() || parcels.isEmpty()) {
            System.out.print("Файл с посылками для погрузки: ");
            fileName = scanner.next();
            parcels = parcelController.readParcelsFromFile(fileName);
        }

        boolean isNumOfCarsIncorrect = true;
        while (isNumOfCarsIncorrect) {
            System.out.print("Количество машин: ");

            int numOfCars = scanner.nextInt();
            List<Car> cars = CarService.prepareEmptyCars(numOfCars);
            isNumOfCarsIncorrect = false;

            boolean isAlgorithmIncorrect = true;
            while (isAlgorithmIncorrect) {
                System.out.println("""
                        Выберите алгоритм (введите 1 или 2):
                        1. Плотная погрузка
                        2. Равномерная погрузка""");
                try {
                    int algorithm = scanner.nextInt();
                    scanner.close();
//                    PackingOutputter packingOutputter = new ConsolePackingOutputter();
                    PackingOutputter packingOutputter = new FilePackingOutputter(new DatePathConstructor(
                            fileName, "src/test/resources/output", numOfCars));
                    PackingController packingController = new PackingController(packingOutputter);
                    packingController.packParcelsByAlgorithm(parcels, cars, algorithm);
                    isAlgorithmIncorrect = false;
                } catch (IncorrectAlgorithmException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public static void carParcelsShowing(Scanner scanner) {
        PackingOutputter packingOutputter = new ConsolePackingOutputter();
        CarController carController = new CarController(new CarService(packingOutputter));
        System.out.print("Файл с погруженной машиной: ");
        String fileName = scanner.next();
        scanner.close();
        carController.showParcelsByCarFromJsonFile(fileName);
    }
}
