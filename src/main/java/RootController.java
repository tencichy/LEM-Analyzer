import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class RootController {

    static MainController mainController;
    static Stage stage;
    static Stage dataRowStage = new Stage();
    static Stage chartsStage = new Stage();
    static boolean DEBUG;
    static boolean RAW;
    private ArrayList<DataRow> rawDataRows;
    private ArrayList<DataRow> repairedDataRows;

    @FXML
    public MenuItem showCurrentDataMenuButton;

    @FXML
    public MenuItem showChartsMenuButton;

    public void initialize(){}

    //Opening file with button in app
    public void handleOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to load");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file (*.*)","*.*"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            mainController.getData(convertFile(file.getPath()));
            showCurrentDataMenuButton.setDisable(false);
            showChartsMenuButton.setDisable(false);
            if(RAW){
                rawDataRows = convertFile(file.getPath());
                mainController.getData(convertFile(file.getPath()));
            }else {
                rawDataRows = convertFile(file.getPath());
                repairedDataRows = dataRepair(convertFile(file.getPath()));
                mainController.getData(repairedDataRows);
            }
            stage.setTitle("LEM - Analyzer - " + file.getName());
        }
    }

    //Opening file with passed argument
    public void handleOpen(String path){
        File file = new File(path);
        if(file.canRead() && mainController != null){
            showCurrentDataMenuButton.setDisable(false);
            showChartsMenuButton.setDisable(false);
            if(RAW){
                rawDataRows = convertFile(file.getPath());
                mainController.getData(convertFile(file.getPath()));
            }else {
                rawDataRows = convertFile(file.getPath());
                repairedDataRows = dataRepair(convertFile(file.getPath()));
                mainController.getData(repairedDataRows);
            }
            stage.setTitle("LEM - Analyzer - " + file.getName());
        }
    }

    //Show informations about program
    public void handleInformations(){
        new AlertGenerator("LEM - Analyzer - informations",
         "Roll Motor Icon - Created by Vitaly Gorbachev - Flaticon\n" +
                 "Pitch Motor Icon - Created by Vitaly Gorbachev - Flaticon\n" +
                 "Yaw Motor Icon - Adobe Stock - Standard License (Trial)"
                 ,Alert.AlertType.INFORMATION);
    }

    public void handleCharts(){
        try {
            ChartsController.rawDataRows = rawDataRows;
            ChartsController.repairedDataRows = repairedDataRows;
            FXMLLoader loaderMain = new FXMLLoader();
            loaderMain.setLocation(MainApp.class.getResource("chartsLayout.fxml"));
            AnchorPane anchorPane = loaderMain.load();
            chartsStage.setScene(new Scene(anchorPane));
            chartsStage.setResizable(false);
            chartsStage.setTitle("LEM - Analyzer - Charts Tool");
            chartsStage.show();
        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e, Alert.AlertType.ERROR);
        }
    }

    //Shows window with raw input data, and basic analysis
    public void showCurrentData(){
        try {
            DataRowController.rawDataRows = rawDataRows;
            DataRowController.repairedDataRows = repairedDataRows;
            FXMLLoader loaderMain = new FXMLLoader();
            loaderMain.setLocation(MainApp.class.getResource("dataRowLayout.fxml"));
            AnchorPane anchorPane = loaderMain.load();
            dataRowStage.setScene(new Scene(anchorPane));
            dataRowStage.setTitle("LEM - Analyzer - Raw Data View");
            dataRowStage.show();
        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e, Alert.AlertType.ERROR);
        }
    }

    // TODO: 10/29/2022 More versatile converting code
    //Converts text file to List of Objects, which are instances of DataRow Class
    private ArrayList<DataRow> convertFile(String path){
        String text;
        ArrayList<DataRow> toReturn = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while( (text = reader.readLine()) != null ){
                toReturn.add(new DataRow(
                        Short.parseShort(text.split(";")[0]),
                        Short.parseShort(text.split(";")[1]),
                        Short.parseShort(text.split(";")[2]),
                        Short.parseShort(text.split(";")[3]),
                        Short.parseShort(text.split(";")[4]),
                        Short.parseShort(text.split(";")[5]),
                        Short.parseShort(text.split(";")[6]),
                        Short.parseShort(text.split(";")[7]),
                        Short.parseShort(text.split(";")[8]),
                        Short.parseShort(text.split(";")[9]),
                        Short.parseShort(text.split(";")[10]),
                        Short.parseShort(text.split(";")[11]),
                        Short.parseShort(text.split(";")[12]),
                        Short.parseShort(text.split(";")[13]),
                        Short.parseShort(text.split(";")[14]),
                        Short.parseShort(text.split(";")[15]),
                        Short.parseShort(text.split(";")[16]),
                        Short.parseShort(text.split(";")[17]),
                        Short.parseShort(text.split(";")[18]),
                        Short.parseShort(text.split(";")[19])

                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e, Alert.AlertType.ERROR);
        }
        return toReturn;
    }

    //This method check if three next number are consistent, i.e. whether there is a sudden deviation in them
    private ArrayList<DataRow> dataRepair(ArrayList<DataRow> dataRows){
        DataRow lastElement = dataRows.get(dataRows.size()-1);
        lastElement.setSpeed((short)0);
        lastElement.setMotor_RPM((short)0);
        dataRows.set(dataRows.size()-1,lastElement);
        for (int i = 1; i < dataRows.size(); i++) {
                for (int j = 0; j < dataRows.get(i).getClass().getDeclaredFields().length; j++) {
                        try {
                            dataRows.get(i).getClass().getDeclaredFields()[j].setShort(dataRows.get(i), dataRowRepair(dataRows, i, j));
                        }catch (Exception e){
                            new TextAlertGenerator(e, Alert.AlertType.ERROR);
                        }
                }
        }
        return dataRows;
    }

    // TODO: 10/29/2022 Add repair report to app
    // TODO: 10/29/2022 Add user control over the process of repairing data
    // TODO: 10/30/2022 Add optional mapping for values
    private short dataRowRepair(@NotNull ArrayList<DataRow> dataRows, int i, int j){
        short toReturn = 0;
        try{
            toReturn = (short)dataRows.get(i).getClass().getDeclaredFields()[j].get(dataRows.get(i));
            short previousNumber = (short)dataRows.get(i-1).getClass().getDeclaredFields()[j].get(dataRows.get(i-1));
            short currentNumber = (short)dataRows.get(i).getClass().getDeclaredFields()[j].get(dataRows.get(i));
            switch (dataRows.get(i).getClass().getDeclaredFields()[j].getName()){
                case "Speed":
                    if(Math.abs(currentNumber-previousNumber) > 80){
                        for (int k = i+1; k < dataRows.size(); k++) {
                            if(Math.abs(currentNumber-(short)dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k))) > 80){
                                if(previousNumber == 0 && (short) dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k)) == 0){
                                    toReturn = 0;
                                    break;
                                }else{
                                    toReturn = (short) ((previousNumber+(short)dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k)))/2);
                                }
                            }
                        }
                    }else if(previousNumber == 0 && currentNumber != 0 && (short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)) == 0){
                        toReturn = 0;
                    }
                break;
                case "Motor_RPM":
                    if(Math.abs(currentNumber-previousNumber) > 4000){
                        for (int k = i+1; k < dataRows.size(); k++) {
                            if(Math.abs(currentNumber-(short)dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k))) > 4000){
                                if(previousNumber == 0 && (short) dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k)) == 0){
                                    toReturn = 0;
                                    break;
                                }else{
                                    toReturn = (short) ((previousNumber+(short)dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k)))/2);
                                }
                            }
                        }
                    }else if(previousNumber == 0 && currentNumber != 0 && (short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)) == 0){
                        toReturn = 0;
                    }else if(previousNumber == 0 && Math.abs(currentNumber-previousNumber) < 20){
                        for (int k = i+1; k < dataRows.size(); k++) {
                            if((short) dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k)) == 0){
                                toReturn = 0;
                                break;
                            }
                        }
                    }
                break;
                case "Motor_Temp":
                case "Controller_Temp":
                case "T1_Contactor_Temp":
                case "T2_SSR_Temp":
                case "T3_Inverter_Temp":
                case "Battery_Voltage":
                    if(Math.abs(currentNumber-previousNumber) > 10 && currentNumber == 0){
                        for (int k = i+1; k < dataRows.size(); k++) {
                            if(Math.abs(currentNumber-(short)dataRows.get(k).getClass().getDeclaredFields()[j].get(dataRows.get(k))) == 0){
                                if(previousNumber != 0 && (short) dataRows.get(k+1).getClass().getDeclaredFields()[j].get(dataRows.get(k+1)) != 0){
                                    toReturn = (short) ((previousNumber+(short)dataRows.get(k+1).getClass().getDeclaredFields()[j].get(dataRows.get(k+1)))/2);
                                    break;
                                }
                            }
                            if(k-i > 100){
                                break;
                            }
                        }
                    }else if(previousNumber != 0 && currentNumber == 0 && (short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)) != 0){
                        toReturn = (short)((previousNumber+(short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)))/2);
                    }
                break;
                case "Rear_Susp_Min":
                case "Rear_Susp_Travel":
                case "Rear_Susp_Max":
                case "Front_Susp_Min":
                case "Front_Susp_Travel":
                case "Front_Susp_Max":
                    if(previousNumber != 0 && currentNumber == 0 && (short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)) != 0){
                        toReturn = (short)((previousNumber+(short)dataRows.get(i+1).getClass().getDeclaredFields()[j].get(dataRows.get(i+1)))/2);
                    }
                break;
            }

        }catch (Exception e){
            e.printStackTrace();
            new TextAlertGenerator(e , Alert.AlertType.ERROR);
        }
        return toReturn;
    }

}
