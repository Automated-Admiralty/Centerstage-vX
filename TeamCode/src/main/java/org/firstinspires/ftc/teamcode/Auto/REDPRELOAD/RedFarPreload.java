package org.firstinspires.ftc.teamcode.Auto.REDPRELOAD;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Auto.AbstractVisionOpMode;
import org.firstinspires.ftc.teamcode.RRExtras.AprilTagDrive;
import org.firstinspires.ftc.teamcode.helpers.PoseStorage;
import org.firstinspires.ftc.teamcode.motor.MotorActions;
import org.firstinspires.ftc.teamcode.motor.MotorControl;

import kotlin.NotImplementedError;

@Autonomous(preselectTeleOp = "Tele", name = "RedFarPreload", group = "Blue")

public class RedFarPreload extends AbstractVisionOpMode {
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
        return new Pose2d(-36,62,Math.toRadians(90));
    }

    @Override
    public Action trajRight(AprilTagDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)

                .stopAndAdd(drive.CorrectWithTagAction())
                // GOTO GROUND PIXEL
                .lineToY(-30)
                .turn(Math.toRadians(90))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-40,-10), Math.toRadians(0))
                .strafeTo(new Vector2d(15,-10))
                .splineToLinearHeading(new Pose2d(40,-35,Math.toRadians(-180)),Math.toRadians(-180))
                .waitSeconds(.25)
                .splineToConstantHeading(new Vector2d(45,-41),Math.toRadians(90))
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(.4)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(.4)


                // PARK
                .splineToConstantHeading(new Vector2d(56,-10),Math.toRadians(0))
                .build();


    }
    @Override
    public Action trajCenter(AprilTagDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)

                .stopAndAdd(drive.CorrectWithTagAction())
                //GOTO GROUND PIXEL
                .lineToY(-14)
                .endTrajectory()
                //OutakePurple
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))

                //GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-18,-12),Math.toRadians(0))
                .setReversed(true)
                .strafeTo(new Vector2d(25,-12))
                .splineToConstantHeading(new Vector2d(38,-35),Math.toRadians(0))
                .turn(Math.toRadians(-90))
                .waitSeconds(.25)
                .lineToX(45)
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(.4)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(.4)


                // PARK
                .splineToConstantHeading(new Vector2d(56,-10), Math.toRadians(0))


                .build();



    }

    @Override
    public Action trajLeft(AprilTagDrive drive, MotorActions motorActions) { // TODO TOO LONG

        return drive.actionBuilder(drive.pose) // new Pose2d(-36,-62,Math.toRadians(-90))
                .setReversed(true)
                .stopAndAdd(drive.CorrectWithTagAction())
                // GOTO GROUND PIXEL
                .lineToY(30)
                .turn(Math.toRadians(-90))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-40,10), Math.toRadians(0))
                .strafeTo(new Vector2d(25,10))
                .splineTo(new Vector2d(38,35),Math.toRadians(180))
                .strafeTo(new Vector2d(45,29))
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(.4)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(.4)

                // Park
                .strafeTo(new Vector2d(56,10))
                .endTrajectory()


                .build();
    }
}