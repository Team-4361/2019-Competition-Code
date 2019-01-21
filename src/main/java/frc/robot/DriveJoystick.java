package frc.robot;

import edu.wpi.first.wpilibj.Joystick;



/*Enumeration means a list of named constant.
In Java, enumeration defines a class type.
An Enumeration can have constructors, methods and instance variables.
It is created using enum keyword.
Each enumeration constant is public, static and final by default.
*/


public class DriveJoystick extends Drive
{
    private Joystick l_stick;
    private Joystick r_stick;
    private double yWithDeadzone;
    private double deadZonePositive = 0.2;
    private double deadZoneNegative = -0.2;

    public DriveJoystick(Joystick l, Joystick r)
    {
        super();
        this.l_stick = l;
        this.r_stick = r;

    }
    @Override
    public void setup()
    {
        left.setInverted(true);
        right.setInverted(false);

    }

    @Override
    public void handleInputs()
    {		
        drive.tankDrive(this.l_stick.getY() * 0.9, this.r_stick.getY() * 0.9);

        //valueL *= (double)Gear/4.0;
	    //valueR *= (double)Gear/4.0;
		
		//chassis.drive(valueL, valueR);
    }
   

    //Y deadzone
    public double getYDeadzone()
    {
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
        return yWithDeadzone;
    }
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