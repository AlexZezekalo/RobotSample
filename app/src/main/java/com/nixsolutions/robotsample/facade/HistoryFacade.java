package com.nixsolutions.robotsample.facade;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.exception.EmptyCommandListException;
import com.nixsolutions.robotsample.exception.RobotNotExistsException;
import com.nixsolutions.robotsample.exception.RobotNotFoundException;
import com.nixsolutions.robotsample.model.WrappedRobot;

import java.util.List;

public interface HistoryFacade {

    void repeatAllInteractionsForAllRobots() throws EmptyCommandListException,
            RobotNotExistsException, RobotNotFoundException;

    void repeatAllInteractions(@NonNull String... robotUids) throws EmptyCommandListException,
            RobotNotExistsException, RobotNotFoundException;

    void repeatAllInteractions(@NonNull List<WrappedRobot> robots) throws EmptyCommandListException,
            RobotNotExistsException, RobotNotFoundException;
}
