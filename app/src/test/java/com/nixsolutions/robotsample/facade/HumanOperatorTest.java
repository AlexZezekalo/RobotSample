package com.nixsolutions.robotsample.facade;


import com.nixsolutions.robotsample.factory.RobotFactory;
import com.nixsolutions.robotsample.interaction.BeepInteraction;
import com.nixsolutions.robotsample.interaction.MoveInteraction;
import com.nixsolutions.robotsample.interaction.TurnInteraction;
import com.nixsolutions.robotsample.model.WrappedRobot;
import com.nixsolutions.robotsample.repository.IHistoryRepository;
import com.nixsolutions.robotsample.repository.IRobotRepository;
import com.nixsolutions.robotsample.repository.MockHistoryRepository;
import com.nixsolutions.robotsample.repository.MockRobotRepository;
import com.nixsolutions.robotsdk.IRobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HumanOperatorTest {

    private static final String MOCK_ROBOT_ID_1 = UUID.randomUUID().toString();
    private static final String MOCK_ROBOT_ID_2 = UUID.randomUUID().toString();
    private static final String MOCK_ROBOT_ID_3 = UUID.randomUUID().toString();

    private IRobotRepository robotRepository = spy(MockRobotRepository.class);
    private IHistoryRepository historyRepository = spy(MockHistoryRepository.class);

    @Mock
    private RobotFactory robotFactory;

    @Mock
    private IRobot mockRobot1;

    @Mock
    private IRobot mockRobot2;

    @Mock
    private IRobot mockRobot3;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private HumanOperator humanOperator;

    @Before
    public void setUp() {
        robotRepository.saveNewRobot(new WrappedRobot(MOCK_ROBOT_ID_1, mockRobot1));
        robotRepository.saveNewRobot(new WrappedRobot(MOCK_ROBOT_ID_2, mockRobot2));
        robotRepository.saveNewRobot(new WrappedRobot(MOCK_ROBOT_ID_3, mockRobot3));

        when(robotFactory.createNewRobot()).thenReturn(new WrappedRobot(MOCK_ROBOT_ID_1, mockRobot1));

        humanOperator = new HumanOperator(historyRepository, robotRepository, robotFactory);
    }

    @Test
    public void createNewRobot() throws Exception {
        String robotUid  = humanOperator.createNewRobot();
        verify(robotFactory).createNewRobot();
        assertNotNull(robotUid);
    }

    @Test
    public void testMove() throws Exception {
        humanOperator.move(MOCK_ROBOT_ID_1, 200d);
        verify(robotRepository).getRobotById(MOCK_ROBOT_ID_1);
        verify(historyRepository).addInteraction(any(MoveInteraction.class));
        verify(mockRobot1).move(200d);
    }

    @Test
    public void testTurn() throws Exception {
        humanOperator.turn(MOCK_ROBOT_ID_3, 90d);
        verify(robotRepository).getRobotById(MOCK_ROBOT_ID_3);
        verify(historyRepository).addInteraction(any(TurnInteraction.class));
        verify(mockRobot3).turn(90d);
    }

    @Test
    public void testBeep() throws Exception {
        humanOperator.beep(MOCK_ROBOT_ID_2);
        verify(robotRepository).getRobotById(MOCK_ROBOT_ID_2);
        verify(historyRepository).addInteraction(any(BeepInteraction.class));
        verify(mockRobot2).beep();
    }

    @Test
    public void testRepeatAllInteractions() throws Exception {
        humanOperator.beep(MOCK_ROBOT_ID_2);
        humanOperator.move(MOCK_ROBOT_ID_1, 70d);
        humanOperator.turn(MOCK_ROBOT_ID_3, 45d);

        humanOperator.repeatAllInteractionsForAllRobots();

        verify(mockRobot1).beep();
        verify(mockRobot2, times(2)).beep();
        verify(mockRobot3).beep();

        verify(mockRobot1, times(2)).move(70d);
        verify(mockRobot2).move(70d);
        verify(mockRobot3).move(70d);

        verify(mockRobot1).turn(45d);
        verify(mockRobot2).turn(45d);
        verify(mockRobot3, times(2)).turn(45d);
    }

    @Test
    public void testRepeatAllInteractionsForAllRobots() throws Exception {
        humanOperator.beep(MOCK_ROBOT_ID_2);
        humanOperator.move(MOCK_ROBOT_ID_2, 70d);
        humanOperator.turn(MOCK_ROBOT_ID_2, 45d);

        humanOperator.repeatAllInteractions(MOCK_ROBOT_ID_1, MOCK_ROBOT_ID_3);

        verify(mockRobot1).turn(45d);
        verify(mockRobot1).move(70d);
        verify(mockRobot1).beep();

        verify(mockRobot3).turn(45d);
        verify(mockRobot3).move(70d);
        verify(mockRobot3).beep();
    }
}

