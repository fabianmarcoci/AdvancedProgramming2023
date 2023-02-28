package lab1.homework;

public class Main {
    public static void main(String[] args){
        /**
         * In Intellij menu I SELECT "Run" > "Edit Configurations"
         * Then I add new configuration using top left corner "+" and select "Application"
         * There I can add my Program arguments
         */
        if(args.length != 1){
            System.out.println("Usage: validate integers");
            System.exit(-1);
        }

        int n = Integer.parseInt(args[0]);

        if(n > 30_000) {

            long StartTime = System.currentTimeMillis();

            /**
             * Considering the fact that every row and every column shouldn't appear more than once
             * And also there cannot be rows that are the same with any column
             * A method I found to create LatinSquares is the following one:
             * I copy the last term of previous row in a variable and start the row with this term,
             * starting from second row
             */

            int[][] LatinSquare = new int[n][n];
            int LastTerm = 1;

            for (int i = 0; i < n; i++) {
                int c = -1;
                for (int j = LastTerm; j <= n; j++) {
                    c++;
                    LatinSquare[i][c] = j;
                }
                for (int j = 1; j < LastTerm; j++) {
                    c++;
                    LatinSquare[i][c] = j;
                }
                LastTerm = LatinSquare[i][c];
            }

            String row, column, TemporaryLine;
            for (int i = 0; i < n; i++) {
                row = "";
                column = "";
                for (int j = 0; j < n; j++) {
                    TemporaryLine = Integer.toString(LatinSquare[i][j]);
                    row = row + TemporaryLine;
                }

                for (int j = 0; j < n; j++) {
                    TemporaryLine = Integer.toString(LatinSquare[j][i]);
                    column = column + TemporaryLine;
                }
                //System.out.println(row);
                //System.out.println(column);
            }

            long EndTime = System.currentTimeMillis();
            long TimePassed = EndTime - StartTime;
            System.out.println("Application running time for n = " + n + " in milliseconds " + TimePassed);

        }else{

            int[][] LatinSquare = new int[n][n];
            int LastTerm = 1;

            for (int i = 0; i < n; i++) {
                int c = -1;
                for (int j = LastTerm; j <= n; j++) {
                    c++;
                    LatinSquare[i][c] = j;
                }
                for (int j = 1; j < LastTerm; j++) {
                    c++;
                    LatinSquare[i][c] = j;
                }
                LastTerm = LatinSquare[i][c];
            }

            String row, column, TemporaryLine;
            for (int i = 0; i < n; i++) {
                row = "";
                column = "";
                for (int j = 0; j < n; j++) {
                    TemporaryLine = Integer.toString(LatinSquare[i][j]);
                    row = row + TemporaryLine;
                }

                for (int j = 0; j < n; j++) {
                    TemporaryLine = Integer.toString(LatinSquare[j][i]);
                    column = column + TemporaryLine;
                }
                System.out.println(row);
                System.out.println(column);
            }
            /**
             * For last point I traveled to homework package with cd in Terminal
             * Then I applied the java Main.java <n value>
             */
        }
    }
}
