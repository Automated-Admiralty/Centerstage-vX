package org.firstinspires.ftc.teamcode.Auto.REDPRELOAD;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Auto.AbstractVisionOpMode;
import org.firstinspires.ftc.teamcode.RRExtras.AprilTagDrive;
import org.firstinspires.ftc.teamcode.RRExtras.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.PoseStorage;
import org.firstinspires.ftc.teamcode.motor.MotorActions;
import org.firstinspires.ftc.teamcode.motor.MotorControl;

import kotlin.NotImplementedError;

@Autonomous(preselectTeleOp = "Tele", name = "RedFarPreload", group = "Red")

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
        return new Pose2d(-36,-62,Math.toRadians(-90));
    }

    @Override
    public Action trajRight(MecanumDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)

                // GOTO GROUND PIXEL
                .lineToY(-32)
                .turn(Math.toRadians(90))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD

                .setReversed(true)
                .turn(Math.toRadians(-90))
                .lineToY(-8)
                .setReversed(true)
                .strafeTo(new Vector2d(50,-12))
                .splineToConstantHeading(new Vector2d(49.75,-43),Math.toRadians(0))
                .turn(Math.toRadians(-90))
                .lineToX(61.4)
                .waitSeconds(1)
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(1)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(1)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(1)


                .build();


    }
    @Override
    public Action trajCenter(MecanumDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)

                //GOTO GROUND PIXEL
                .lineToY(-11)
                .endTrajectory()
                //OutakePurple
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))

                //GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-18,-12),Math.toRadians(0))
                .setReversed(true)
                .strafeTo(new Vector2d(50,-12))
                .splineToConstantHeading(new Vector2d(49.5,-37),Math.toRadians(0))
                .turn(Math.toRadians(-90))
                .lineToX(59.5)
                .waitSeconds(1)
                //.splineToConstantHeading(new Vector2d(41,37.5),Math.toRadians(0))
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(1)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(1)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(1)


                // PARK
                //.splineToConstantHeading(new Vector2d(56,10), Math.toRadians(0))


                .build();



    }

    @Override
    public Action trajLeft(MecanumDrive drive, MotorActions motorActions) { // TODO TOO LONG

        return drive.actionBuilder(drive.pose) // new Pose2d(-36,-62,Math.toRadians(-90))
                .setReversed(true)
                // .stopAndAdd(drive.CorrectWithTagAction())
                // GOTO GROUND PIXEL
                .lineToY(-40)
                .splineToConstantHeading(new Vector2d(-51.5,-18), Math.toRadians(-90))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-16,-10),Math.toRadians(0))
                .splineTo(new Vector2d(50,-12),Math.toRadians(0))
                .splineTo(new Vector2d(60,-36),Math.toRadians(-90))
                .waitSeconds(.5)
                // .splineToConstantHeading(new Vector2d(44,30),Math.toRadians(90))
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(1)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(1)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                .waitSeconds(1)


                // PARK
                //  .strafeTo(new Vector2d(56,10))
                .build();
    }
}