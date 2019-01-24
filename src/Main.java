import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Simulator parkingSimulator = new Simulator();
        //parkingSimulator.run();

        //!!** RUN MAIN TO TEST! **!!//
        LicensePlateGenerator ln = new LicensePlateGenerator(200, 100, 5);
        CarBrand cb = new CarBrand();
        int countNL = 0;
        int countD = 0;
        int countB = 0;
        for (int x = 0; x < 10000; x++) {
            String lplate = ln.generatePlate();
            if (lplate.startsWith("D")) {
                System.out.println(ln.generatePlate());
                countD++;
            } else if (lplate.startsWith("NL")) {
                System.out.println(ln.generatePlate());
                countNL++;
            } else if (lplate.startsWith("B")) {
                System.out.println(ln.generatePlate());
                countB++;
            }
        }
        System.out.println("Nederlands auto's: " + countNL);
        System.out.println("Duitse auto's: " + countD);
        System.out.println("Belgische auto's: " + countB);

        System.out.println("------------------------------------");

        Car ahc = new AdHocCar(ln.generatePlate(), cb.getRandomAverage());
        Car ppc = new ParkingPassCar(ln.generatePlate(), cb.getRandomExpensive());
        Car rc = new ReservationCar(ln.generatePlate(), cb.getRandomExotic());

        System.out.println("Kenteken bij AHC-car= " + ahc.getLicensePlate() + " en van het merk: " + ahc.getBrand());
        System.out.println("Kenteken bij PPC-car= " + ppc.getLicensePlate() + " en van het merk: " + ppc.getBrand());
        System.out.println("Kenteken bij RC-car= " + rc.getLicensePlate() + " en van het merk: " + rc.getBrand());



    }
}
