import static java.lang.System.out;

public class GameBoard {
    char[][] board =   {{' ', ' ', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', ' '}};
    int openSpacesLeft = 9;
    int currentTurn = 1;
    int score = 0;
    int[] latestMove;
    
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
        newGameBoard.currentTurn = this.currentTurn;
        newGameBoard.openSpacesLeft = this.openSpacesLeft;
        //newGameBoard.latestMove = this.latestMove;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                newGameBoard.board[row][col] = this.board[row][col];
            }
        }
        return newGameBoard.board;
    }

    public void updateBoard(int[] input) {
        changeTurn();
        this.latestMove = input;
        int row = input[0];
        int col = input[1];
        if (this.currentTurn == 1) {
            this.board[row][col] = 'x';
        } else if (this.currentTurn == 2){
            this.board[row][col] = 'o';
        }
        openSpacesLeft--;
    }

    public void changeTurn() {
        if (this.currentTurn == 1) {
            this.currentTurn = 2;
        } else if (this.currentTurn == 2){
            this.currentTurn = 1;
        }
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
        int [][] openSpaces = new int[this.openSpacesLeft][2];
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

    public int evaluateBoard(GameBoard gameBoard) {
        // check rows
        int score = 0;
        int xTot = 0;
        int oTot = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char curChar = gameBoard.board[row][col];
                if (curChar == 'x') {
                    xTot++;
                } else if (curChar == 'o') {
                    oTot++;
                }
            }
            score += calculateScore(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }

        
        // check columns for a win
        xTot = 0;
        oTot = 0;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                char curChar = gameBoard.board[row][col];
                if (curChar == 'x') {
                    xTot++;
                } else if (curChar == 'o') {
                    oTot++;
                }
            }
            score += calculateScore(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }


        //check diagonals for a win
        xTot = 0;
        oTot = 0;
        for (int i = 0; i < 3; i++) {
            char curChar = gameBoard.board[i][i];
            if (curChar == 'x') {
                xTot++;
            } else if (curChar == 'o') {
                oTot++;
            }
            score += calculateScore(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }

        for (int i = 0; i < 3; i++) {
            int j = 2 - i;
            char curChar = gameBoard.board[i][j];
            if (curChar == 'x') {
                xTot++;
            } else if (curChar == 'o') {
                oTot++;
            }
            score += calculateScore(xTot, oTot);
            xTot = 0;
            oTot = 0;
        }
        return score;
    }

    public int calculateScore(int xTot, int oTot) {
        int score = 0;
        if (xTot == 2) {
            score += 3;
        } else if (xTot == 1) {
            score += 1;
        }
        if (oTot == 2) {
            score -= 3;
        } else if (oTot == 1) {
            score -= 1;
        }
        return score;
    }
    
    public int evaluateBoardNEW() {
        int score = 0;
        // evaluate rows
        int rowX = 0;
        int rowO = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // logic for checking one row
                rowX = 0;
                rowO = 0;
                if (this.board[row][col] == 'x') {
                    rowX++;
                }
                if (this.board[row][col] == 'o') {
                    rowO++;
                }
            }
            score += heuristic(rowX, rowO);
        }

        // evaluate columns
        int colX = 0;
        int colO = 0;
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                // logic for checking one column
                rowX = 0;
                rowO = 0;
                if (this.board[row][col] == 'x') {
                    rowX++;
                }
                if (this.board[row][col] == 'o') {
                    rowO++;
                }
            }
            score += heuristic(colX, colO);
        }

        // evaluate left to right diagonal
        int lrdiagX = 0;
        int lrdiagO = 0;
        for (int rowCol = 0; rowCol < 3; rowCol++) {
            rowX = 0;
            rowO = 0;
            if (this.board[rowCol][rowCol] == 'x') {
                lrdiagX++;
            }
            if (this.board[rowCol][rowCol] == 'o') {
                lrdiagO++;
            }
            score += heuristic(lrdiagX, lrdiagO);
        }

        // evaluate right to left diagonal
        int rldiagX = 0;
        int rldiagO = 0;
        for (int row = 0; row < 3; row++) {
            rowX = 0;
            rowO = 0;
            if (this.board[row][2-row] == 'x') {
                rldiagX++;
            }
            if (this.board[row][2-row] == 'o') {
                rldiagO++;
            }
            score += heuristic(rldiagX, rldiagO);
        }

        return score;
    }

    public int heuristic(int numX, int numO) {
        if (numX == 3)
            return 999;
        if (numO == 3)
            return -999;
        if (numX == 2 && numO == 0)
            return 3;
        if (numX == 0 && numO == 2)
            return -3;
        if (numX == 1 && numO == 0)
            return 1;
        if (numX == 0 && numO == 1)
            return -1;
        return 0;
    }
}