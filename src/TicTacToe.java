import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char playerX = 'X';
        char playerO = 'O';
        char currentPlayer = playerX;
        boolean isGameOver = false;

        char[][] grid = createGrid();
        printGrid(grid);

        while (!isGameOver) {
            makeMove(grid, scanner, currentPlayer);
            printGrid(grid);

            if (currentPlayer == playerX) {
                currentPlayer = playerO;
            } else {
                currentPlayer = playerX;
            }

            isGameOver = isGameOver(grid);
        }
    }

    public static char[][] createGrid() {
        return new char[][] {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };
    }

    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (char[] row : grid) {
            System.out.print("| ");
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void populateGrid(char[][] grid, Scanner scanner) {
        String cells = scanner.nextLine();

        for (int i = 0; i < grid.length; i++) {
            grid[i] = cells.substring(i * grid.length, (i + 1) * grid.length).toCharArray();
        }
    }

    public static boolean isGameOver(char[][] grid) {
        int xCount = 0;
        int oCount = 0;
        boolean xWins = false;
        boolean oWins = false;

        for (char[] row : grid) {
            for (char c : row) {
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            // Check rows
            if (grid[i][0] != ' ' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                if (grid[i][0] == 'X') xWins = true;
                if (grid[i][0] == 'O') oWins = true;
            }

            //Check columns
            if (grid[0][i] != ' ' && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                if (grid[0][i] == 'X') xWins = true;
                if (grid[0][i] == 'O') oWins = true;
            }
        }

        // Diagonal checks
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            if (grid[0][0] == 'X') xWins = true;
            if (grid[0][0] == 'O') oWins = true;
        }
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            if (grid[0][2] == 'X') xWins = true;
            if (grid[0][2] == 'O') oWins = true;
        }

        // Game impossible check if both player win or difference > 1
        if ((xWins && oWins) || Math.abs(xCount - oCount) > 1) {
            System.out.println("Impossible");
            return true;
        }  else if (xWins) {
            System.out.println("X wins");
            return true;
        } else if (oWins) {
            System.out.println("O wins");
            return true;
        } else if (xCount + oCount == 9) {
            System.out.println("Draw");
            return true;
        } else {
            System.out.println("Game not finished");
            return false;
        }
    }

    public static void makeMove(char[][] grid, Scanner scanner, char currentPlayer) {
        System.out.println("Enter the coordinates");

        while (true) {
            try {
                int row = scanner.nextInt() - 1;
                int column = scanner.nextInt() - 1;

                if (grid[row][column] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    grid[row][column] = currentPlayer;
                    break;
                }

            } catch (InputMismatchException inputMismatchException) {
                System.out.println("You should enter numbers!");
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
}