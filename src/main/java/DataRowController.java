import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class DataRowController {

    public static ArrayList<DataRow> rawDataRows;
    public static ArrayList<DataRow> repairedDataRows;

    @FXML
    public ListView<DataRow> rawDataRowListView;

    @FXML
    public ListView<DataRow> repairedDataRowListView;

    @FXML
    public TextArea specialDataTextArea;

    public void initialize(){

         short Speed_Max_Value = 0;
         short Motor_RPM_Max_Value = 0;
         short Motor_Temp_Max_Value = 0;
         short Controller_Temp_Max_Value = 0;
         short Battery_Voltage_Max_Value = 0;
         short Rear_Susp_Min_Max_Value = 0;
         short Rear_Susp_Travel_Max_Value = 0;
         short Rear_Susp_Max_Max_Value = 0;
         short Front_Susp_Min_Max_Value = 0;
         short Front_Susp_Travel_Max_Value = 0;
         short Front_Susp_Max_Max_Value = 0;
         short T1_Contactor_Temp_Max_Value = 0;
         short T2_SSR_Temp_Max_Value = 0;
         short T3_Inverter_Temp_Max_Value = 0;
         short Yaw_Max_Value = 0;
         short Pitch_Max_Value = 0;
         short Roll_Max_Value = 0;
         short Acc_X_Max_Value = 0;
         short Acc_Y_Max_Value = 0;
         short Acc_Z_Max_Value = 0;

        short Speed_Max_Value_R = 0;
        short Motor_RPM_Max_Value_R = 0;
        short Motor_Temp_Max_Value_R = 0;
        short Controller_Temp_Max_Value_R = 0;
        short Battery_Voltage_Max_Value_R = 0;
        short Rear_Susp_Min_Max_Value_R = 0;
        short Rear_Susp_Travel_Max_Value_R = 0;
        short Rear_Susp_Max_Max_Value_R = 0;
        short Front_Susp_Min_Max_Value_R = 0;
        short Front_Susp_Travel_Max_Value_R = 0;
        short Front_Susp_Max_Max_Value_R = 0;
        short T1_Contactor_Temp_Max_Value_R = 0;
        short T2_SSR_Temp_Max_Value_R = 0;
        short T3_Inverter_Temp_Max_Value_R = 0;
        short Yaw_Max_Value_R = 0;
        short Pitch_Max_Value_R = 0;
        short Roll_Max_Value_R = 0;
        short Acc_X_Max_Value_R = 0;
        short Acc_Y_Max_Value_R = 0;
        short Acc_Z_Max_Value_R = 0;

        for (DataRow d: rawDataRows) {
            rawDataRowListView.getItems().add(d);

            Speed_Max_Value = Math.abs(d.getSpeed())>Math.abs(Speed_Max_Value) ? d.getSpeed() : Speed_Max_Value;
            Motor_RPM_Max_Value = Math.abs(d.getMotor_RPM())>Math.abs(Motor_RPM_Max_Value) ? d.getMotor_RPM() : Motor_RPM_Max_Value;
            Motor_Temp_Max_Value = Math.abs(d.getMotor_Temp())>Math.abs(Motor_Temp_Max_Value) ? d.getMotor_Temp() : Motor_Temp_Max_Value;
            Controller_Temp_Max_Value = Math.abs(d.getController_Temp())>Math.abs(Controller_Temp_Max_Value) ? d.getController_Temp() : Controller_Temp_Max_Value;
            Battery_Voltage_Max_Value = Math.abs(d.getBattery_Voltage())>Math.abs(Battery_Voltage_Max_Value) ? d.getBattery_Voltage() : Battery_Voltage_Max_Value;
            Rear_Susp_Min_Max_Value = Math.abs(d.getRear_Susp_Min())>Math.abs(Rear_Susp_Min_Max_Value) ? d.getRear_Susp_Min() : Rear_Susp_Min_Max_Value;
            Rear_Susp_Travel_Max_Value = Math.abs(d.getRear_Susp_Travel())>Math.abs(Rear_Susp_Travel_Max_Value) ? d.getRear_Susp_Travel() : Rear_Susp_Travel_Max_Value;
            Rear_Susp_Max_Max_Value = Math.abs(d.getRear_Susp_Max())>Math.abs(Rear_Susp_Max_Max_Value) ? d.getRear_Susp_Max() : Rear_Susp_Max_Max_Value;
            Front_Susp_Min_Max_Value = Math.abs(d.getFront_Susp_Min())>Math.abs(Front_Susp_Min_Max_Value) ? d.getFront_Susp_Min() : Front_Susp_Min_Max_Value;
            Front_Susp_Travel_Max_Value = Math.abs(d.getFront_Susp_Travel())>Math.abs(Front_Susp_Travel_Max_Value) ? d.getFront_Susp_Travel() : Front_Susp_Travel_Max_Value;
            Front_Susp_Max_Max_Value = Math.abs(d.getFront_Susp_Max())>Math.abs(Front_Susp_Max_Max_Value) ? d.getFront_Susp_Max() : Front_Susp_Max_Max_Value;
            T1_Contactor_Temp_Max_Value = Math.abs(d.getT1_Contactor_Temp())>Math.abs(T1_Contactor_Temp_Max_Value) ? d.getT1_Contactor_Temp() : T1_Contactor_Temp_Max_Value;
            T2_SSR_Temp_Max_Value = Math.abs(d.getT2_SSR_Temp())>Math.abs(T2_SSR_Temp_Max_Value) ? d.getT2_SSR_Temp() : T2_SSR_Temp_Max_Value;
            T3_Inverter_Temp_Max_Value = Math.abs(d.getT3_Inverter_Temp())>Math.abs(T3_Inverter_Temp_Max_Value) ? d.getT3_Inverter_Temp() : T3_Inverter_Temp_Max_Value;
            Yaw_Max_Value = Math.abs(d.getYaw())>Math.abs(Yaw_Max_Value) ? d.getYaw() : Yaw_Max_Value;
            Pitch_Max_Value = Math.abs(d.getPitch())>Math.abs(Pitch_Max_Value) ? d.getPitch() : Pitch_Max_Value;
            Roll_Max_Value = Math.abs(d.getRoll())>Math.abs(Roll_Max_Value) ? d.getRoll() : Roll_Max_Value;
            Acc_X_Max_Value = Math.abs(d.getAcc_X())>Math.abs(Acc_X_Max_Value) ? d.getAcc_X() : Acc_X_Max_Value;
            Acc_Y_Max_Value = Math.abs(d.getAcc_Y())>Math.abs(Acc_Y_Max_Value) ? d.getAcc_Y() : Acc_Y_Max_Value;
            Acc_Z_Max_Value = Math.abs(d.getAcc_Z())>Math.abs(Acc_Z_Max_Value) ? d.getAcc_Z() : Acc_Z_Max_Value;
        }

        if(repairedDataRows != null) {
            for (DataRow d : repairedDataRows) {
                repairedDataRowListView.getItems().add(d);

                Speed_Max_Value_R = Math.abs(d.getSpeed())>Math.abs(Speed_Max_Value_R) ? d.getSpeed() : Speed_Max_Value_R;
                Motor_RPM_Max_Value_R = Math.abs(d.getMotor_RPM())>Math.abs(Motor_RPM_Max_Value_R) ? d.getMotor_RPM() : Motor_RPM_Max_Value_R;
                Motor_Temp_Max_Value_R = Math.abs(d.getMotor_Temp())>Math.abs(Motor_Temp_Max_Value_R) ? d.getMotor_Temp() : Motor_Temp_Max_Value_R;
                Controller_Temp_Max_Value_R = Math.abs(d.getController_Temp())>Math.abs(Controller_Temp_Max_Value_R) ? d.getController_Temp() : Controller_Temp_Max_Value_R;
                Battery_Voltage_Max_Value_R = Math.abs(d.getBattery_Voltage())>Math.abs(Battery_Voltage_Max_Value_R) ? d.getBattery_Voltage() : Battery_Voltage_Max_Value_R;
                Rear_Susp_Min_Max_Value_R = Math.abs(d.getRear_Susp_Min())>Math.abs(Rear_Susp_Min_Max_Value_R) ? d.getRear_Susp_Min() : Rear_Susp_Min_Max_Value_R;
                Rear_Susp_Travel_Max_Value_R = Math.abs(d.getRear_Susp_Travel())>Math.abs(Rear_Susp_Travel_Max_Value_R) ? d.getRear_Susp_Travel() : Rear_Susp_Travel_Max_Value_R;
                Rear_Susp_Max_Max_Value_R = Math.abs(d.getRear_Susp_Max())>Math.abs(Rear_Susp_Max_Max_Value_R) ? d.getRear_Susp_Max() : Rear_Susp_Max_Max_Value_R;
                Front_Susp_Min_Max_Value_R = Math.abs(d.getFront_Susp_Min())>Math.abs(Front_Susp_Min_Max_Value_R) ? d.getFront_Susp_Min() : Front_Susp_Min_Max_Value_R;
                Front_Susp_Travel_Max_Value_R = Math.abs(d.getFront_Susp_Travel())>Math.abs(Front_Susp_Travel_Max_Value_R) ? d.getFront_Susp_Travel() : Front_Susp_Travel_Max_Value_R;
                Front_Susp_Max_Max_Value_R = Math.abs(d.getFront_Susp_Max())>Math.abs(Front_Susp_Max_Max_Value_R) ? d.getFront_Susp_Max() : Front_Susp_Max_Max_Value_R;
                T1_Contactor_Temp_Max_Value_R = Math.abs(d.getT1_Contactor_Temp())>Math.abs(T1_Contactor_Temp_Max_Value_R) ? d.getT1_Contactor_Temp() : T1_Contactor_Temp_Max_Value_R;
                T2_SSR_Temp_Max_Value_R = Math.abs(d.getT2_SSR_Temp())>Math.abs(T2_SSR_Temp_Max_Value_R) ? d.getT2_SSR_Temp() : T2_SSR_Temp_Max_Value_R;
                T3_Inverter_Temp_Max_Value_R = Math.abs(d.getT3_Inverter_Temp())>Math.abs(T3_Inverter_Temp_Max_Value_R) ? d.getT3_Inverter_Temp() : T3_Inverter_Temp_Max_Value_R;
                Yaw_Max_Value_R = Math.abs(d.getYaw())>Math.abs(Yaw_Max_Value_R) ? d.getYaw() : Yaw_Max_Value;
                Pitch_Max_Value_R = Math.abs(d.getPitch())>Math.abs(Pitch_Max_Value_R) ? d.getPitch() : Pitch_Max_Value;
                Roll_Max_Value_R = Math.abs(d.getRoll())>Math.abs(Roll_Max_Value_R) ? d.getRoll() : Roll_Max_Value;
                Acc_X_Max_Value_R = Math.abs(d.getAcc_X())>Math.abs(Acc_X_Max_Value_R) ? d.getAcc_X() : Acc_X_Max_Value;
                Acc_Y_Max_Value_R = Math.abs(d.getAcc_Y())>Math.abs(Acc_Y_Max_Value_R) ? d.getAcc_Y() : Acc_Y_Max_Value;
                Acc_Z_Max_Value_R = Math.abs(d.getAcc_Z())>Math.abs(Acc_Z_Max_Value_R) ? d.getAcc_Z() : Acc_Z_Max_Value;
            }
        }

        assert repairedDataRows != null;
        StringBuilder changes = new StringBuilder();
        int repairedRecords = 0;
        for (int i = 0; i < repairedDataRows.size(); i++) {
            if(!repairedDataRows.get(i).equals(rawDataRows.get(i))){
                changes.append("Position ").append(i).append(", changed from: { \n\t").append(rawDataRows.get(i).toString()).append("\n\t")
                .append("To \n\t").append(repairedDataRows.get(i)).append("\n}\n");
                repairedRecords++;
            }
        }

        specialDataTextArea.setText(
                    "Raw data values: { " + "\n\n\t" +
                    "Length of array with values: " + rawDataRows.size() + "\n\t" +
                    "Max value of Speed: " + Speed_Max_Value + "\n\t" +
                    "Max value of Motor_RPM: " + Motor_RPM_Max_Value + "\n\t" +
                    "Max value of Motor_Temp: " + Motor_Temp_Max_Value + "\n\t" +
                    "Max value of Controller_Temp: " + Controller_Temp_Max_Value + "\n\t" +
                    "Max value of Battery_Voltage: " + Battery_Voltage_Max_Value + "\n\t" +
                    "Max value of Rear_Susp_Min: " + Rear_Susp_Min_Max_Value + "\n\t" +
                    "Max value of Rear_Susp_Travel: " + Rear_Susp_Travel_Max_Value + "\n\t" +
                    "Max value of Rear_Susp_Max: " + Rear_Susp_Max_Max_Value + "\n\t" +
                    "Max value of Front_Susp_Min: " + Front_Susp_Min_Max_Value + "\n\t" +
                    "Max value of Front_Susp_Travel: " + Front_Susp_Travel_Max_Value + "\n\t" +
                    "Max value of Front_Susp_Max: " + Front_Susp_Max_Max_Value + "\n\t" +
                    "Max value of T1_Contactor_Temp: " + T1_Contactor_Temp_Max_Value + "\n\t" +
                    "Max value of T2_SSR_Temp: " + T2_SSR_Temp_Max_Value + "\n\t" +
                    "Max value of T3_Inverter_Temp: " + T3_Inverter_Temp_Max_Value + "\n\t" +
                    "Max value of Yaw: " + Yaw_Max_Value + "\n\t" +
                    "Max value of Pitch: " + Pitch_Max_Value + "\n\t" +
                    "Max value of Roll: " + Roll_Max_Value + "\n\t" +
                    "Max value of Acc_X: " + Acc_X_Max_Value + "\n\t" +
                    "Max value of Acc_Y: " + Acc_Y_Max_Value + "\n\t" +
                    "Max value of Acc_Z: " + Acc_Z_Max_Value + "\n\n" +
                    "}" + "\n\n" +
                    "Repaired data values: {" + "\n\n\t" +
                            "Length of array with values: " + repairedDataRows.size() + "\n\t" +
                            "Max value of Speed: " + Speed_Max_Value_R + "\n\t" +
                            "Max value of Motor_RPM: " + Motor_RPM_Max_Value_R + "\n\t" +
                            "Max value of Motor_Temp: " + Motor_Temp_Max_Value_R + "\n\t" +
                            "Max value of Controller_Temp: " + Controller_Temp_Max_Value_R + "\n\t" +
                            "Max value of Battery_Voltage: " + Battery_Voltage_Max_Value_R + "\n\t" +
                            "Max value of Rear_Susp_Min: " + Rear_Susp_Min_Max_Value_R + "\n\t" +
                            "Max value of Rear_Susp_Travel: " + Rear_Susp_Travel_Max_Value_R + "\n\t" +
                            "Max value of Rear_Susp_Max: " + Rear_Susp_Max_Max_Value_R + "\n\t" +
                            "Max value of Front_Susp_Min: " + Front_Susp_Min_Max_Value_R + "\n\t" +
                            "Max value of Front_Susp_Travel: " + Front_Susp_Travel_Max_Value_R + "\n\t" +
                            "Max value of Front_Susp_Max: " + Front_Susp_Max_Max_Value_R + "\n\t" +
                            "Max value of T1_Contactor_Temp: " + T1_Contactor_Temp_Max_Value_R + "\n\t" +
                            "Max value of T2_SSR_Temp: " + T2_SSR_Temp_Max_Value_R + "\n\t" +
                            "Max value of T3_Inverter_Temp: " + T3_Inverter_Temp_Max_Value_R + "\n\t" +
                            "Max value of Yaw: " + Yaw_Max_Value_R + "\n\t" +
                            "Max value of Pitch: " + Pitch_Max_Value_R + "\n\t" +
                            "Max value of Roll: " + Roll_Max_Value_R + "\n\t" +
                            "Max value of Acc_X: " + Acc_X_Max_Value_R + "\n\t" +
                            "Max value of Acc_Y: " + Acc_Y_Max_Value_R + "\n\t" +
                            "Max value of Acc_Z: " + Acc_Z_Max_Value_R + "\n\n" +
                            "}" + "\n\n" +
                            "Repaired " + repairedRecords + " records: \n\n" + changes
        );

    }

}
