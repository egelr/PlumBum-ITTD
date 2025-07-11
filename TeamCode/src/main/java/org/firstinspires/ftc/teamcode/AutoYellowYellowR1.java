package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.config.Config;
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

import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.FlipServo;
import org.firstinspires.ftc.teamcode.hardware.IntakeArm;
import org.firstinspires.ftc.teamcode.hardware.IntakeClaw;
import org.firstinspires.ftc.teamcode.hardware.IntakeSlides;
import org.firstinspires.ftc.teamcode.hardware.Lift;
import org.firstinspires.ftc.teamcode.hardware.Pivot;
import org.firstinspires.ftc.teamcode.hardware.TransferClaw;
import org.firstinspires.ftc.teamcode.hardware.SpecimenArmAngle;
import org.firstinspires.ftc.teamcode.hardware.SpecimenClaw;
import org.firstinspires.ftc.teamcode.hardware.SpecimenPivot;

//Autonomous for delivering 4 samples (67 points)

@Config
@Autonomous(name = "YellowYellowR1", group = "Autonomous")
public class AutoYellowYellowR1 extends LinearOpMode {

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(2, -23.6, Math.toRadians(180));
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
        SpecimenClaw specimenClaw = new SpecimenClaw(hardwareMap);
        SpecimenPivot specimenPivot = new SpecimenPivot(hardwareMap);
        SpecimenArmAngle specimenArmAngle = new SpecimenArmAngle(hardwareMap);


        TrajectoryActionBuilder initialSampleScoreTrajectory = drive.actionBuilder(initialPose)
                .setReversed(true)
                .strafeTo(new Vector2d(-8, -48))
                .turn(Math.toRadians(-45));
        //.splineToSplineHeading(new Pose2d(-8,-48,Math.toRadians(-40)), Math.toRadians(0));
        TrajectoryActionBuilder firstSampleGrabTrajectory = drive.actionBuilder(new Pose2d(-8,-48,Math.toRadians(135)))
                .setReversed(false)
                .turn(Math.toRadians(28));
        TrajectoryActionBuilder firstSampleScoreTrajectory = drive.actionBuilder(new Pose2d(-8, -48, Math.toRadians(163)))
                .setReversed(false)
                .turn(Math.toRadians(-28)); //4
        TrajectoryActionBuilder secondSampleGrabTrajectory = drive.actionBuilder(new Pose2d(-8, -48,Math.toRadians(135)))
                .setReversed(false)
                .turn(Math.toRadians(49.5));
        TrajectoryActionBuilder secondSampleScoreTrajectory = drive.actionBuilder(new Pose2d(-8, -48, Math.toRadians(184.5)))
                .setReversed(false)
                .turn(Math.toRadians(-49.5));
        TrajectoryActionBuilder thirdSampleGrabTrajectory = drive.actionBuilder(new Pose2d(-8, -44, Math.toRadians(135)))
                .setReversed(false)
                .turn(Math.toRadians(76))
                .lineToX(-10);
        TrajectoryActionBuilder thirdSampleScoreTrajectory = drive.actionBuilder(new Pose2d(-10, -48, Math.toRadians(211)))
                .setReversed(false)
                .lineToX(-8)
                .turn(Math.toRadians(-76));
        TrajectoryActionBuilder firstParkingTrajectory = drive.actionBuilder(new Pose2d(-8, -48, Math.toRadians(135)))
                .setReversed(false)
                .strafeTo(new Vector2d(-51, -40))
                .turn(Math.toRadians(138))
                .strafeTo(new Vector2d(-51, -17));
        //                .splineToSplineHeading(new Pose2d(-51, -40, Math.toRadians(278)), Math.toRadians(0))

        //Actions that need to happen on init

        Action initialSampleScoreTrajectoryAction;
        Action firstSampleGrabTrajectoryAction;
        Action firstSampleScoreTrajectoryAction;
        Action secondSampleGrabTrajectoryAction;
        Action secondSampleScoreTrajectoryAction;
        Action thirdSampleGrabTrajectoryAction;
        Action thirdSampleScoreTrajectoryAction;
        Action firstParkingTrajectoryAction;

        Actions.runBlocking(claw.closeClaw());
        Actions.runBlocking(flipServo.downFlip());
        Actions.runBlocking(intakeClaw.openIntakeClaw());
        Actions.runBlocking(transferClaw.closeTransferClaw());
        Actions.runBlocking(intakeArm.intakeArmUp());
        Actions.runBlocking(pivot.PivotN());
        Actions.runBlocking(IntakeSlides.SlidePark());
        Actions.runBlocking(lift.liftPark());
        Actions.runBlocking(specimenClaw.closeSpecimenClaw());
        Actions.runBlocking(specimenPivot.normal());
        Actions.runBlocking(specimenArmAngle.grabSpecimen());

        initialSampleScoreTrajectoryAction = initialSampleScoreTrajectory.build();
        firstSampleGrabTrajectoryAction = firstSampleGrabTrajectory.build();
        firstSampleScoreTrajectoryAction = firstSampleScoreTrajectory.build();
        secondSampleGrabTrajectoryAction = secondSampleGrabTrajectory.build();
        secondSampleScoreTrajectoryAction = secondSampleScoreTrajectory.build();
        thirdSampleGrabTrajectoryAction = thirdSampleGrabTrajectory.build();
        thirdSampleScoreTrajectoryAction = thirdSampleScoreTrajectory.build();
        firstParkingTrajectoryAction = firstParkingTrajectory.build();

        while (!isStopRequested() && !opModeIsActive()) {

            waitForStart();

            if (isStopRequested()) return;


            Actions.runBlocking(
                    new SequentialAction(

                            //Actions for delivering first sample

                            initialSampleScoreTrajectoryAction,
                            new ParallelAction(
                                    flipServo.upFlip(),
                                    lift.liftBasket()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(0.4),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.1),

                            //Actions for delivering second sample

                            new ParallelAction(
                                    flipServo.downFlip(),
                                    lift.liftPark(),
                                    firstSampleGrabTrajectoryAction,
                                    IntakeSlides.SlideExtend2(),
                                    intakeArm.intakeArmDown(),
                                    pivot.Pivot1yellow()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.25),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.3),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.4),
                            new ParallelAction(
                                    pivot.PivotN(),
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),
                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    firstSampleScoreTrajectoryAction,
                                    intakeClaw.openIntakeClaw()
                            ),
                            new ParallelAction(
                                    flipServo.upFlip(),
                                    lift.liftBasket()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(0.4),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.1),

                            //Actions for delivering third sample

                            new ParallelAction(
                                    flipServo.downFlip(),
                                    lift.liftPark(),
                                    secondSampleGrabTrajectoryAction,
                                    IntakeSlides.SlideExtend(),
                                    intakeArm.intakeArmDown()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.25),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.3),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.4),
                            new ParallelAction(
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),
                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    secondSampleScoreTrajectoryAction,
                                    intakeClaw.openIntakeClaw()),
                            new ParallelAction(
                                    flipServo.upFlip(),
                                    lift.liftBasket()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(0.4),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.1),

                            //Actions for delivering fourth sample

                            new ParallelAction(
                                    flipServo.downFlip(),
                                    lift.liftPark(),
                                    thirdSampleGrabTrajectoryAction,
                                    IntakeSlides.SlideExtend2(),
                                    intakeArm.intakeArmDown(),
                                    pivot.Pivot3yellow()
                            ),
                            intakeArm.intakeArmGrab(),
                            new SleepAction(0.25),
                            intakeClaw.closeIntakeClaw(),
                            new SleepAction(0.3),
                            intakeArm.intakeArmSt(),
                            new SleepAction(0.4),
                            new ParallelAction(
                                    pivot.PivotN(),
                                    intakeArm.intakeArmUp(),
                                    IntakeSlides.SlidePark()
                            ),
                            new ParallelAction(
                                    transferClaw.closeTransferClaw(),
                                    thirdSampleScoreTrajectoryAction,
                                    intakeClaw.openIntakeClaw()),
                            new ParallelAction(
                                    flipServo.upFlip(),
                                    lift.liftBasket()
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(0.4),
                            transferClaw.openTransferClaw(),
                            new SleepAction(0.1),
                            new ParallelAction(
                                    flipServo.upFlip(),
                                    lift.liftPark(),
                                    firstParkingTrajectoryAction
                            ),
                            flipServo.basketFlip(),
                            new SleepAction(0.5)
                    ));
        }
    }
}
