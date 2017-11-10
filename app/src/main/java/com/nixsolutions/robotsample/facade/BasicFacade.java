package com.nixsolutions.robotsample.facade;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.exception.RobotNotFoundException;

public interface BasicFacade {

    void move(@NonNull String robotId, double distance) throws RobotNotFoundException;

    void turn(@NonNull String robotId, double angle) throws RobotNotFoundException;

    void beep(@NonNull String robotId) throws RobotNotFoundException;

    @NonNull
    String createNewRobot();
}
