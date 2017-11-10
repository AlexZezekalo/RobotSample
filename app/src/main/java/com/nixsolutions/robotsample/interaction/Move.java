package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrapRobot;


public class Move extends BaseInteraction {

    @NonNull
    private Double distance;

    public Move(@NonNull Double distance) {
        this.distance = distance;
    }

    @Override
    public void doInteract(@NonNull WrapRobot robot) {
        super.doInteract(robot);
        robot.getRobot().move(distance);
    }
}
