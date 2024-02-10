import static java.lang.System.out;

import java.util.Arrays;
public class GameBoard {
    char[][] board =   {{' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}};
    int openSpacesLeft = 9;
    int currentTurn = 1;
    
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                out.print(" " + this.board[i][j] + " ");
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
    
    public char[][] copyGameBoard() {
        GameBoard newGameBoard = new GameBoard();
        // newGameBoard.currentTurn = this.currentTurn;   D:
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                newGameBoard.board[row][col] = this.board[row][col];
            }
        }
        return newGameBoard.board;
    }

    public void updateBoard(int[] input) {
        int row = input[0];
        int col = input[1];
        if (this.currentTurn == 1) {
            this.board[row][col] = 'x';
            this.currentTurn = 2;
        } else if (this.currentTurn == 2){
            this.board[row][col] = 'o';
            this.currentTurn = 1;
        }
        
        openSpacesLeft--;
    }

    public boolean checkSpot(int[] input) {
        int row = input[0];
        int col = input[1];
        if (this.board[row][col] != ' ') {
            return true;
        } else {
            return false;
        }
    }

    public int[][] returnOpenSpaces(){
        int spot = 0;
        int [][] openSpaces = new int[openSpacesLeft][2];
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                int[] input = {row, col};
                if (checkSpot(input) == false) {
                    openSpaces[spot] = input;
                    spot++;
                }
            }
        }
		return openSpaces;
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
                char curChar = this.board[row][col];
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
                char curChar = this.board[row][col];
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
            char curChar = this.board[i][i];
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
            char curChar = this.board[i][j];
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