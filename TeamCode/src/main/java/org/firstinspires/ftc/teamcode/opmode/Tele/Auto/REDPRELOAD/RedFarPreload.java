package org.firstinspires.ftc.teamcode.opmode.Tele.Auto.REDPRELOAD;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Tele.Auto.AbstractVisionOpMode;
import org.firstinspires.ftc.teamcode.COMMON.RRExtras.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.PoseStorage;
import org.firstinspires.ftc.teamcode.COMMON.motor.MotorActions;
import org.firstinspires.ftc.teamcode.COMMON.motor.MotorControl;

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
                .lineToX(57)
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
                .waitSeconds(.5)
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
                .strafeTo(new Vector2d(52,-12))
                .splineToConstantHeading(new Vector2d(52,-36.5),Math.toRadians(0))
                .turn(Math.toRadians(-90))
                .lineToX(58.25)
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
                .waitSeconds(.5)
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                // PARK
                //.splineToConstantHeading(new Vector2d(56,10), Math.toRadians(0))
                .waitSeconds(1)


                .build();



    }

    @Override
    public Action trajLeft(MecanumDrive drive, MotorActions motorActions) { // TODO TOO LONG

        return drive.actionBuilder(drive.pose) // new Pose2d(-36,-62,Math.toRadians(-90))
                .setReversed(true)
                // .stopAndAdd(drive.CorrectWithTagAction())
                // GOTO GROUND PIXEL
                .lineToY(-40)
                .splineToConstantHeading(new Vector2d(-46.85,-18), Math.toRadians(-90))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-16,-10),Math.toRadians(0))
                .splineTo(new Vector2d(50,-14),Math.toRadians(0))
                .splineTo(new Vector2d(59.75,-36),Math.toRadians(-90))
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
                .waitSeconds(.5)
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
                // PARK
                .waitSeconds(1)

                //  .strafeTo(new Vector2d(56,10))
                .build();
    }
}