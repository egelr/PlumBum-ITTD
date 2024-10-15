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

    public double drive_speed = 1;
    private SimpleServo clawServo;
    private SimpleServo intakeAngleServo;
    public CRServo intakeSpinServo;
    private DcMotor LMLeft;
    private DcMotor LMRight;
    private Motor fL, fR, bL, bR;

    @Override
    public void runOpMode() throws InterruptedException {

        //Creating Drivetrain Motors
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


        LMLeft = hardwareMap.get(DcMotor.class, "LiftMotorLeft"); //new DcMotor(hardwareMap, "LiftMotorLeft");
        LMRight = hardwareMap.get(DcMotor.class, "LiftMotorRight"); //new DcMotor(hardwareMap, "LiftMotorRight");
        telemetry.addData("Hardware: ", "Initialized");
        telemetry.update();
        LMLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LMLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LMRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int pos = LMLeft.getCurrentPosition();
//            int pos2 = LMRight.getCurrentPosition();
        intakeSpinServo = hardwareMap.get(CRServo.class, "intakeSpinServo");
        telemetry.addData("LeftLift: ", LMLeft.getCurrentPosition());
        telemetry.addData("RightLift: ", LMRight.getCurrentPosition());
        telemetry.update();

        clawServo = new SimpleServo(
                hardwareMap, "clawServo", 0, 300,
                AngleUnit.DEGREES
        );
        intakeAngleServo = new SimpleServo(
                hardwareMap, "intakeAngleServo", 0, 300,
                AngleUnit.DEGREES
        );


        ElapsedTime timer = new ElapsedTime();


        waitForStart();

        while (!isStopRequested()) {

            drive.driveRobotCentric(
                    -gamepad1.left_stick_x * drive_speed,
                    gamepad1.left_stick_y * drive_speed,
                    -gamepad1.right_stick_x * drive_speed,
                    false

            );
            //Drivetrain Motors speed Change
            if (gamepad1.right_trigger > 0.5) {
                drive_speed = 0.35;
            } else {
                drive_speed = 1;
            }
            if (gamepad1.share) {
                pos = pos + 1900;
                LMLeft.setTargetPosition(pos);
                LMRight.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while ((LMLeft.isBusy()|| LMRight.isBusy()) && timer.seconds() < 2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);

                }
                LMLeft.setPower(0);
                LMRight.setPower(0);
                telemetry.addData("timer:", timer.seconds());
                telemetry.addData("Status Left: ", LMLeft.getCurrentPosition());
                telemetry.update();
            }

            if (gamepad1.options) {
                pos = pos - 1875;
                LMLeft.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(pos);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while ((LMLeft.isBusy()|| LMRight.isBusy()) && timer.seconds() < 2) {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.guide)
            {
                pos = pos + 200;
                LMLeft.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(pos);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.cross)
            {
                pos = pos - 200;
                LMLeft.setTargetPosition(pos);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(pos);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.square)
            {
                //pos = pos+300;
                LMLeft.setTargetPosition(580);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(580);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            if(gamepad1.dpad_down)
            {
                //pos = pos - 450;
                LMLeft.setTargetPosition(30);
                LMLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                LMRight.setTargetPosition(30);
                LMRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                timer.reset();
                while((LMLeft.isBusy()||LMRight.isBusy()) && timer.seconds()<2)
                {
                    LMLeft.setPower(1);
                    LMRight.setPower(1);
                }
            }
            LMLeft.setPower(0);
            LMRight.setPower(0);
            telemetry.addData("Status: ", LMLeft.getCurrentPosition());
            telemetry.update();

            if (gamepad1.circle)
            {
                clawServo.turnToAngle(220);
            }
            if (gamepad1.triangle)
            {
                clawServo.turnToAngle(293);
            }
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

            if(gamepad1.dpad_right)
            {
                intakeAngleServo.turnToAngle(88);
            }
            if(gamepad1.dpad_up)
            {
                intakeAngleServo.turnToAngle(250 );
            }
        }

    }
}

