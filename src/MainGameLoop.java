import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainGameLoop extends Application {
    // Human player object.
    static Human humanPlayer;
    // Computer player object.
    static Computer computerPlayer;
    // Booleans used in various checks.
    static boolean hasPlayed, humanUnableToPlay, computerUnableToPlay;

    @Override
    public void start(Stage primaryStage) {
        Boneyard.main();
        humanPlayer = new Human();
        computerPlayer = new Computer();
        //Board.outputBoard();

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
                Tiles holder = Boneyard.drawDomino(humanPlayer.humanHand.hand);
                Display.gameInfo.setText("Boneyard contains "+Boneyard.boneyard.size()+" dominoes.\n" +
                        "Computer has "+MainGameLoop.computerPlayer.computerHand.hand.size()+" dominoes.");
                Display.playerItems.getChildren().add(0,holder.dominoShape);
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

    /**
     * computerTurn is where the computer attempts to play.
     * Only plays first found move.
     * Return void.
     */
    public static void computerTurn(){
        computerUnableToPlay = false;
        hasPlayed = false;

        // Path for if the computer still has dominoes in hand.
        if(computerPlayer.computerHand.hand.size() > 0){
            // Checks hand for possible moves and plays first find.
            for(int j=0;j<computerPlayer.computerHand.hand.size();j++){
                if(!hasPlayed && Board.checkPlay(computerPlayer
                        .computerHand.hand.get(j))){
                    Board.playDomino(computerPlayer.computerHand.hand
                            .get(j), computerPlayer.computerHand.hand);
                    computerEndItems();
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
                Display.gameInfo.setText("""
                        Human Player Wins.
                        Game Over!
                        Please exit and restart.""");
                disableButtons();
            }
            else if(hand == computerPlayer.computerHand.hand){
                Display.gameInfo.setText("""
                        Computer Player Wins.
                        Game Over!
                        Please exit and restart.""");
                disableButtons();
            }
        }
        // Checks if both players could not play in a row, causing unplayable.
        if(humanUnableToPlay && computerUnableToPlay){
            Display.gameInfo.setText("""
                    Neither player can play.
                    Game Over!
                    Please exit and restart.""");
            disableButtons();
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
        Display.gameInfo.setText("Boneyard contains "+Boneyard
                .boneyard.size()+" dominoes.\n" +
                "Computer has "+MainGameLoop.computerPlayer
                .computerHand.hand.size()+" dominoes.");
        humanTurn();
        // Covers for continuation after recursion.
        hasPlayed = true;
    }

    /**
     * disableButtons disables and hides the two buttons when needed.
     * Return void.
     */
    public static void disableButtons(){
        Display.drawButton.setDisable(true);
        Display.drawButton.setVisible(false);
        Display.noPlayButton.setDisable(true);
        Display.noPlayButton.setVisible(false);
    }
}