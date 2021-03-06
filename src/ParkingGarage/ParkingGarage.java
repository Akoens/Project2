package ParkingGarage;

import Car.*;
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

        for (int floor=0; floor<floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++)
                    locations[floor][row][place] = new Location();
        for (int i = 0; i < 8; i++) {
            locations[0][0][i].setLocationType(Location.LocationType.DISABLED);
        }
        for (int i = 8; i < 40; i++) {
            locations[0][0][i].setLocationType(Location.LocationType.RESERVED);
        }
        for (int i = 0; i < 20; i++) {
            locations[1][0][i].setLocationType(Location.LocationType.RECHARGE);
        }

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
        return getFirstFreeLocation(Location.LocationType.DEFAULT);
    }

    public Location getFirstFreeLocation(Car car) {
        Location location = null;

        if (car instanceof ReservationCar) {
            location = getFirstFreeLocation(Location.LocationType.RESERVED);
        }

        if (car instanceof ElectricCar) {
            location = getFirstFreeLocation(Location.LocationType.RECHARGE);
        }

        if (car instanceof DisabledCar) {
            location = getFirstFreeLocation(Location.LocationType.DISABLED);
        }

        if (location == null) {
            location = getFirstFreeLocation();
        }

        return location;
    }

    public Location getFirstFreeLocation(Location.LocationType locationType) {
        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++) {
                    Location location = getLocation(floor, row, place);
                    if (location != null && !location.hasCar() && location.getLocationType() == locationType) {
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

    public int getLocationCount() {
        int count = 0;
        for (int floor = 0; floor < floors; floor++)
            for (int row = 0; row < rows; row++)
                for (int place = 0; place < places; place++) {
                    Location location = getLocation(floor, row, place);
                    if (location != null) {
                        count++;
                    }
                }
        return count;
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
