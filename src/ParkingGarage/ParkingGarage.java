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

    public int getFloors() {
        return floors;
    }

    public int getRows() {
        return rows;
    }

    public int getPlaces() {
        return places;
    }

    public ArrayList<CarQueue> getCarQueues() {
        return queues;
    }

    public Location[][][] getLocations() {
        return locations;
    }

    public boolean validateLocation(int floor, int row, int place) {
        return floor > -1 && row > -1 && place > -1 && floor < floors && row < rows && place < places;
    }

    public Location getLocation(int floor, int row, int place) {
        if (validateLocation(floor, row, place))
            return locations[floor][row][place];
        return null;
    }

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
