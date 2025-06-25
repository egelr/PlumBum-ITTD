package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class SpecimenArmAngle{
    private Servo specimenArmAngleServo;

    public SpecimenArmAngle(HardwareMap hardwareMap) {
        specimenArmAngleServo = hardwareMap.get(Servo.class, "specimenArmAngleServo");
    }

    public class grabSpecimen implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenArmAngleServo.setPosition(Variables.specimenArmAngleGrabPos);
            return false;
        }
    }
    public Action grabSpecimen() {
        return new SpecimenArmAngle.grabSpecimen();
    }
    public class hangSpecimen implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenArmAngleServo.setPosition(Variables.specimenArmAngleHangPos);
            return false;
        }
    }
    public Action hangSpecimen() {
        return new SpecimenArmAngle.hangSpecimen();
    }

}