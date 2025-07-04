package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class TransferClaw {
    private Servo transferClaw;

    public TransferClaw(HardwareMap hardwareMap) {
        transferClaw = hardwareMap.get(Servo.class, "transferClawServo");
    }

    public class CloseTransferClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            transferClaw.setPosition(Variables.transferClawServoAngleClosed/300);
            return false;
        }
    }

    public Action closeTransferClaw() {return new TransferClaw.CloseTransferClaw();}

    public class OpenTransferClaw implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            transferClaw.setPosition(Variables.transferClawServoAngleOpened/300);
            return false;
        }
    }
    public Action openTransferClaw() {
        return new TransferClaw.OpenTransferClaw();
    }
}