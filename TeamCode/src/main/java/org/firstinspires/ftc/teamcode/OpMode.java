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

    //Creating the variables for Servos and Motors
    private Servo clawServo;
    private Servo intakePivotServo;
    private Servo intakeArmServo;
    private Servo intakeClawServo;
    private Servo transferClawServo;
    private Servo flipServo;
    private DcMotor LMLeft;
    private DcMotor LMRight;
    private DcMotor IntakeMotor;
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
        LMLeft = hardwareMap.get(DcMotor.class, "LiftMotorLeft");
        LMRight = hardwareMap.get(DcMotor.class, "LiftMotorRight");
        IntakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");

        LMLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LMRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Resetting the encoders of Lift and Intake Motors
        LMLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LMRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        IntakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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
                drive_speed = 0.85;
            }
            //Picking up Specimens from the wall control
            if (gamepad1.dpad_up)
            {
                LMLeft.setTargetPosition(200);
                LMRight.setTargetPosition(200);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
                clawServo.setPosition(variables.specimenClawAngleClosed/300);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                timer.reset();
                LMLeft.setTargetPosition(1550);
                LMRight.setTargetPosition(1550);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            //Placing/Hanging the Specimens control
            if (gamepad1.dpad_down)
            {
                LMLeft.setTargetPosition(1050);
                LMRight.setTargetPosition(1050);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
                clawServo.setPosition(variables.specimenClawAngleOpened/300);;
                wait.reset();
                while (wait.seconds() < 0.2) {}
                timer.reset();
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
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
                LMLeft.setTargetPosition(1450);
                LMRight.setTargetPosition(1450);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.options)
            {
                LMLeft.setTargetPosition(0);
                LMRight.setTargetPosition(0);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }

            //Intake slide extension or reduction controls
            if(gamepad1.circle && gamepad1.left_trigger < 0.5)
            {
                IntakeMotor.setTargetPosition(1350);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
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
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
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
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
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
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            /*if(gamepad1.left_bumper)
            {
                transferClawServo.turnToAngle(90);
                wait.reset();
                while (wait.seconds() < 0.3) {}
                intakeClawServo.turnToAngle(150);
                flipServo.turnToAngle(100);
            }*/
            if(gamepad1.guide){
                transferClawServo.setPosition(variables.transferClawServoAngleOpened/300);
                flipServo.setPosition(variables.flipServoAngleDown/300);
                intakeArmServo.setPosition(variables.armServoAngleUp/300);
                intakeClawServo.setPosition(variables.intakeClawServoAngleOpened/300);
                intakePivotServo.setPosition(variables.pivotVerticalAngle/300);
                clawServo.setPosition(variables.specimenClawAngleOpened/300);;
            }
            //Telemetry and slides' powers
            LMLeft.setPower(0.1);
            LMRight.setPower(0.1);
            IntakeMotor.setPower(0.1);
            telemetry.addData("Status: ", LMLeft.getCurrentPosition());
            telemetry.addData("Statusintake", IntakeMotor.getCurrentPosition());
            telemetry.update();

        }

    }
}