package org.firstinspires.ftc.teamcode;

public class Variables {

    //Arm Servo angles for TeleOP and Autonomous
    public static final double armServoAngleUp = 20; //289;
    public static final double x = 275;
    public static final double armServoAngleDown = armServoAngleUp + 162; //182; //x - 147;
    public static final double armServoAngleGrab = armServoAngleUp + 182; //198; //x - 163;
    public static final double armServoAngleStraight = armServoAngleUp + 58; //78; //x - 58;


    //Intake claw angles for TeleOP and Autonomous
    public static final double intakeClawServoAngleClosed = 270;
    public static final double intakeClawServoAngleOpened = intakeClawServoAngleClosed - 85;


    //Transfer claw angles for TeleOP and Autonomous
    public static final double transferClawServoAngleClosed = 95;
    public static final double transferClawServoAngleOpened = transferClawServoAngleClosed - 60;


    //Outtake (flip) servo angles for TeleOp and Autonomous
    public static final double flipServoAngleDown = 249;
    public static final double flipServoAngleUp = flipServoAngleDown - 144;
    public static final double flipServoAngleUpTeleOp = flipServoAngleDown - 150;
    public static final double flipServoAngleBasket = flipServoAngleDown - 174;


    //Pivot servo angles for TeleOP and Autonomous
    public static final double pivotVerticalAngle = 67;
    public static final double pivotHorizontalAngle = pivotVerticalAngle + 90; //3
    public static final double pivot1yellowAngle = pivotVerticalAngle + 14;
    public static final double pivot3yellowAngle = pivotVerticalAngle - 40;
    public static final double pivot1ColourAngle = pivotVerticalAngle + 26;




    //Specimen Claw angles for TeleOP and Autonomous
    public static final double specimenClawAngleClosed = 293;
    public static final double specimenClawAngleOpened = specimenClawAngleClosed - 73;


    //Intake Slides extension positions for TeleOP and Autonomous
    public static final int intakeSlideExtendPos = 1300;
    public static final int intakeSlideExtendPos2 = 1350;
    public static final int intakeSlideExtendPosSmall = 750;
    public static final int intakeSlideExtendPosMedium = 950;


    //Lift Slides positions for TeleOP and Autonomous
    public static final int liftUpPos = 1550;
    public static final int liftBasketPos = 1650;
    public static final int liftDownPos = 1050;
    public static final int liftSpecimenPos = 175;

    //Specimen arm angle positions for TeleOP and Autonomous

    public static final double specimenArmAngleGrabPos = 0.85;
    public static final double specimenArmAngleHangPos = 0.29;

    //Specimen pivot  positions for TeleOP and Autonomous
    public static final double pivotNormalPos = 0.77;
    public static final double pivotInvertedPos = 0.08;

    //Specimen claw  positions for TeleOP and Autonomous
    public static final double specimenArmClawOpened = 0.35;
    public static final double specimenArmClawClosed = 0.16;

    //Auto positions

    public static final double specimenAdjustable = 0;



}