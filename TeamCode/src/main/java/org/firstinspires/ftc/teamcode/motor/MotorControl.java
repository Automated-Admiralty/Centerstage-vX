package org.firstinspires.ftc.teamcode.motor;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.TELETOOLS.RobotHardware.PIDCONTROLLERTOOL;
import org.firstinspires.ftc.teamcode.Tele;

/**
 * This class is used to control the motor systems on the robot.
 */
public class MotorControl {


    public final Slides USlides;
    public final ClawPivot UClawPivot;

    public final Claw UClaw;
    public final Intake UIntake;
    public final MiniArm UMiniArm;

    /**
     * This initializes the arm and slide motors, and resets the mode to the default. This should be run before any other methods.
     *
     * @param hardwareMap The hardware map to use to get the motors.
     */


    public MotorControl(@NonNull HardwareMap hardwareMap) {
        // TODO; probably not needed to automate init, but if you did use annotations
        UMiniArm = new MiniArm(hardwareMap);
        USlides = new Slides(hardwareMap);
        UClaw = new Claw(hardwareMap);
        UClawPivot = new ClawPivot(hardwareMap);
        UIntake = new Intake(hardwareMap);

        UClaw.MCCurrentClawState = Claw.MCClawState.CLOSED;
        UMiniArm.MCCurrentMiniArmState = MiniArm.MCMiniArmState.HOVERING;
        USlides.MCCurrentSlideState = Slides.MCSlideState.RETRACTED;

        UClawPivot.MCCurrentClawPivotState = ClawPivot.MCClawPivotState.RetractedPivot;
        UIntake.MCCurrentIntakeState = Intake.MCIntakeState.IDLE;
    }

    /**
     * This class updates the arm and slide motors to match the current state.
     */
    public void update() {

        USlides.update();
        UClawPivot.update();
        UMiniArm.update();
        UClaw.update();
        UIntake.update();
    }



    /**
     * MINI Arm
     */
    @Config
    public static class MiniArm {

        public Servo MiniArmLeft;
        public Servo MiniArmRight;

        public enum MCMiniArmState {
            Scoring(.7),
            HOVERING(.0),
            Intaking(.05);

            private final double MCminiarmangle;

            private MCMiniArmState(final double MCminiarmangle) {
                this.MCminiarmangle = MCminiarmangle;
            }


        }

        MCMiniArmState MCCurrentMiniArmState = MCMiniArmState.HOVERING;

        public MiniArm(@NonNull HardwareMap hardwareMap) {
            MiniArmLeft = hardwareMap.get(Servo.class, "MiniArmLeft");
            MiniArmRight = hardwareMap.get(Servo.class, "MiniArmRight");
            MiniArmRight.setDirection(Servo.Direction.REVERSE);
            MiniArmLeft.setDirection(Servo.Direction.REVERSE);
            //controller.setOutputBounds(-0.3, 0.5);
        }


        public void update() {
            MiniArmLeft.setPosition(MCCurrentMiniArmState.MCminiarmangle);
            MiniArmRight.setPosition(MCCurrentMiniArmState.MCminiarmangle);
        }

        public void SetMiniArmState(MCMiniArmState TargetMiniArmState) {
            MCCurrentMiniArmState = TargetMiniArmState;
        }

    }











    /**
     * Slide Control
     */
    public static class Slides {

        //boolean resetting = false;
        public enum MCSlideState {
            RETRACTED(0),
            EXTEND1(300),
            EXTEND2(600),
            EXTEND3(900),
            EXTEND4(1200),
            EXTEND5(1500),
            MAXEXTEND(1800);
            private final int MCticks;

            private MCSlideState(final int MCticks) {
                this.MCticks = MCticks;
            }


        }

        MCSlideState MCCurrentSlideState = MCSlideState.RETRACTED;
        public DcMotorEx LeftSlide;
        public DcMotorEx RightSlide;
        public PIDCONTROLLERTOOL MCSlideControllerLeft;
        public PIDCONTROLLERTOOL MCSlideControllerRight;

        /**
         * This initializes the slide motor. This should be run before any other methods.
         *
         * @param hardwareMap The hardware map to use to get the motors.
         */
        public Slides(HardwareMap hardwareMap) {
            LeftSlide = hardwareMap.get(DcMotorEx.class, "LeftSlide");
            RightSlide = hardwareMap.get(DcMotorEx.class, "RightSlide");
            LeftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LeftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

            MCSlideControllerLeft = new PIDCONTROLLERTOOL(.018, 0, .00002, .0005, 384.5 / 360, LeftSlide);//TODO tune these values in the test file
            MCSlideControllerRight = new PIDCONTROLLERTOOL(.018, 0, .00002, .0005, 384.5 / 360, RightSlide);//TODO tune these values in the test file

        }

        /**
         * This stops the slide, sets the state to down, sets the target to 0, and resets the encoder.
         */
        public void reset() {
            LeftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }


        /**
         * This updates the slide motor to match the current state. This should be run in a loop.
         */
        public void update() {

            LeftSlide.setPower(MCSlideControllerLeft.calculatePid(MCCurrentSlideState.MCticks));
            RightSlide.setPower(MCSlideControllerRight.calculatePid(MCCurrentSlideState.MCticks));
        }


        public void SetSlideState(MCSlideState TargetSlideState) {
            MCCurrentSlideState = TargetSlideState;
        }
    }







    /**
     * This class controls the claw.
     */
    public static class Claw {

        //boolean resetting = false;
        public enum MCClawState {
            OPEN(0),
            CLOSED(1);
            private final int MCCLAWOPENVSCLOSED;

            private MCClawState(final int MCCLAWOPENVSCLOSED) {
                this.MCCLAWOPENVSCLOSED = MCCLAWOPENVSCLOSED;
            }


        }

        MCClawState MCCurrentClawState = MCClawState.OPEN;
        public Servo Claw;


        public Claw(HardwareMap hardwareMap) {
            Claw = hardwareMap.get(Servo.class, "Claw");
        }


        /**
         * This updates the slide motor to match the current state. This should be run in a loop.
         */
        public void update() {
            Claw.setPosition(MCCurrentClawState.MCCLAWOPENVSCLOSED);
        }


        public void SetClawState(MCClawState TargetClawState) {
            MCCurrentClawState = TargetClawState;
        }
    }





    /**
     * This class controls the clawPivot.
     */
    public static class ClawPivot {

        public Servo MCClawPivotLeft;
        public Servo MCClawPivotRight;
        public enum MCClawPivotState{
            RetractedPivot(.0),
            Extend1Pivot(.2),
            Extend2Pivot(.22),
            Extend3Pivot(.24),
            Extend4Pivot(.26),
            Extend5Pivot(.28),
            MaxHiehgtPivot(.3);
            private final double MCClawAngle;
            private MCClawPivotState(final double MCClawAngle) { this.MCClawAngle = MCClawAngle; }
        }
        MCClawPivotState MCCurrentClawPivotState = MCClawPivotState.RetractedPivot;



        public ClawPivot(HardwareMap hardwareMap) {
            MCClawPivotLeft = hardwareMap.get(Servo.class,"ClawPivotLeft");
            MCClawPivotRight = hardwareMap.get(Servo.class,"ClawPivotRight");
            MCClawPivotRight.setDirection(Servo.Direction.REVERSE);
        }


        /**
         *Updates Claw Pivot
         */
        public void update() {
            MCClawPivotLeft.setPosition(MCCurrentClawPivotState.MCClawAngle);
            MCClawPivotRight.setPosition(MCCurrentClawPivotState.MCClawAngle);
        }


        public void SetClawPivotState(MCClawPivotState TargetClawPivot) {
            MCCurrentClawPivotState = TargetClawPivot;
        }
    }








    /**
     *Updates Intake
     */

    public static class Intake {

        //boolean resetting = false;
        public enum MCIntakeState {
            IDLE(0),
            IN(.5),
            OUT(-.5);
            private final double MCIntakeSpeed;

            private MCIntakeState(final double MCIntakeSpeed) {
                this.MCIntakeSpeed = MCIntakeSpeed;
            }


        }

        MCIntakeState MCCurrentIntakeState = MCIntakeState.IDLE;
        public DcMotorEx IntakeMotor;

        public Intake(HardwareMap hardwareMap) {
            IntakeMotor = hardwareMap.get(DcMotorEx.class, "IntakeMotor");
            IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }


        /**
         * This updates the slide motor to match the current state. This should be run in a loop.
         */
        public void update() {
           IntakeMotor.setPower(MCCurrentIntakeState.MCIntakeSpeed);
        }


        public void SetIntakeState(MCIntakeState TargetIntakeState) {
            MCCurrentIntakeState = TargetIntakeState;
        }
    }
}