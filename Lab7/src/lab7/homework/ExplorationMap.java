package lab7.compulsory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExplorationMap {
    private static Cell[][] matrix;
    private Robot robot;
    private static int numberOfCells;
    private static int[][] matrixRepresentation;
    public static Object Lock = new Object();
    public ExplorationMap(Robot robot) {
        this.robot = robot;
    }

    public boolean visit(int row, int col, Robot robot) {
       synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> tokens = new ArrayList<>();
                tokens = (List<Token>) this.robot.getExplore().getMem().extractTokens(numberOfCells);
                robot.increasedTerritory();
                matrix[row][col].visit(tokens);
                matrixRepresentation[row][col] = robot.getRobotNumber();
                synchronized (Lock) {
                    System.out.println("Cell of row: " + row + " and column: " + col + " was visited successfully by robot " + robot.getName() + ".");
                    seeMatrixShortcut();
                }
                return true;
            }
        }
        return false;
    }

    public static void setNumberOfCells() {
        JSpinner spinner = RobotManager.getMemoryAndCellsSpinner();
        numberOfCells = (Integer) spinner.getValue();
        matrix = new Cell[numberOfCells + 1][numberOfCells + 1];
        for (int i = 0; i < numberOfCells; i++) {
            for (int j = 0; j < numberOfCells; j++) {
                matrix[i][j] = new Cell(i, j);
            }
        }
        matrixRepresentation = new int[numberOfCells + 1][numberOfCells + 1];
    }

    public static void seeMatrixShortcut() {
        System.out.println("Matrix representation: ");
        for (int i = 0; i < numberOfCells; i++) {
            for (int j = 0; j < numberOfCells; j++) {
                System.out.print(matrixRepresentation[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int getNumberOfCells() {
        return numberOfCells;
    }

    public Cell getCell(int x, int y) {
        return matrix[x][y];
    }
}
