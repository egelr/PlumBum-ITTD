package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.reflection.ReflectionConfig;
import com.acmerobotics.roadrunner.MotorFeedforward;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.AngularRampLogger;
import com.acmerobotics.roadrunner.ftc.DeadWheelDirectionDebugger;
import com.acmerobotics.roadrunner.ftc.DriveType;
import com.acmerobotics.roadrunner.ftc.DriveView;
import com.acmerobotics.roadrunner.ftc.DriveViewFactory;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.EncoderGroup;
import com.acmerobotics.roadrunner.ftc.EncoderRef;
import com.acmerobotics.roadrunner.ftc.ForwardPushTest;
import com.acmerobotics.roadrunner.ftc.ForwardRampLogger;
import com.acmerobotics.roadrunner.ftc.LateralPushTest;
import com.acmerobotics.roadrunner.ftc.LateralRampLogger;
import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.acmerobotics.roadrunner.ftc.LynxQuadratureEncoderGroup;
import com.acmerobotics.roadrunner.ftc.ManualFeedforwardTuner;
import com.acmerobotics.roadrunner.ftc.MecanumMotorDirectionDebugger;
import com.acmerobotics.roadrunner.ftc.OTOSAngularScalarTuner;
import com.acmerobotics.roadrunner.ftc.OTOSEncoderGroup;
import com.acmerobotics.roadrunner.ftc.OTOSHeadingOffsetTuner;
import com.acmerobotics.roadrunner.ftc.OTOSIMU;
import com.acmerobotics.roadrunner.ftc.OTOSLinearScalarTuner;
import com.acmerobotics.roadrunner.ftc.OTOSPositionOffsetTuner;
import com.acmerobotics.roadrunner.ftc.PinpointEncoderGroup;
import com.acmerobotics.roadrunner.ftc.PinpointIMU;
import com.acmerobotics.roadrunner.ftc.PinpointView;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.OTOSLocalizer;
import org.firstinspires.ftc.teamcode.PinpointLocalizer;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.ThreeDeadWheelLocalizer;
import org.firstinspires.ftc.teamcode.TwoDeadWheelLocalizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TuningOpModes {
    public static final Class<?> DRIVE_CLASS = MecanumDrive.class;

    public static final String GROUP = "quickstart";
    public static final boolean DISABLED = false;

    private TuningOpModes() {}

    private static OpModeMeta metaForClass(Class<? extends OpMode> cls) {
        return new OpModeMeta.Builder()
                .setName(cls.getSimpleName())
                .setGroup(GROUP)
                .setFlavor(OpModeMeta.Flavor.TELEOP)
                .build();
    }

    private static PinpointView makePinpointView(PinpointLocalizer pl) {
        return new PinpointView() {
            GoBildaPinpointDriver.EncoderDirection parDirection = pl.initialParDirection;
            GoBildaPinpointDriver.EncoderDirection perpDirection = pl.initialPerpDirection;

            @Override
            public void update() {
                pl.driver.update();
            }

            @Override
            public int getParEncoderPosition() {
                return pl.driver.getEncoderX();
            }

            @Override
            public int getPerpEncoderPosition() {
                return pl.driver.getEncoderY();
            }

            @Override
            public float getHeadingVelocity() {
                return (float) pl.driver.getHeadingVelocity();
            }

            @Override
            public void setParDirection(@NonNull DcMotorSimple.Direction direction) {
                parDirection = direction == DcMotorSimple.Direction.FORWARD ?
                        GoBildaPinpointDriver.EncoderDirection.FORWARD :
                        GoBildaPinpointDriver.EncoderDirection.REVERSED;
                pl.driver.setEncoderDirections(parDirection, perpDirection);
            }

            @Override
            public void setPerpDirection(@NonNull DcMotorSimple.Direction direction) {
                perpDirection = direction == DcMotorSimple.Direction.FORWARD ?
                        GoBildaPinpointDriver.EncoderDirection.FORWARD :
                        GoBildaPinpointDriver.EncoderDirection.REVERSED;
                pl.driver.setEncoderDirections(parDirection, perpDirection);
            }
        };
    }

    @OpModeRegistrar
    public static void register(OpModeManager manager) {
        if (DISABLED) return;

        DriveViewFactory dvf;
        if (DRIVE_CLASS.equals(MecanumDrive.class)) {
            dvf = hardwareMap -> {
                MecanumDrive md = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
                LazyImu lazyImu = md.lazyImu;

                List<EncoderGroup> encoderGroups = new ArrayList<>();
                List<EncoderRef> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<EncoderRef> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                if (md.localizer instanceof MecanumDrive.DriveLocalizer) {
                    MecanumDrive.DriveLocalizer dl = (MecanumDrive.DriveLocalizer) md.localizer;
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(dl.leftFront, dl.leftBack, dl.rightFront, dl.rightBack)
                    ));
                    leftEncs.add(new EncoderRef(0, 0));
                    leftEncs.add(new EncoderRef(0, 1));
                    rightEncs.add(new EncoderRef(0, 2));
                    rightEncs.add(new EncoderRef(0, 3));
                } else if (md.localizer instanceof ThreeDeadWheelLocalizer) {
                    ThreeDeadWheelLocalizer dl = (ThreeDeadWheelLocalizer) md.localizer;
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(dl.par0, dl.par1, dl.perp)
                    ));
                    parEncs.add(new EncoderRef(0, 0));
                    parEncs.add(new EncoderRef(0, 1));
                    perpEncs.add(new EncoderRef(0, 2));
                } else if (md.localizer instanceof TwoDeadWheelLocalizer) {
                    TwoDeadWheelLocalizer dl = (TwoDeadWheelLocalizer) md.localizer;
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(dl.par, dl.perp)
                    ));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                } else if (md.localizer instanceof OTOSLocalizer) {
                    OTOSLocalizer ol = (OTOSLocalizer) md.localizer;
                    encoderGroups.add(new OTOSEncoderGroup(ol.otos));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                    lazyImu = new OTOSIMU(ol.otos);

                    manager.register(metaForClass(OTOSAngularScalarTuner.class), new OTOSAngularScalarTuner(ol.otos));
                    manager.register(metaForClass(OTOSLinearScalarTuner.class), new OTOSLinearScalarTuner(ol.otos));
                    manager.register(metaForClass(OTOSHeadingOffsetTuner.class), new OTOSHeadingOffsetTuner(ol.otos));
                    manager.register(metaForClass(OTOSPositionOffsetTuner.class), new OTOSPositionOffsetTuner(ol.otos));
                }  else if (md.localizer instanceof PinpointLocalizer) {
                    PinpointView pv = makePinpointView((PinpointLocalizer) md.localizer);
                    encoderGroups.add(new PinpointEncoderGroup(pv));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                    lazyImu = new PinpointIMU(pv);
                } else {
                    throw new RuntimeException("unknown localizer: " + md.localizer.getClass().getName());
                }

                return new DriveView(
                    DriveType.MECANUM,
                        MecanumDrive.PARAMS.inPerTick,
                        MecanumDrive.PARAMS.maxWheelVel,
                        MecanumDrive.PARAMS.minProfileAccel,
                        MecanumDrive.PARAMS.maxProfileAccel,
                        encoderGroups,
                        Arrays.asList(
                                md.leftFront,
                                md.leftBack
                        ),
                        Arrays.asList(
                                md.rightFront,
                                md.rightBack
                        ),
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        lazyImu,
                        md.voltageSensor,
                        () -> new MotorFeedforward(MecanumDrive.PARAMS.kS,
                                MecanumDrive.PARAMS.kV / MecanumDrive.PARAMS.inPerTick,
                                MecanumDrive.PARAMS.kA / MecanumDrive.PARAMS.inPerTick),
                        0
                );
            };
        } else if (DRIVE_CLASS.equals(TankDrive.class)) {
            dvf = hardwareMap -> {
                TankDrive td = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));
                LazyImu lazyImu = td.lazyImu;

                List<EncoderGroup> encoderGroups = new ArrayList<>();
                List<EncoderRef> leftEncs = new ArrayList<>(), rightEncs = new ArrayList<>();
                List<EncoderRef> parEncs = new ArrayList<>(), perpEncs = new ArrayList<>();
                if (td.localizer instanceof TankDrive.DriveLocalizer) {
                    TankDrive.DriveLocalizer dl = (TankDrive.DriveLocalizer) td.localizer;
                    List<Encoder> allEncoders = new ArrayList<>();
                    allEncoders.addAll(dl.leftEncs);
                    allEncoders.addAll(dl.rightEncs);
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            allEncoders
                    ));
                    for (int i = 0; i < dl.leftEncs.size(); i++) {
                        leftEncs.add(new EncoderRef(0, i));
                    }
                    for (int i = 0; i < dl.rightEncs.size(); i++) {
                        rightEncs.add(new EncoderRef(0, dl.leftEncs.size() + i));
                    }
                } else if (td.localizer instanceof ThreeDeadWheelLocalizer) {
                    ThreeDeadWheelLocalizer dl = (ThreeDeadWheelLocalizer) td.localizer;
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(dl.par0, dl.par1, dl.perp)
                    ));
                    parEncs.add(new EncoderRef(0, 0));
                    parEncs.add(new EncoderRef(0, 1));
                    perpEncs.add(new EncoderRef(0, 2));
                } else if (td.localizer instanceof TwoDeadWheelLocalizer) {
                    TwoDeadWheelLocalizer dl = (TwoDeadWheelLocalizer) td.localizer;
                    encoderGroups.add(new LynxQuadratureEncoderGroup(
                            hardwareMap.getAll(LynxModule.class),
                            Arrays.asList(dl.par, dl.perp)
                    ));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                }  else if (td.localizer instanceof PinpointLocalizer) {
                    PinpointView pv = makePinpointView((PinpointLocalizer) td.localizer);
                    encoderGroups.add(new PinpointEncoderGroup(pv));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                    lazyImu = new PinpointIMU(pv);
                } else if (td.localizer instanceof OTOSLocalizer) {
                    OTOSLocalizer ol = (OTOSLocalizer) td.localizer;
                    encoderGroups.add(new OTOSEncoderGroup(ol.otos));
                    parEncs.add(new EncoderRef(0, 0));
                    perpEncs.add(new EncoderRef(0, 1));
                    lazyImu = new OTOSIMU(ol.otos);

                    manager.register(metaForClass(OTOSAngularScalarTuner.class), new OTOSAngularScalarTuner(ol.otos));
                    manager.register(metaForClass(OTOSLinearScalarTuner.class), new OTOSLinearScalarTuner(ol.otos));
                    manager.register(metaForClass(OTOSHeadingOffsetTuner.class), new OTOSHeadingOffsetTuner(ol.otos));
                    manager.register(metaForClass(OTOSPositionOffsetTuner.class), new OTOSPositionOffsetTuner(ol.otos));
                } else {
                    throw new RuntimeException("unknown localizer: " + td.localizer.getClass().getName());
                }

                return new DriveView(
                        DriveType.TANK,
                        TankDrive.PARAMS.inPerTick,
                        TankDrive.PARAMS.maxWheelVel,
                        TankDrive.PARAMS.minProfileAccel,
                        TankDrive.PARAMS.maxProfileAccel,
                        encoderGroups,
                        td.leftMotors,
                        td.rightMotors,
                        leftEncs,
                        rightEncs,
                        parEncs,
                        perpEncs,
                        lazyImu,
                        td.voltageSensor,
                        () -> new MotorFeedforward(TankDrive.PARAMS.kS,
                                TankDrive.PARAMS.kV / TankDrive.PARAMS.inPerTick,
                                TankDrive.PARAMS.kA / TankDrive.PARAMS.inPerTick),
                        0
                );
            };
        } else {
            throw new RuntimeException();
        }

        manager.register(metaForClass(AngularRampLogger.class), new AngularRampLogger(dvf));
        manager.register(metaForClass(ForwardPushTest.class), new ForwardPushTest(dvf));
        manager.register(metaForClass(ForwardRampLogger.class), new ForwardRampLogger(dvf));
        manager.register(metaForClass(LateralPushTest.class), new LateralPushTest(dvf));
        manager.register(metaForClass(LateralRampLogger.class), new LateralRampLogger(dvf));
        manager.register(metaForClass(ManualFeedforwardTuner.class), new ManualFeedforwardTuner(dvf));
        manager.register(metaForClass(MecanumMotorDirectionDebugger.class), new MecanumMotorDirectionDebugger(dvf));
        manager.register(metaForClass(DeadWheelDirectionDebugger.class), new DeadWheelDirectionDebugger(dvf));

        manager.register(metaForClass(ManualFeedbackTuner.class), ManualFeedbackTuner.class);
        manager.register(metaForClass(SplineTest.class), SplineTest.class);
        manager.register(metaForClass(LocalizationTest.class), LocalizationTest.class);

        FtcDashboard.getInstance().withConfigRoot(configRoot -> {
            for (Class<?> c : Arrays.asList(
                    AngularRampLogger.class,
                    ForwardRampLogger.class,
                    LateralRampLogger.class,
                    ManualFeedforwardTuner.class,
                    MecanumMotorDirectionDebugger.class,
                    ManualFeedbackTuner.class
            )) {
                configRoot.putVariable(c.getSimpleName(), ReflectionConfig.createVariableFromClass(c));
            }
        });
    }
}
