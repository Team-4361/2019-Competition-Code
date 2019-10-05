package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PowerManagement
{
   public static PowerDistributionPanel powerCheck = new PowerDistributionPanel(Constant.PDPID);
   public static void sendChannelToShuffleBoard(int channel)
   {
       String bigString = "Current Power of Talon Channel" + channel;
       SmartDashboard.putNumber(bigString, powerCheck.getCurrent(channel));
   }
   public static void sendWattageToShuffleBoard()
   {
       SmartDashboard.putNumber("Current Power of All Channels", powerCheck.getTotalPower());
   }
   public static void possibleBrownOut()
   {
       if (powerCheck.getVoltage()<9.5 && powerCheck.getVoltage()>8.0)
       {
           SmartDashboard.putString("Possible Brown Out?", "Warning, Possible Brown Out");
       }
       else if (powerCheck.getVoltage()<8.0)
       {
            SmartDashboard.putString("Possible Brown Out?", "Yes, Shutting Down");
       }
       else
       {
           SmartDashboard.putString("Possible Brown Out?", "No, Probably No Brownout");
       }
   }
}