package Generator;

import Car.Car;
import Car.AdHocCar;
import Car.ParkingPassCar;
import Car.ReservationCar;
import Generator.CarBrandGenerator;
import Generator.LicensePlateGenerator;

import java.util.ArrayList;
import java.util.Random;

public class CarSpawnGenerator {

    private CarBrandGenerator cbg;
    private LicensePlateGenerator lpg;
    private Random rd;
    private double spawnRate;

    public CarSpawnGenerator() {
        rd = new Random();
        cbg = new CarBrandGenerator();
        lpg = new LicensePlateGenerator(200, 10, 5);

    }

    public Car randomCar() {
        int decide = rd.nextInt(99) + 1;
        if (decide <= 33) {
            return new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand());
        } else if (decide > 33 && decide < 66) {
            return new ParkingPassCar(lpg.generatePlate(), cbg.getRandomBrand());
        } else {
            return new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand());
        }

    }

    public ArrayList<Car> carGeneration(int hour) {
        ArrayList<Car> cars = new ArrayList<Car>();
        spawnRate = rd.nextDouble();

        if (hour < 3 && spawnRate < 0.02) {
            cars.add(randomCar());
            return cars;
        } else if ((hour >= 3 && hour <= 5) && spawnRate < 0.05) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 6 || hour == 7) && spawnRate < 1.00) {
            for (int x = 0; x <= rd.nextInt(2); x++) {
                cars.add(randomCar());
            }
            return cars;
        } else if ((hour == 8) && spawnRate < 0.65) {
            cars.add(randomCar());
            return cars;
        } else if ((hour > 8 && hour <= 11) && spawnRate < 0.30) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 12) && spawnRate < 0.40) {
            cars.add(randomCar());
            return cars;
        } else if ((hour > 12 && hour <= 14) && spawnRate < 0.25) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 15) && spawnRate < 0.15) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 16 || hour == 17) && spawnRate < 0.02) {
            cars.add(randomCar());
            return cars;
        } else if ((hour >= 18 && hour <= 20) && spawnRate < 0.07) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 21 || hour == 22) && spawnRate < 0.05) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 23) && spawnRate < 0.03) {
            cars.add(randomCar());
            return cars;
        } else if ((hour == 24) && spawnRate < 0.02) {
            cars.add(randomCar());
            return cars;
        }
        return cars;
    }
}
