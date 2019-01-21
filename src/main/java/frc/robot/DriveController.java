package frc.robot;

import edu.wpi.first.wpilibj.XboxController;



/*Enumeration means a list of named constant.
In Java, enumeration defines a class type.
An Enumeration can have constructors, methods and instance variables.
It is created using enum keyword.
Each enumeration constant is public, static and final by default.
*/


public class DriveController extends Drive
{
    private XboxController xboxCont;

    public DriveController(XboxController x)
    {
        super();
        this.xboxCont = x;

    }
    @Override
    public void handleInputs() {

    }

    @Override
    public void setup() {

    }
    /*
    ascaiiDogeiy
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