package frc.Library.Chassis;

import edu.wpi.first.wpilibj.Encoder;
import frc.Library.Controllers.*;
import frc.Library.Util.Constant;

public class TankDrive implements Chassis
{
	Drive Left, Right;
	Encoder lEnc, rEnc;
	double DIAMETER;
	
	public TankDrive(Drive Left, Drive Right)
	{
		this.Left = Left;
		this.Right = Right;
	}
	
	public TankDrive(Drive Left, Drive Right, Encoder lEnc, Encoder rEnc, double diameter)
	{
		this(Left, Right);
		
		this.lEnc = lEnc;
		this.rEnc = rEnc;
		this.DIAMETER = diameter;
	}
	
	public void Forward(double value)
	{
		drive(value, -value);
	}
	
	public void Straight(double value)
	{
		if(lEnc == null || rEnc == null)
			Forward(value);
		
		double SpeedChange = .1 * value;
		
		if(Math.abs(lEnc.getDistance())> Math.abs(rEnc.getDistance()))
			drive(value - SpeedChange, -value);
		else if(Math.abs(lEnc.getDistance()) < Math.abs(rEnc.getDistance()))
			drive(value, -(value - SpeedChange));
		else
			Forward(value);
	}
	
	public void Turn(double value)
	{
		Left.drive(value);
		Right.drive(value);
	}
	
	public double GetDistance()
	{
		return (Math.max(Math.abs(lEnc.getDistance()), Math.abs(rEnc.getDistance())) / 256) *DIAMETER*Math.PI;
	}
	
	public void Stop()
	{
		Left.drive(0);
		Right.drive(0);
	}
	
	public void drive(double lVal, double rVal)
	{
		Left.drive(lVal);
		Right.drive(rVal);
	}

	public void drive(double[] values)
	{
		Left.drive(values[0]);
		Right.drive(values[1]);
	}
	
	public boolean HasEncoder()
	{
		return !(lEnc == null || rEnc == null);
	}
	
	public void ResetEncoders()
	{
		lEnc.reset();
		rEnc.reset();
	}
}
