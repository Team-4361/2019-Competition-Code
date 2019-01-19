package org.usfirst.frc.team4361.robot;

//import of libraries 
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



/*Enumeration means a list of named constant.
In Java, enumeration defines a class type.
An Enumeration can have constructors, methods and instance variables.
It is created using enum keyword.
Each enumeration constant is public, static and final by default.
*/


public class Drive
{
    private XboxController controllyBOI = new XboxController();
    private Hand rightHand = 1;
    private Hand leftHand = 0;
    //variables for talons
    private static final int l_motor1 = 1;
    private static final int l_motor2 = 2;
    private static final int l_motor3 = 3;
    private static final int r_motor1 = 4;
    private static final int r_motor2 = 5;
    private static final int r_motor3 = 6;
    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(l_motor1);
    private WPI_TalonSRX middleLeft = new WPI_TalonSRX(l_motor2);
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(l_motor3);

    frontLeft.setInverted(true);
    middleLeft.setInverted(true);
    rearLeft.setInverted(true);

    private SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);
    left.set(l_stick);

    private WPI_TalonSRX frontRight = new WPI_TalonSRX(r_motor1);
    private WPI_TalonSRX middleRight = new WPI_TalonSRX(r_motor2);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(r_motor3);

    frontRight.setInverted(true);
    middleRight.setInverted(true);
    rearRight.setInverted(true);  

    private SpeedControllerGroup right = new SpeedControllerGroup(frontRight, middleRight, rearRight);
    right.set(r_stick);

    public DifferentialDrive drive = new DifferentialDrive(left, right);

    //used to determine the deadzone of the joystick.
    private double deadZonePositive = 0.2;
    private double deadZoneNegative = -0.2;


    //declaration of variables for deadzones
    private double xWithDeadzone;
    private double yWithDeadzone;


    //X Deadzone
    if(l_stick.getX()<=deadZonePositive && l_stick.getX()>=deadZoneNegative)
    {
        xWithDeadzone = 0.0;
    }
    else if(l_stick.getX()>deadZonePositive)
    {
        xWithDeadzone = l_stick.getX()-deadZonePositive;
    }
    else if(l_stick.getX()<deadZoneNegative)
    {
        xWithDeadzone = l_stick.getX()-deadZoneNegative;
    }

    if(r_stick.getX()<=deadZonePositive && r_stick.getX()>=deadZoneNegative)
    {
        xWithDeadzone = 0.0;
    }
    else if(r_stick.getX()>deadZonePositive)
    {
        xWithDeadzone = r_stick.getX()-deadZonePositive;
    }
    else if(r_stick.getX()<deadZoneNegative)
    {
        xWithDeadzone = r_stick.getX()-deadZoneNegative;
    }

    //Y Deadzones    
    if(l_stick.getY()<=deadZonePositive && l_stick.getY()>=deadZoneNegative)
    {
        yWithDeadzone = 0.0;
    }
    else if(l_stick.getY()>deadZonePositive)
    {
        yWithDeadzone = l_stick.getY()-deadZonePositive;
    }
    else if(l_stick.getY()<deadZoneNegative)
    {
        yWithDeadzone = l_stick.getY()-deadZoneNegative;
    }

    if(r_stick.getY()<=deadZonePositive && r_stick.getY()>=deadZoneNegative)
    {
        yWithDeadzone = 0.0;
    }
    else if(r_stick.getY()>deadZonePositive)
    {
        yWithDeadzone = r_stick.getX()-deadZonePositive;
    }
    else if(r_stick.getY()<deadZoneNegative)
    {
        yWithDeadzone = r_stick.getY()-deadZoneNegative;
    }
    /*
    acaiiDogeiy
    ░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░
    ░█░░░░░░░▀▄░░░░░░▄░
    █░░▀░░▀░░░░░▀▄▄░░█░█ 
    █░▄░█▀░▄░░░░░░░▀▀░░█
    █░░▀▀▀▀░░░░░░░░░░░░█
    █░░░░░░░░░░░░░░░░░░█
    █░░░░░░░░░░░░░░░░░░█
    ░█░░▄▄░░▄▄▄▄░░▄▄░░█░
    ░█░▄▀█░▄▀░░█░▄▀█░▄▀░
    ░░▀░░░▀░░░░░▀░░░▀░░░
*/
}   