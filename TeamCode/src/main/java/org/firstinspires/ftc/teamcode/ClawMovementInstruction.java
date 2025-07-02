package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawMovementInstruction extends MovementInstruction{
    final double COMPLETIONTIME = 500;
    private Claw clawUnit;
    private boolean state;
    ElapsedTime elapsedTime = new ElapsedTime();
    double previousTime = 0;
    public ClawMovementInstruction(Robot robot, boolean state) {
        super();
        this.clawUnit = robot.claw;
        this.state = state; //True is closed, False open
    }

    @Override
    public void executeMovement() {
        double currentTime = elapsedTime.milliseconds();

        clawUnit.setState(state);

        previousTime = currentTime;
    }

    @Override
    public boolean isComplete() {
        double currentTime = elapsedTime.milliseconds();
        return currentTime - previousTime >= COMPLETIONTIME;
    }

}
