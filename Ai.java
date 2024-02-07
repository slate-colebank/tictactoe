public class Ai {
    public int[] takeTurn(char[][] gameBoard, int whosTurn) {
        int[] coordinates = {0,0};
        // coordinates = chooseMove(gameBoard);
        return coordinates;

    }

    public int[] chooseMove(char[][] gameBoard) {
        int[] move = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 0; j++) {
                if (gameBoard[i][j] == ' ') {
                    move[0] = i;
                    move[1] = j;
                    return move;
                }
            }
        }
        System.out.println("Board is full");
        return move;
    }
}