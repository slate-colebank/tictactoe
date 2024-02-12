import java.util.ArrayList;

public class GameTreeNode {
    public ArrayList<GameTreeNode> children;
    public int miniMaxValue;
    private boolean isMax = true; // MAX is 0, MIN is 1
    private static final int MAX_DEPTH = 3;
    GameBoard gameBoard;

    public GameTreeNode(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.children = new ArrayList<GameTreeNode>();
    }

    public void expandNode(int depth){
        if (depth > 2) {
            this.miniMaxValue = this.gameBoard.evaluateBoardNEW();
            return;
        }
        //Expands game tree to the given depth limit
        int possibleMoves[][] = gameBoard.returnOpenSpaces();
        int numPosMoves = possibleMoves.length;
       
        for (int i = 0; i < numPosMoves; i++) {
            // create child node with new gb here
            GameBoard childGameBoard = new GameBoard();
            childGameBoard.board = gameBoard.copyGameBoard();
            // childGameBoard.currentTurn = gameBoard.currentTurn;
            GameTreeNode child = new GameTreeNode(childGameBoard);
            child.gameBoard.updateBoard(possibleMoves[i]);

            if (this.isMax) {
                // root is MAX, children will be MIN
                child.isMax = false;
            } else {
                // root is MIN, children will be MAX
                child.isMax = true;
            }

            this.children.add(child);
        }
        // recursive call on the children, pass in the current depth
        // if depth = MAX_DEPTH, end
        this.miniMaxValue = this.gameBoard.evaluateBoardNEW();
        this.gameBoard.printBoard();
        System.out.println("score " + this.miniMaxValue);
        for (int i = 0; i < this.children.size(); i++) {
            // int score = this.children.get(i).expandNode(depth + 1);
            if (isMax) {
                if (this.children.get(i).miniMaxValue > this.miniMaxValue) {
                    this.miniMaxValue = this.children.get(i).miniMaxValue;
                }
            } else {
                if (this.children.get(i).miniMaxValue < this.miniMaxValue) {
                    this.miniMaxValue = this.children.get(i).miniMaxValue;
                }
            }
            
        }
        return;
        // System.out.println("the final running score is " + runningScore);
        // this.miniMaxValue = runningScore;
        // return runningScore;
    }


    // public GameTreeNode runMiniMax(boolean max) {
    //     // get to the node at the bottom of the tree with children that dont have children
    //     // check all minimax values and set the node that that value and delete all children
    //     // move on to new node

    //     // create the children
    //     // evalate the children
        
    //     //Runs MINIMAX on the game tree rooted at this node
    //     //max is true if the MAX result is desired
    //     //max is false if the MIN result is desired
    //     //Returns the child node that the maximizes or minimizes the result
        
    // }
}
