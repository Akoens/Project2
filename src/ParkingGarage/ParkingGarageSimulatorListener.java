package ParkingGarage;

import Car.Car;

public interface ParkingGarageSimulatorListener {

    /**
     * Gets called at the end of every ParkingGarageSimulator tick.
     */
    void onTick();

    /**
     * Gets called every time a car enters the ParkingGarage.
     * @param car the car that has been added to the ParkingGarage.
     */
    void onCarEnter(Car car);

    /**
     * Gets called every time a car pays the parking fee.
     * @param car the car that has paid.
     * @param revenue the amount the car has paid.
     */
    void onCarPayment(Car car, double revenue);

    /**
     * Gets called every time a car leaves the ParkingGarage.
     * @param car the car that has been added to the ParkingGarage.
     */
    void onCarExit(Car car);

}
