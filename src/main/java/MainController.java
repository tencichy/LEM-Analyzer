import eu.hansolo.medusa.Gauge;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    public static boolean DEBUG;
    private ArrayList<DataRow> dataRows;
    private int timerPosition = 0;


    private Timer timer;

    @FXML
    public Button fastBackwardButton;

    @FXML
    public Button backwardButton;

    @FXML
    public Button playButton;

    @FXML
    public Button forwardButton;

    @FXML
    public Button fastForwardButton;

    @FXML
    public Slider timelineSlider;

    @FXML
    public Label timeLabel;

    @FXML
    public Label dataRowsLabel;

    @FXML
    public Spinner<Integer> multiplierSpinner;

    @FXML
    public Gauge speedGauge;

    @FXML
    public Gauge rpmGauge;

    @FXML
    public Gauge motorTemperatureGauge;

    @FXML
    public Gauge controllerTemperatureGauge;

    @FXML
    public Gauge T1TemperatureGauge;

    @FXML
    public Gauge T2TemperatureGauge;

    @FXML
    public Gauge T3TemperatureGauge;

    @FXML
    public Gauge voltageGauge;

    @FXML
    public HBox controlsHBox;

    @FXML
    public ImageView rollMotorImageView;

    @FXML
    public Label rollLabel;

    @FXML
    public ImageView pitchMotorImageView;

    @FXML
    public Label pitchLabel;

    @FXML
    public ImageView yawMotorImageView;

    @FXML
    public Label yawLabel;

    @FXML
    public RadioButton accelerationPoint;

    @FXML
    public Line accelerationLine;

    @FXML
    public Label accYLabel;

    @FXML
    public Label accXLabel;

    @FXML
    public RadioButton accelerationPointZ;

    @FXML
    public Label accZLabel;

    @FXML
    public Rectangle backSusp;

    @FXML
    public Rectangle frontSusp1;

    @FXML
    public Rectangle frontSusp2;

    @FXML
    public Label frontSuspMinLabel;

    @FXML
    public Label frontSuspTravelLabel;

    @FXML
    public Label frontSuspMaxLabel;

    @FXML
    public Label backSuspMinLabel;

    @FXML
    public Label backSuspTravelLabel;

    @FXML
    public Label backSuspMaxLabel;

    @FXML
    public Label tempoLabel;

    public void initialize(){

        multiplierSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,32,1));

        controlsHBox.setDisable(true);
        tempoLabel.setDisable(true);

        multiplierSpinner.setOnMouseClicked(x -> {
            tempoLabel.setText(multiplierSpinner.getValue()*4 + " records per second");
        });

        playButton.setOnMouseClicked(x -> {
            if(playButton.getText().equals("Play")){
                start();
                multiplierSpinner.setDisable(true);
                playButton.setText("Pause");
            }else{
                stop();
                multiplierSpinner.setDisable(false);
                playButton.setText("Play");
            }
        });

        fastBackwardButton.setOnMouseClicked(x -> {
            if(timerPosition < 1){
                timerPosition = dataRows.size() -4;
            }else{
                timerPosition -= timerPosition%4==0 ? 4 : timerPosition%4;
            }
            updateData(timerPosition);
        });
        backwardButton.setOnMouseClicked(x -> {
            if(timerPosition < 1){
                timerPosition = dataRows.size() -1;
            }else{
                timerPosition -= 1;
            }
            updateData(timerPosition);
        });
        fastForwardButton.setOnMouseClicked(x -> {
            if(timerPosition > dataRows.size()-4-1){
                timerPosition = 0;
            }else{
                timerPosition += timerPosition%4==0 ? 4 : timerPosition%4;
            }
            updateData(timerPosition);
        });
        forwardButton.setOnMouseClicked(x -> {
            if(timerPosition > dataRows.size()-1-1){
                timerPosition = 0;
            }else{
                timerPosition += 1;
            }
            updateData(timerPosition);
        });

        timelineSlider.setOnMousePressed(x -> {
            if(timer != null){
                stop();
            }
        });
        timelineSlider.setOnMouseReleased(x -> {
            timerPosition = (int) timelineSlider.getValue();
            if(playButton.getText().equals("Pause")) {
                start();
            }
        });
        timelineSlider.setOnMouseDragged(x -> {
            timerPosition = (int) timelineSlider.getValue();
            dataRowsLabel.setText(timerPosition + " / " + dataRows.size());
            updateData(timerPosition);
        });
        timelineSlider.setOnMouseClicked(x -> {
            timerPosition = (int) timelineSlider.getValue();
            dataRowsLabel.setText(timerPosition + " / " + dataRows.size());
            updateData(timerPosition);
        });

    }

    void getData(ArrayList<DataRow> data){
        dataRows = data;
        timelineSlider.setMax(dataRows.size()-1);
        timelineSlider.setBlockIncrement(1);
        dataRowsLabel.setText("0 / " + (dataRows.size()-1));
        timelineSlider.setValue(0);
        timerPosition = 0;
        updateData(timerPosition);
        controlsHBox.setDisable(false);
        tempoLabel.setDisable(false);
    }

    void stop(){
        timer.cancel();
        timer.purge();
    }

    void start(){

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(timerPosition > dataRows.size()-2){
                    timerPosition = 0;
                }else {
                    Platform.runLater(()-> updateData(timerPosition));
                    timerPosition++;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0,  250/multiplierSpinner.getValue());
    }

    private void updateData(int timerPosition){
        Platform.runLater(() -> dataRowsLabel.setText(timerPosition + " / " + (dataRows.size()-1)));
        timelineSlider.setValue(timerPosition);

        speedGauge.setValue(dataRows.get(timerPosition).getSpeed());
        rpmGauge.setValue(dataRows.get(timerPosition).getMotor_RPM());
        motorTemperatureGauge.setValue(dataRows.get(timerPosition).getMotor_Temp());
        controllerTemperatureGauge.setValue(dataRows.get(timerPosition).getController_Temp());
        T1TemperatureGauge.setValue(dataRows.get(timerPosition).getT1_Contactor_Temp());
        T2TemperatureGauge.setValue(dataRows.get(timerPosition).getT2_SSR_Temp());
        T3TemperatureGauge.setValue(dataRows.get(timerPosition).getT3_Inverter_Temp());
        voltageGauge.setValue(dataRows.get(timerPosition).getBattery_Voltage());

        rollMotorImageView.setRotate(dataRows.get(timerPosition).getRoll());
        rollLabel.setText("Roll: " + dataRows.get(timerPosition).getRoll());

        pitchMotorImageView.setRotate(dataRows.get(timerPosition).getPitch()*-1);
        pitchLabel.setText("Pitch: " + dataRows.get(timerPosition).getPitch()*-1);

        yawMotorImageView.setRotate(dataRows.get(timerPosition).getYaw());
        yawLabel.setText("Yaw: " + dataRows.get(timerPosition).getYaw());

        //Center for XY is X: 850 Y: 446, center for Z is X: 712 Y: 446
        // TODO: 10/30/2022 Add option to get maximum values form provided file with data
        accYLabel.setText("Accelerometer Y: " + dataRows.get(timerPosition).getAcc_Y());
        accXLabel.setText("Accelerometer X: " + dataRows.get(timerPosition).getAcc_X());
        accZLabel.setText("Accelerometer Z: " + dataRows.get(timerPosition).getAcc_Z());
        int accX;
        if(dataRows.get(timerPosition).getAcc_X() >= 0){
            accX = map(dataRows.get(timerPosition).getAcc_X(),0,4000, 850,921);

        }else{
            accX = map(dataRows.get(timerPosition).getAcc_X(),-4000,0, 779,850);
        }
        int accY;
        if(dataRows.get(timerPosition).getAcc_Y() >= 0){
            accY = map(dataRows.get(timerPosition).getAcc_Y(),0,6400,446,375);
        }else{
            accY = map(dataRows.get(timerPosition).getAcc_Y(),-6400,0,517,446);
        }
        int accZ;
        if(dataRows.get(timerPosition).getAcc_Z() >= 0){
            accZ = map(dataRows.get(timerPosition).getAcc_Z(),0,7800,446,375);
        }else{
            accZ = map(dataRows.get(timerPosition).getAcc_Z(),-7800,0,517,446);
        }
        accelerationPoint.setLayoutX(accX);
        accelerationPoint.setLayoutY(accY);
        accelerationPointZ.setLayoutY(accZ);
        if(accX > 0){
            accelerationLine.setEndX(accX-850+184);
        }else {
            accelerationLine.setEndX(accX+850+184);
        }
        if(accY > 0){
            accelerationLine.setEndY(accY-446-64);
        }else {
            accelerationLine.setEndY(accY+446-64);
        }


        backSuspMaxLabel.setText("Max: " + dataRows.get(timerPosition).getRear_Susp_Max());
        backSuspTravelLabel.setText("Travel: " + dataRows.get(timerPosition).getRear_Susp_Travel());
        backSuspMinLabel.setText("Min: " + dataRows.get(timerPosition).getRear_Susp_Min());
        frontSuspMaxLabel.setText("Max: " + dataRows.get(timerPosition).getFront_Susp_Max());
        frontSuspTravelLabel.setText("Travel: " + dataRows.get(timerPosition).getFront_Susp_Travel());
        frontSuspMinLabel.setText("Min: " + dataRows.get(timerPosition).getFront_Susp_Min());

        //Suspension 0 pos Y: 433, Max pos Y: 453, Min pos Y: 413
        backSusp.setLayoutY(443 + dataRows.get(timerPosition).getRear_Susp_Travel()*-1);
        frontSusp1.setLayoutY(416 + dataRows.get(timerPosition).getFront_Susp_Travel()*-1);
        frontSusp2.setLayoutY(416 + dataRows.get(timerPosition).getFront_Susp_Travel()*-1);
    }


    private int map(int x, int in_min, int in_max, int out_min, int out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

}
