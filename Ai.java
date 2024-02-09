public class Ai {
    public int[] takeTurn(char[][] gameBoard, int whosTurn) {
        int[] coordinates = chooseMove(gameBoard, whosTurn);
        // coordinates = chooseMove(gameBoard);
        return coordinates;

    }

    public int[] chooseMove(char[][] gameBoard, int turn) {
        int[] space = {0,0};
        return space;
    }

    public int evaluateBoard(char[][] gameBoard) {
        // check rows
        int score = 0;
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
            score += calculateScore(xTot, oTot);
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
            score += calculateScore(xTot, oTot);
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
            score += calculateScore(xTot, oTot);
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
        return score;
    }
}