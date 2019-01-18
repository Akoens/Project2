import Car.*;
import Car.ReservationCar;
import Simulation.Location;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.fail;

public class CarViewTest {

    @Test
    public void testView() {
        Car car = new ReservationCar();
        car.setLocation(new Location(5, 0, 55));
        CarView view = new CarView();
        CarController controller = new CarController(car, view);

        JFrame frame = new JFrame("CarViewTest");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(view);
        frame.setVisible(true);

        controller.updateView();
        frame.revalidate();
        frame.repaint();
        while (frame.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                fail();
            }
        }
    }

}
