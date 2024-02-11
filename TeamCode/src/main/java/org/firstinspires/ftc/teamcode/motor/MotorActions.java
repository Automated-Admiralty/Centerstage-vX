package org.firstinspires.ftc.teamcode.motor;

import android.transition.Slide;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;

public class MotorActions {
    public final MotorControl motorControl;
    public final Slides slide;

    public final Claw claw;
    public final MiniArm miniArm;
    public final ClawPivot clawPivot;
    public final Intake intake;

    public MotorActions(MotorControl motorControl) {
        this.motorControl = motorControl;
        this.slide = new Slides();
        this.miniArm = new MiniArm();
        this.claw = new Claw();
        this.intake = new Intake();
        this.clawPivot = new ClawPivot();

    }

    public Action update() {
        return t -> {
            motorControl.update();
            return true; // this returns true to make it loop forever; use RaceParallelCommand
        };
    }
    public Action DepositToScoringPose() {
        return t -> {
            slide.setSlideTargetState(MotorControl.Slides.MCSlideState.EXTEND3);
            miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.Scoring);
            clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.Extend3Pivot);

            return true; // this returns true to make it loop forever; use RaceParallelCommand
        };
    }
    public Action DepositToRetractedPose() {
        return t -> {
            miniArm.setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState.HOVERING);
            clawPivot.setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState.RetractedPivot);
            slide.setSlideTargetState(MotorControl.Slides.MCSlideState.RETRACTED);
            return true; // this returns true to make it loop forever; use RaceParallelCommand
        };
    }

    public class Slides {
        public Action setSlideTargetState(MotorControl.Slides.MCSlideState SlideState) {
            return t -> {
                motorControl.USlides.SetSlideState(SlideState);
                return false;
            };
        }

    }
    public class Claw {
        public Action setClawTargetState(MotorControl.Claw.MCClawState ClawState) {
            return t -> {
                motorControl.UClaw.SetClawState(ClawState);
                return false;
            };
        }

    }


    public class MiniArm {
        public Action setMiniArmTargetState(MotorControl.MiniArm.MCMiniArmState MiniArmState) {
            return t -> {
                motorControl.UMiniArm.SetMiniArmState(MiniArmState);
                return false;
            };
        }

    }
    public class ClawPivot {
        public Action setClawPivotTargetState(MotorControl.ClawPivot.MCClawPivotState ClawPivotState) {
            return t -> {
                motorControl.UClawPivot.SetClawPivotState(ClawPivotState);
                return false;
            };
        }
    }
    public class Intake {
        public Action setIntakeState(MotorControl.Intake.MCIntakeState IntakeState) {
            return t -> {
                motorControl.UIntake.SetIntakeState(IntakeState);
                return false;
            };
        }
    }




}