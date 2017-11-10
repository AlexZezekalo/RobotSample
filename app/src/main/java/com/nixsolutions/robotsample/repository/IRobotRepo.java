package com.nixsolutions.robotsample.repository;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nixsolutions.robotsample.model.WrapRobot;

import java.util.List;

public interface IRobotRepo {

    @Nullable
    WrapRobot getRobotById(String uid);

    @NonNull
    List<WrapRobot> getAllRobots();

    void addNewRobot(@NonNull WrapRobot robot);
}
