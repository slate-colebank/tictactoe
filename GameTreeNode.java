import java.util.ArrayList;

public class GameTreeNode {
    public ArrayList<GameTreeNode> children;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;
    GameBoard gameBoard;

    public GameTreeNode(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.children = new ArrayList<GameTreeNode>();
    }

    public void expandChildren(int depthLimit){
        //Expands game tree to the given depth limit
        /*spectator zone: | | */
        int possibleMoves[][] = gameBoard.returnOpenSpaces();
        int numPosMoves = possibleMoves.length;
       
        for (int i = 0; i < numPosMoves; i++) {
            // create child node with new gb here
            GameBoard childGameBoard = new GameBoard();
            childGameBoard.board = gameBoard.copyGameBoard();
            childGameBoard.currentTurn = gameBoard.currentTurn;
            GameTreeNode child = new GameTreeNode(childGameBoard);
            child.gameBoard.updateBoard(possibleMoves[i]);
            this.children.add(child);
        }
    }

    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        return null;
    }
}
