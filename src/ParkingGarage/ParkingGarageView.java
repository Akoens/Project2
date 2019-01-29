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

    public void updateView(Date time, ParkingGarage parkingGarage, double moneyPaid) {
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

        for (int floor = 0; floor < parkingGarage.getFloors(); floor++)
            for (int row = 0; row < parkingGarage.getRows(); row++)
                for (int place = 0; place < parkingGarage.getPlaces(); place++)
                    drawPlace(graphics, parkingGarage.getLocation(floor, row, place), floor, row, place);

        graphics.setColor(Color.WHITE);
        graphics.drawString(dateFormat.format(time), 4, (int) size.getHeight() - 8);
        graphics.drawString("$"+ String.format("%1.2f",moneyPaid), 4, (int) size.getHeight() - 20);
    }

    private void drawPlace(Graphics graphics, Location location, int floor, int row, int place) {
        if (location == null) {
            return;
        }

        if (location.hasCar()) {
            graphics.setColor(location.getCar().getColor());
        } else {
            graphics.setColor(location.getColor());
        }
        graphics.fillRect(
                floor * 260 + (1 + (int) Math.floor(row * 0.5)) * 75 + (row % 2) * 20,
                60 + place * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

}
