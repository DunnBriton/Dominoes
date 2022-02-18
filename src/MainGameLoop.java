import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class MainGameLoop extends Application {
    static Human humanPlayer;
    static Computer computerPlayer;
    static Scanner input = new Scanner(System.in);
    static String holderOne, holderTwo, lineOne, lineTwo;
    static boolean usersTurn, humanTurnReset, hasPlayed;
    static boolean humanUnableToPlay, computerUnableToPlay;
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

    public static void humanTurn(){
        usersTurn = true;
        humanTurnReset = false;

        System.out.println("Human's Turn\nTray: " +
                            humanPlayer.humanHand.hand.toString());
        restartTurnText();
        humanTurnReset = true;
        takeTurn();
    }

    public static void takeTurn(){
        // If true then it is the human's turn.
        if (usersTurn){
            String in = input.next();
            switch (in) {
                case "p" -> {
                    System.out.println("Which domino? (0-" +
                            (humanPlayer.humanHand.hand.size()-1) + ")\n" +
                            "Count left to right starting at zero.\n");

                    if(input.hasNextInt()){
                        dominoSelected = input.nextInt();

                        if(dominoSelected >= 0 && dominoSelected <=
                                (humanPlayer.humanHand.hand.size()-1)){
                            System.out.println("Left or Right side of " +
                                               "Board? (l/r) (Lowercase)");
                            holderOne =  input.next();

                            if(holderOne.equals("l") || holderOne.equals("r")){
                                System.out.println("Rotate first? (y/n) " +
                                                   "(Lowercase)");
                                holderTwo = input.next();

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
                                    if(Board.validMoveCheck(dominoSelected,
                                            holderOne, humanPlayer.humanHand
                                                    .hand)){
                                        Tiles saveDomino = humanPlayer
                                                .humanHand.hand.get
                                                        (dominoSelected);
                                        Board.playDomino(dominoSelected,
                                                holderOne, humanPlayer
                                                        .humanHand.hand);
                                        humanUnableToPlay = false;
                                        checkWin(humanPlayer.humanHand.hand);

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


                                        usersTurn = false;
                                        takeTurn();
                                    }
                                    else{
                                        System.out.println("Incorrect " +
                                                "domino placement counts "+
                                                "as invalid input.");
                                        restartTurnText();
                                        takeTurn();
                                    }
                                }
                                else{
                                    takeTurn();
                                }
                            }
                            else{
                                restartTurnText();
                                input.nextLine();
                                takeTurn();
                            }
                        }
                        else{takeTurn();}
                    }
                    else{
                        takeTurn();
                    }
                }
                case "d" -> {
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
                case "u" -> {
                    System.out.println("Are you sure? (y/n) (Lowercase)");
                    String answer = input.next();
                    if(answer.equals("y")){
                        humanUnableToPlay = true;
                        checkWin(humanPlayer.humanHand.hand);
                        usersTurn = false;
                        takeTurn();
                    }
                    else if(answer.equals("n")){
                        humanTurnReset = false;
                        takeTurn();
                    }
                    else{
                        humanTurnReset = true;
                        takeTurn();
                    }
                }
                case "q" -> {
                    System.out.println("Hope you enjoyed.\nQuitting.");
                    exit();
                }
                default -> {
                    restartTurnText();
                    takeTurn();
                }
            }
        }
        // If false then it is the computer's turn.
        else{
            System.out.println("\nComputer Turn\n");
            computerUnableToPlay = false;
            Random random = new Random();

            if(computerPlayer.computerHand.hand.size() > 0){
                hasPlayed = false;
                for(int j=0;j<computerPlayer.computerHand.hand.size();j++){
                    int x;

                    if(computerPlayer.computerHand.hand.size() > 1){
                            x = random.nextInt(0, computerPlayer.computerHand
                                    .hand.size()-1);
                        }
                    else{
                            x = random.nextInt(0, computerPlayer.computerHand
                                    .hand.size());
                        }

                    if(Board.board == null || Board.board.size() == 0){
                        Board.playDomino(x, "l", computerPlayer.computerHand
                                .hand);
                        computerEndItems();
                        break;
                    }
                    else if(Board.board.get(0).left == 0){
                        Board.playDomino(x, "l", computerPlayer.computerHand
                                .hand);
                        computerEndItems();
                        break;
                    }
                    else if(Board.board.get(Board.board.size()-1).right == 0){
                        Board.playDomino(x, "r", computerPlayer.computerHand
                                .hand);
                        computerEndItems();
                        break;
                    }
                    else if(computerPlayer.computerHand.hand.get(j).left == 0){
                        Board.playDomino(computerPlayer.computerHand.hand
                                .get(j).left, "r", computerPlayer
                                .computerHand.hand);
                        computerEndItems();
                        break;
                    }
                    else if(computerPlayer.computerHand.hand.get(j).right == 0){
                        Board.playDomino(computerPlayer.computerHand.hand
                                .get(j).right, "l", computerPlayer
                                .computerHand.hand);
                        computerEndItems();
                        break;
                    }
                    else if(computerPlayer.computerHand.hand.get(j).left ==
                            Board.board.get(Board.board.size()-1).right){
                        Board.playDomino(j,"r", computerPlayer.computerHand
                                .hand);
                        computerEndItems();
                        break;
                    }
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
                    else if(computerPlayer.computerHand.hand.get(j).right ==
                            Board.board.get(0).left){
                        Board.playDomino(j,"l", computerPlayer.computerHand
                                .hand);
                        computerEndItems();
                        break;
                    }
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
                if(!hasPlayed && Boneyard.boneyard.size() > 0){
                    Boneyard.drawDomino(computerPlayer.computerHand.hand);
                    computerUnableToPlay = false;
                    Board.outputBoard();
                    System.out.println(lineOne+"\n"+lineTwo);
                    humanTurn();
                }
                else if(!hasPlayed){
                    computerUnableToPlay = true;
                    checkWin(computerPlayer.computerHand.hand);
                    Board.outputBoard();
                    System.out.println(lineOne+"\n"+lineTwo);
                    humanTurn();
                }
            }
            else if(Boneyard.boneyard.size() > 0){
                Boneyard.drawDomino(computerPlayer.computerHand.hand);
                computerUnableToPlay = false;
                Board.outputBoard();
                System.out.println(lineOne+"\n"+lineTwo);
                humanTurn();
            }
            else{
                computerUnableToPlay = true;
                checkWin(computerPlayer.computerHand.hand);
                Board.outputBoard();
                System.out.println(lineOne+"\n"+lineTwo);
                humanTurn();
            }
        }
    }

    public static void restartTurnText(){
        if(humanTurnReset){
            System.out.println("Invalid input, restarting turn.");
        }
        System.out.println("""
                [p] Play Domino.
                [d] Draw from boneyard.
                [u] Unable to play.
                [q] Quit.""");
    }

    public void updateText(){
        System.out.print("Dominoes!\nPlayer has " + humanPlayer.humanHand.hand
                .size() + " dominoes.\n"+"Computer has " + computerPlayer
                .computerHand.hand.size()+" dominoes.\n\n");
    }

    public static void checkWin(ArrayList<Tiles> hand){
        if(hand.size() == 0 && Boneyard.boneyard.size() == 0){
            if(hand == humanPlayer.humanHand.hand){
                System.out.println("\nCongratulations, you win!");
            }
            else if(hand == computerPlayer.computerHand.hand){
                System.out.println("\nComputer wins.");
            }
            System.exit(0);
        }
        if(humanUnableToPlay && computerUnableToPlay){
            System.out.println("\nNo moves available.\nGame Over!");
            System.exit(0);
        }
    }

    public static void computerEndItems(){
        computerUnableToPlay = false;
        checkWin(computerPlayer.computerHand.hand);
        Board.outputBoard();
        System.out.println(lineOne+"\n"+lineTwo);
        humanTurn();
        hasPlayed = true;
    }
}
