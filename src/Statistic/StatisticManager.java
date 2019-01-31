package Statistic;

import UI.InterfaceContext;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticManager {

    private HashMap<Integer, DataSet> dataSetMap;

    public StatisticManager() {
        dataSetMap = new HashMap<>();
    }

    public void putDataSet(Integer key, DataSet dataSet) {
        dataSetMap.put(key, dataSet);
    }

    public DataSet getDataSet(Integer key) {
        if (!dataSetMap.containsKey(key)) {
            return null;
        }

        return dataSetMap.get(key);
    }

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

    public void updateView() {
        ArrayList<DataSet> dataSetList = new ArrayList<>();

        for (DataSet dataSet : dataSetMap.values()) {
            dataSetList.add(dataSet);
        }

        InterfaceContext.getInstance().getGraphPanel().getGraphView().updateView(dataSetList);
    }

}

