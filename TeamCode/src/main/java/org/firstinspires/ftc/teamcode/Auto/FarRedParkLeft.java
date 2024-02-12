package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
//import com.outoftheboxrobotics.photoncore.Photon;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Auto.AbstractVisionOpMode;
import org.firstinspires.ftc.teamcode.RRExtras.AprilTagDrive;
import org.firstinspires.ftc.teamcode.helpers.PoseStorage;
//import org.firstinspires.ftc.teamcode.motor.MotorActions;
//@Photon
@Autonomous(preselectTeleOp = "Teleop Field Centric", name = "2+1 Left Red, Far Park, Left Pixel", group = "Blue")
public class FarRedParkLeft extends AbstractVisionOpMode {
    /**
     * Is this a red or a blue autonomous?
     *
     * @return the team
     */
    @Override
    public PoseStorage.Team team() {
        return PoseStorage.Team.RED;
    }

    /**
     * Starting Position of the trajectories
     *
     * @return the starting pose
     */
    @Override
    public Pose2d startPose() {
        return new Pose2d(-36,-62,Math.toRadians(-90));
    }

    //@Override
    public Action trajRight(AprilTagDrive drive) {
        return drive.actionBuilder(drive.pose)
                .setReversed(true)
                // GOTO GROUND PIXEL
                .splineToConstantHeading(new Vector2d(-42,-47), Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-33,-32, Math.toRadians(0)), Math.toRadians(90))
                .endTrajectory()
               // .stopAndAdd(motorActions.claw.release())

                // GOTO STACK
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(-63,-23.5,Math.toRadians(180)), Math.toRadians(180))
                .endTrajectory()
                //.stopAndAdd(motorActions.claw.grab())
                .waitSeconds(0.5)


                // GOTO BACKBOARD
                .setReversed(true)
                .setTangent(0)
               // .afterTime(0.5, motorActions.pixelToHook())
                .splineToConstantHeading(new Vector2d(-36, -12), Math.toRadians(0))
                .splineTo(new Vector2d(20, -12), Math.toRadians(0))
                //.afterTime(0.5, drive.CorrectWithTagAction())
                .splineToSplineHeading(new Pose2d(53,-44, Math.toRadians(180)), Math.toRadians(0))
                //.stopAndAdd(new ParallelAction(motorActions.autoPlace(),motorActions.placePixel()))
                .endTrajectory()


                // PARK
                .splineToLinearHeading(new Pose2d(54,-14, Math.toRadians(180)), Math.toRadians(0))
                .build();
    }
  //  @Override
    public Action trajCenter(AprilTagDrive drive) {

        return drive.actionBuilder(drive.pose)
                .setReversed(true)
                .setReversed(true)
                // GOTO GROUND PIXEL
                .splineToConstantHeading(new Vector2d(-38,-19), Math.toRadians(90))
                .endTrajectory()
               // .stopAndAdd(motorActions.claw.release())

                // GOTO STACK
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(-62,-11.5,Math.toRadians(180)), Math.toRadians(180))
                .endTrajectory()
                //.stopAndAdd(motorActions.claw.grab())
                .waitSeconds(0.5)


                // GOTO BACKBOARD
                .setReversed(true)
                //.afterTime(0.5, motorActions.pixelToHook())
                .splineToConstantHeading(new Vector2d(-28, -12), Math.toRadians(0))
                .splineTo(new Vector2d(20, -12), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(56.5,-37, Math.toRadians(180)), Math.toRadians(0))
               // .stopAndAdd(new ParallelAction(motorActions.autoPlace(),motorActions.placePixel()))
                .endTrajectory()


                // PARK
                .splineToLinearHeading(new Pose2d(52,-11, Math.toRadians(180)), Math.toRadians(0))
                .build();

    }

    //@Override
    public Action trajLeft(AprilTagDrive drive) {

        return drive.actionBuilder(drive.pose)
                .setReversed(true)
                // GOTO GROUND PIXEL
                .strafeTo(new Vector2d(-47,-48))
                .splineToConstantHeading(new Vector2d(-47,-21), Math.toRadians(90))
                .endTrajectory()
                //.stopAndAdd(motorActions.claw.release())

                // GOTO STACK
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-48, -12), Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-64,-12,Math.toRadians(180)), Math.toRadians(180))
                .endTrajectory()
                //.stopAndAdd(motorActions.claw.grab())
                .waitSeconds(0.5)


                // GOTO BACKBOARD
                .setReversed(true)
               // .afterTime(0.5, motorActions.pixelToHook())
                .splineToConstantHeading(new Vector2d(-36, -14), Math.toRadians(0))
                .splineTo(new Vector2d(20, -14), Math.toRadians(0))
                //.afterTime(0.5, drive.CorrectWithTagAction())
                .splineToSplineHeading(new Pose2d(56.5,-33, Math.toRadians(180)), Math.toRadians(0))
               // .stopAndAdd(new ParallelAction(motorActions.autoPlace(),motorActions.placePixel()))
                .endTrajectory()


                // PARK
                .splineToLinearHeading(new Pose2d(54,-14, Math.toRadians(180)), Math.toRadians(0))
                .build();
    }
}