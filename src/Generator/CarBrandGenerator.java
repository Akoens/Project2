package Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CarBrandGenerator {

    private ArrayList<String> exotic;
    private ArrayList<String> expensive;
    private ArrayList<String> average;
    private Random rd;

    /**
     * Constructor for the CarBrandGenerator with zero parameters where the different car brands are added to ArrayLists.
     * These ArrayLists can be expanded upon through the corresponding method.
     */
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

    /**
     * Method to return a random brand name from the three ArrayLists with a different chance of appearing per ArrayList.
     * Exotic -> 1% chance.
     * Expensive -> 15% chance.
     * Average -> 84% chance.
     *
     * @return a string containing a brand name.
     */
    public String getRandomBrand() {
        rd = new Random();
        int x = rd.nextInt(1000);
        String result;
        if (x <= 10) {
            result = exotic.get(rd.nextInt(exotic.size() - 1));
        } else if (x <= 150) {
            result = expensive.get(rd.nextInt(expensive.size() - 1));
        } else {
            result = average.get(rd.nextInt(average.size() - 1));
        }
        return result;
    }

    /**
     * Method to get the list of exotic cost cars.
     * @return the Exotic ArrayList.
     */
    public ArrayList<String> getExotic() {
        return exotic;
    }

    /**
     * Method to add an exotic car brand to the exotic cost ArrayList.
     * @param brand a car brand name as a String.
     */
    public void addExotic(String brand) {
        exotic.add(brand);
    }

    /**
     * Method to get the list of expensive cost cars.
     * @return the Expensive ArrayList.
     */
    public ArrayList<String> getExpensive() {
        return expensive;
    }

    /**
     * Method to add an expensive car brand to the expensive cost ArrayList.
     * @param brand a car brand name as a String.
     */
    public void addEpensive(String brand) {
        expensive.add(brand);
    }

    /**
     * Method to get the list of average cost cars.
     * @return the average ArrayList.
     */
    public ArrayList<String> getAverage() {
        return average;
    }

    /**
     * Method to add an expensive car brand to the average cost ArrayList.
     * @param brand a car brand name as a String.
     */
    public void addAverage(String brand) {
        average.add(brand);
    }

}
