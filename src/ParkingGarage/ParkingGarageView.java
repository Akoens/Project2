package ParkingGarage;

import Car.Car;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingGarageView extends JPanel {

    private SimpleDateFormat dateFormat;
    private Image carParkImage;
    private Dimension size;

    public ParkingGarageView() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        size = new Dimension(800, 800);

        setBackground(Color.GRAY);
    }

    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        } else {
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void updateView(Date time, ParkingGarage parkingGarage) {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }

        if (carParkImage == null) {
            return;
        }

        Graphics graphics = carParkImage.getGraphics();

        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());

        for(int floor = 0; floor < parkingGarage.getFloors(); floor++) {
            for(int row = 0; row < parkingGarage.getRows(); row++) {
                for(int place = 0; place < parkingGarage.getPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = parkingGarage.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }

        graphics.setColor(Color.WHITE);
        graphics.drawString(dateFormat.format(time), 4, (int) size.getHeight() - 8);
    }

    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

}
