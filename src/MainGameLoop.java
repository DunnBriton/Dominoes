import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class MainGameLoop extends Application {
    // Human player object.
    static Human humanPlayer;
    // Computer player object.
    static Computer computerPlayer;
    // Booleans used in various checks.
    static boolean hasPlayed, humanUnableToPlay, computerUnableToPlay;
    // Variable for the selected domino.
    static int dominoSelected;

    @Override
    public void start(Stage primaryStage) {
        Boneyard.main();
        humanPlayer = new Human();
        computerPlayer = new Computer();

        Display.main(primaryStage);

        humanTurn();
        //exit();
    }

    /**
     * humanTurn resets needed variables, outputs text for player,
     * and starts the turn.
     * Return void.
     */
    public static void humanTurn(){
        // What to do if draw button is clicked.
        Display.drawButton.setOnAction(event -> {
            if(Boneyard.boneyard.size() > 0){
                // Draw from boneyard, update text, switch turn.
                Boneyard.drawDomino(humanPlayer.humanHand.hand);
                Display.gameInfo.setText("Boneyard contains "+Boneyard.boneyard.size()+" dominoes.\n" +
                        "Computer has "+MainGameLoop.computerPlayer.computerHand.hand.size()+" dominoes.");
                computerTurn();
            }
        });
        // What to do if unable to play button is clicked.
        Display.noPlayButton.setOnAction(event -> {
            humanUnableToPlay = true;
            checkWin(humanPlayer.humanHand.hand);
            computerTurn();
        });

    }

    public static void computerTurn(){

        computerUnableToPlay = false;
        Random random = new Random();

        // Path for if the computer still has dominoes in hand.
        if(computerPlayer.computerHand.hand.size() > 0){
            // Reset unable to play value.
            hasPlayed = false;
            // Checks hand for possible moves and plays first find.
            for(int j=0;j<computerPlayer.computerHand.hand.size();j++){
                // Used as holder for domino side values.
                int x;
                // Gets random int based on hand size.
                if(computerPlayer.computerHand.hand.size() > 1){
                    x = random.nextInt(0, computerPlayer.computerHand
                            .hand.size()-1);
                }
                else{
                    x = random.nextInt(0, computerPlayer.computerHand
                            .hand.size());
                }
                // Plays first domino if board is empty.
                // Human always go first so a redundant check.
                if(Board.board == null || Board.board.size() == 0){
                    Board.playDomino(x, "l", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if left domino on board is a wild.
                else if(Board.board.get(0).left == 0){
                    Board.playDomino(x, "l", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if right domino on board is a wild.
                else if(Board.board.get(Board.board.size()-1).right == 0){
                    Board.playDomino(x, "r", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if left side of played domino is a wild.
                else if(computerPlayer.computerHand.hand.get(j).left == 0){
                    Board.playDomino(computerPlayer.computerHand.hand
                            .get(j).left, "r", computerPlayer
                            .computerHand.hand);
                    computerEndItems();
                    break;
                }
                // Checks if right side of played domino is a wild.
                else if(computerPlayer.computerHand.hand.get(j).right == 0){
                    Board.playDomino(computerPlayer.computerHand.hand
                            .get(j).right, "l", computerPlayer
                            .computerHand.hand);
                    computerEndItems();
                    break;
                }
                // Checks if left domino value is equal to right option.
                else if(computerPlayer.computerHand.hand.get(j).left ==
                        Board.board.get(Board.board.size()-1).right){
                    Board.playDomino(j,"r", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if left domino value is equal to left option.
                else if(computerPlayer.computerHand.hand.get(j).left ==
                        Board.board.get(0).left){
                    int left = computerPlayer.computerHand.hand.get(j)
                            .left;
                    computerPlayer.computerHand.hand.get(j).left =
                            computerPlayer.computerHand.hand.get(j).right;
                    computerPlayer.computerHand.hand.get(j).right = left;
                    Board.playDomino(j, "l", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if right domino value is equal to left option.
                else if(computerPlayer.computerHand.hand.get(j).right ==
                        Board.board.get(0).left){
                    Board.playDomino(j,"l", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
                // Checks if right domino value is equal to right option.
                else if(computerPlayer.computerHand.hand.get(j).right ==
                        Board.board.get(Board.board.size()-1).right){
                    int left = computerPlayer.computerHand.hand.get(j)
                            .left;
                    computerPlayer.computerHand.hand.get(j).left =
                            computerPlayer.computerHand.hand.get(j).right;
                    computerPlayer.computerHand.hand.get(j).right = left;
                    Board.playDomino(j, "r", computerPlayer.computerHand
                            .hand);
                    computerEndItems();
                    break;
                }
            }
            // Draws boneyard if no move was found.
            if(!hasPlayed && Boneyard.boneyard.size() > 0){
                Boneyard.drawDomino(computerPlayer.computerHand.hand);
                computerUnableToPlay = false;
                humanTurn();
            }
            // Unable to play or draw causes unplayable flag.
            else if(!hasPlayed){
                computerUnableToPlay = true;
                checkWin(computerPlayer.computerHand.hand);

                humanTurn();
            }
        }
        // If hand is empty but boneyard is not, draws if possible.
        else if(Boneyard.boneyard.size() > 0){
            Boneyard.drawDomino(computerPlayer.computerHand.hand);
            computerUnableToPlay = false;
            humanTurn();
        }
        // If hand and boneyard is empty, checks for win.
        else{
            computerUnableToPlay = true;
            checkWin(computerPlayer.computerHand.hand);

            humanTurn();
        }
    }

    /**
     * checkWin takes which hand to check and sees if win conditions are met
     * and also check if an unplayable impasse has occurred.
     * @param hand - Which hand to check. Human/Computer.
     */
    public static void checkWin(ArrayList<Tiles> hand){
        // Checks for hand and boneyard to be empty.
        if(hand.size() == 0 && Boneyard.boneyard.size() == 0){
            if(hand == humanPlayer.humanHand.hand){
                Display.gameInfo.setText("Human Player Wins.\nGame Over!\nPlease exit and restart.");
            }
            else if(hand == computerPlayer.computerHand.hand){
                Display.gameInfo.setText("Computer Player Wins.\nGame Over!\nPlease exit and restart.");
            }
        }
        // Checks if both players could not play in a row, causing unplayable.
        if(humanUnableToPlay && computerUnableToPlay){
            Display.gameInfo.setText("Neither player can play.\nGame Over!\nPlease exit and restart.");
        }
    }

    /**
     * computerEndItems preforms actions needed if the computer can play.
     * Created to save space in the computer section of takeTurn();
     * Return void.
     */
    public static void computerEndItems(){
        // Computer can play so no move boolean reset.
        computerUnableToPlay = false;
        // Check if move caused a game ending condition.
        checkWin(computerPlayer.computerHand.hand);
        humanTurn();
        // Covers for continuation after recursion.
        hasPlayed = true;
    }
}