package lab7.compulsory;

import java.util.ArrayList;
import java.util.List;

public class TimeKeeperThread implements Runnable {
    private int timeLimitInSeconds;
    private long startTime;
    private boolean isRunning;
    private final List<Robot> robots = new ArrayList<>();
    private boolean done = false;
    public TimeKeeperThread(int timeLimitInSeconds) {
        this.timeLimitInSeconds = timeLimitInSeconds;
        this.isRunning = true;
    }

    public void run() {
        startTime = System.currentTimeMillis();
        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            long elapsedTimeInSeconds = (currentTime - startTime) / 1000;
            synchronized (ExplorationMap.Lock) {
                System.out.println("Elapsed Time: " + elapsedTimeInSeconds + " seconds");
                if (elapsedTimeInSeconds >= timeLimitInSeconds) {
                    System.out.println("Time limit exceeded. Stopping all threads.");
                    isRunning = false;
                    for (int i = 0; i < robots.size(); i++) {
                        robots.get(i).pause();
                    }
                    done = true;
                }
            }
            sleep(); // Sleep for 1 second before checking again
            if (done == true) {
                sleep();
                int memory = robots.get(0).getExplore().getMemNumber();
                for (int i = 0; i < robots.size(); i++) {
                    System.out.println(robots.get(i).getName() + " visited " + robots.get(i).getCellsVisited()
                            + " cells and extracted " + robots.get(i).getCellsVisited() * memory);
                }
            }
        }
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addRobotAsThread(Robot robot) {
        robots.add(robot);
    }
}