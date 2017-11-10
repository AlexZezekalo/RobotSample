package com.nixsolutions.robotsample.facade;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.nixsolutions.robotsample.exception.EmptyCommandListException;
import com.nixsolutions.robotsample.exception.NoRobotExistsException;
import com.nixsolutions.robotsample.exception.NoRobotFoundException;
import com.nixsolutions.robotsample.factory.RobotFactory;
import com.nixsolutions.robotsample.interaction.Beep;
import com.nixsolutions.robotsample.interaction.Interaction;
import com.nixsolutions.robotsample.interaction.Move;
import com.nixsolutions.robotsample.interaction.Turn;
import com.nixsolutions.robotsample.model.WrapRobot;
import com.nixsolutions.robotsample.repository.IHistoryRepo;
import com.nixsolutions.robotsample.repository.IRobotRepo;

import java.util.List;


public class HumanOperator implements BasicCommand, HistoryCommand {

    @NonNull
    private IHistoryRepo historyRepo;

    @NonNull
    private IRobotRepo robotRepo;

    @NonNull
    private RobotFactory robotFactory;

    public HumanOperator(@NonNull IHistoryRepo historyRepo, @NonNull IRobotRepo robotRepo,
                         @NonNull RobotFactory robotFactory) {
        this.historyRepo = historyRepo;
        this.robotRepo = robotRepo;
        this.robotFactory = robotFactory;
    }

    @Override
    public void move(String robotId, Double distance) {
        WrapRobot robot = robotRepo.getRobotById(robotId);
        if (robot != null) {
            Interaction move = new Move(distance);
            move.doInteract(robot);

            historyRepo.addInteraction(move);
        }
    }

    @Override
    public void turn(String robotId, Double angle) {
        WrapRobot robot = robotRepo.getRobotById(robotId);
        if (robot !=null) {
            Interaction turn = new Turn(angle);
            turn.doInteract(robot);

            historyRepo.addInteraction(turn);
        }
    }

    @Override
    public void beep(String robotId) {
        WrapRobot robot = robotRepo.getRobotById(robotId);
        if (robot != null) {
            Interaction beep = new Beep();
            beep.doInteract(robot);

            historyRepo.addInteraction(beep);
        }
    }

    @Override
    public String createNewRobot() {
        WrapRobot wrapRobot = robotFactory.createNewRobot();
        robotRepo.addNewRobot(wrapRobot);
        return wrapRobot.getRobotUid();
    }

    @Override
    public void repeatAllCommandsByAllRobots() throws NoRobotExistsException, EmptyCommandListException,
            NoRobotFoundException{
        List<String> robotsIds = Stream.of(robotRepo.getAllRobots()).map(WrapRobot::getRobotUid).toList();
        repeatAllCommands(robotsIds);
    }

    @Override
    public void repeatAllCommands(@NonNull List<String> robotUids) throws NoRobotExistsException,
            EmptyCommandListException, NoRobotFoundException {
        if (robotUids.isEmpty()) {
            throw new NoRobotExistsException();
        } else {
            List<Interaction> allInteractions = historyRepo.getAllInteractions();

            for (String robotUid : robotUids) {
                WrapRobot wrapRobot = robotRepo.getRobotById(robotUid);
                if (wrapRobot != null) {
                    if (!allInteractions.isEmpty()) {
                        for (Interaction allInteraction : allInteractions) {
                            allInteraction.doInteract(wrapRobot);
                        }
                    } else {
                        throw new EmptyCommandListException();
                    }
                } else {
                    throw new NoRobotFoundException();
                }
            }
        }
    }
}
