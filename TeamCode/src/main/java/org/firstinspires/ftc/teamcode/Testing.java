package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

import org.firstinspires.ftc.teamcode.Variables;
@TeleOp(name = "Testing")
public class Testing extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo specimenArmAngleServo = hardwareMap.get(Servo.class, "specimenArmAngleServo");
        Servo specimenPivotServo = hardwareMap.get(Servo.class, "specimenPivotServo");
        Servo specimenGrabServo = hardwareMap.get(Servo.class, "specimenGrabServo");

        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        while (!isStopRequested()) {
            if(gamepad1.options)
            {
                specimenArmAngleServo.setPosition(0.9); //grab from human player
            }
            if(gamepad1.share){
                specimenArmAngleServo.setPosition(0.25); //hang
            }
            if(gamepad1.square){
                specimenPivotServo.setPosition(0.12); //inverted
            }
            if(gamepad1.circle){
                specimenPivotServo.setPosition(0.8); //normal
            }
            if(gamepad1.dpad_up){
                specimenGrabServo.setPosition(0.1); //close
            }
            if(gamepad1.dpad_down){
                specimenGrabServo.setPosition(0.3); //open
            }
            if(gamepad1.dpad_right){
                specimenGrabServo.setPosition(0.1); //close
                sleep(200);
                specimenPivotServo.setPosition(0.12); //inverted
                specimenArmAngleServo.setPosition(0.29); //hang
            }
            if(gamepad1.dpad_left){
                specimenGrabServo.setPosition(0.3); //open
                sleep(200);
                specimenPivotServo.setPosition(0.8); //normal
                specimenArmAngleServo.setPosition(0.85); //grab from human player
            }
        }
    }
}
