package com.nixsolutions.robotsample.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nixsolutions.robotsample.exception.RobotNotFoundException;
import com.nixsolutions.robotsample.model.WrappedRobot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MockRobotRepository implements IRobotRepository {

    private Map<String, WrappedRobot> robotMaps = new HashMap<>();

    @Nullable
    @Override
    public WrappedRobot getRobotById(String uid) throws RobotNotFoundException {
        WrappedRobot wrappedRobot = robotMaps.get(uid);
        if (wrappedRobot == null) {
            throw new RobotNotFoundException();
        }
        return wrappedRobot;
    }

    @NonNull
    @Override
    public List<WrappedRobot> getAllRobots() {
        return new ArrayList<>(robotMaps.values());
    }

    @Override
    public void saveNewRobot(@NonNull WrappedRobot robot) {
        robotMaps.put(robot.getRobotUid(), robot);
    }
}
