import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Simulator parkingSimulator = new Simulator();
        //parkingSimulator.run();

        LicensePlateGenerator ln = new LicensePlateGenerator();
        for (int x = 0; x < 11; x++) {
            System.out.println("NL "+ln.getPlate());
        }
    }
}
