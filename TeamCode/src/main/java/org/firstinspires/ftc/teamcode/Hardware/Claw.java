package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class Claw {
    private Servo claw;

    public Claw(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public class CloseClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(Variables.specimenClawAngleClosed/300);
            return false;
        }
    }

    public Action closeClaw() {
        return new Claw.CloseClaw();
    }

    public class OpenClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(Variables.specimenClawAngleOpened/300);
            return false;
        }
    }

    public Action openClaw() {
        return new Claw.OpenClaw();
    }
}
