package Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CarBrandGenerator {

    private ArrayList<String> exotic;
    private ArrayList<String> expensive;
    private ArrayList<String> average;
    private Random rd;


    public CarBrandGenerator() {
        exotic = new ArrayList<>();
        Collections.addAll(exotic, "Bugatti", "Ferrari", "Lamborghini", "Koenigsegg", "McLaren", "Rolls Royce", "Pagani", "Maserati");
        expensive = new ArrayList<>();
        Collections.addAll(expensive, "Alfa Romeo", "Aston Martin", "BMW", "Cadillac", "Chrysler", "Dodge",
                "Jeep", "Porsche", "Mercedes-Benz", "Mini", "Tesla", "Lexus", "Mazda");
        average = new ArrayList<>();
        Collections.addAll(average, "Ford", "Kia", "Acura", "Audi", "Fiat", "Honda", "Hyundai", "Peugeot", "Nissan",
                "Mitsubishi", "Suzuki", "Volkswagen", "Renault", "Saab", "Subaru", "Volvo");
    }

    public String getRandomBrand() {
        rd = new Random();
        int x = rd.nextInt(1000);
        String result;
        if (x <= 10) {
            result = exotic.get(rd.nextInt(exotic.size() - 1));
        } else if (x > 10 && x <= 150) {
            result = expensive.get(rd.nextInt(expensive.size() - 1));
        } else {
            result = average.get(rd.nextInt(average.size() - 1));
        }
        return result;
    }

    public ArrayList<String> getExotic() {
        return exotic;
    }

    public void addExotic(String brand) {
        exotic.add(brand);
    }

    public ArrayList<String> getExpensive() {
        return expensive;
    }

    public void addEpensive(String brand) {
        expensive.add(brand);
    }

    public ArrayList<String> getAverage() {
        return average;
    }

    public void addAverage(String brand) {
        average.add(brand);
    }
}
