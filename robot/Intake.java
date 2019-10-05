package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Intake
{
    private DoubleSolenoid sol;
    private boolean isIntakeOpen;
    private WPI_TalonSRX leftArm;
    private WPI_TalonSRX rightArm;
    public Intake(DoubleSolenoid sol)
    {
        this.sol = sol;
        this.leftArm = new WPI_TalonSRX(Constant.intakeMotor1);
        this.rightArm = new WPI_TalonSRX(Constant.intakeMotor2);

    }

    public void Setup()
    {
        leftArm.setInverted(true);
        rightArm.setInverted(false);
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
		this.sol.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putString("Intake Status", "Open");
		this.isIntakeOpen = true;
	}
	
	public void closeIntake()
	{
		this.sol.set(DoubleSolenoid.Value.kReverse);
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
