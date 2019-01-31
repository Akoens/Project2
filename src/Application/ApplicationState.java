package Application;

import ParkingGarage.*;

public class ApplicationState {

    private static final ApplicationState state = new ApplicationState();

    private ParkingGarage parkingGarage;
    private ParkingGarageSimulator parkingGarageSimulator;

    private ApplicationState() {
        reset();
    }

    public static ApplicationState getInstance() {
        return state;
    }

    public void reset() {
        parkingGarage = new ParkingGarage(3, 2, 64);
        parkingGarageSimulator = new ParkingGarageSimulator(parkingGarage);
    }

    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    public ParkingGarageSimulator getParkingGarageSimulator() {
        return parkingGarageSimulator;
    }

    public void start() {
        parkingGarageSimulator.start();
    }

    public void togglePause() {
        parkingGarageSimulator.togglePause();
    }

    public boolean isPaused() {
        return parkingGarageSimulator.isPaused();
    }

}
