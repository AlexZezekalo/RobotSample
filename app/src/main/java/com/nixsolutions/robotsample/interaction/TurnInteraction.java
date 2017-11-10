package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrappedRobot;


public class TurnInteraction extends BaseInteraction {

    @NonNull
    private double angle;

    public TurnInteraction(@NonNull double angle) {
        this.angle = angle;
    }

    @Override
    public void doInteract(@NonNull WrappedRobot robot) {
        super.doInteract(robot);
        robot.getRobot().turn(angle);
    }
}
