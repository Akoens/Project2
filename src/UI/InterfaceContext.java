package UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceContext {

    private static final InterfaceContext context = new InterfaceContext();

    private SimulatorPanel simulatorPanel;
    private CarFeedPanel carFeedPanel;
    private ControlPanel controlPanel;
    private GraphPanel graphPanel;
    private HeaderPanel headerPanel;
    private DetailPanel detailPanel;

    private BufferedImage adHocCarImage;
    private BufferedImage parkingPassCarImage;
    private BufferedImage reservationCarImage;
    private BufferedImage electricCarImage;
    private BufferedImage disabledCarImage;

    private InterfaceContext() {}

    public void init() {
        simulatorPanel = new SimulatorPanel();
        carFeedPanel = new CarFeedPanel();
        controlPanel = new ControlPanel();
        graphPanel = new GraphPanel();
        headerPanel = new HeaderPanel();
        detailPanel = new DetailPanel();

        try {
            adHocCarImage = ImageIO.read(new File("res/adHocCarImage.png"));
            parkingPassCarImage = ImageIO.read(new File("res/parkingPassCarImage.png"));
            reservationCarImage = ImageIO.read(new File("res/reservationCarImage.png"));
            electricCarImage = ImageIO.read(new File("res/electricCarImage.png"));
            disabledCarImage = ImageIO.read(new File("res/disabledCarImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InterfaceContext getInstance() {
        return context;
    }

    public void repaint() {
        simulatorPanel.repaint();
        graphPanel.repaint();
    }

    public SimulatorPanel getSimulatorPanel() {
        return simulatorPanel;
    }

    public CarFeedPanel getCarFeedPanel() {
        return carFeedPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public DetailPanel getDetailPanel() {
        return detailPanel;
    }

    public BufferedImage getAdHocCarImage() {
        return adHocCarImage;
    }

    public BufferedImage getParkingPassCarImage() {
        return parkingPassCarImage;
    }

    public BufferedImage getReservationCarImage() {
        return reservationCarImage;
    }

    public BufferedImage getElectricCarImage() {
        return electricCarImage;
    }

    public BufferedImage getDisabledCarImage() {
        return disabledCarImage;
    }
}
