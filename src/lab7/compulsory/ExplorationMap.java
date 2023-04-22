package lab7.compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExplorationMap {
    private static Cell[][] matrix;
    private Robot robot;
    private static int numberOfCells;
    private static int[][] matrixRepresentation;
    private static Object matrixLock = new Object();
    public ExplorationMap(Robot robot) {
        this.robot = robot;
    }

    public boolean visit(int row, int col, Robot robot) {
       synchronized (matrix[row][col]) {
            if (!matrix[row][col].isVisited()) {
                List<Token> tokens = new ArrayList<>();
                tokens = (List<Token>) this.robot.getExplore().getMem().extractTokens(numberOfCells);
                matrix[row][col].visit(tokens);
                matrixRepresentation[row][col] = robot.getRobotNumber();
                synchronized (matrixLock) {
                    System.out.println("Cell of row: " + row + " and column: " + col + " was visited successfully by robot " + robot.getName() + ".");
                    seeMatrixShortcut();
                }
                return true;
            }
        }
        return false;
    }

    public static void setNumberOfCells() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number for number of cells: ");
        numberOfCells = scanner.nextInt();
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
