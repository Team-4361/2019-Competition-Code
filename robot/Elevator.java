package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.Library.Controllers.Drive;

public class Elevator
{
    private Drive Elevator;
    private final int elevatorMotor = 9;
    private WPI_TalonSRX elevator;
    private int desiredLevel = 0;
    private XboxController controller;
    private boolean isButtonPressed = false;
    private DigitalInput bottomLim, topLim;
    private Encoder elevatorEnc;
    private double desiredHeight = 0.0;
    private double elevatorSpeed;




    public Elevator(Drive Elevator, DigitalInput botSwitch, DigitalInput topSwitch, XboxController cont, Encoder enc)
    {
        this.Elevator = Elevator;
        this.bottomLim = botSwitch;
        this.topLim = topSwitch;
        this.controller = cont;
        this.elevatorEnc = enc;

    }
    public void ElevatorRun()
    {
        SensorRun();
    }
    private void SensorRun()
    {
        if(!bottomLim.get() && Elevator.GetSpeed() < 0.0)
        {
            Elevator.drive(0.0);
        }
        else if(!topLim.get() && Elevator.GetSpeed() > 0.0)
        {
            Elevator.drive(0.0);
        }
        else
        {
            Elevator.drive(elevatorSpeed);
        }
    }
    public void Manual(double speed)
    {
        if(speed < 0 && !bottomLim.get() || speed > 0 && !topLim.get())
        {
            Elevator.drive(0);
        }
        else
        {
            Elevator.drive(-speed);
        }
    }


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

    //constructor with encoder
    public Elevator(XboxController xbox, DigitalInput bottomSwitch, Encoder enc)
    {
        elevator = new WPI_TalonSRX(elevatorMotor);
        this.controller = xbox;

    }
    public void Setup()
    {                  
    }
    public void handleInputs()
    {
        //Going Up a level.
        if (controller.getRawButton(Constant.YButton) && !isButtonPressed && desiredLevel != 6)
        {
            desiredLevel++;
            isButtonPressed = true;
            SmartDashboard.putString("Current Level", Position.values()[desiredLevel].toString());
            desiredHeight = levels[desiredLevel];
        }
        
        else if(!controller.getRawButton(Constant.YButton))
        {
            isButtonPressed = false;
        }

        //Going Down a level.
        if (controller.getRawButton(Constant.AButton) && !isButtonPressed && desiredLevel != 0)
        {
            desiredLevel--;
            isButtonPressed=true;
            SmartDashboard.putString("Current Level", Position.values()[desiredLevel].toString());
            desiredHeight = levels[desiredLevel];
        }
        else if (!controller.getRawButton(Constant.AButton))
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
