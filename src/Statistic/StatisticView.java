package Statistic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class StatisticView extends JComponent {

    private ArrayList<DataSet> dataSetList;

    public StatisticView() {
        dataSetList = new ArrayList<>();
    }

    public void updateView(ArrayList<DataSet> dataSetList) {
        this.dataSetList = dataSetList;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawView(g, dataSetList.toArray(new DataSet[0]));
    }

    public abstract void drawView(Graphics g, DataSet[] dataSetList);

}
