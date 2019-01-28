package Statistic;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticManager {

    private StatisticWindow window;
    private int dataPos;

    private HashMap<Integer, DataSetCursor> dataSetMap;

    public StatisticManager(StatisticWindow window) {
        this.window = window;
        dataSetMap = new HashMap<>();
    }

    public void putDataSet(Integer key, DataSet dataSet) {
        dataSetMap.put(key, new DataSetCursor(dataSet));
    }

    public DataSet getDataSet(Integer key) {
        if (!dataSetMap.containsKey(key)) {
            return null;
        }

        return dataSetMap.get(key).dataSet;
    }

    public void updateDataSet(Integer key, double value) {
        if (!dataSetMap.containsKey(key)) {
            return;
        }

        DataSetCursor data = dataSetMap.get(key);

        if (data == null || data.dataSet == null || data.dataSet.data == null) {
            return;
        }

        if (data.cursor > data.dataSet.data.length - 1) {
            data.cursor = 0;
        }

        data.dataSet.data[data.cursor] = value;
        data.cursor++;

        updateView();
    }

    public void updateView() {
        ArrayList<DataSet> dataSetList = new ArrayList<>();

        for (DataSetCursor data : dataSetMap.values()) {
            dataSetList.add(data.dataSet);
        }

        if (window.getStatisticView() instanceof GraphView) {
            GraphView graphView = (GraphView) window.getStatisticView();
            graphView.updateView(dataSetList);
        }
    }

    private class DataSetCursor {

        public int cursor;
        public DataSet dataSet;

        public DataSetCursor(DataSet dataSet) {
            this.cursor = 0;
            this.dataSet = dataSet;
        }

    }

}

