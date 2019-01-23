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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Intake
{
    private DoubleSolenoid sol;
    private boolean isIntakeOpen;
    private WPI_TalonSRX leftArm;
    private WPI_TalonSRX rightArm;
    private final int intakeMotor1 = 7;
    private final int intakeMotor2 = 8;
    public Intake()
    {
        //this.sol = new DoubleSolenoid(Constant.intFSol, Constant.intRSol);
        this.leftArm = new WPI_TalonSRX(this.intakeMotor1);
        this.rightArm = new WPI_TalonSRX(this.intakeMotor2);

    }

    public void Setup()
    {
        leftArm.setInverted(true);
    }

    public void Suction()
    {
        leftArm.set(Constant.IntakeSpeed);
        rightArm.set(Constant.IntakeSpeed);
    }

    public void Shoot()
    {
        leftArm.set(Constant.OuttakeSpeed);
        rightArm.set(Constant.OuttakeSpeed);
    }

    public void openIntake()
	{
		//this.sol.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putString("Intake Status", "Open");
		this.isIntakeOpen = true;
	}
	
	public void closeIntake()
	{
		//this.sol.set(DoubleSolenoid.Value.kReverse);
		SmartDashboard.putString("Intake Status", "Close");
		this.isIntakeOpen = false;
    }
    public void Stop()
    {
        leftArm.stopMotor();
        rightArm.stopMotor();
    }

    public boolean isIntakeOpen()
    {
        return isIntakeOpen;
    }
}
/*
asciichu
░░░░█░▀▄░░░░░░░░░░▄▄███▀░░
░░░░█░░░▀▄░▄▄▄▄▄░▄▀░░░█▀░░
░░░░░▀▄░░░▀░░░░░▀░░░▄▀░░░░
░░░░░░░▌░▄▄░░░▄▄░▐▀▀░░░░░░
░░░░░░▐░░█▄░░░▄█░░▌▄▄▀▀▀▀█
░░░░░░▌▄▄▀▀░▄░▀▀▄▄▐░░░░░░█
░░░▄▀▀▐▀▀░░░░░░░▀▀▌▄▄▄░░░█
░░░█░░░▀▄░░░░░░░▄▀░░░░█▀▀▀
░░░░▀▄░░▀░░▀▀▀░░▀░░░▄█░░░░
*/