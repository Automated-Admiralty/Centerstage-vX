package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, -62, Math.toRadians(-90)))

                .lineToY(-40)
                .splineToConstantHeading(new Vector2d(-46,-18), Math.toRadians(-90))
                .endTrajectory()
                //.stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.OUT))
                .waitSeconds(1)
                //.stopAndAdd(motorActions.intake.setIntakeState(MotorControl.Intake.MCIntakeState.IDLE))




                // GOTO BACKBOARD
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(-16,-10),Math.toRadians(0))
                .splineTo(new Vector2d(25,-10),Math.toRadians(0))
                .splineTo(new Vector2d(38,-35),Math.toRadians(-90))
                .waitSeconds(.25)
                .splineToConstantHeading(new Vector2d(45,-41),Math.toRadians(-90))
                .endTrajectory()
                //Score
                //.stopAndAdd(motorActions.DepositToScoringPose())
                .waitSeconds(.4)
                //.stopAndAdd(motorActions.claw.setClawTargetState(MotorControl.Claw.MCClawState.OPEN))
                //.stopAndAdd(motorActions.DepositToRetractedPose())
                .waitSeconds(.4)


                // PARK
                        .setReversed(true)
                .splineToConstantHeading(new Vector2d(56,-10), Math.toRadians(0))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}