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

    void clawControl (boolean YButton, boolean BButton, boolean DPADLeft, boolean DPADRight, boolean DPADUp) {
        if (YButton) {clawServo.setPosition(OPENEDSTATE);}
        if (BButton) {clawServo.setPosition(CLOSEDSTATE);}
        if (!YButton && !BButton) {
            if (DPADLeft) {clawServo.setPosition(OPENEDSTATE);}
            if (DPADRight) {clawServo.setPosition(CLOSEDSTATE);}
            if (DPADUp) {clawServo.setPosition(OPENEDSTATE);}
        }
    }

    public void setState (boolean state){
        if (state) { //True close
            clawServo.setPosition(CLOSEDSTATE);
        } else {
            clawServo.setPosition(OPENEDSTATE);
        }
    }
}
