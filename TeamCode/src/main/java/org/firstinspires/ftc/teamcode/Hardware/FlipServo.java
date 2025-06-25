package org.firstinspires.ftc.teamcode.hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Variables;

public class FlipServo {
    private Servo flipServo;

    public FlipServo(HardwareMap hardwareMap) {
        flipServo = hardwareMap.get(Servo.class, "flipServo");
    }

    public class downFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            flipServo.setPosition(Variables.flipServoAngleDown/300);
            return false;
        }
    }
    public Action downFlip() {
        return new FlipServo.downFlip();
    }
    public class upFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            flipServo.setPosition(Variables.flipServoAngleUp/300);
            return false;
        }
    }
    public Action upFlip() {
        return new FlipServo.upFlip();
    }

    public class basketFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            flipServo.setPosition(Variables.flipServoAngleBasket/300);
            return false;
        }
    }
    public Action basketFlip() {return new FlipServo.basketFlip();}
}