package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;

public class Climber
{
    private WPI_TalonSRX frontLift;
    private WPI_TalonSRX backLift;
    private WPI_TalonSRX rollers;
    private DigitalInput frontTopSensor;
    private DigitalInput frontBottomSensor;
    private DigitalInput backTopSensor;
    private DigitalInput backBottomSensor;
    private DoubleSolenoid frontPiston;
    private DoubleSolenoid backPiston;


    public Climber(DoubleSolenoid frontPiston, DoubleSolenoid backPiston, WPI_TalonSRX front, WPI_TalonSRX back, WPI_TalonSRX rollers)
    {
        this.frontLift = front;
        this.backLift = back;
        this.rollers = rollers;
        this.frontPiston = frontPiston;
        this.backPiston = backPiston;
        //frontTopSensor = new DigitalInput(Constant.limitSwitch1);
        //frontBottomSensor = new DigitalInput(Constant.limitSwitch2);
        //backTopSensor = new DigitalInput(Constant.limitSwitch3);
        //backBottomSensor = new DigitalInput(Constant.limitSwitch4);
    }
    public void goDownFront()
    {
        frontLift.set(-.58);   
    }
    public void goUpFront()
    {
        frontLift.set(.58);
    }
    public void goDownBack()
    {
        backLift.set(-.38);
    }
    public void goUpBack()
    {
        backLift.set(.38);
    }
    public void stopFront()
    {
        frontLift.set(0);   
    }
    public void stopBack()
    {
        backLift.set(0);
    }
    public void rollerForward()
    {
        rollers.set(.5);
    }
    public void rollerBackward()
    {
        rollers.set(-.5);
    }
    public void stopRoller()
    {
        rollers.set(0);
    }
    public void frontPistonForward()
    {
        frontPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public void frontPistonBackward()
    {
        frontPiston.set(DoubleSolenoid.Value.kForward);
    }
    public void backPistonForward()
    {
        backPiston.set(DoubleSolenoid.Value.kReverse);
    }
    public void backPistonBackward()
    {
        backPiston.set(DoubleSolenoid.Value.kForward);
    }
}