package frc.robot;

public final class ShooterConstants {
  private ShooterConstants() {}

  public static final class ShooterConstants {
    public static final int LEFT_LEADER_ID = 3;
    public static final int LEFT_FOLLOWER_ID = 10;    // real can id
    public static final int RIGHT_ID = 11;

    public static final int CURRENT_LIMIT_AMPS = 40;
    public static final double MAX_OUTPUT = 0.5;
    public static final double DEADBAND = 0.1;



    
    public static final double RotationSpeed1 = 0.5;
    public static final double PiviotSpeed1 = 0.1;



    public static final double RotationSpeed2 = 0.5;
    public static final double PiviotSpeed2 = 0.1;
  }

  public static final class OIConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
  }
}
