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

public class Climber
{
    private WPI_TalonSRX frontLift;
    private WPI_TalonSRX backLift;
    private WPI_TalonSRX rollers;
    private DigitalInput frontSensor;
    private DigitalInput backSensor;
    
    public Climber()
    {
        frontLift = new WPI_TalonSRX(Constant.frontLiftMotor);
        backLift = new WPI_TalonSRX(Constant.backLiftMotor);
        rollers = new WPI_TalonSRX(Constant.backRollerMotor);
    }
    public void goDownFront()
    {
        frontLift.set(.5);
    }
    public void goUpFront()
    {
        frontLift.set(-.5);
    }
    public void goDownBack()
    {
        backLift.set(.5);
    }
    public void goUpBack()
    {
        frontLift.set(.5);
    }
    public void rollerForward()
    {
        rollers.set(.5);
    }
    public void rollerBackward()
    {
        rollers.set(-.5);
    }
}