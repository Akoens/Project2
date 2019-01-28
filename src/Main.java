import Car.*;
import Generator.*;
import Generator.LicensePlateGenerator;
import ParkingGarage.ParkingGarage;
import ParkingGarage.ParkingGarageView;
import ParkingGarage.ParkingGarageSimulator;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LicensePlateGenerator lpg = new LicensePlateGenerator(200, 10, 5);
        CarBrandGenerator cbg = new CarBrandGenerator();

        ParkingGarage parkingGarage = new ParkingGarage(3, 2, 64);
        ParkingGarageView parkingGarageView = new ParkingGarageView();

        ArrayList<CarQueue> queues = parkingGarage.getCarQueues();
        CarEntryQueue entryQueue = new CarEntryQueue(3);

        entryQueue.addCar(new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ParkingPassCar(lpg.generatePlate(), cbg.getRandomBrand()));
        entryQueue.addCar(new ParkingPassCar(lpg.generatePlate(), cbg.getRandomBrand()));
        queues.add(entryQueue);
        queues.add(new CarPaymentQueue(7));
        queues.add(new CarExitQueue( 5));

        ParkingGarageSimulator parkingGarageSimulator = new ParkingGarageSimulator(parkingGarage, parkingGarageView);

        JFrame frame = new JFrame("Parkeer Beheer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(parkingGarageView);

        frame.setVisible(true);
        parkingGarageSimulator.start();

    }
}
