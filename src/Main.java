import Car.*;
import ParkingGarage.ParkingGarage;
import ParkingGarage.ParkingGarageView;
import ParkingGarage.ParkingGarageSimulator;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ParkingGarage parkingGarage = new ParkingGarage(3, 2, 64);
        ParkingGarageView parkingGarageView = new ParkingGarageView();

        ArrayList<CarQueue> queues = parkingGarage.getCarQueues();
        CarEntryQueue entryQueue = new CarEntryQueue(3);
        entryQueue.addCar(new AdHocCar());
        entryQueue.addCar(new AdHocCar());
        entryQueue.addCar(new AdHocCar());
        entryQueue.addCar(new AdHocCar());
        entryQueue.addCar(new ReservationCar());
        entryQueue.addCar(new ReservationCar());
        entryQueue.addCar(new ReservationCar());
        entryQueue.addCar(new ReservationCar());
        entryQueue.addCar(new ParkingPassCar());
        entryQueue.addCar(new ParkingPassCar());
        queues.add(entryQueue);
        queues.add(new CarExitQueue(7, 5));

        ParkingGarageSimulator parkingGarageSimulator = new ParkingGarageSimulator(parkingGarage, parkingGarageView);

        JFrame frame = new JFrame("ParkingGarage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(parkingGarageView);

        frame.setVisible(true);
        parkingGarageSimulator.start();

    }
}
