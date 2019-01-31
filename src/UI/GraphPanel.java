package UI;

import Application.ApplicationState;
import Statistic.GraphView;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    private ApplicationState applicationState;
    private InterfaceContext interfaceContext;

    private GraphView graphView;

    public GraphPanel() {
        applicationState = ApplicationState.getInstance();
        interfaceContext = InterfaceContext.getInstance();

        setMinimumSize(new Dimension(100, 400));
        setPreferredSize(new Dimension(100, 575));
        setBorder(Theme.getDefaultBorder());

        graphView =  new GraphView("Number of cars", "Hour of day", Color.BLACK);

        setLayout(new BorderLayout());
        add(graphView);

        setVisible(true);
    }

    public GraphView getGraphView() {
        return graphView;
    }

}
