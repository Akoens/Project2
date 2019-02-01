import Generator.LicensePlateGenerator;
import org.junit.jupiter.api.Test;

public class LicensePlateGeneratorTest {

    @Test
    public void testGenerator() {

        int plates = 1000000;

        int dutch = 0;
        int german = 0;
        int belgian = 0;

        LicensePlateGenerator lpg = new LicensePlateGenerator(10, 10, 10);

        for (int i=0; i<plates; i++) {
            String plate = lpg.generatePlate();
            if (plate.startsWith("NL:")) {
                dutch++;
            } else if (plate.startsWith("D:")) {
                german++;
            } else if (plate.startsWith("B:")) {
                belgian++;
            }
            System.out.println(plate);
        }

        System.out.println(plates + " plates generated with a ratio of " + lpg.getDutchRatio() + " dutch to " + lpg.getGermanRatio() + " german to " + lpg.getBelgianRatio() + " belgian plates.");
        System.out.println(dutch + " dutch plates.");
        System.out.println(german + " german plates.");
        System.out.println(belgian + " belgian plates.");
    }

}
