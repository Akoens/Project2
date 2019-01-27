package Statistic;

import java.awt.*;
import java.awt.event.*;

public class GraphView extends StatisticView implements MouseListener, MouseMotionListener, MouseWheelListener {

    private Color graphColor;

    private String xAxisLabel;
    private String yAxisLabel;
    private int axisLabelPadding = 10; //Distance from side
    private int axisLabelOffset = 40; //Distance from corner
    private int xAxisStep = 1;
    private int yAxisStep = 1;
    private int xAxisStepSize = 40;
    private int yAxisStepSize = 40;

    private Point shift;
    private Point lastShift;



    public GraphView(String xAxisLabel, String yAxisLabel, Color graphColor) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.graphColor = graphColor;
        shift = new Point();
        lastShift = new Point();
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    public void drawView(Graphics g, DataSet[] dataSets) {
        Graphics2D g2d = (Graphics2D) g;

        if (g2d == null) {
            return;
        }

        g2d.setColor(graphColor);
        g2d.translate(getX() + shift.x, getY() + shift.y);

        //Draw sidelines
        g2d.drawLine(axisLabelPadding+22, -shift.y, axisLabelPadding+22, getHeight());
        g2d.drawLine(0, getHeight()-axisLabelPadding-24, getWidth() - shift.x, getHeight()-axisLabelPadding-24);

        int j=0;
        for (int step=0; step<getWidth() - shift.x; step+=xAxisStepSize) {
            int x = step+axisLabelPadding+22-2;
            int y = getHeight()-axisLabelPadding-24-2;
            g2d.fillOval(x, y, 4, 4);
            g2d.drawString(""+j*xAxisStep, x, y + 15);
            j++;
        }

        j=0;
        for (int step=getHeight(); step>-shift.y; step-=yAxisStepSize) {
            int x = axisLabelPadding+22-2;
            int y = step-axisLabelPadding-24-2;
            g2d.fillOval(x, y, 4, 4);
            g2d.drawString(""+j*yAxisStep, x - 15, y);
            j++;
        }

        //Draw data
        if (dataSets != null) {
            for (DataSet dataSet : dataSets) {
                int dataX;
                int dataY;
                int lastDataX = 0;
                int lastDataY = 0;
                g2d.setColor(dataSet.color);
                for (int i=0; i<dataSet.data.length; i++) {
                    dataX = i * xAxisStepSize + axisLabelPadding + 22 - 2;
                    dataY = (int) (((getHeight() - axisLabelPadding - 24) - dataSet.data[i] * yAxisStepSize) - 2);
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        xAxisStepSize = (int) Math.max(xAxisStepSize + e.getPreciseWheelRotation(), 10);
        yAxisStepSize = (int) Math.max(yAxisStepSize + e.getPreciseWheelRotation(), 10);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        shift.x += e.getX() - lastShift.x;
        shift.y += e.getY() - lastShift.y;
        lastShift = e.getPoint();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastShift = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
