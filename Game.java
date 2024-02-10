import static java.lang.System.out;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    final int DEPTH_LIMIT = 3;
    Scanner s = new Scanner(System.in);
    Ai ai = new Ai();
    int whosTurn = 1;
    int totalTurns = 0;
//     char[][] gameBoard = {{' ', ' ', ' '},
                        // {' ', ' ', ' '},
                        // {' ', ' ', ' '}};

    public void gameStart() {
        GameBoard board = new GameBoard();
        GameTreeNode root = new GameTreeNode(board);
        root.expandChildren(DEPTH_LIMIT);
        for (int i = 0; i < root.children.size(); i++) {
            root.children.get(i).expandChildren(DEPTH_LIMIT);
        }
        System.exit(0);

        int eval = ai.evaluateBoard(board);
        out.println("Game board eval: " + eval);
        // System.exit(0);
        int gameType = chooseType();
        board.printBoard();
        while(totalTurns < 9) {
            board.updateBoard(userInput(board));
            totalTurns++;
            board.printBoard();
            board.checkWin();
            board.returnOpenSpaces();
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

    public int[] userInput(GameBoard board) {
        int whosTurn = 0;
        int row = -1;
        int col = -1;
        boolean checkSpot = true;
        int[] coordinates = new int[2];
        // Scanner coordinatesInput = new Scanner(System.in);
        System.out.println("Player " + whosTurn + "'s turn...");
        
        while (checkSpot == true) {
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
            checkSpot = board.checkSpot(coordinates);
            
            if (checkSpot == true) {
                out.println("Spot is full enter a new row and col >:(");
            }
        }
        
        System.out.println(Arrays.toString(coordinates));

        return coordinates;
    }

    public int[] aiInput(GameBoard board) {
        out.println("Player " + whosTurn + "'s turn...");
        int[] turn = ai.takeTurn(board, whosTurn);
        out.println(turn[0] + ", " + turn[1]);
        out.println("Nodes expanded = 1");
        if (whosTurn == 1) {
            whosTurn = 2;
        } else {
            whosTurn = 1;
        }
        return turn;
    }

    // public void printBoard() {
    //     for (int i = 0; i < 3; i++) {
    //         for (int j = 0; j < 3; j++) {
    //             out.print(" " + gameBoard[i][j] + " ");
    //             if (j < 2) {
    //                 out.print('|');
    //             } else {
    //                 out.println();
    //             }
    //         }
    //         if (i < 2) {
    //             out.println("---+---+---");
    //         }
    //     }
    // }

    // public void updateBoard(int[] input) {
    //     int row = input[0];
    //     int col = input[1];
    //     if (whosTurn == 1) {
    //         gameBoard[row][col] = 'x';
    //     } else if (whosTurn == 2){
    //         gameBoard[row][col] = 'o';
    //     }
        
    //     if (whosTurn == 1) {
    //         whosTurn = 2;
    //     } else {
    //         whosTurn = 1;
    //     }
    // }

    // public boolean isFull(int[] input) {
    //     int row = input[0];
    //     int col = input[1];
    //     if (gameBoard[row][col] != ' ') {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    
}
