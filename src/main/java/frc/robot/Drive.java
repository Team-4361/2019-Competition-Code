package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public abstract class Drive
{
    protected final int l_motor1 = 1;
    protected final int l_motor2 = 2;
    protected final int l_motor3 = 3;
    protected final int r_motor1 = 4;
    protected final int r_motor2 = 5;
    protected final int r_motor3 = 6;

    protected WPI_TalonSRX frontLeft;
    protected WPI_TalonSRX middleLeft;
    protected WPI_TalonSRX backLeft;
    protected SpeedControllerGroup left;

    protected WPI_TalonSRX frontRight;
    protected WPI_TalonSRX middleRight;
    protected WPI_TalonSRX backRight;
    protected SpeedControllerGroup right;

    protected DifferentialDrive drive;

    public Drive()
    {
        frontLeft = new WPI_TalonSRX(l_motor1);
        middleLeft = new WPI_TalonSRX(l_motor2);
        backLeft = new WPI_TalonSRX(l_motor3);
        left = new SpeedControllerGroup(frontLeft, middleLeft, backLeft);

        frontRight = new WPI_TalonSRX(r_motor1);
        middleRight = new WPI_TalonSRX(r_motor2);
        backRight = new WPI_TalonSRX(r_motor3);
        right = new SpeedControllerGroup(frontRight, middleRight, backLeft);

        drive = new DifferentialDrive(left, right);
    }
    
    public abstract void handleInputs();

    public abstract void setup();
}