package lab7.compulsory;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class Robot implements Runnable {
    private Queue<Coordinates> coordinates;
    private String name;
    private boolean running = true;
    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private int currentX, currentY;
    private static int robotCount = 0;
    private int robotNumber;
    private Exploration explore = new Exploration();
    public Robot(String name, int currentX, int currentY, Exploration explore) {
        this.name = name;
        this.currentX = currentX;
        this.currentY = currentY;
        this.explore = explore;
        robotCount++;
        this.robotNumber = robotCount;
        coordinates = new LinkedList<>();
    }

    public void run() {
        while (running) {
            do {
                //pick a new cell to explore
                for (int i = 0; i < 4; i++) {
                    int nextX = currentX + directions[i][0];
                    int nextY = currentY + directions[i][1];
                    // checking if nextX and nextY are valid coordinates for moving
                    if (checkTheCell(nextX, nextY)) {
                        explore.getMap().visit(nextX, nextY, this);
                        coordinates.offer(new Coordinates(nextX, nextY));
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                if (coordinates.isEmpty()) {
                    break;
                } else {
                    Coordinates nextCord = coordinates.poll();
                    currentX = nextCord.x;
                    currentY = nextCord.y;
                }
            } while (true);
            running = false;
        }
    }

    public boolean checkTheCell(int nextX, int nextY) {
        //checking the size too
        if (nextX < 0 || nextY < 0) {
            return false;
        }

        if (nextX >= ExplorationMap.getNumberOfCells() || nextY >= ExplorationMap.getNumberOfCells()) {
            return false;
        }

        if (explore.getMap().getCell(nextX, nextY).isVisited()) {
            return false;
        }
        return true;
    }

    public int getRobotNumber() {
        return robotNumber;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Exploration getExplore() {
        return explore;
    }

    public String getName() {
        return name;
    }
}
