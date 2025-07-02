package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Pivot;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class PivotMovementInstruction extends MovementInstruction{

    private Pivot pivotUnit;
    private double power;
    private double time;
    ElapsedTime elapsedTime = new ElapsedTime();
    double previousTime = 0;
    public PivotMovementInstruction (Robot robot, double power, double time) {
        super();
        this.pivotUnit = robot.pivot;
        this.power = power;
        this.time = time;
    }

    @Override
    public void executeMovement() {
        double currentTime = elapsedTime.milliseconds();

        if (currentTime - previousTime < time){
            pivotUnit.AutoPivotMove(power);
        }

        previousTime = currentTime;
    }

    @Override
    public boolean isComplete() {
        double currentTime = elapsedTime.milliseconds();
        return currentTime - previousTime >= time;
    }

}
