package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "OpMode")
public class OpMode extends LinearOpMode {

    //Creating the variables for Servos and Motors
    private SimpleServo clawServo;
    private SimpleServo intakePivotServo;
    private SimpleServo intakeArmServo;
    private SimpleServo intakeClawServo;
    private SimpleServo transferClawServo;
    private SimpleServo flipServo;
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

        clawServo = new SimpleServo(
                hardwareMap, "clawServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakePivotServo = new SimpleServo(
                hardwareMap, "intakePivotServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakeArmServo = new SimpleServo(
                hardwareMap, "intakeArmServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakeClawServo = new SimpleServo(
                hardwareMap, "intakeClawServo", 0, 300,
                AngleUnit.DEGREES
        );
        transferClawServo = new SimpleServo(
                hardwareMap, "transferClawServo", 0, 300,
                AngleUnit.DEGREES
        );
        flipServo = new SimpleServo(
                hardwareMap, "flipServo", 0, 300,
                AngleUnit.DEGREES
        );

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
                clawServo.turnToAngle(293);
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
                clawServo.turnToAngle(220);
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


            //Intake flipping controls
            /*if(gamepad1.triangle && gamepad1.left_trigger < 0.5 && gamepad1.right_trigger < 0.5)
            {
                intakeArmServo.turnToAngle(200);
                wait.reset();
                while (wait.seconds() < 0.5) {}

                intakeArmServo.turnToAngle(275);
            }
            if(gamepad1.cross && gamepad1.left_trigger < 0.5 && gamepad1.right_trigger < 0.5)
            {
                intakeArmServo.turnToAngle(110);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.triangle) {
                intakeClawServo.turnToAngle(250);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.cross) {
                intakeClawServo.turnToAngle(150);
            }
             */
            if (gamepad1.right_trigger > 0.5 && gamepad1.triangle) {
                intakePivotServo.turnToAngle(140);
            }
            if (gamepad1.right_trigger > 0.5 && gamepad1.cross) {
                intakePivotServo.turnToAngle(230);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.circle) {
                transferClawServo.turnToAngle(30);
            }
            if (gamepad1.left_trigger > 0.5 && gamepad1.square) {
                transferClawServo.turnToAngle(90);
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
                IntakeMotor.setTargetPosition(1900);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
                }
                intakeClawServo.turnToAngle(150);
                intakeArmServo.turnToAngle(120);
            }
            if(gamepad1.square && gamepad1.left_trigger < 0.5)
            {
                intakeArmServo.turnToAngle(100);
                timer.reset();
                while (timer.seconds() < 0.4) {}
                intakeClawServo.turnToAngle(250);
                intakePivotServo.turnToAngle(140);
                intakeArmServo.turnToAngle(200);
                wait.reset();
                while (wait.seconds() < 0.5) {}
                intakeArmServo.turnToAngle(275);
                IntakeMotor.setTargetPosition(0);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
                }
            }
            if(gamepad1.dpad_left)
            {
                transferClawServo.turnToAngle(30);
                wait.reset();
                while (wait.seconds() < 0.2) {}
                flipServo.turnToAngle(217);
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
                transferClawServo.turnToAngle(90);
                wait.reset();
                while (wait.seconds() < 0.1) {}
                intakeClawServo.turnToAngle(150);
                flipServo.turnToAngle(60);
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
            if(gamepad1.left_bumper)
            {
                transferClawServo.turnToAngle(90);
                wait.reset();
                while (wait.seconds() < 0.1) {}
                intakeClawServo.turnToAngle(150);
                flipServo.turnToAngle(60);
                timer.reset();
                while (timer.seconds() < 0.7) {}
                transferClawServo.turnToAngle(30);
            }
            //Telemetry and slides' powers
            LMLeft.setPower(0.1);
            LMRight.setPower(0.1);
            IntakeMotor.setPower(0.1);
            telemetry.addData("Status: ", LMLeft.getCurrentPosition());
            telemetry.addData("Statusintake", IntakeMotor.getCurrentPosition());
            telemetry.addData("armServo",intakeArmServo.getAngle());
            telemetry.addData("pivot",intakePivotServo.getAngle());
            telemetry.addData("clawIntake",intakeClawServo.getAngle());
            telemetry.update();

        }

    }
}

