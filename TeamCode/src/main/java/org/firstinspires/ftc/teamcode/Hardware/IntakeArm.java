package org.firstinspires.ftc.teamcode.Hardware;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.autoyellow;
import org.firstinspires.ftc.teamcode.variables;

public class IntakeArm {
    private Servo intakeArm;

    public IntakeArm(HardwareMap hardwareMap) {
        intakeArm = hardwareMap.get(Servo.class, "intakeArmServo");
    }

    public class intakeArmDown implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeArm.setPosition(variables.armServoAngleDown/300);
            return false;
        }
    }

    public Action intakeArmDown() {return new IntakeArm.intakeArmDown();}

    public class intakeArmSt implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeArm.setPosition(variables.armServoAngleStraight/300);
            return false;
        }
    }
    public Action intakeArmSt() {return new IntakeArm.intakeArmSt();}
    public class intakeArmUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeArm.setPosition(variables.armServoAngleUp/300);
            return false;
        }
    }
    public Action intakeArmUp() {return new IntakeArm.intakeArmUp();}
    public class intakeArmGrab implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeArm.setPosition(variables.armServoAngleGrab/300);
            return false;
        }
    }
    public Action intakeArmGrab() {return new IntakeArm.intakeArmGrab();}
}
