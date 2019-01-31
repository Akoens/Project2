package ParkingGarage;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ParkingGarageView extends JPanel implements ActionListener {

    private static final String ACTION_PREVIOUS = "ACTION_PREVIOUS";
    private static final String ACTION_NEXT = "ACTION_NEXT";

    private JButton nextFloorButton;
    private JButton previousFloorButton;
    private JLabel floorLabel;

    private JPanel grid;
    private ArrayList<LocationView> locationViews;
    private int floor = 0;

    public ParkingGarageView() {
        setLayout(new BorderLayout());
        setDoubleBuffered(true);
        locationViews = new ArrayList<LocationView>();

        JPanel header = new JPanel(new BorderLayout());
        nextFloorButton = new JButton("Next");
        nextFloorButton.setActionCommand(ACTION_NEXT);
        previousFloorButton = new JButton("Previous");
        previousFloorButton.setActionCommand(ACTION_PREVIOUS);
        floorLabel = new JLabel("Floor 1", SwingConstants.CENTER);
        header.add(nextFloorButton, BorderLayout.EAST);
        header.add(previousFloorButton, BorderLayout.WEST);
        header.add(floorLabel, BorderLayout.CENTER);

        JButton[] buttons = new JButton[]{previousFloorButton, nextFloorButton};
        for (JButton button : buttons) {
            button.addActionListener(this);
            button.setBackground(MaterialColors.WHITE);
            button.setForeground(MaterialColors.BLUE_500);
            MaterialUIMovement.add(button, MaterialColors.GRAY_300);
        }

        JPanel content = new JPanel(new GridBagLayout());
        grid = new JPanel(new GridLayout(0, 4, 8, 2));
        grid.setMaximumSize(new Dimension(200, 800));
        grid.setPreferredSize(new Dimension(200, 800));
        grid.setMinimumSize(new Dimension(200, 800));
        content.add(grid);
        add(header, BorderLayout.PAGE_START);
        add(content, BorderLayout.CENTER);
    }

    public void updateView(ParkingGarage parkingGarage) {
        if (locationViews.size() != parkingGarage.getRows()*parkingGarage.getPlaces()) {
            grid.removeAll();
            for (int i=0; i<parkingGarage.getRows()*parkingGarage.getPlaces(); i++) {
                LocationView locationView = new LocationView();
                locationViews.add(locationView);
                grid.add(locationView, 0, i%4);
            }
        }

        if (floor < 0) {
            floor = parkingGarage.getFloors() - 1;
        }

        if (floor >= parkingGarage.getFloors()) {
            floor = 0;
        }

        Location[][] locations = parkingGarage.getLocations()[floor];

        int i = 0;
        for (Location[] row : locations)
            for (Location l : row)
                if (i < locationViews.size()) {
                    LocationView locationView = locationViews.get(i++);
                    if (locationView != null) {
                        locationView.updateView(l);
                    }
                }
        floorLabel.setText("Floor " + (floor + 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_NEXT:
                floor++;
                break;
            case ACTION_PREVIOUS:
                floor--;
                break;
        }
    }
}
