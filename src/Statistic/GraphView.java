package Statistic;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GraphView extends StatisticView {

    private Color graphColor;

    private String xAxisLabel;
    private String yAxisLabel;
    private int axisLabelPadding = 10; //Distance from side
    private int axisLabelOffset = 40; //Distance from corner


    public GraphView(String xAxisLabel, String yAxisLabel, Color graphColor) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.graphColor = graphColor;
    }

    @Override
    public void drawView(Graphics g, DataSet[] dataSets) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(graphColor);
        g2d.translate(getX(), getY());

        //Draw sidelines
        g2d.drawLine(axisLabelPadding+22, 0, axisLabelPadding+22, getHeight());
        g2d.drawLine(0, getHeight()-axisLabelPadding-24, getWidth(), getHeight()-axisLabelPadding-24);

        //Draw data
        if (dataSets != null) {
            for (DataSet dataSet : dataSets) {
                int dataX;
                int dataY;
                int lastDataX = 0;
                int lastDataY = 0;
                g2d.setColor(dataSet.color);
                for (int i=0; i<dataSet.data.length; i++) {
                    dataX = i * 10 + axisLabelPadding + 22 - 2;
                    dataY = (int) (((getHeight() - axisLabelPadding - 24) - dataSet.data[i] * 10) - 2);
                    g.drawOval(dataX, dataY, 4, 4);
                    if (lastDataX != 0 && lastDataY != 0) {
                        g.drawLine(lastDataX + 2, lastDataY + 2, dataX + 2, dataY + 2);
                    }
                    lastDataX = dataX;
                    lastDataY = dataY;
                }
            }
        }

        g2d.setColor(graphColor);
        //Draw labels
        g2d.drawString(yAxisLabel, axisLabelOffset, getHeight()-axisLabelPadding+7);
        g2d.translate(axisLabelPadding, getHeight()-axisLabelOffset);
        g2d.rotate((3 * Math.PI) / 2);
        g2d.drawString(xAxisLabel, 0, 0);

    }
}
