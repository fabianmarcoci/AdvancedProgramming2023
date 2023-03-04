package lab1.bonus;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        /* Declaring a scanner object */
        Scanner in = new Scanner(System.in);
        /* Reading from the console the number of vertices in Cn */
        System.out.println("Number of vertices in cycle graph: ");
        int n = in.nextInt();

        /* Creating the adjacent matrix
         * We can do this easily only by looking at the next neighbour of each vertex
         * Instead of looking at the previous one too
         * Rows should be equal to columns
         *  */
        int[][][] A = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            A[1][i][i + 1] = 1;
            A[1][i + 1][i] = 1;
        }
        A[1][1][n] = A[1][n][1] = 1;

        for (int t = 2; t <= n; t++) {
            A[t] = multiplyMatrices(A[t - 1], A[1]);
        }
        for (int t = 1; t <= n; t++) {
            System.out.println("A[" + t + "]:");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    System.out.print(A[t][i][j] + " ");
                }
                System.out.println();
            }
        }

        System.out.println("Number of vertices in regular graph: ");
        int m = in.nextInt();
        int[][] B = new int[m+1][m+1];
        for(int i = 1; i < m; i++){
            for (int j = i+1; j <= m; j++)
                B[i][j] = B[j][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }
}