package frc.robot;

public class Constant {
	
	/*
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

	///2018 Distances
	public static final double StationWidth = 72.0;
	public static final double ExchangerWidth = 36.0;
	public static final double ExchangerDepth = 48.0;
	public static final double SwitchDepth = 180.0;
	public static final double SwitchWidth = 52.0;
	public static final double WallToAutoLin = 120.0;
	public static final double AutoLineToSwitch = 20;
	public static final double SwitchToMidNull=120.0;
	public static final double SwitchToWall = 82.25;
	public static final double ArcadeDepth = 324.0;
	public static final double WallToScale = 72.0;
	public static final double BoxWidth = 13.0;
	public static final double PortalDepth = 30.0;
	public static final double CubeZoneWidth = 42.0;
	public static final double ScalePlate = 48.0;
	*/

	////////////////////YEAR SWITCH\\\\\\\\\\\\\\\\\\\\

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

	///2019 Robot Size Dimensions
	public static final double BumperWidth = 0.0;
	public static final double RobotWidth =  28.0 + BumperWidth*2;
	public static final double RobotLength = 32.0 + BumperWidth*2;
	public static final double wheelDiameter = 8.0;


	///2019 Stuff
	public static final double elevatorSpeed=.5;
	public static final double elevatorDownSpeed=.4;
	public static final double spoolDiameter = 0.0;

	//Encoders
	public static final int encTicksPerRev = 1024;
	public static final int eleEncChannelA = 0;
	public static final int eleEncChannelB = 1;
	public static final int encoderTimeout = 30;
	public static final boolean DiscontinuityPresent = true;

	///Heights of Holes for Elevator
	public static final int Minimum = 0; 
	public static final int RocketLowBall = 10;
	public static final int RocketMediumHatch = 20;
	public static final int RocketMediumBall = 30;
	public static final int RocketHighHatch = 40;
	public static final int RocketHighBall = 50;
	public static final int Maximum = 60;


	//Magic Numbers
	public static final double IntakeSpeed = 1;
	public static final double OuttakeSpeed = -1;
	public static final double distanceForPulse = (1 / 1024.0);


	///Talons
	public static final int CANLength=13;
	public static final int Left0 = 5;//Top Cim
	public static final int Left1 = 12;//Bottom Cim
	public static final int Left2 = 6;//Mini (Middle) Cim
	public static final int Right0 = 3;//Top Cim
	public static final int Right1 = 2;//Bottom Cim
	public static final int Right2 = 7;//Mini (Middle) Cim
	public static final int intakeMotor1 = 11;
	public static final int intakeMotor2 = 9;
	public static final int elevatorMotor = 10;
	public static final int frontLiftMotor = 1;
	public static final int backLiftMotor = 8;
	public static final int backRollerMotor = 4;

	//PDP ID
	public static final int PDPID = 13;	

	///Limit Switches
	public static final int limitSwitch1 = 2;
	public static final int limitSwitch2 = 3;
	public static final int limitSwitch3 = 6;
	public static final int limitSwitch4 = 7;
	public static final int limitSwitch5 = 9;
	public static final int limitSwitch6 = 8;


	///Solenoids
	public static final int pcmValue = 0;
	public static final int pdpValue = 15;
	public static final int driveTrainRChan = 0;
	public static final int driveTrainFChan = 1; 
	public static final int climber2FChan = 2;
	public static final int climber2RChan = 3;
	public static final int climber1FChan = 4;
	public static final int climber1RChan = 5;
	public static final int intake1FChan = 6;
	public static final int intake1RChan = 7;
	public static final double PneumaticOffset = 4.547368;
	public static final int PressureSensor = 3;


	///2019 Distances
	public static final double offHAB = 34;
	public static final double cargoStopToCargoShip = 13;
	public static final double lv1HabPlatformNORAMP = 35.78;
	public static final double lv1HabPlatformWITHRAMP = 48.28;
	///Same Side
	public static final double startToHabEdge = 31;
	public static final double sameSideToCargo1 = 182;
	public static final double sameSideToCargo2 = 202;
	public static final double sameSideToCargo3 = 222;
	
	///Middle to Either Side
	public static final double firstTurnMid = 141;
	public static final double secondTurnMid = 41;
	public static final double thirdTurnMidToCargo1 = 24;
	public static final double thirdTurnMidToCargo2 = 44;
	public static final double thirdTurnMidToCargo3 = 64;

	///To Middle Cargos
	public static final double middleToMiddle = 141;
	public static final double middleToCargo = 30;
	public static final double middleToMiddleCargoTurn1 = 10;
	public static final double middleToMiddleCargoTurn2 = 12;
	public static final double oppositeSideToMiddleCargo = 50;

	///Opposite Side
	public static final double oppositeSideToMiddle = 141;
	public static final double oppositeSideToCargoSide = 82;
	public static final double oppositeSideToCargo1 = 24;
	public static final double oppositeSideToCargo2 = 44;
	public static final double oppositeSideToCargo3 = 64;

	///Servos
	public static final int autoHatchServo = 9;
}