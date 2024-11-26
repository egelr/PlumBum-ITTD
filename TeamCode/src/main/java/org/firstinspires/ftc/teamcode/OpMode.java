package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.FlipServo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "OpMode")
public class OpMode extends LinearOpMode {

    int liftLastPosition;
    //Creating the variables for Motors
    private Motor LMLeft;
    private Motor LMRight;
    private Motor IntakeMotor;
    private Motor fL, fR, bL, bR;
    //Creating drive speed variable
    public double drive_speed = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors and Setting their behaviour to "brake"
        fL = new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312);
        fR = new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312);
        bL = new Motor(hardwareMap, "leftBack", Motor.GoBILDA.RPM_312);
        bR = new Motor(hardwareMap, "rightBack", Motor.GoBILDA.RPM_312);

        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //Creating the Mecanum Drivetrain
        MecanumDrive drive = new MecanumDrive(fL, fR, bL, bR);

        //Creating Lift/Slides and Intake Motors, Setting their behaviour to "brake"
        LMLeft = new Motor(hardwareMap, "LiftMotorLeft");
        LMRight = new Motor(hardwareMap, "LiftMotorRight");
        IntakeMotor = new Motor(hardwareMap, "IntakeMotor");

        LMLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        LMLeft.setInverted(true);
        LMRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


        LMLeft.setPositionCoefficient(0.05);
        LMRight.setPositionCoefficient(0.05);
        IntakeMotor.setPositionCoefficient(0.05);

        LMLeft.setPositionTolerance(50);
        LMRight.setPositionTolerance(50);
        IntakeMotor.setPositionTolerance(50);

        LMLeft.resetEncoder();
        LMRight.resetEncoder();
        IntakeMotor.resetEncoder();
        //Resetting the encoders of Lift and Intake Motors
        LMLeft.setTargetPosition(0);
        LMRight.setTargetPosition(0);
        IntakeMotor.setTargetPosition(0);
        LMLeft.setRunMode(Motor.RunMode.PositionControl);
        LMRight.setRunMode(Motor.RunMode.PositionControl);
        IntakeMotor.setRunMode(Motor.RunMode.PositionControl);

        //Creating Servos

        Servo clawServo = hardwareMap.get(Servo.class, "clawServo");
        Servo intakePivotServo = hardwareMap.get(Servo.class, "intakePivotServo");
        Servo intakeArmServo = hardwareMap.get(Servo.class, "intakeArmServo");
        Servo intakeClawServo = hardwareMap.get(Servo.class, "intakeClawServo");

        Servo transferClawServo = hardwareMap.get(Servo.class, "transferClawServo");

        Servo flipServo =  hardwareMap.get(Servo.class, "flipServo");

        //Creating timer variables
        ElapsedTime timer = new ElapsedTime();
        ElapsedTime wait = new ElapsedTime();

        waitForStart();

        while (!isStopRequested()) {

            //Drivetrain controls
            drive.driveRobotCentric(
                    -gamepad1.left_stick_x * drive_speed,
                    gamepad1.left_stick_y * drive_speed,
                    -gamepad1.right_stick_x * drive_speed,
                    false

            );
            //Drivetrain Motors speed Change controls
            if (gamepad1.right_trigger > 0.5) {
                drive_speed = 0.35;
            } else {
                drive_speed = 1;
            }
            //Picking up Specimens from the wall control
            if (gamepad1.dpad_up)
            {
                LMLeft.setTargetPosition(250);
                LMRight.setTargetPosition(250);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
                LMLeft.set(0);
                LMRight.set(0);

                clawServo.setPosition(variables.specimenClawAngleClosed/300);
                //wait.reset();
                //while (wait.seconds() < 0.6) {}
                sleep(200);
                timer.reset();
                LMLeft.setTargetPosition(1550);
                LMRight.setTargetPosition(1550);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
            }
            //Placing/Hanging the Specimens control
            if (gamepad1.dpad_down)
            {
                LMLeft.setTargetPosition(variables.liftDownPos);
                LMRight.setTargetPosition(variables.liftDownPos);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
                clawServo.setPosition(variables.specimenClawAngleOpened/300);
                LMLeft.set(0);
                LMRight.set(0);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                timer.reset();
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                //Drive forward for 0.02sec
                timer.reset();
                while( timer.seconds()<0.04)
                {

                    drive.driveRobotCentric(
                            0,
                            -0.3,
                            0,
                            false

                    );
                }
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
            }


            if (gamepad1.right_trigger > 0.5 && gamepad1.triangle) {
                intakePivotServo.setPosition(variables.pivotVerticalAngle/300);
            }
            if (gamepad1.right_trigger > 0.5 && gamepad1.cross) {
                intakePivotServo.setPosition(variables.pivotHorizontalAngle/300);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.cross) {
                transferClawServo.setPosition(variables.transferClawServoAngleOpened/300);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.triangle) {
                transferClawServo.setPosition(variables.transferClawServoAngleClosed/300);
            }
            //Robot hanging controls
            if(gamepad1.share)
            {
                LMLeft.setTargetPosition(1500);
                LMRight.setTargetPosition(1500);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
            }
            if(gamepad1.options)
            {
                LMLeft.setTargetPosition(1150);
                LMRight.setTargetPosition(1150);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2)
                {
                    LMLeft.set(0.5);
                    LMRight.set(0.5);
                }
            }

            //Intake slide extension or reduction controls
            if(gamepad1.circle && gamepad1.left_trigger < 0.5)
            {
                IntakeMotor.setTargetPosition(1350);
                IntakeMotor.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while (!IntakeMotor.atTargetPosition() && timer.seconds() < 2) {
                    IntakeMotor.set(0.9);
                }
                intakeClawServo.setPosition(variables.intakeClawServoAngleOpened/300);
                intakeArmServo.setPosition(variables.armServoAngleDown/300);
            }
            if(gamepad1.square && gamepad1.left_trigger < 0.5)
            {
                intakeArmServo.setPosition(variables.armServoAngleGrab/300);
                timer.reset();
                while (timer.seconds() < 0.4) {}
                intakeClawServo.setPosition(variables.intakeClawServoAngleClosed/300);
                timer.reset();
                while (timer.seconds() < 0.4) {}
                intakePivotServo.setPosition(variables.pivotVerticalAngle/300);
                intakeArmServo.setPosition(variables.armServoAngleStraight/300);
                wait.reset();
                while (wait.seconds() < 0.5) {}
                intakeArmServo.setPosition(variables.armServoAngleUp/300);
                IntakeMotor.setTargetPosition(0);
                IntakeMotor.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while (!IntakeMotor.atTargetPosition() && timer.seconds() < 2) {
                    IntakeMotor.set(0.9);
                }
            }
            if(gamepad1.dpad_left)
            {
                transferClawServo.setPosition(variables.transferClawServoAngleOpened/300);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                flipServo.setPosition(variables.flipServoAngleDown/300);
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2) {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }

            }
            if(gamepad1.dpad_right)
            {
                transferClawServo.setPosition(variables.transferClawServoAngleClosed/300);
                wait.reset();
                while (wait.seconds() < 0.3) {}
                intakeClawServo.setPosition(variables.intakeClawServoAngleOpened/300);
                flipServo.setPosition(variables.flipServoAngleUp/300);
                LMLeft.setTargetPosition(1550);
                LMRight.setTargetPosition(1550);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);
                timer.reset();
                while((!LMLeft.atTargetPosition()) && timer.seconds()<2) {
                    LMLeft.set(0.9);
                    LMRight.set(0.9);
                }
            }
            if(gamepad1.left_bumper)
            {
                transferClawServo.setPosition(variables.transferClawServoAngleOpened/300);
                intakeArmServo.setPosition(variables.armServoAngleDown/300);
                sleep(100);
                intakeClawServo.setPosition(variables.intakeClawServoAngleOpened/300);
                sleep(100);
                intakeArmServo.setPosition(variables.armServoAngleUp/300);
            }
            if(gamepad1.guide){
                transferClawServo.setPosition(variables.transferClawServoAngleOpened/300);
                flipServo.setPosition(variables.flipServoAngleDown/300);
                intakeArmServo.setPosition(variables.armServoAngleUp/300);
                intakeClawServo.setPosition(variables.intakeClawServoAngleClosed/300);
                intakePivotServo.setPosition(variables.pivotVerticalAngle/300);
                clawServo.setPosition(variables.specimenClawAngleOpened/300);
                liftLastPosition = LMLeft.getCurrentPosition();

                LMLeft.setRunMode(Motor.RunMode.RawPower);
                LMRight.setRunMode(Motor.RunMode.RawPower);
                LMLeft.set(-0.1);
                LMRight.set(-0.1);

                sleep(100);

                while (!isStopRequested() && (LMLeft.getCurrentPosition() != liftLastPosition)) {
                    liftLastPosition = LMLeft.getCurrentPosition();
                    sleep(100);
                }

                LMLeft.set(0);
                LMRight.set(0);
                LMLeft.resetEncoder();
                LMRight.resetEncoder();

                sleep(50);

                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setRunMode(Motor.RunMode.PositionControl);
                LMRight.setRunMode(Motor.RunMode.PositionControl);

                liftLastPosition = IntakeMotor.getCurrentPosition();

                IntakeMotor.setRunMode(Motor.RunMode.RawPower);
                IntakeMotor.set(-0.1);

                sleep(100);

                while (!isStopRequested() && (IntakeMotor.getCurrentPosition() != liftLastPosition)) {
                    liftLastPosition = IntakeMotor.getCurrentPosition();
                    sleep(100);
                }

                IntakeMotor.set(0);
                IntakeMotor.resetEncoder();

                sleep(50);

                IntakeMotor.setTargetPosition(0);
                IntakeMotor.setRunMode(Motor.RunMode.PositionControl);
             }
            //Telemetry and slides' powers
            LMLeft.set(0.1);
            LMRight.set(0.1);
            IntakeMotor.set(0.1);
            telemetry.addData("Status: ", LMLeft.getCurrentPosition());
            telemetry.addData("Statusintake", IntakeMotor.getCurrentPosition());
            telemetry.update();

        }

    }
}