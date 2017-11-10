package com.nixsolutions.robotsample.facade;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.exception.EmptyCommandListException;
import com.nixsolutions.robotsample.exception.NoRobotExistsException;
import com.nixsolutions.robotsample.exception.NoRobotFoundException;

import java.util.List;

public interface HistoryCommand {

    void repeatAllCommandsByAllRobots() throws NoRobotExistsException, EmptyCommandListException,
            NoRobotFoundException;

    void repeatAllCommands(@NonNull List<String> robotUids) throws NoRobotExistsException,
            EmptyCommandListException, NoRobotFoundException;
}
