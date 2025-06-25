package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class SpecimenClaw {
    private Servo specimenGrabServo;

    public SpecimenClaw(HardwareMap hardwareMap) {
        specimenGrabServo = hardwareMap.get(Servo.class, "specimenGrabServo");
    }

    public class closeSpecimenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenGrabServo.setPosition(Variables.specimenArmClawClosed);
            return false;
        }
    }
    public Action closeSpecimenClaw() {return new SpecimenClaw.closeSpecimenClaw();}

    public class openSpecimenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            specimenGrabServo.setPosition(Variables.specimenArmClawOpened);
            return false;
        }
    }
    public Action openSpecimenClaw() {
        return new SpecimenClaw.openSpecimenClaw();
    }
}
