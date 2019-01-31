package frc.robot;

public class Constant {
	

	///Xbox Controller
	public static final int LStickX=0;
	public static final int LStickY=1;
	public static final int LTrigger=2;
	public static final int RTrigger=3;
	public static final int RStickX=4;
	public static final int RStickY=5;
	public static final int AButton=0;
	public static final int BButton=1;
	public static final int XButton=2;
	public static final int YButton=3;
	public static final int LeftBumper=4;
	public static final int RightBumper=5;
	public static final int BackButton=6;
	public static final int StartButton=7;

	///Joysticks
	public static final int LeftStick = 0;
	public static final int RightStick = 1;
	public static final int Xbox = 2;

	///Solenoids
	public static final int intFSol = 4;
	public static final int intRSol = 5;
	public static final int stopF = 3;
	public static final int stopR = 2;

	///Sensors
	///Analog

	///Digital
	public static final int lEnc1 = 8;
	public static final int lEnc2 = 9;
	public static final int rEnc1 = 6;
	public static final int rEnc2 = 7;
	public static final int Upper = 0;
	public static final int Middle = 1;
	public static final int Lower = 3;
	public static final int Stop = 2;

	///Values
	public static final double WheelDiameter=6;
	public static final double kP = 0.03;
	public static final double kI = 0.0003;
	public static final double kD = 0.02;
	public static final double kF = 0.00;
	public static final double kToleranceDegrees = 4.0f;

	public static final double LintakeSpeed=-.58;
	public static final double RintakeSpeed=-.6;
	public static final double outtakeSpeed=.55;
	public static final double fastOuttakeSpeed=.7;
	public static final double stopIntakeSpeed=.05;
	public static final double climbSpeed=-.4;

	///2017 Distances
	public static final double StationWidth = 72.0;
	public static final double ExchangerWidth = 36.0;
	public static final double ExchangerDepth = 48.0;
	public static final double SwitchDepth = 180.0;
	public static final double SwitchWidth = 52.0;
	public static final double WallToAutoLine = 120.0;
	public static final double AutoLineToSwitch = 20;
	public static final double SwitchToMidNull=120.0;
	public static final double SwitchToWall = 82.25;
	public static final double ArcadeDepth = 324.0;
	public static final double WallToScale = 72.0;
	public static final double BoxWidth = 13.0;
	public static final double PortalDepth = 30.0;
	public static final double CubeZoneWidth = 42.0;
	public static final double ScalePlate = 48.0;

	///2019 Base Constants
	public static final double RobotWidth =  28.0;
	public static final double RobotLength = 32.0;
	public static final double lv1HabPlatformNORAMP = 35.78;
	public static final double lv1HabPlatformWITHRAMP = 48.28;
	public static final double elevatorSpeed=.5;
	public static final double elevatorDownSpeed=.4;
	public static final double spoolDiameter = 0.0;
	public static final int encTicksPerRev = 1024;
	public static final int encChannelA = 0;
	public static final int encChannelB = 1;

	///Heights of Holes for Elevator
	public static final int Minimum = 0; 
	public static final int RocketLowBall = 10;
	public static final int RocketMediumHatch = 20;
	public static final int RocketMediumBall = 30;
	public static final int RocketHighHatch = 40;
	public static final int RocketHighBall = 50;
	public static final int Maximum = 60;

	//Magic Numbers
	public static final double IntakeSpeed = 0.4;
	public static final double OuttakeSpeed = 0.4;

	///Talons
	public static final int CANLength=12;
	public static final int Left0 = 1;
	public static final int Left1 = 2;
	public static final int Left2 = 3;
	public static final int Right0 = 4;
	public static final int Right1 = 5;
	public static final int Right2 = 6;
	public static final int intakeMotor1 = 7;
	public static final int intakeMotor2 = 8;
	public static final int elevatorMotor = 9;
	public static final int frontLiftMotor = 10;
	public static final int backLiftMotor = 11;
	public static final int backRollerMotor = 12;
	public static final double PneumaticOffset = 4.547368;
	public static final int PressureSensor = 0;
	public static final int PneumaticCompressor = 0;
}