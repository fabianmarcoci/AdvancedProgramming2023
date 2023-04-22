package lab7.compulsory;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Exploration {
    private SharedMemory mem;
    private ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    public Exploration() {

    }

    public void start() {
        for (int i = 0; i < robots.size(); i++) {
            Thread thread = new Thread(robots.get(i));
            thread.start();
        }
    }

    public void addRobot (Robot robot) {
        robots.add(robot);
        map = new ExplorationMap(robot);
        // marking the initial location of the robot as a visited cell
        map.visit(robot.getCurrentX(), robot.getCurrentY(), robot);
    }

    public void setMem() {
        int sharedMemory;
        System.out.print("Input number for shared memory: ");
        sharedMemory = scanner.nextInt();
        mem = new SharedMemory(sharedMemory);
    }

    public SharedMemory getMem() {
        return mem;
    }

    public ExplorationMap getMap() {
        return map;
    }
}
