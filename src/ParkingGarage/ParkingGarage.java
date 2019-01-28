package ParkingGarage;

import Car.Car;
import Car.CarQueue;

import java.util.ArrayList;

public class ParkingGarage {

    private int floors;
    private int rows;
    private int places;

    private Car[][][] cars;
    private ArrayList<CarQueue> queues;

    public ParkingGarage(int floors, int rows, int places) {
        this.floors = floors;
        this.rows = rows;
        this.places = places;
        cars = new Car[floors][rows][places];
        queues = new ArrayList<CarQueue>();
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

    public Car[][][] getCars() {
        return cars;
    }

    public boolean validateLocation(Location location) {
        return (location != null) && (cars != null) && (location.getFloor() > -1 && location.getRow() > -1 && location.getPlace() > -1 && location.getFloor() < floors && location.getRow() < rows && location.getPlace() < places);
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < floors; floor++) {
            for (int row = 0; row < rows; row++) {
                for (int place = 0; place < places; place++) {
                    Location location = new Location(floor, row, place);
                    if (cars[location.getFloor()][location.getRow()][location.getPlace()] == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public void carLeavesSpot(Car car) {
        if (car == null) {
            return;
        }

        Location location = car.getLocation();

        if (!validateLocation(location)) {
            return;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
    }

    public Car getCarAt(Location location) {
        if (!validateLocation(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public int getCarCount() {
        int count = 0;
        for (Car[][] carFloor : cars)
            for (Car[] carRow : carFloor)
                for (Car car : carRow)
                    if (car != null) {
                        count++;
                    }
        return count;
    }

    public void setCarAt(Location location, Car car) {
        if (!validateLocation(location)) {
            return;
        }

        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
        }
    }
}
