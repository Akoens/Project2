package Statistic;

import javax.swing.*;
import java.awt.*;

public class StatisticWindow extends JFrame {

    private StatisticView view;

    public StatisticWindow(String title, Point location, StatisticView view) {
        super(title);
        this.view = view;
        setSize(800, 800);
        setLocation(location);
        setContentPane(view);
        setVisible(true);
    }

    public StatisticView getStatisticView() {
        return view;
    }

}
