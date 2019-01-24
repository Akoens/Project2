import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CarBrand {
    private Random rd;
    private ArrayList<String> brandsExpensive;
    private ArrayList<String> brandsExotic;
    private ArrayList<String> brandsAverage;

    public CarBrand() {
        rd = new Random();
        brandsExpensive = new ArrayList<>();
        Collections.addAll(brandsExpensive, "Alfa Romeo", "Aston Martin", "BMW", "Audi", "Chrysler", "Dodge", "Tesla", "Lexus", "Ram", "Mercedes-Benz");
        brandsExotic = new ArrayList<>();
        Collections.addAll(brandsExotic, "Bugatti", "Jaguar", "Bentley", "Lamborghini", "Koenigsegg", "Pagani", "Rolls Royce", "McLaren", "Ferrari");
        brandsAverage = new ArrayList<>();
        Collections.addAll(brandsAverage, "Acura", "Dacia", "Chevrolet", "Citroën", "Fiat", "Ford", "Honda", "Hyundai", "Jeep", "Kia", "Mazda", "Peugeot" +
                "Renault", "Saab", "Subaru", "Suzuki", "Mitsubishi", "Nissan", "Toyota", "Volkswagen", "Volvo");
    }

    public String getRandomExpensive() {
        return brandsExpensive.get(rd.nextInt(brandsExpensive.size() - 1));
    }

    public String getRandomExotic() {
        return brandsExotic.get(rd.nextInt(brandsExotic.size() - 1));
    }

    public String getRandomAverage() {
        return brandsAverage.get(rd.nextInt(brandsAverage.size() - 1));
    }

    public void addExpensive(String brandName) {
        brandsExpensive.add(brandName);
    }

    public void addExotic(String brandName) {
        brandsExotic.add(brandName);
    }

    public void addAverage(String brandName) {
        brandsAverage.add(brandName);
    }

}
