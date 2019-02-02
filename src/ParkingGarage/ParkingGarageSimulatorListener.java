package ParkingGarage;

import Car.Car;

public interface ParkingGarageSimulatorListener {

    void onTick();
    void onCarEnter(Car car);
    void onCarPayment(Car car, double revenue);
    void onCarExit(Car car);

}
