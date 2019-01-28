import Statistic.DataSet;
import Statistic.GraphView;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

public class GraphViewTest {

    @Test
    public void testView() {

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        ArrayList<DataSet> dataSetList = new ArrayList<>();
        GraphView graphView = new GraphView("X Axis", "Y Axis", Color.BLACK);

        JFrame frame = new JFrame("GraphViewTest");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(graphView);
        frame.setVisible(true);


        int x = 0;
        while (frame.isVisible()) {
            try {
                dataSetList.clear();
                double[] dataSet = new double[100];
                for (int i=0; i<dataSet.length; i++) {
                    dataSet[i] = Math.sin((double)(i+x)/2) * 3 + 5;
                }
                dataSetList.add(new DataSet(dataSet, Color.GREEN));

                dataSet = new double[100];
                for (int i=0; i<dataSet.length; i++) {
                    dataSet[i] = Math.sin((double)(i+x)/5) * 10 + 20;
                }
                dataSetList.add(new DataSet(dataSet, Color.BLUE));
                graphView.updateView(dataSetList);
                x++;
                Thread.sleep(33);
            } catch (InterruptedException e) {
                fail();
            }
        }
    }

}
