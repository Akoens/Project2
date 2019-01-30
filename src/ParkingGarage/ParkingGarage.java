package ParkingGarage;

import Car.Car;
import Car.CarQueue;

import java.util.ArrayList;

public class ParkingGarage {

    private int floors;
    private int rows;
    private int places;

    private Location[][][] locations;
    private ArrayList<CarQueue> queues;

    /**
     * Constructor for the ParkingGarage which takes three parameters.
     *
     * @param floors an integer to decide how many floors there will be.
     * @param rows   an integer to decide how many rows there will be.
     * @param places an integer to decide how many places there will be.
     */
    public ParkingGarage(int floors, int rows, int places) {
        this.floors = floors;
        this.rows = rows;
        this.places = places;
        locations = new Location[floors][rows][places];
        queues = new ArrayList<CarQueue>();

        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++)
                    locations[floor][row][place] = new Location();
    }

    /**
     * Method to see how many floors the corresponding ParkingGarage object has.
     * @return the amount of floors as an integer.
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Method to see how many rows the corresponding ParkingGarage object has.
     * @return the amount of rows as an integer.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Method to see how many places the corresponding ParkingGarage object has.
     * @return the amount of places as an integer.
     */
    public int getPlaces() {
        return places;
    }

    /**
     * Method to get a list with the given car queues.
     * @return an ArrayList containing the different CarQueue objects.
     */
    public ArrayList<CarQueue> getCarQueues() {
        return queues;
    }

    /**
     * Method to get a list with the given locations [Floors][Rows][Places].
     * @return a multi-dimensional array containing the possible locations in the generated ParkingGarage.
     */
    public Location[][][] getLocations() {
        return locations;
    }

    /**
     * Method to check if a location is valid.
     * @param floor the floor to check as an integer.
     * @param row the row to check as an integer.
     * @param place the place to check as an integer.
     * @return TRUE or FALSE;
     */
    public boolean validateLocation(int floor, int row, int place) {
        return floor > -1 && row > -1 && place > -1 && floor < floors && row < rows && place < places;
    }

    /**
     * Method to get a specific location.
     * @param floor the floor to check as an integer.
     * @param row the row to check as an integer.
     * @param place the place to check as an integer.
     * @return the location as a multi-dimensional array.
     */
    public Location getLocation(int floor, int row, int place) {
        if (validateLocation(floor, row, place))
            return locations[floor][row][place];
        return null;
    }

    /**
     * Method to obtain the first location in the ParkingGarage.
     * @return a location object.
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++) {
                    Location location = getLocation(floor, row, place);
                    if (location != null && !location.hasCar()) {
                        return location;
                    }
                }
        return null;
    }

    /**
     * Method to remove a car from his set location.
     * @param car a car object.
     */
    public void carLeavesSpot(Car car) {
        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++) {
                    Location location = getLocation(floor, row, place);
                    if (location != null && location.getCar() == car) {
                        location.setCar(null);
                    }
                }
    }

    /**
     * Method to see how many cars are inside of the ParkingGarage at that time.
     * @return the car count as an integer.
     */
    public int getCarCount() {
        int count = 0;
        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++) {
                    Location location = getLocation(floor, row, place);
                    if (location != null && location.hasCar()) {
                        count++;
                    }
                }
        return count;
    }

    /**
     * Method to allocate a specific spot to a specific car inside the ParkingGarage.
     * @param location a location object.
     * @param car a car object.
     */
    public void setCarAt(Location location, Car car) {
        if (location == null || car == null) {
            return;
        }

        if (location.hasCar()) {
            carLeavesSpot(location.getCar());
        }

        location.setCar(car);
    }
}
