package Application;

import ParkingGarage.*;

public class ApplicationState {

    private static final ApplicationState state = new ApplicationState();

    private ParkingGarage parkingGarage;
    private ParkingGarageSimulator parkingGarageSimulator;

    /**
     * Constructor that resets the application.
     */
    private ApplicationState() {
        reset();
    }

    /**
     * Method used to get the instance of the applicationState.
     * @return ApplicationState as the variable state.
     */
    public static ApplicationState getInstance() {
        return state;
    }

    /**
     * method used to reset the application by setting a new ParkingGarage and a new ParkingGarageSimulator.
     */
    public void reset() {
        parkingGarage = new ParkingGarage(5, 2, 50);
        parkingGarageSimulator = new ParkingGarageSimulator(parkingGarage);
    }

    /**
     * Method used to get the parkingGarage variable.
     * This variable is used in the application to store the instance of a ParkingGarage.
     * @return A parking garage.
     */
    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    /**
     * Method used to get the parkingGarageSimulator variable.
     * This variable is used in the application to store the instance of a ParkingGarageSimulator.
     * @return A parking garage simulator.
     */
    public ParkingGarageSimulator getParkingGarageSimulator() {
        return parkingGarageSimulator;
    }

    /**
     * Method used to start the ParkingGarageSimulator.
     */
    public void start() {
        parkingGarageSimulator.start();
    }

    /**
     * Method used to pause the ParkingGarageSimulator.
     */
    public void togglePause() {
        parkingGarageSimulator.togglePause();
    }

    /**
     * Method used to see if the parkingGarageSimulator is paused.
     * @return A boolean for determining if the ParkingGarageSimulator is paused or not.
     */
    public boolean isPaused() {
        return parkingGarageSimulator.isPaused();
    }

}
