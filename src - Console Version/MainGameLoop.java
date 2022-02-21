import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class MainGameLoop extends Application {
    // Human player object.
    static Human humanPlayer;
    // Computer player object.
    static Computer computerPlayer;
    // General input scanner.
    static Scanner input = new Scanner(System.in);
    // Various strings used to move data around.
    static String holderOne, holderTwo, lineOne, lineTwo;
    // Booleans used in various checks.
    static boolean usersTurn, humanTurnReset, hasPlayed;
    static boolean humanUnableToPlay, computerUnableToPlay;
    // Variable for the selected domino.
    static int dominoSelected;

    @Override
    public void start(Stage primaryStage) {
        //Display.main(primaryStage);

        Boneyard.main();
        humanPlayer = new Human();
        computerPlayer = new Computer();

        updateText();
        humanTurn();
        exit();
    }

    /**
     * humanTurn resets needed variables, outputs text for player,
     * and starts the turn.
     * Return void.
     */
    public static void humanTurn(){
        usersTurn = true;
        humanTurnReset = false;

        System.out.println("Human's Turn\nTray: " +
                            humanPlayer.humanHand.hand.toString());
        restartTurnText();
        humanTurnReset = true;
        takeTurn();
    }

    /**
     * takeTurn handles the gameplay for human and computer. It
     * handles incorrect input and interprets input.
     * Return void.
     */
    public static void takeTurn(){
        // If true then it is the human's turn.
        if (usersTurn){
            String in = input.next();
            switch (in) {
                // If user wants to play.
                case "p" -> {
                    System.out.println("Which domino? (0-" +
                            (humanPlayer.humanHand.hand.size()-1) + ")\n" +
                            "Count left to right starting at zero.\n");

                    // Checks that input is an int.
                    if(input.hasNextInt()){
                        // Saves selection.
                        dominoSelected = input.nextInt();

                        // Checks that input int is valid.
                        if(dominoSelected >= 0 && dominoSelected <=
                                (humanPlayer.humanHand.hand.size()-1)){
                            // Gets what side of the board to play on.
                            System.out.println("Left or Right side of " +
                                               "Board? (l/r) (Lowercase)");
                            holderOne =  input.next();

                            // Checks that input is a valid choice.
                            if(holderOne.equals("l") || holderOne.equals("r")){
                                // Gets if the user wants to rotate domino.
                                System.out.println("Rotate first? (y/n) " +
                                                   "(Lowercase)");
                                holderTwo = input.next();

                                // Checks user input.
                                if(holderTwo.equals("y") || holderTwo
                                        .equals("n")){
                                    // Flips left/right values
                                    if(holderTwo.equals("y")){
                                        int left = humanPlayer.humanHand.hand
                                                .get(dominoSelected).left;
                                        humanPlayer.humanHand.hand.get
                                                (dominoSelected).left =
                                                humanPlayer.humanHand.hand.get
                                                        (dominoSelected).right;
                                        humanPlayer.humanHand.hand.get
                                                (dominoSelected).right = left;
                                    }
                                    // Checks if selected move is possible.
                                    if(Board.validMoveCheck(dominoSelected,
                                            holderOne, humanPlayer.humanHand
                                                    .hand)){
                                        // Saves selection and plays domino.
                                        Tiles saveDomino = humanPlayer
                                                .humanHand.hand.get
                                                        (dominoSelected);
                                        Board.playDomino(dominoSelected,
                                                holderOne, humanPlayer
                                                        .humanHand.hand);
                                        // Resets unable to play check.
                                        humanUnableToPlay = false;
                                        // Checks for game end.
                                        checkWin(humanPlayer.humanHand.hand);

                                        // Outputs needed texts.
                                        System.out.println("\nPlaying ["
                                                +saveDomino.left+" "
                                                +saveDomino.right+"]");
                                        System.out.println("Human has "
                                                +humanPlayer.humanHand
                                                .hand.size()+" dominoes.");
                                        System.out.println("Computer has "
                                                +computerPlayer.computerHand
                                                .hand.size()+" dominoes.");
                                        System.out.println("Boneyard contains "
                                                +Boneyard.boneyard.size()
                                                +" dominoes.\n");
                                        Board.outputBoard();
                                        System.out.println(lineOne+"\n"
                                                +lineTwo);

                                        // Changes turn value, starts turn.
                                        usersTurn = false;
                                        takeTurn();
                                    }
                                    // Invalid input case. Restarts turn.
                                    else{
                                        System.out.println("Incorrect " +
                                                "domino placement counts "+
                                                "as invalid input.");
                                        restartTurnText();
                                        takeTurn();
                                    }
                                }
                                // Invalid input case. Restarts turn.
                                else{
                                    takeTurn();
                                }
                            }
                            // Invalid input case. Restarts turn.
                            else{
                                restartTurnText();
                                input.nextLine();
                                takeTurn();
                            }
                        }
                        // Invalid input case. Restarts turn.
                        else{takeTurn();}
                    }
                    // Invalid input case. Restarts turn.
                    else{
                        takeTurn();
                    }
                }
                // If user wants to draw. Counts as turns move.
                case "d" -> {
                    // Draws from boneyard or restarts turn if not possible.
                    if(Boneyard.boneyard.size() > 0){
                        Tiles dominoDrew = Boneyard.drawDomino(humanPlayer
                                .humanHand.hand);
                        System.out.println("You drew ["+dominoDrew.left
                                +" "+dominoDrew.right+"]. Turn over.\n");
                        usersTurn = false;
                        takeTurn();
                    }
                    else{
                        System.out.println("Cannot draw, Boneyard empty.");
                        takeTurn();
                    }
                }
                // If user wants to declare turn unplayable.
                case "u" -> {
                    // Confirms selection as it could end the game.
                    System.out.println("Are you sure? (y/n) (Lowercase)");
                    String answer = input.next();
                    // Calls and changes for turn change.
                    if(answer.equals("y")){
                        humanUnableToPlay = true;
                        checkWin(humanPlayer.humanHand.hand);
                        usersTurn = false;
                        takeTurn();
                    }
                    // Restarts turn.
                    else if(answer.equals("n")){
                        humanTurnReset = false;
                        takeTurn();
                    }
                    else{
                        humanTurnReset = true;
                        takeTurn();
                    }
                }
                // If user wants to quit the program.
                case "q" -> {
                    // Ends game.
                    System.out.println("Hope you enjoyed.\nQuitting.");
                    exit();
                }
                // Invalid input case. Restarts turn.
                default -> {
                    restartTurnText();
                    takeTurn();
                }
            }
        }
        // Case for computer's turn.
        else{
            System.out.println("\nComputer Turn\n");
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
                    Board.outputBoard();
                    System.out.println(lineOne+"\n"+lineTwo);
                    humanTurn();
                }
                // Unable to play or draw causes unplayable flag.
                else if(!hasPlayed){
                    computerUnableToPlay = true;
                    checkWin(computerPlayer.computerHand.hand);
                    Board.outputBoard();
                    System.out.println(lineOne+"\n"+lineTwo);
                    humanTurn();
                }
            }
            // If hand is empty but boneyard is not, draws if possible.
            else if(Boneyard.boneyard.size() > 0){
                Boneyard.drawDomino(computerPlayer.computerHand.hand);
                computerUnableToPlay = false;
                Board.outputBoard();
                System.out.println(lineOne+"\n"+lineTwo);
                humanTurn();
            }
            // If hand and boneyard is empty, checks for win.
            else{
                computerUnableToPlay = true;
                checkWin(computerPlayer.computerHand.hand);
                Board.outputBoard();
                System.out.println(lineOne+"\n"+lineTwo);
                humanTurn();
            }
        }
    }

    /**
     * restartTurnText outputs text for human turn if it is the start of
     * a human turn or if an invalid input was entered.
     * Return void.
     */
    public static void restartTurnText(){
        // If the user did an invalid input. This text is triggered.
        if(humanTurnReset){
            System.out.println("Invalid input, restarting turn.");
        }
        // General output for start of turn.
        System.out.println("""
                [p] Play Domino.
                [d] Draw from boneyard.
                [u] Unable to play.
                [q] Quit.""");
    }

    /**
     * updateText is used to let the user know hand sizes.
     * Return void.
     */
    public void updateText(){
        // Update text.
        System.out.print("Dominoes!\nPlayer has " + humanPlayer.humanHand.hand
                .size() + " dominoes.\n"+"Computer has " + computerPlayer
                .computerHand.hand.size()+" dominoes.\n\n");
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
                System.out.println("\nCongratulations, you win!");
            }
            else if(hand == computerPlayer.computerHand.hand){
                System.out.println("\nComputer wins.");
            }
            System.exit(0);
        }
        // Checks if both players could not play in a row, causing unplayable.
        if(humanUnableToPlay && computerUnableToPlay){
            System.out.println("\nNo moves available.\nGame Over!");
            System.exit(0);
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
        // Outputs needed text and starts next turn.
        Board.outputBoard();
        System.out.println(lineOne+"\n"+lineTwo);
        humanTurn();
        // Covers for continuation after recursion.
        hasPlayed = true;
    }
}