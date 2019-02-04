package Statistic;

import java.awt.*;

public class DataSet {

    public double[] data;
    public Color color;

    /**
     * A dataset to represent a line on a GraphView.
     */
    public DataSet(double[] data, Color color) {
        this.data = data;
        this.color = color;
    }

}
