public class Drive
{
    private final int l_motor1 = 1;
    private final int l_motor2 = 2;
    private final int l_motor3 = 3;
    private final int r_motor1 = 4;
    private final int r_motor2 = 5;
    private final int r_motor3 = 6;

    private WPI_TalonSRX frontLeft;
    private WPI_TalonSRX middleLeft;
    private WPI_TalonSRX backLeft;
    private SpeedControllerGroup left;

    private WPI_TalonSRX frontRight;
    private WPI_TalonSRX middleRight;
    private WPI_TalonSRX backRight;
    private SpeedControllerGroup right;

    private DifferentialDrive drive;

    public Drive()
    {
        frontLeft = new WPI_TalonSRX(l_motor1);
        middleLeft = new WPI_TalonSRX(l_motor2);
        backLeft = new WPI_TalonSRX(l_motor3);
        left = new SpeedControllerGroup(frontLeft, middleLeft, rearLeft);

        frontRight = new WPI_TalonSRX(r_motor1);
        middleRight = new WPI_TalonSRX(r_motor2);
        backRight = new WPI_TalonSRX(r_motor3);
        right = new SpeedControllerGroup(frontRight, middleRight, rearRight);

        drive = new DifferentialDrive(left, right);
    }
}