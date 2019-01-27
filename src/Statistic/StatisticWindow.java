package Statistic;

import javax.swing.*;

public class StatisticWindow extends JFrame {

    private StatisticView view;

    public StatisticWindow(String title, StatisticView view) {
        super(title);
        this.view = view;
        setSize(800, 800);
        setLocationRelativeTo(null);
        setContentPane(view);
        setVisible(true);
    }

    public StatisticView getStatisticView() {
        return view;
    }

}
