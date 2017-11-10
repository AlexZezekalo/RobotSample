package com.nixsolutions.robotsample.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nixsolutions.robotsample.model.WrapRobot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RobotRepo implements IRobotRepo {

    private Map<String, WrapRobot> robotMaps = new HashMap<>();

    @Nullable
    @Override
    public WrapRobot getRobotById(String uid) {
        return robotMaps.get(uid);
    }

    @NonNull
    @Override
    public List<WrapRobot> getAllRobots() {
        return new ArrayList<>(robotMaps.values());
    }

    @Override
    public void addNewRobot(@NonNull WrapRobot robot) {
        robotMaps.put(robot.getRobotUid(), robot);
    }
}
