package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrapRobot;


public class Beep extends BaseInteraction {

    @Override
    public void doInteract(@NonNull WrapRobot robot) {
        super.doInteract(robot);
        robot.getRobot().beep();
    }
}
