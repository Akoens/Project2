package Car;

import javax.swing.*;

public class CarView extends Box {

    private JLabel isPayingLabel;
    private JLabel hasToPayLabel;

    /**
     * Constructor for the CarView object, takes zero parameters.
     */
    public CarView() {
        super(BoxLayout.Y_AXIS);
        isPayingLabel = new JLabel();
        hasToPayLabel = new JLabel();
        add(isPayingLabel);
        add(hasToPayLabel);
    }

    /**
     * Method to set a Label's string/text to It's corresponding values whether it has paid, needs to pay, is paying or isn't paying.
     *
     * @param isPaying
     * @param hasToPay
     */
    public void update(boolean isPaying, boolean hasToPay) {
        isPayingLabel.setText(isPaying ? "This car is paying" : "This car is not paying");
        hasToPayLabel.setText(hasToPay ? "This car has not paid yet" : "This car does not have to pay");
    }

}
