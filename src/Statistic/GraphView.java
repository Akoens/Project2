package Statistic;

import java.awt.*;
import java.awt.event.*;

public class GraphView extends StatisticView implements MouseListener, MouseMotionListener, MouseWheelListener {

    private Color graphColor;

    private String xAxisLabel;
    private String yAxisLabel;
    private int xMargin = 22;
    private int yMargin = 24;
    private int axisLabelPadding = 10; //Distance from side
    private int axisLabelOffset = 40; //Distance from corner
    private int xAxisStep = 1;
    private int yAxisStep = 40;
    private int xAxisStepSize = 23;
    private int yAxisStepSize = 23;
    private int ovalSize = 0;

    private Point shift;
    private Point lastShift;

    /**
     * Updates the view to represent the given ParkingGarage.
     */
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
        g2d.translate(getX() + shift.x + 15, getY() + shift.y - 15);

        //Draw sidelines
        g2d.drawLine(axisLabelPadding + xMargin, -shift.y, axisLabelPadding + 22, getHeight() - axisLabelPadding - yMargin);
        g2d.drawLine(axisLabelPadding + xMargin, getHeight() - axisLabelPadding - 24, getWidth() - shift.x, getHeight() - axisLabelPadding - yMargin);

        if (xAxisStepSize > 10) {
            int i = 0;
            for (int step = 0; step < getWidth() - shift.x; step += xAxisStepSize) {
                int x = step + axisLabelPadding + xMargin - ovalSize/2;
                int y = getHeight() - axisLabelPadding - 24 - ovalSize/2;
                g2d.fillOval(x, y, ovalSize, ovalSize);
                //g2d.drawString("" + i * xAxisStep % 24, x - 3, y + 15);//TODO non-static step %
                i++;
            }
        }

        if (yAxisStepSize > 10) {
            int i = 0;
            for (int step = getHeight(); step > -shift.y; step -= yAxisStepSize) {
                int x = axisLabelPadding + xMargin - ovalSize/2;
                int y = step - axisLabelPadding - yMargin - ovalSize/2;
                g2d.fillOval(x, y, ovalSize, ovalSize);
                g2d.drawString("" + i * yAxisStep, x - 32, y);
                i++;
            }
        }

        //Draw data
        if (dataSets != null) {
            for (DataSet dataSet : dataSets) {
                if (dataSet != null && dataSet.data != null) {
                    int dataX;
                    int dataY;
                    int lastDataX = 0;
                    int lastDataY = 0;
                    g2d.setColor(dataSet.color);
                    for (int i = 0; i < dataSet.data.length; i++) {
                        dataX = i *xAxisStepSize + axisLabelPadding + xMargin - ovalSize/2;
                        dataY = (int) ((getHeight() - axisLabelPadding - yMargin) - (dataSet.data[i] * ((double)yAxisStepSize / yAxisStep)) - (double)ovalSize/2);
                        g.drawOval(dataX, dataY, ovalSize, ovalSize);
                        if (lastDataX != 0 && lastDataY != 0) {
                            g.drawLine(lastDataX + ovalSize/2, lastDataY + ovalSize/2, dataX + ovalSize/2, dataY + ovalSize/2);
                        }
                        lastDataX = dataX;
                        lastDataY = dataY;
                    }
                }
            }
        }

        //Draw labels
        g2d.setColor(graphColor);
        g2d.drawString(yAxisLabel, axisLabelOffset, getHeight() - axisLabelPadding + 7);
        g2d.translate(axisLabelPadding, getHeight() - axisLabelOffset);
        g2d.rotate((3 * Math.PI) / 2);
        g2d.drawString(xAxisLabel, 0, -22);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        xAxisStepSize = (int) Math.max(xAxisStepSize + e.getPreciseWheelRotation(), 1);
        yAxisStepSize = (int) Math.max(yAxisStepSize + e.getPreciseWheelRotation(), 1);
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
