package lab7.compulsory;

public class Main {
    public static void main(String args[]) {
        var explore = new Exploration();
        // setting the shared memory value
        explore.setMem();
        // setting the number of cells we want
        ExplorationMap.setNumberOfCells();
        explore.addRobot(new Robot("Wall-E", 6, 7, explore));
        explore.addRobot(new Robot("R2D2", 1, 2, explore));
        explore.addRobot(new Robot("Optimus Prime", 4, 5, explore));
        explore.start();
    }
}