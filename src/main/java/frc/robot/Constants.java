package frc.robot;

public final class Constants {
  private Constants() {}

  public static final class DriveConstants {
    public static final int LEFT_LEADER_ID = 3;
    public static final int LEFT_FOLLOWER_ID = 10;    // real can id
    public static final int RIGHT_LEADER_ID = 11;    // real can id
    public static final int RIGHT_FOLLOWER_ID = 1;

    public static final int CURRENT_LIMIT_AMPS = 40;
    public static final double MAX_OUTPUT = 0.5;
    public static final double DEADBAND = 0.1;
  }

  public static final class OIConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
  }
}
