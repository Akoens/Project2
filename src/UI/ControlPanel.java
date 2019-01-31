package UI;

import Application.ApplicationState;
import ParkingGarage.ParkingGarageSimulator;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ChangeListener, ActionListener {

    private static final String ACTION_PAUSE = "ACTION_PAUSE";
    private static final String ACTION_RESET = "ACTION_RESET";

    private ApplicationState applicationState;
    private JButton pauseButton;
    private JButton resetButton;
    private JSlider timescaleSlider;
    private JLabel timescaleLabel;

    public ControlPanel() {
        applicationState = ApplicationState.getInstance();
        setMinimumSize(new Dimension(400, 64));
        setBorder(Theme.getDefaultBorder());

        setLayout (new BorderLayout());

        timescaleSlider = new JSlider();
        timescaleSlider.setBorder(null);
        timescaleSlider.setMinimum(ParkingGarageSimulator.TIMESCALE_MIN);
        timescaleSlider.setMaximum(ParkingGarageSimulator.TIMESCALE_MAX);
        timescaleSlider.setValue(applicationState.getParkingGarageSimulator().getTimescale());
        timescaleSlider.addChangeListener(this);
        timescaleLabel = new JLabel(timescaleSlider.getValue() + "x");

        JPanel timescalePanel = new JPanel(new BorderLayout());
        timescalePanel.add(timescaleSlider, BorderLayout.CENTER);
        timescalePanel.add(timescaleLabel, BorderLayout.EAST);

        JButton[] buttons = new JButton[2];
        buttons[0] = new JButton("Start");
        buttons[1] = new JButton("Reset");

        for (JButton button : buttons) {
            button.setBackground(MaterialColors.WHITE);
            button.setForeground(MaterialColors.BLUE_500);
            MaterialUIMovement.add(button, MaterialColors.GRAY_300);
        }

        pauseButton = buttons[0];
        resetButton = buttons[1];

        updatePauseButtonText();

        pauseButton.setActionCommand(ACTION_PAUSE);
        resetButton.setActionCommand(ACTION_RESET);
        pauseButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(pauseButton, BorderLayout.WEST);
        add(resetButton, BorderLayout.EAST);
        add(timescalePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            JSlider slider = (JSlider) e.getSource();
            applicationState.getParkingGarageSimulator().setTimescale(slider.getValue());
            timescaleLabel.setText("" + slider.getValue() + "x");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_PAUSE:
                applicationState.togglePause();
                updatePauseButtonText();
                break;
            case ACTION_RESET:
                break;
        }

    }

    private void updatePauseButtonText() {
        pauseButton.setText(applicationState.isPaused() ? "Resume" : "Pause");
    }
}
