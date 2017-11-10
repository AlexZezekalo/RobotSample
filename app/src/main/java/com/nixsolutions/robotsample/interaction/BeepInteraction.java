package com.nixsolutions.robotsample.interaction;

import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.model.WrappedRobot;


public class BeepInteraction extends BaseInteraction {

    @Override
    public void doInteract(@NonNull WrappedRobot robot) {
        super.doInteract(robot);
        robot.getRobot().beep();
    }
}
