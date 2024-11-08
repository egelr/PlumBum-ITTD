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
@Autonomous(name = "AutoYellow", group = "Autonomous")
public class autoyellow extends LinearOpMode {
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
            //lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        public class LiftBasket implements Action {
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

        public Action liftBasket() {
            return new LiftBasket();
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
                if (pos > 1120) {
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
    public class IntakeSlides {
        private DcMotorEx iSlide;

        public IntakeSlides(HardwareMap hardwareMap) {
            iSlide = hardwareMap.get(DcMotorEx.class, "IntakeMotor");
            iSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            iSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        public class SlideExtend implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    iSlide.setPower(1);
                    initialized = true;
                }

                double pos1 = iSlide.getCurrentPosition();
                packet.put("liftPos", pos1);
                if (pos1 < 1280) {
                    return true;
                } else {
                    iSlide.setPower(0);
                    return false;
                }
            }
        }
        public  Action SlideExtend() {
            return new IntakeSlides.SlideExtend();
        }
        public class SlideExtend2 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    iSlide.setPower(1);
                    initialized = true;
                }

                double pos1 = iSlide.getCurrentPosition();
                packet.put("liftPos", pos1);
                if (pos1 < 1350) {
                    return true;
                } else {
                    iSlide.setPower(0);
                    return false;
                }
            }
        }
        public  Action SlideExtend2() {
            return new IntakeSlides.SlideExtend2();
        }
        public class SlidePark implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    iSlide.setPower(-0.8);
                    initialized = true;
                }

                double pos1 = iSlide.getCurrentPosition();
                packet.put("SlidePos", pos1);
                if (pos1 > 0) {
                    return true;
                } else {
                    iSlide.setPower(0);
                    return false;
                }
            }
        }
        public Action SlidePark() {
            return new IntakeSlides.SlidePark();
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
    public class FlipServo {
        private Servo flipServo;

        public FlipServo(HardwareMap hardwareMap) {
            flipServo = hardwareMap.get(Servo.class, "flipServo");
        }

        public class downFlip implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                flipServo.setPosition(0.856666);
                return false;
            }
        }
        public Action downFlip() {
            return new FlipServo.downFlip();
        }
        public class upFlip implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                flipServo.setPosition(0.34);
                return false;
            }
        }
        public Action upFlip() {
            return new FlipServo.upFlip();
        }
    }
    public class IntakeClaw {
        private Servo intakeClaw;

        public IntakeClaw(HardwareMap hardwareMap) {
            intakeClaw = hardwareMap.get(Servo.class, "intakeClawServo");
        }

        public class CloseIntakeClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeClaw.setPosition(0.8833333);
                return false;
            }
        }

        public Action closeIntakeClaw() {return new IntakeClaw.CloseIntakeClaw();}

        public class OpenIntakeClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeClaw.setPosition(0.6);
                return false;
            }
        }
        public Action openIntakeClaw() {
            return new IntakeClaw.OpenIntakeClaw();
        }
    }
    public class TransferClaw {
        private Servo transferClaw;

        public TransferClaw(HardwareMap hardwareMap) {
            transferClaw = hardwareMap.get(Servo.class, "transferClawServo");
        }

        public class CloseTransferClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                transferClaw.setPosition(0.3);
                return false;
            }
        }

        public Action closeTransferClaw() {return new TransferClaw.CloseTransferClaw();}

        public class OpenTransferClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                transferClaw.setPosition(0.1);
                return false;
            }
        }
        public Action openTransferClaw() {
            return new TransferClaw.OpenTransferClaw();
        }
    }
    public class Pivot {
        private Servo pivot;

        public Pivot(HardwareMap hardwareMap) {
            pivot = hardwareMap.get(Servo.class, "intakePivotServo");
        }

        public class PivotN implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                pivot.setPosition(0.46667);
                return false;
            }
        }

        public Action PivotN() {
            return new Pivot.PivotN();
        }
    }
    public class IntakeArm {
        private Servo intakeArm;

        public IntakeArm(HardwareMap hardwareMap) {
            intakeArm = hardwareMap.get(Servo.class, "intakeArmServo");
        }

        public class intakeArmDown implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeArm.setPosition(0.3667);
                return false;
            }
        }

        public Action intakeArmDown() {return new IntakeArm.intakeArmDown();}

        public class intakeArmSt implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeArm.setPosition(0.6667);
                return false;
            }
        }
        public Action intakeArmSt() {return new IntakeArm.intakeArmSt();}
        public class intakeArmUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeArm.setPosition(0.91667);
                return false;
            }
        }
        public Action intakeArmUp() {return new IntakeArm.intakeArmUp();}
        public class intakeArmGrab implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeArm.setPosition(0.37);
                return false;
            }
        }
        public Action intakeArmGrab() {return new IntakeArm.intakeArmGrab();}
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(0));
        Pose2d Pose1 = new Pose2d(0, 0, Math.toRadians(180));

        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Lift lift = new Lift(hardwareMap);
        IntakeSlides IntakeSlides = new IntakeSlides(hardwareMap);
        IntakeClaw intakeClaw = new IntakeClaw(hardwareMap);
        IntakeArm intakeArm = new IntakeArm(hardwareMap);
        FlipServo flipServo = new FlipServo(hardwareMap);
        TransferClaw transferClaw = new TransferClaw(hardwareMap);
        Pivot pivot = new Pivot(hardwareMap);


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .setReversed(true)
                .lineToX(-29.5);
        TrajectoryActionBuilder tab0 = drive.actionBuilder(new Pose2d(-29.5,0,Math.toRadians(0)))
                .setReversed(false)
                .lineToX(-29);
        TrajectoryActionBuilder tab12 = drive.actionBuilder(new Pose2d(-29,0,Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-7.5, -50, Math.toRadians(157)), Math.toRadians(0));
        TrajectoryActionBuilder tab2 = drive.actionBuilder(new Pose2d(-7.5, -50, Math.toRadians(153)))
                .setReversed(false)
                .turn(Math.toRadians(-32)); //4
        TrajectoryActionBuilder tab3 = drive.actionBuilder(new Pose2d(-7.5,-50,Math.toRadians(125)))
                .setReversed(false)
                .turn(Math.toRadians(54));
        TrajectoryActionBuilder tab4 = drive.actionBuilder(new Pose2d(-7.5, -50, Math.toRadians(179)))
                .setReversed(false)
                .turn(Math.toRadians(-54));
        TrajectoryActionBuilder tab5 = drive.actionBuilder(new Pose2d(-7.5, -50, Math.toRadians(125)))
                .setReversed(false)
                .turn(Math.toRadians(67))
                .lineToX(-10);
        TrajectoryActionBuilder tab6 = drive.actionBuilder(new Pose2d(-10, -50, Math.toRadians(193)))
                .setReversed(false)
                .lineToX(-7.5)
                .turn(Math.toRadians(-70));
        TrajectoryActionBuilder tab7 = drive.actionBuilder(new Pose2d(4, 30, Math.toRadians(180)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(-10, -2.5, Math.toRadians(0)),Math.toRadians(0));
        TrajectoryActionBuilder tab8 = drive.actionBuilder(new Pose2d(-10, -2.5, Math.toRadians(0)))
                .setReversed(true)
                .lineToX(-28);
        TrajectoryActionBuilder tab9 = drive.actionBuilder(new Pose2d(-38, -2.5, Math.toRadians(0)))
                .setReversed(false)
                .splineToSplineHeading(new Pose2d(0, 30, Math.toRadians(0)),Math.toRadians(0));

        // actions that need to happen on init; for instance, a claw tightening.
        Actions.runBlocking(claw.closeClaw());
        Actions.runBlocking(flipServo.downFlip());
        Actions.runBlocking(intakeClaw.openIntakeClaw());
        Actions.runBlocking(transferClaw.openTransferClaw());
        Actions.runBlocking(intakeArm.intakeArmUp());
        Actions.runBlocking(pivot.PivotN());


        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;

            Action trajectoryActionChosen;
            Action trajectoryActionChosen0;
            Action trajectoryActionChosen12;
            Action trajectoryActionChosen1;
            Action trajectoryActionChosen3;
            Action trajectoryActionChosen4;
            Action trajectoryActionChosen5;
            Action trajectoryActionChosen6;
            Action trajectoryActionChosen7;
            Action trajectoryActionChosen8;
            Action trajectoryActionChosen9;


            trajectoryActionChosen = tab1.build();
            trajectoryActionChosen0 = tab0.build();
            trajectoryActionChosen12 = tab12.build();
            trajectoryActionChosen1 = tab2.build();
            trajectoryActionChosen3 = tab3.build();
            trajectoryActionChosen4 = tab4.build();
            trajectoryActionChosen5 = tab5.build();
            trajectoryActionChosen6 = tab6.build();
            trajectoryActionChosen7 = tab7.build();
            trajectoryActionChosen8 = tab8.build();
            trajectoryActionChosen9 = tab9.build();


            Actions.runBlocking(
                    new SequentialAction(
                            new ParallelAction(
                                    lift.liftUp(),
                                    trajectoryActionChosen
                            ),
                            lift.liftDown(),
                            new ParallelAction(
                                    claw.openClaw(),
                                    trajectoryActionChosen0
                            ),
                            new ParallelAction(
                                    lift.liftPark(),
                                    trajectoryActionChosen12
                            ),
                            IntakeSlides.SlideExtend(),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(1),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.4),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.5),
                            intakeArm.intakeArmUp(),
                            IntakeSlides.SlidePark(),
                            transferClaw.closeTransferClaw(),
                            trajectoryActionChosen1,
                            intakeClaw.openIntakeClaw(),
                            new SleepAction(0.5),
                            flipServo.upFlip(),
                            new SleepAction(0.1),
                            lift.liftBasket(),
                            new SleepAction(0.3),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.2),
                            flipServo.downFlip(),
                            lift.liftPark(),
                            trajectoryActionChosen3,
                            IntakeSlides.SlideExtend2(),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.8),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.4),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.5),
                            intakeArm.intakeArmUp(),
                            IntakeSlides.SlidePark(),
                            transferClaw.closeTransferClaw(),
                            trajectoryActionChosen4,
                            intakeClaw.openIntakeClaw(),
                            new SleepAction(0.5),
                            flipServo.upFlip(),
                            new SleepAction(0.1),
                            lift.liftBasket(),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.2),
                            flipServo.downFlip(),
                            lift.liftPark(),
                            trajectoryActionChosen5,
                            IntakeSlides.SlideExtend2(),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.8),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.4),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.5),
                            intakeArm.intakeArmUp(),
                            IntakeSlides.SlidePark(),
                            transferClaw.closeTransferClaw(),
                            trajectoryActionChosen6,
                            intakeClaw.openIntakeClaw(),
                            new SleepAction(0.5),
                            flipServo.upFlip(),
                            new SleepAction(0.1),
                            lift.liftBasket(),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.2),
                            flipServo.downFlip(),
                            lift.liftPark()

                    ));
        }
    }
}