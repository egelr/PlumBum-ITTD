package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.variables;

public class IntakeClaw {
    private Servo intakeClaw;

    public IntakeClaw(HardwareMap hardwareMap) {
        intakeClaw = hardwareMap.get(Servo.class, "intakeClawServo");
    }

    public class CloseIntakeClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeClaw.setPosition(variables.intakeClawServoAngleClosed/300);
            return false;
        }
    }

    public Action closeIntakeClaw() {return new IntakeClaw.CloseIntakeClaw();}

    public class OpenIntakeClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeClaw.setPosition(variables.intakeClawServoAngleOpened/300);
            return false;
        }
    }
    public Action openIntakeClaw() {
        return new IntakeClaw.OpenIntakeClaw();
    }
}

