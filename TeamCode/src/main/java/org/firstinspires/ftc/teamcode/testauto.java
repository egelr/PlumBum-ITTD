package org.firstinspires.ftc.teamcode;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "Autotest", group = "Autonomous")
public class testauto extends LinearOpMode {
    public class Lift {
        private DcMotorEx lift1;
        private DcMotorEx lift2;

        public Lift(HardwareMap hardwareMap) {
            lift1 = hardwareMap.get(DcMotorEx.class, "LiftMotorRight");
            lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift1.setDirection(DcMotorSimple.Direction.FORWARD);
            lift2 = hardwareMap.get(DcMotorEx.class, "LiftMotorLeft");
            lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift2.setDirection(DcMotorSimple.Direction.REVERSE);

        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setPower(1);
                    lift2.setPower(1);
                    initialized = true;
                }

                double pos = lift1.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < 1550) {
                    return true;
                } else {
                    lift1.setPower(0);
                    lift2.setPower(0);

                    return false;
                }
            }
        }

        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setPower(-0.8);
                    lift2.setPower(-0.8);
                    initialized = true;
                }

                double pos = lift1.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 1100) {
                    return true;
                } else {
                    lift1.setPower(0);
                    lift2.setPower(0);

                    return false;
                }
            }
        }

        public Action liftDown() {
            return new LiftDown();
        }

        //liftPark
        public class LiftPark implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setPower(-0.8);
                    lift2.setPower(-0.8);
                    initialized = true;
                }

                double pos = lift1.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 0) {
                    return true;
                } else {
                    lift1.setPower(0);
                    lift2.setPower(0);

                    return false;
                }
            }
        }

        public Action liftPark() {
            return new Lift.LiftPark();
        }


        //liftSpeciment
        public class LiftSpeciment implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift1.setPower(-0.8);
                    lift2.setPower(-0.8);
                    initialized = true;
                }

                double pos = lift1.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > 200) {
                    return true;
                } else {
                    lift1.setPower(0);
                    lift2.setPower(0);

                    return false;
                }
            }
        }

        public Action liftSpeciment() {
            return new Lift.LiftSpeciment();
        }
    }

    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "clawServo");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(0.9766);
                return false;
            }
        }

        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(0.5);
                return false;
            }
        }

        public Action openClaw() {
            return new OpenClaw();
        }
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(0));
        Pose2d Pose1 = new Pose2d(0, 0, Math.toRadians(180));

        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Lift lift = new Lift(hardwareMap);


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(90));
        TrajectoryActionBuilder tab0 = drive.actionBuilder(new Pose2d(-31,0,Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-30.5);
        TrajectoryActionBuilder tab2 = drive.actionBuilder(new Pose2d(-30.5,0,Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10,30,Math.toRadians(185)),Math.toRadians(185))
                .setReversed(true)
                .lineToX(-0.5);
        TrajectoryActionBuilder tab3 = drive.actionBuilder(new Pose2d(-10.5 ,30,Math.toRadians(185)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -10, Math.toRadians(10)),Math.toRadians(0));
        TrajectoryActionBuilder tab4 = drive.actionBuilder(new Pose2d(-10, -10, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-31);
        TrajectoryActionBuilder tab5 = drive.actionBuilder(new Pose2d(-31, -10, Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-30);
        TrajectoryActionBuilder tab6 = drive.actionBuilder(new Pose2d(-30, -10, Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-8, 35, Math.toRadians(0)),Math.toRadians(0));


        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(claw.closeClaw());


        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;

            Action trajectoryActionChosen;
            Action trajectoryActionChosen0;
            Action trajectoryActionChosen1;
            Action trajectoryActionChosen3;
            Action trajectoryActionChosen4;
            Action trajectoryActionChosen5;
            Action trajectoryActionChosen6;


            trajectoryActionChosen = tab1.build();
            trajectoryActionChosen0 = tab0.build();
            trajectoryActionChosen1 = tab2.build();
            trajectoryActionChosen3 = tab3.build();
            trajectoryActionChosen4 = tab4.build();
            trajectoryActionChosen5 = tab5.build();
            trajectoryActionChosen6 = tab6.build();


            Actions.runBlocking(
                    new SequentialAction(
                                    trajectoryActionChosen
                    ));
        }
    }
}
