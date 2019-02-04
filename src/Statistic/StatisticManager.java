package Statistic;

import UI.InterfaceContext;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticManager {

    private HashMap<Integer, DataSet> dataSetMap;

    /**
     * Constructor for the StatisticManager.
     */
    public StatisticManager() {
        dataSetMap = new HashMap<>();
    }

    /**
     * Adds a new dataset the the Manager wit the specified key.
     * @param key an identifiable key for the dataset.
     * @param dataSet the dataset to add.
     */
    public void putDataSet(Integer key, DataSet dataSet) {
        dataSetMap.put(key, dataSet);
    }

    /**
     * Adds a new value to a dataset based on a key
     * @param key an identifiable key for the dataset.
     * @param value the next value to be added to the dataset.
     */
    public void updateDataSet(Integer key, double value) {
        if (!dataSetMap.containsKey(key)) {
            return;
        }

        DataSet dataSet = dataSetMap.get(key);

        if (dataSet == null || dataSet.data == null) {
            return;
        }

        for (int i=1; i<dataSet.data.length; i++) {
            dataSet.data[i-1] = dataSet.data[i];
        }

        dataSet.data[dataSet.data.length-1] = value;

        updateView();
    }

    /**
     * Updates the GraphView based on the current dataSetList.
     */
    public void updateView() {
        ArrayList<DataSet> dataSetList = new ArrayList<>();

        for (DataSet dataSet : dataSetMap.values()) {
            dataSetList.add(dataSet);
        }

        InterfaceContext.getInstance().getGraphPanel().getGraphView().updateView(dataSetList);
    }

}

