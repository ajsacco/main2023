package org.team100.frc2023.commands;

import java.util.function.Supplier;

import org.team100.lib.commands.DriveUtil;
import org.team100.lib.motion.drivetrain.SpeedLimits;
import org.team100.lib.motion.drivetrain.SwerveDriveSubsystem;

import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Accepts [-1,1] input and scales it to the specified maximum speeds. */
public class DriveScaled extends CommandBase {
    private final Supplier<Twist2d> twistSupplier;
    private final SwerveDriveSubsystem m_robotDrive;
    private final SpeedLimits m_speedLimits;

    public DriveScaled(
            Supplier<Twist2d> twistSupplier,
            SwerveDriveSubsystem robotDrive,
            SpeedLimits speedLimits) {
        this.twistSupplier = twistSupplier;
        m_robotDrive = robotDrive;
        m_speedLimits = speedLimits;
        addRequirements(m_robotDrive);
    }

    @Override
    public void execute() {
        Twist2d twistM_S = DriveUtil.scale(
                twistSupplier.get(),
                m_speedLimits.speedM_S,
                m_speedLimits.angleSpeedRad_S);
        m_robotDrive.driveInFieldCoords(twistM_S);
    }
}
