import java.util.ArrayList;

public class Board {
    // Variable used to store domino tiles played.
    static ArrayList<Tiles> board = new ArrayList<>();

    /**
     * playDomino takes a domino index, side of the board, and which
     * side of the domino to use. It plays the domino and removes from hand.
     * Does not check for valid move as that is checked before calling.
     * @param selectedDomino - The index of selected domino in selected hand.
     * @param side           - Which side of the board to play on. (l or r).
     * @param hand           - Which hand to act on. (Human or Computer).
     */
    public static void playDomino(int selectedDomino, String side,
                                  ArrayList<Tiles> hand){
        // Plays domino on left or right side of board and removes from hand.
        if(side.equals("l")){
            board.add(0, hand.get(selectedDomino));
        }
        else{
            board.add(hand.get(selectedDomino));
        }
        hand.remove(selectedDomino);
    }

    /**
     * validMoveCheck takes selected domino's index, what side of the board,
     * and what hand to check. Used to check if a desired move is possible.
     * @param selectedDomino - Index of selected domino.
     * @param side           - String for side to check. (l or r).
     * @param hand           - What hand to act on. (Human or Computer).
     * @return               - Returns boolean if move can be done or not.
     */
    public static boolean validMoveCheck(int selectedDomino,String side,
                                         ArrayList<Tiles> hand){
        // Returns valid move if board is empty or if a value on
        // domino lines up on desired side of the board.
        if(board.size() == 0){
            return true;
        }
        if((side.equals("l") && (hand.get(selectedDomino).right == board
                .get(0).left || hand.get(selectedDomino).right == 0
                || board.get(0).left == 0))){
            return true;
        }
        return side.equals("r") && (hand.get(selectedDomino).left == board
                .get(board.size()-1).right || hand.get(selectedDomino).left
                == 0 || board.get(board.size()-1).right == 0);

    }

    /**
     * outputBoard is used to output the current board to console.
     * Note: Currently shifts dominoes so top and bottom may switch.
     * Return void.
     */
    public static void outputBoard(){
        // String builders for top and bottom line outputs of board.
        StringBuilder oneHold = new StringBuilder();
        StringBuilder twoHold = new StringBuilder();

        // Adds needed offset.
        twoHold.append("   ");
        // Loops over the current board and adds domino tiles to builders.
        for(int i=0;i<board.size();i++){
            if(i % 2 == 0){
                oneHold.append(board.get(i).toString());
            }
            else{
                twoHold.append(board.get(i).toString());
            }
        }
        // Assigns value in MainGameLoop to call from.
        MainGameLoop.lineOne = oneHold.toString();
        MainGameLoop.lineTwo = twoHold.toString();
    }
}