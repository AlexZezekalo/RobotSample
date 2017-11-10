package com.nixsolutions.robotsample.repository;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nixsolutions.robotsample.exception.RobotNotFoundException;
import com.nixsolutions.robotsample.model.WrappedRobot;

import java.util.List;

public interface IRobotRepository {

    @Nullable
    WrappedRobot getRobotById(String uid) throws RobotNotFoundException;

    @NonNull
    List<WrappedRobot> getAllRobots();

    void saveNewRobot(@NonNull WrappedRobot robot);
}
