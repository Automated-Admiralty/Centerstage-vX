package org.firstinspires.ftc.teamcode.opmode.Tele.Auto.BLUEPRELOAD;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Tele.Auto.AbstractVisionOpMode;
import org.firstinspires.ftc.teamcode.COMMON.RRExtras.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.PoseStorage;
import org.firstinspires.ftc.teamcode.COMMON.motor.MotorActions;
import org.firstinspires.ftc.teamcode.COMMON.motor.MotorControl;

@Autonomous(preselectTeleOp = "Tele", name = "BlueClosePreload", group = "Blue")

public class BlueClosePreload extends AbstractVisionOpMode {
    /**
     * Is this a red or a blue autonomous?
     *
     * @return the team
     */
    @Override
    public PoseStorage.Team team() {
        return PoseStorage.Team.BLUE;
    }

    /**
     * Starting Position of the trajectories
     *
     * @return the starting pose
     */
    @Override
    public Pose2d startPose() {
        return new Pose2d(12,62,Math.toRadians(90));
    }

    @Override
    public Action trajRight(MecanumDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)
                .setReversed(true)
                .strafeTo(new Vector2d(45,45))
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(45,30))
                .strafeTo(new Vector2d(16,30))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .strafeTo(new Vector2d(51.5,26.5))
                .endTrajectory()
                //Score
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring))
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot))
                .waitSeconds(1)
                .stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING))
                .waitSeconds(.5)
                .stopAndAdd(motorActions.clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot))
                .stopAndAdd(motorActions.slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED))
//                .waitSeconds(1)


                // PARK
                .strafeTo(new Vector2d(56,62))
                .build();


    }
    @Override
    public Action trajCenter(MecanumDrive drive, MotorActions motorActions) {

        return drive.actionBuilder(drive.pose)

                // .stopAndAdd(drive.CorrectWithTagAction())
                //GOTO GROUND PIXEL
                .setReversed(true)
                .strafeTo(new Vector2d(45,45))
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(45,35))
                .strafeTo(new Vector2d(30,21))
                .endTrajectory()
                //OutakePurple
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))

                //GOTO BACKBOARD
                .strafeTo(new Vector2d(51,36))
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
                //.waitSeconds(1)


                // PARK
                .strafeTo(new Vector2d(56,62))


                .build();



    }

    @Override
    public Action trajLeft(MecanumDrive drive, MotorActions motorActions) { // TODO TOO LONG

        return drive.actionBuilder(drive.pose) // new Pose2d(-36,-62,Math.toRadians(-90))

                //.stopAndAdd(drive.CorrectWithTagAction())
                // GOTO GROUND PIXEL
                .setReversed(true)
                .strafeTo(new Vector2d(45,45))
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(45,30))
                .strafeTo(new Vector2d(43.5,30))
                .endTrajectory()
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                .stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .strafeTo(new Vector2d(51.5,44.25))
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
                //.waitSeconds(1)

                .strafeTo(new Vector2d(56,62))

                .build();


    }
}