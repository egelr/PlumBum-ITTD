package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.variables;

public class FlipServo {
    private Servo flipServo;

    public FlipServo(HardwareMap hardwareMap) {
        flipServo = hardwareMap.get(Servo.class, "flipServo");
    }

    public class downFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            flipServo.setPosition(variables.flipServoAngleDown/300);
            return false;
        }
    }
    public Action downFlip() {
        return new FlipServo.downFlip();
    }
    public class upFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            flipServo.setPosition(0.35);
            return false;
        }
    }
    public Action upFlip() {
        return new FlipServo.upFlip();
    }

public class basketFlip implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        flipServo.setPosition(0.25);
        return false;
    }
}
    public Action basketFlip() {
        return new FlipServo.basketFlip();
    }
}

