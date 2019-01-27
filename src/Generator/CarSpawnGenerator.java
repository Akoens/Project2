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

    public Car randomCar(int minutesLeft) {
        int decide = rd.nextInt(99) + 1;
        if (decide <= 33) {
            return new AdHocCar(lpg.generatePlate(), cbg.getRandomBrand(), randomStayMinutes(minutesLeft));
        } else if (decide > 33 && decide < 66) {
            return new ParkingPassCar(lpg.generatePlate(), cbg.getRandomBrand(), randomStayMinutes(minutesLeft));
        } else {
            return new ReservationCar(lpg.generatePlate(), cbg.getRandomBrand(), randomStayMinutes(minutesLeft));
        }

    }

    public int randomStayMinutes(int minimumMinutes) {
        if (rd.nextInt(100) < 50) {
            return minimumMinutes - rd.nextInt(minimumMinutes / 10);
        } else {
            return minimumMinutes + rd.nextInt(minimumMinutes / 10);
        }
    }



    public ArrayList<Car> carGeneration(int hour) {
        ArrayList<Car> cars = new ArrayList<Car>();
        spawnRate = rd.nextDouble();

        if (hour < 3 && spawnRate < 0.02) {
            cars.add(randomCar(360));
            return cars;
        } else if ((hour >= 3 && hour <= 5) && spawnRate < 0.05) {
            cars.add(randomCar(300));
            return cars;
        } else if ((hour == 6 || hour == 7) && spawnRate < 1.00) {
            for (int x = 0; x <= rd.nextInt(2); x++) {
                cars.add(randomCar(700));
            }
            return cars;
        } else if ((hour == 8) && spawnRate < 0.65) {
            cars.add(randomCar(680));
            return cars;
        } else if ((hour > 8 && hour <= 11) && spawnRate < 0.30) {
            cars.add(randomCar(120));
            return cars;
        } else if ((hour == 12) && spawnRate < 0.40) {
            cars.add(randomCar(60));
            return cars;
        } else if ((hour > 12 && hour <= 14) && spawnRate < 0.25) {
            cars.add(randomCar(90));
            return cars;
        } else if ((hour == 15) && spawnRate < 0.15) {
            cars.add(randomCar(60));
            return cars;
        } else if ((hour == 16 || hour == 17) && spawnRate < 0.02) {
            cars.add(randomCar(580));
            return cars;
        } else if ((hour >= 18 && hour <= 20) && spawnRate < 0.07) {
            cars.add(randomCar(520));
            return cars;
        } else if ((hour == 21 || hour == 22) && spawnRate < 0.05) {
            cars.add(randomCar(480));
            return cars;
        } else if ((hour == 23) && spawnRate < 0.03) {
            cars.add(randomCar(500));
            return cars;
        } else if ((hour == 24) && spawnRate < 0.02) {
            cars.add(randomCar(360));
            return cars;
        }
        return cars;
    }
}
