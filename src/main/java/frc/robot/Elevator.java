package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Elevator
{
    private final int elevatorMotor = 9;
    private WPI_TalonSRX elevator;
    private int desiredLevel = 0;
    private int actualLevel = 0;
    private XboxController controller;
    private boolean isButtonPressed = false;
    private DigitalInput[] sensors;
    private final int SENSOR_COUNT = 7;
    private Encoder elevatorEnc;
    private double desiredHeight = 0.0;


    public enum Position
	{
		Minimum, RocketLowBall, RocketMediumHatch, RocketMediumBall, RocketHighHatch, RocketHighBall, Maximum
    }
    int[] levels = {Constant.Minimum, 
                    Constant.RocketLowBall, 
                    Constant.RocketMediumHatch, 
                    Constant.RocketMediumBall, 
                    Constant.RocketHighHatch, 
                    Constant.RocketHighBall, 
                    Constant.Maximum};

    public Elevator(XboxController xbox)
    {
        elevator = new WPI_TalonSRX(elevatorMotor);
        this.controller = xbox;

    }
    public void Setup()
    {
        sensors = new DigitalInput[SENSOR_COUNT];
        for(int i = 0; i <= SENSOR_COUNT; i++)
        {
            sensors[i] = new DigitalInput(i);
        }
        elevatorEnc = new Encoder(Constant.encChannelA, Constant.encChannelB);
        double elevatorDrum = Math.PI*Constant.spoolDiameter;
        elevatorEnc.setDistancePerPulse(elevatorDrum/Constant.encTicksPerRev);                    
    }
    public void handleInputs()
    {
        //Going Up a level.
        if (controller.getRawButton(Constant.AButton) && !isButtonPressed && desiredLevel != 6)
        {
            desiredLevel++;
            isButtonPressed = true;
            SmartDashboard.putString("Current Level", Position.values()[desiredLevel].toString());
            desiredHeight = levels[desiredLevel];
        }
        
        else if(!controller.getRawButton(Constant.AButton))
        {
            isButtonPressed = false;
        }

        //Going Down a level.
        if (controller.getRawButton(Constant.YButton) && !isButtonPressed && desiredLevel != 0)
        {
            desiredLevel--;
            isButtonPressed=true;
            SmartDashboard.putString("Current Level", Position.values()[desiredLevel].toString());
            desiredHeight = levels[desiredLevel];
        }
        else if (!controller.getRawButton(Constant.YButton))
        {
            isButtonPressed=false;
        }
        runElevator();
    }
    public void setLevel(int level)
    {
        desiredLevel = level;
    }
    public void runElevator()
    {
        checkLevel();
        if(getDesiredHeight() < getActualHeight())
        {
            goDown();
        }
        else if (getDesiredHeight() > getActualHeight())
        {
            goUp();
        }
        else if(getDesiredHeight() == getActualHeight())
        {
            stop();
        }
    }
    public void checkLevel()
    {
        if(desiredLevel > 6)
        {
            desiredLevel = 6;
        }
        else if(desiredLevel < 0)
        {
            desiredLevel = 0;
        }                        
    }
    public double canMove()
    {
        double err = getDesiredHeight()-getActualHeight();
        return err;
    }
    public void goUp()
    {
        elevator.setInverted(false);
        elevator.set(Constant.elevatorSpeed);
    }
    public void goDown()
    {
        elevator.setInverted(true);
        elevator.set(Constant.elevatorDownSpeed);                            
    }
    public void stop()
    {
        elevator.stopMotor();
    }
    public double getActualHeight()
    {
        return Math.abs(elevatorEnc.getDistance());
    }
    public double getDesiredHeight()
    {
        return desiredHeight;
    }
}