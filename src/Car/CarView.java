package Car;

import javax.swing.*;

public class CarView extends Box {

    private JLabel isPayingLabel;
    private JLabel hasToPayLabel;

    public CarView() {
        super(BoxLayout.Y_AXIS);
        isPayingLabel = new JLabel();
        hasToPayLabel = new JLabel();
        add(isPayingLabel);
        add(hasToPayLabel);
    }

    public void update(boolean isPaying, boolean hasToPay) {
        isPayingLabel.setText(isPaying ? "This car is paying" : "This car is not paying");
        hasToPayLabel.setText(hasToPay ? "This car has not paid yet" : "This car does not have to pay");
    }

}
