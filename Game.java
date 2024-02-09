import static java.lang.System.out;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    Scanner s = new Scanner(System.in);
    Ai ai = new Ai();
    int whosTurn = 1;
    int totalTurns = 0;
    char[][] gameBoard = {{' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}};

    public void gameStart() {
        int gameType = chooseType();
        printBoard();
        while(totalTurns < 9) {
            updateBoard(userInput());
            totalTurns++;
            printBoard();
            checkWin();

        }
        
        s.close();
    }

    public int chooseType() {
        // Scanner typeInput = new Scanner(System.in);
        int type = 0;
        out.println("===============================");
        out.println("Select the AIs opponent:\n[1] Human\n[2] AI");

        System.out.println("==> ");

        while (true){
            String opponent = s.nextLine();
            if (opponent.equals("1")){
                System.out.println("Opponent is: Human");
                type = 1;
                break;
            } else if(opponent.equals("2")) {
                
                System.out.println("Opponent is: AI");
                type = 2;
                break;
            } else {
                System.out.println("Please Enter 1 or 2");
            }
        }
        return type;
    }

    public int[] userInput() {
        int whosTurn = 0;
        int row = -1;
        int col = -1;
        boolean isFull = true;
        int[] coordinates = new int[2];
        // Scanner coordinatesInput = new Scanner(System.in);
        System.out.println("Player " + whosTurn + "'s turn...");
        
        while (isFull == true) {
            row = -1;
            col = -1;
            while (row != 0 && row != 1 && row != 2) {
                out.println("Enter row [0 to 2]: ");
                row = s.nextInt();
            }

            while (col != 0 && col != 1 && col != 2) {
                out.println("Enter col [0 to 2]: ");
                col = s.nextInt();
            }

            coordinates[0] = row;
            coordinates[1] = col;
            isFull = isFull(coordinates);
            
            if (isFull == true) {
                out.println("Spot is full enter a new row and col >:(");
            }
        }
        
        System.out.println(Arrays.toString(coordinates));

        return coordinates;
    }

    public int[] aiInput() {
        out.println("Player " + whosTurn + "'s turn...");
        int[] turn = ai.takeTurn(gameBoard, whosTurn);
        out.println(turn[0] + ", " + turn[1]);
        out.println("Nodes expanded = 1");
        if (whosTurn == 1) {
            whosTurn = 2;
        } else {
            whosTurn = 1;
        }
        return turn;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                out.print(" " + gameBoard[i][j] + " ");
                if (j < 2) {
                    out.print('|');
                } else {
                    out.println();
                }
            }
            if (i < 2) {
                out.println("---+---+---");
            }
        }
    }

    public void updateBoard(int[] input) {
        int row = input[0];
        int col = input[1];
        if (whosTurn == 1) {
            gameBoard[row][col] = 'x';
        } else if (whosTurn == 2){
            gameBoard[row][col] = 'o';
        }
        
        if (whosTurn == 1) {
            whosTurn = 2;
        } else {
            whosTurn = 1;
        }
    }

    public boolean isFull(int[] input) {
        int row = input[0];
        int col = input[1];
        if (gameBoard[row][col] != ' ') {
            return true;
        } else {
            return false;
        }
    }

    public void checkWin() {
        // 3 rows 3 cols 2 diagonals
        // rows: {00, 01, 02}, {10, 11, 12}, {20, 21, 22}
        // cols: {00, 10, 20}, {01, 11, 21}, {02, 12, 22}
        // diagonals: {00, 11, 22}, {02, 11, 20}
        
        // check rows for a win
        int xTot = 0;
        int oTot = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char curChar = gameBoard[row][col];
                if (curChar == 'x') {
                    xTot++;
                } else if (curChar == 'o') {
                    oTot++;
                }
            }
            checkXO(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }

        
        // check columns for a win
        xTot = 0;
        oTot = 0;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                char curChar = gameBoard[row][col];
                if (curChar == 'x') {
                    xTot++;
                } else if (curChar == 'o') {
                    oTot++;
                }
            }
            checkXO(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }


        //check diagonals for a win
        xTot = 0;
        oTot = 0;
        for (int i = 0; i < 3; i++) {
            char curChar = gameBoard[i][i];
            if (curChar == 'x') {
                xTot++;
            } else if (curChar == 'o') {
                oTot++;
            }
            checkXO(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }

        for (int i = 0; i < 3; i++) {
            int j = 2 - i;
            char curChar = gameBoard[i][j];
            if (curChar == 'x') {
                xTot++;
            } else if (curChar == 'o') {
                oTot++;
            }
            checkXO(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }
    }

    public void checkXO(int xTot, int oTot) {
        if (xTot == 3) {
            out.println("X WINS");
            System.exit(0);
        } else if (oTot == 3) {
            out.println("O WINS");
            System.exit(0);
        } else {
            xTot = 0;
            oTot = 0;
        }
    }
}
