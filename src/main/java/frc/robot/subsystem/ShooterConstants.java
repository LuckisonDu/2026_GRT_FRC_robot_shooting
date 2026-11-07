package frc.robot.subsystem;

public final class ShooterConstants {
  private ShooterConstants() {}

  public static final int LEFT_LEADER_ID = 17;
  public static final int LEFT_FOLLOWER_ID = 25;
  public static final double FLYWHEEL_GEAR_RATIO = 1;


  public static final int RIGHT_ID = 16;

  public static final int CANCODER_ID = 18;
  public static final int CURRENT_LIMIT_AMPS = 50;

  public static final double RotationSpeed1 = 40;
  public static final double RotationSpeed2 = 60;
  public static final double PiviotAngle1 = 0.014;
  public static final double PiviotAngle2 = 0.096;


  public static final double CANCODER_OFFSET = 0;
  public static final double PIVOT_SENSOR_RATIO = 1;
  public static final double FLYWHEEL_ON_VOLTS = 2;
  public static final double PIVOT_MAX_VOLTS = 3;

  public static final class PID {
    public static final double kP = 10;
    public static final double kI = 0;
    public static final double kD = 0;
  }

  public static final class OIConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
  }
}