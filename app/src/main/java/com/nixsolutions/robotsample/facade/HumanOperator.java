package com.nixsolutions.robotsample.facade;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.nixsolutions.robotsample.exception.EmptyCommandListException;
import com.nixsolutions.robotsample.exception.RobotNotExistsException;
import com.nixsolutions.robotsample.exception.RobotNotFoundException;
import com.nixsolutions.robotsample.factory.RobotFactory;
import com.nixsolutions.robotsample.interaction.BeepInteraction;
import com.nixsolutions.robotsample.interaction.Interaction;
import com.nixsolutions.robotsample.interaction.MoveInteraction;
import com.nixsolutions.robotsample.interaction.TurnInteraction;
import com.nixsolutions.robotsample.model.WrappedRobot;
import com.nixsolutions.robotsample.repository.IHistoryRepository;
import com.nixsolutions.robotsample.repository.IRobotRepository;

import java.util.List;


public class HumanOperator implements BasicFacade, HistoryFacade {

    @NonNull
    private final IHistoryRepository historyRepository;

    @NonNull
    private final IRobotRepository robotRepository;

    @NonNull
    private final RobotFactory robotFactory;

    public HumanOperator(@NonNull IHistoryRepository historyRepository, @NonNull IRobotRepository robotRepo,
                         @NonNull RobotFactory robotFactory) {
        this.historyRepository = historyRepository;
        this.robotRepository = robotRepo;
        this.robotFactory = robotFactory;
    }

    @Override
    public void move(@NonNull String robotId, double distance) throws RobotNotFoundException {
        WrappedRobot robot = robotRepository.getRobotById(robotId);
        if (robot != null) {
            Interaction move = new MoveInteraction(distance);
            move.doInteract(robot);

            historyRepository.addInteraction(move);
        }
    }

    @Override
    public void turn(@NonNull String robotId, double angle) throws RobotNotFoundException {
        WrappedRobot robot = robotRepository.getRobotById(robotId);
        if (robot !=null) {
            Interaction turn = new TurnInteraction(angle);
            turn.doInteract(robot);

            historyRepository.addInteraction(turn);
        }
    }

    @Override
    public void beep(@NonNull String robotId) throws RobotNotFoundException {
        WrappedRobot robot = robotRepository.getRobotById(robotId);
        if (robot != null) {
            Interaction beep = new BeepInteraction();
            beep.doInteract(robot);

            historyRepository.addInteraction(beep);
        }
    }

    @NonNull
    @Override
    public String createNewRobot() {
        WrappedRobot wrappedRobot = robotFactory.createNewRobot();
        robotRepository.saveNewRobot(wrappedRobot);
        return wrappedRobot.getRobotUid();
    }

    @Override
    public void repeatAllInteractionsForAllRobots() throws RobotNotExistsException, EmptyCommandListException,
            RobotNotFoundException{
        List<String> robotsIds = Stream.of(robotRepository.getAllRobots()).map(WrappedRobot::getRobotUid).toList();
        repeatAll(robotsIds);
    }

    @Override
    public void repeatAllInteractions(@NonNull String... robotUids) throws EmptyCommandListException,
            RobotNotExistsException, RobotNotFoundException {
        List<String> robotsIds = Stream.of(robotUids).toList();
        repeatAll(robotsIds);
    }

    @Override
    public void repeatAllInteractions(@NonNull List<WrappedRobot> robots) throws EmptyCommandListException,
            RobotNotExistsException, RobotNotFoundException {
        if (robots.isEmpty()) {
            throw new RobotNotExistsException();
        } else {
            List<Interaction> allInteractions = historyRepository.getAllInteractions();

            if (allInteractions.isEmpty()) {
                throw new EmptyCommandListException();
            } else {
                for (WrappedRobot wrappedRobot : robots) {
                    for (Interaction allInteraction : allInteractions) {
                        allInteraction.doInteract(wrappedRobot);
                    }
                }
            }
        }

    }

    private void repeatAll(@NonNull List<String> robotUids) throws RobotNotExistsException,
            EmptyCommandListException, RobotNotFoundException {
        if (robotUids.isEmpty()) {
            throw new RobotNotExistsException();
        } else {
            List<Interaction> allInteractions = historyRepository.getAllInteractions();

            if (allInteractions.isEmpty()) {
                throw new EmptyCommandListException();
            } else {
                for (String robotUid : robotUids) {
                    WrappedRobot wrappedRobot = robotRepository.getRobotById(robotUid);
                    for (Interaction allInteraction : allInteractions) {
                        allInteraction.doInteract(wrappedRobot);
                    }
                }
            }
        }
    }

}
