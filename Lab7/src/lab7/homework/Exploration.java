package lab7.compulsory;
import javax.swing.*;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Exploration {
    private SharedMemory mem;
    private ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();
    private int sharedMemory;
    private TimeKeeperThread time = new TimeKeeperThread(10);
    public Exploration() {

    }

    public void start() {
        Thread threadTime = new Thread(time);
        threadTime.start();
        for (int i = 0; i < robots.size(); i++) {
            Thread threadRobot = new Thread(robots.get(i));
            time.addRobotAsThread(robots.get(i));
            threadRobot.start();
        }
    }

    public void addRobot (Robot robot) {
        robots.add(robot);
        map = new ExplorationMap(robot);
        // marking the initial location of the robot as a visited cell
        map.visit(robot.getCurrentX(), robot.getCurrentY(), robot);
    }

    public void setMem() {
        JSpinner spinner = RobotManager.getMemoryAndCellsSpinner();
        sharedMemory = (Integer) spinner.getValue();
        mem = new SharedMemory(sharedMemory);
    }

    public SharedMemory getMem() {
        return mem;
    }

    public int getMemNumber() {
        return sharedMemory;
    }

    public ExplorationMap getMap() {
        return map;
    }
}
