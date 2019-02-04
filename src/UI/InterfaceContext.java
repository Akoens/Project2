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

    /**
     * Constructor/initializer for the interfaceContext object regarding car images.
     */
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

    /**
     * @return the interfaceContext object.
     */
    public static InterfaceContext getInstance() {
        return context;
    }

    /**
     * Method to refresh the panel with updated values.
     */
    public void repaint() {
        simulatorPanel.repaint();
        graphPanel.repaint();
    }

    /**
     * @return the corresponding simulatorPanel object.
     */
    public SimulatorPanel getSimulatorPanel() {
        return simulatorPanel;
    }

    /**
     * @return the corresponding carFeedPanel object.
     */
    public CarFeedPanel getCarFeedPanel() {
        return carFeedPanel;
    }

    /**
     * @return the corresponding controlPanel object.
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * @return the corresponding graphPanel object.
     */
    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    /**
     * @return the corresponding headerPanel object.
     */
    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    /**
     * @return the corresponding detailPanel object.
     */
    public DetailPanel getDetailPanel() {
        return detailPanel;
    }

    /**
     * @return the assigned AdHocCar image object.
     */
    public BufferedImage getAdHocCarImage() {
        return adHocCarImage;
    }

    /**
     * @return the assigned Parking Pass image object.
     */
    public BufferedImage getParkingPassCarImage() {
        return parkingPassCarImage;
    }

    /**
     * @return the assigned reservation car object.
     */
    public BufferedImage getReservationCarImage() {
        return reservationCarImage;
    }

    /**
     * @return the assigned electric car object.
     */
    public BufferedImage getElectricCarImage() {
        return electricCarImage;
    }

    /**
     * @return the assigned disabled car object.
     */
    public BufferedImage getDisabledCarImage() {
        return disabledCarImage;
    }
}
