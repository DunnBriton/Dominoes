import java.util.ArrayList;

public class Board {
    // Variable used to store domino tiles played.
    static ArrayList<Tiles> board = new ArrayList<>();

    /**
     * validMoveCheck takes selected domino's index, what side of the board,
     * and what hand to check. Used to check if a move is possible.
     * @param selectedDomino - Index of selected domino.
     */
    public static boolean checkPlay(Tiles selectedDomino){
        // Checks if selection is valid move.
        return (board.size() == 0)
                || (selectedDomino.left == board.get(board.size() - 1).right)
                || (selectedDomino.right == board.get(0).left)
                || (selectedDomino.left == board.get(board.size() - 1).left)
                || (selectedDomino.right == board.get(0).right)
                || (selectedDomino.left == 0)
                || (board.get(board.size() - 1).right == 0)
                || (selectedDomino.right == 0)
                || (board.get(0).left == 0);
    }

    /**
     * playDomino takes a domino index, side of the board.
     * It plays the domino and removes from hand.
     * Does not check for valid move as that is checked before calling.
     * @param selectedDomino - The index of selected domino in selected hand.
     * @param hand           - Which hand to act on. (Human or Computer).
     */
    public static void playDomino(Tiles selectedDomino,
                                  ArrayList<Tiles> hand){

        // Plays if the selected domino has a valid play.
        if(board.size() == 0){
            play(selectedDomino, "l", hand);
        }
        else if(selectedDomino.left == board.get(board.size()-1).right){
            play(selectedDomino, "r", hand);
        }
        else if(selectedDomino.right == board.get(0).left){
            play(selectedDomino, "l", hand);
        }
        else if(selectedDomino.left == board.get(0).left){
            int holder = selectedDomino.left;
            selectedDomino.left = selectedDomino.right;
            selectedDomino.right = holder;
            selectedDomino.dominoShape = selectedDomino.drawDomino
                    (selectedDomino.left, selectedDomino.right);
            play(selectedDomino, "l", hand);
        }
        else if(selectedDomino.right == board.get(board.size()-1).right){
            int holder = selectedDomino.left;
            selectedDomino.left = selectedDomino.right;
            selectedDomino.right = holder;
            selectedDomino.dominoShape = selectedDomino.drawDomino
                    (selectedDomino.left, selectedDomino.right);
            play(selectedDomino, "r", hand);
        }
        else if((selectedDomino.left == 0) || (board
                .get(board.size()-1).right == 0)){
            play(selectedDomino, "r", hand);
        }
        else if((selectedDomino.right == 0) || (board
                .get(0).left == 0)){
            play(selectedDomino, "l", hand);
        }
    }

    /**
     * play is used play the selected domino.
     * @param selectedDomino - The domino selected by user.
     * @param side           - The side of the board to be played on.
     * @param hand           - Which hand is being acted upon.
     * Return void.
     */
    public static void play(Tiles selectedDomino, String side,
                            ArrayList<Tiles> hand){
        if(side.equals("l")){
            board.add(0, selectedDomino);
            Display.gamePane.getChildren().add(0,selectedDomino.dominoShape);
        }
        else{
            board.add(selectedDomino);
            Display.gamePane.getChildren().add(selectedDomino.dominoShape);
        }
        hand.remove(selectedDomino);
        Display.playerItems.getChildren().remove(selectedDomino.dominoShape);
    }
}