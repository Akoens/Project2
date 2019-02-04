package UI;

import Application.ApplicationState;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class SimulatorFrame extends JFrame {

    private ApplicationState applicationState;
    private InterfaceContext interfaceContext;

    /**
     * Constructor for the simulator frame with no parameters and set values.
     */
    public SimulatorFrame() {
        super("Parkeer Beheer");

        applicationState = ApplicationState.getInstance();
        interfaceContext = InterfaceContext.getInstance();
        interfaceContext.init();

        initWindow();
        applicationState.start();
    }

    /**
     * Method to initialize the window for the simulator frame.
     */
    private void initWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1600, 900));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JComponent component = initComponents();
        JPanel content = new JPanel(new BorderLayout());
        content.add(component, BorderLayout.CENTER);
        add(content, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    /**
     * @return the split-view of the main frame with the corresponding panels.
     * Graphpanel, Carfeedpanel and the Detailpanel.
     */
    private JComponent initComponents() {
        JPanel simulatorControlsPanel = new JPanel(new BorderLayout());
        simulatorControlsPanel.setMinimumSize(new Dimension(432, 500));
        simulatorControlsPanel.add(interfaceContext.getHeaderPanel(), BorderLayout.PAGE_START);
        simulatorControlsPanel.add(interfaceContext.getSimulatorPanel(), BorderLayout.CENTER);
        simulatorControlsPanel.add(interfaceContext.getControlPanel(), BorderLayout.PAGE_END);

        JSplitPane[] splitPanes = new JSplitPane[3];
        splitPanes[0] = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, interfaceContext.getGraphPanel(), interfaceContext.getCarFeedPanel());
        splitPanes[1] = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, simulatorControlsPanel, splitPanes[0]);
        splitPanes[2] = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, interfaceContext.getDetailPanel(), splitPanes[1]);

        for (JSplitPane splitPane : splitPanes) {
            splitPane.setDividerSize(5);
            splitPane.setUI(new BasicSplitPaneUI() {
                public BasicSplitPaneDivider createDefaultDivider () {
                    return new BasicSplitPaneDivider (this) {
                        public void paint (Graphics g) {
                            g.setColor (Color.WHITE);
                            g.fillRect (0, 0, getSize ().width, getSize ().height);
                            super.paint (g);
                        }
                    };
                }
            });
        }

        return splitPanes[splitPanes.length-1];
    }

}
