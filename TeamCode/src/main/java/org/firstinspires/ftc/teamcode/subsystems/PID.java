package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {

    public double KP = 0;
    public double KI = 0;
    public double KD = 0;

    double integralSum = 0;

    ElapsedTime timer = new ElapsedTime();
    double lastError = 0;

    public PID (double KP, double KI, double KD){
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }

    public double update (double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();

        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        return (error * KP) + (derivative * KD) + (integralSum * KI);
    }

}
