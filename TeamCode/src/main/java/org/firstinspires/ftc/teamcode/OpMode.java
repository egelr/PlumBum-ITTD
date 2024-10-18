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
    private SimpleServo intakeAngleServo;
    public CRServo intakeSpinServo;
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
        intakeSpinServo = hardwareMap.get(CRServo.class, "intakeSpinServo");

        clawServo = new SimpleServo(
                hardwareMap, "clawServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakeAngleServo = new SimpleServo(
                hardwareMap, "intakeAngleServo", 0, 300,
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
                drive_speed = 1;
            }
            //Picking up Speciments from the wall control
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
            //Placing/Hanging the Speciments control
            if (gamepad1.dpad_down)
            {
                LMLeft.setTargetPosition(1100);
                LMRight.setTargetPosition(1100);
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

            //Intake spinning controls
            if (gamepad1.right_bumper)
            {
                intakeSpinServo.setPower(1);
                timer.reset();
            }
            if (gamepad1.left_bumper)
            {
                intakeSpinServo.setPower(-1);
                timer.reset();
            }
            if (timer.seconds() > 0.1)
            {
                intakeSpinServo.setPower(0);
            }

            //Intake flipping controls
            if(gamepad1.triangle)
            {
                intakeAngleServo.turnToAngle(0);
            }
            if(gamepad1.cross)
            {
                intakeAngleServo.turnToAngle(300 );
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
            if(gamepad1.circle)
            {
                IntakeMotor.setTargetPosition(1900);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
                }
            }
            if(gamepad1.square)
            {
                IntakeMotor.setTargetPosition(0);
                IntakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while (IntakeMotor.isBusy() && timer.seconds() < 2) {
                    IntakeMotor.setPower(1);
                }
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

