package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Claw {
    Servo clawServo;
    final int CLOSEDSTATE = 0; //change to appropriate state
    final int OPENEDSTATE = 0; //change to appropriate state

    Claw (HardwareMap map) {
        clawServo = map.get(Servo.class, "clawServo");
    }

    void clawControl (boolean YButton, boolean BButton) {
        clawServo.setPosition(YButton ? OPENEDSTATE : clawServo.getPosition());
        clawServo.setPosition(BButton ? CLOSEDSTATE : clawServo.getPosition());
    }
}
