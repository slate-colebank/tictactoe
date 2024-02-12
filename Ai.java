public class Ai {
    public int[] takeTurn(GameBoard gameBoard) {
        GameTreeNode root = new GameTreeNode(gameBoard);

        root.expandNode(0);
        // gameBoard.openSpacesLeft--;

        for (int i = 0; i < root.children.size(); i++) {
            System.out.println("child " + i + ": " + root.children.get(i).miniMaxValue);
            if (root.children.get(i).miniMaxValue == root.miniMaxValue) {
                // return root.children.get(i).latestMove;
                return root.children.get(i).gameBoard.latestMove;
            }
        }
        System.out.println("root: " + root.miniMaxValue);
        System.out.println("Error: ai could not chose move");
        return null;
    }
}