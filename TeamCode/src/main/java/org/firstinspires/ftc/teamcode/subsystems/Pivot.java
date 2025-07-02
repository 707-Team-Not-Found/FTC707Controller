/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Pivot {

    Servo pivotServo;

    double pivotServoAngle;

    int pivotSpeed = 1;
    ElapsedTime elapsedTime = new ElapsedTime();
    double previousTime = 0;
    final int OUTTAKEPOS = 0; //change to outtake position
    final int INTAKEPOS = 0; //change to intake position
    final int DEFAULTPOS = 0; //change to default position
    Pivot (HardwareMap map) {
        pivotServo = map.get(Servo.class, "pivotServo");
        pivotServoAngle = pivotServo.getPosition();
    }

    void pivotControl (boolean LBumper, boolean RBumper, boolean DPADLeft, boolean DPADRight, boolean DPADUp) {
        int LBumperInt = LBumper ? 1 : 0;
        int RBumperInt = RBumper ? 1 : 0;

        double currentTime = elapsedTime.milliseconds();

        if (!DPADLeft && !DPADRight && !DPADUp) {
            // May need to clamp servo range so no overshooting
            pivotServo.setPosition(pivotServoAngle + pivotSpeed * (currentTime - previousTime) * LBumperInt - pivotSpeed * (currentTime - previousTime) * RBumperInt);
            previousTime = currentTime;
        } else if (DPADLeft){pivotServo.setPosition(INTAKEPOS);}
          else if (DPADRight){pivotServo.setPosition(OUTTAKEPOS);}
          else { pivotServo.setPosition(DEFAULTPOS);}
    }

    void updatePivotServoAngle () {
        pivotServoAngle = pivotServo.getPosition();
    }

    public void AutoPivotMove (double power){
        updatePivotServoAngle();
        pivotServo.setPosition(pivotServoAngle + pivotSpeed);
    }

}
