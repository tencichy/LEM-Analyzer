import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChartsController {

    public static ArrayList<DataRow> rawDataRows;
    public static ArrayList<DataRow> repairedDataRows;

    private HashMap<String, HashMap<Integer, Short>> chartData = new HashMap<>();

    private final HashMap<String, Integer> dataRawClassFields = new HashMap<>() {{
        put("Speed",0);
        put("Motor_RPM",1);
        put("Motor_Temp",2);
        put("Controller_Temp",3);
        put("Battery_Voltage",4);
        put("Rear_Susp_Min",5);
        put("Rear_Susp_Travel",6);
        put("Rear_Susp_Max",7);
        put("Front_Susp_Min",8);
        put("Front_Susp_Travel",9);
        put("Front_Susp_Max",10);
        put("T1_Contactor_Temp",11);
        put("T2_SSR_Temp",12);
        put("T3_Inverter_Temp",13);
        put("Yaw",14);
        put("Pitch",15);
        put("Roll",16);
        put("Acc_X",17);
        put("Acc_Y",18);
        put("Acc_Z",19);
    }};

    @FXML
    public ChoiceBox<String> singleInputDataChoiceBox;

    @FXML
    public Spinner<Integer> singleDataRangeFromSpinner;

    @FXML
    public Spinner<Integer> singleDataRangeToSpinner;

    @FXML
    public Spinner<Integer> singleDataResolutionSpinner;

    @FXML
    public CheckBox singleDataAllCheckBox;

    @FXML
    public CheckBox singleDataUnfilteredCheckBox;

    @FXML
    public ListView<String> dataListView;

    @FXML
    public CheckBox showPointsCheckBox;

    public void initialize(){

        for (Field f: DataRow.class.getDeclaredFields()) {
            singleInputDataChoiceBox.getItems().add(f.getName());
        }

        singleDataResolutionSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,128,2));

        singleDataRangeToSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,repairedDataRows.size()-1,1));
        singleDataRangeFromSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,0,1));
        singleDataRangeToSpinner.setOnMouseClicked(x -> {
            int value = singleDataRangeFromSpinner.getValue();
            singleDataRangeFromSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,singleDataRangeToSpinner.getValue()-1,1));
            singleDataRangeFromSpinner.getValueFactory().setValue(value);
        });

        singleDataRangeToSpinner.focusedProperty().addListener((obs, oldVal, newVal) -> {
            int value = singleDataRangeFromSpinner.getValue();
            singleDataRangeFromSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,singleDataRangeToSpinner.getValue()-1,1));
            singleDataRangeFromSpinner.getValueFactory().setValue(value);
        });

        singleDataAllCheckBox.setOnMouseClicked(x -> {
            if(singleDataAllCheckBox.isSelected()){
                singleDataRangeFromSpinner.setDisable(true);
                singleDataRangeToSpinner.setDisable(true);
            }else{
                singleDataRangeFromSpinner.setDisable(false);
                singleDataRangeToSpinner.setDisable(false);
            }
        });

    }

    public void handleAddData(){
        if(singleInputDataChoiceBox.getValue() != null){
            if(singleDataAllCheckBox.isSelected()){
                ArrayList<Short> dataToUse = new ArrayList<>();
                try {
                    if (singleDataUnfilteredCheckBox.isSelected()) {
                        for (DataRow d : rawDataRows) {
                            dataToUse.add((short) d.getClass().getDeclaredFields()[dataRawClassFields.get(singleInputDataChoiceBox.getValue())].get(d));
                        }
                    } else {
                        for (DataRow d : repairedDataRows) {
                            dataToUse.add((short) d.getClass().getDeclaredFields()[dataRawClassFields.get(singleInputDataChoiceBox.getValue())].get(d));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    new TextAlertGenerator(e, Alert.AlertType.ERROR);
                }
                if(singleDataUnfilteredCheckBox.isSelected()){
                    chartData.put(singleInputDataChoiceBox.getValue() + " - Unfiltered - Resolution: " + singleDataResolutionSpinner.getValue(), averageData(dataToUse, singleDataResolutionSpinner.getValue(),0));
                    dataListView.getItems().add(singleInputDataChoiceBox.getValue() + " - Unfiltered - Resolution: " + singleDataResolutionSpinner.getValue());
                }else{
                    chartData.put(singleInputDataChoiceBox.getValue() + " - Filtered - Resolution: " + singleDataResolutionSpinner.getValue(), averageData(dataToUse, singleDataResolutionSpinner.getValue(),0));
                    dataListView.getItems().add(singleInputDataChoiceBox.getValue() + " - Filtered - Resolution: " + singleDataResolutionSpinner.getValue());
                }
            }else{
                ArrayList<Short> dataToUse = new ArrayList<>();
                try {
                    if (singleDataUnfilteredCheckBox.isSelected()) {
                        for (int i = singleDataRangeFromSpinner.getValue(); i < singleDataRangeToSpinner.getValue(); i++) {
                            dataToUse.add((short) rawDataRows.get(i).getClass().getDeclaredFields()[dataRawClassFields.get(singleInputDataChoiceBox.getValue())].get(rawDataRows.get(i)));
                        }
                    } else {
                        for (int i = singleDataRangeFromSpinner.getValue(); i < singleDataRangeToSpinner.getValue(); i++) {
                            dataToUse.add((short) repairedDataRows.get(i).getClass().getDeclaredFields()[dataRawClassFields.get(singleInputDataChoiceBox.getValue())].get(repairedDataRows.get(i)));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    new TextAlertGenerator(e, Alert.AlertType.ERROR);
                }
                if(singleDataUnfilteredCheckBox.isSelected()){
                    chartData.put(singleInputDataChoiceBox.getValue() + " - Unfiltered - Resolution: " + singleDataResolutionSpinner.getValue(), averageData(dataToUse, singleDataResolutionSpinner.getValue(),singleDataRangeFromSpinner.getValue()));
                    dataListView.getItems().add(singleInputDataChoiceBox.getValue() + " - Unfiltered - Resolution: " + singleDataResolutionSpinner.getValue());
                }else{
                    chartData.put(singleInputDataChoiceBox.getValue() + " - Filtered - Resolution: " + singleDataResolutionSpinner.getValue(), averageData(dataToUse, singleDataResolutionSpinner.getValue(),singleDataRangeFromSpinner.getValue()));
                    dataListView.getItems().add(singleInputDataChoiceBox.getValue() + " - Filtered - Resolution: " + singleDataResolutionSpinner.getValue());
                }
            }
        }
    }

    public void handleDelete(){
        if(dataListView.getSelectionModel().getSelectedItem() != null){
            chartData.remove(dataListView.getSelectionModel().getSelectedItem());
            dataListView.getItems().remove(dataListView.getSelectionModel().getSelectedItem());
        }
    }

    public void handleGenerate(){
                 if(chartData.size() > 0) {
                     int maxYaxis = 0;
                     boolean hasNegativeNumbers = false;
                     for (Map.Entry e : chartData.entrySet()) {
                         HashMap<Integer,Short> map = (HashMap<Integer, Short>) e.getValue();
                         if(Math.abs(Collections.max(map.values()))>Math.abs(Collections.min(map.values()))){
                             maxYaxis = Collections.max(map.values())>maxYaxis ? Collections.max(map.values()) : maxYaxis;
                         }else{
                             maxYaxis = Math.abs(Collections.min(map.values()))>maxYaxis ? Math.abs(Collections.max(map.values())) : maxYaxis;
                         }
                         hasNegativeNumbers = Collections.min(map.values()) < 0;
                     }
                     NumberAxis yAxis;
                     if(hasNegativeNumbers) {
                            yAxis = new NumberAxis(-1 * maxYaxis - 20, maxYaxis + 20, 100);
                     }else{
                         yAxis = new NumberAxis(0,maxYaxis + 20,100);
                     }
                     yAxis.setLabel("Values");
                     NumberAxis xAxis = new NumberAxis(0, repairedDataRows.size(), 100);
                     xAxis.setLabel("Position");
                     LineChart lineChart = new LineChart(xAxis, yAxis);
                     for (Map.Entry e : chartData.entrySet()) {
                        XYChart.Series series = new XYChart.Series();
                        series.setName(e.getKey().toString());
                        HashMap<Integer,Short> map = (HashMap<Integer, Short>) e.getValue();
                         for (Map.Entry<Integer, Short> m: map.entrySet()) {
                             series.getData().add(new XYChart.Data<>(m.getKey(), m.getValue()));
                         }
                         lineChart.getData().add(series);
                     }

                     lineChart.setPrefWidth(1200);
                     if(maxYaxis > 1000){
                         lineChart.setPrefHeight(800);
                     }else {
                         lineChart.setPrefHeight(400);
                     }
                     lineChart.setCreateSymbols(showPointsCheckBox.isSelected());
                     Stage chartStage = new Stage();
                     chartStage.setResizable(false);
                     Group group = new Group(lineChart);
                     if(maxYaxis > 1000){
                         chartStage.setScene(new Scene(group, 1200,800));
                     }else {
                         chartStage.setScene(new Scene(group, 1200, 400));
                     }
                     chartStage.setTitle("LEM - Analyzer - Chart View");
                     chartStage.show();
                 }
    }

    private HashMap<Integer, Short> averageData(ArrayList<Short> inputData, int resolution, int startPos){
        HashMap<Integer,Short> toReturn = new HashMap<>();
        for (int i = 0; i < inputData.size();  i+=resolution) {
            toReturn.put(startPos+i,inputData.get(i));
        }
        return toReturn;
    }

}
