import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataRow {

    short Speed;

     short Motor_RPM;

     short Motor_Temp;

     short Controller_Temp;

     short Battery_Voltage;

     short Rear_Susp_Min;

     short Rear_Susp_Travel;

     short Rear_Susp_Max;

     short Front_Susp_Min;

     short Front_Susp_Travel;

     short Front_Susp_Max;

     short T1_Contactor_Temp;

     short T2_SSR_Temp;

     short T3_Inverter_Temp;

     short Yaw;

     short Pitch;

     short Roll;

     short Acc_X;

     short Acc_Y;

     short Acc_Z;

     @Override
     public boolean equals(Object o){
         if(this == o){
           return true;
         }
         if(o == null || getClass() != o.getClass()){
           return false;
         }
         DataRow that = (DataRow) o;
         return
         Speed == that.Speed &&
         Motor_RPM == that.Motor_RPM &&
       Motor_Temp == that.Motor_Temp &&
       Controller_Temp == that.Controller_Temp &&
       Battery_Voltage == that.Battery_Voltage &&
       Rear_Susp_Min == that.Rear_Susp_Min &&
       Rear_Susp_Travel == that.Rear_Susp_Travel &&
       Rear_Susp_Max == that.Rear_Susp_Max &&
       Front_Susp_Min == that.Front_Susp_Min &&
       Front_Susp_Travel == that.Front_Susp_Travel &&
       Front_Susp_Max == that.Front_Susp_Max &&
       T1_Contactor_Temp == that.T1_Contactor_Temp &&
       T2_SSR_Temp == that.T2_SSR_Temp &&
       T3_Inverter_Temp == that.T3_Inverter_Temp &&
       Yaw == that.Yaw &&
       Pitch == that.Pitch &&
       Roll == that.Roll &&
       Acc_X == that.Acc_X &&
       Acc_Y == that.Acc_Y &&
       Acc_Z == that.Acc_Z;
     }

}
