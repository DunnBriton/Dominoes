import java.util.ArrayList;

public class Board {

    static ArrayList<Tiles> board = new ArrayList<>();

    public static void playDomino(int selectedDomino, String side,
                                  ArrayList<Tiles> hand){
        if(side.equals("l")){
            board.add(0, hand.get(selectedDomino));
        }
        else{
            board.add(hand.get(selectedDomino));
        }
        hand.remove(selectedDomino);
    }

    public static boolean validMoveCheck(int selectedDomino,String side,
                                         ArrayList<Tiles> hand){

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

    public static void outputBoard(){
        StringBuilder oneHold = new StringBuilder();
        StringBuilder twoHold = new StringBuilder();
        int i;

        twoHold.append("   ");
        for(i=0;i<board.size();i++){
            if(i % 2 == 0){
                oneHold.append(board.get(i).toString());
            }
            else{
                twoHold.append(board.get(i).toString());
            }
        }
        MainGameLoop.lineOne = oneHold.toString();
        MainGameLoop.lineTwo = twoHold.toString();
    }

}