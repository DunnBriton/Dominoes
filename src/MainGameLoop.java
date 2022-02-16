import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class MainGameLoop extends Application {
    Human humanPlayer;
    Computer computerPlayer;
    Scanner input = new Scanner(System.in);
    Boolean whosTurn;
    String holderOne, holderTwo;

    public static void main(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("PROGRAM START");

        //Display.main(primaryStage);

        Boneyard.main();
        humanPlayer = new Human();
        computerPlayer = new Computer();

        updateText();
        System.out.println("Human goes first.");
        whosTurn = true;
        humanTurn();

        System.out.println("PROGRAM END");
    }

    public void humanTurn(){
        System.out.println("Human's Turn\nTray: " +
                            humanPlayer.humanHand.hand.toString());
        System.out.println("""
                [p] Play Domino
                [d] Draw from boneyard
                [q] Quit
                """);
        takeTurn();
    }

    public void computerTurn(){
        System.out.println("Insert Disk -Update Computer Turn");
        whosTurn = false;
        takeTurn();
    }

    public void takeTurn(){
        // If true then it is the human's turn.
        if (whosTurn){
            switch (input.nextLine()) {
                case ("p") -> {
                    System.out.println("Which domino? (0-" +
                            (humanPlayer.humanHand.hand.size()-1) + ")\n" +
                            "Count left to right starting at zero.\n");

                    if(input.hasNextInt()){
                        int dominoSelected = input.nextInt();

                        if(dominoSelected >= 0 && dominoSelected <= (humanPlayer.humanHand.hand.size()-1)){
                            System.out.println("Left or Right side? (l/r) (Lowercase)");
                            holderOne =  input.next();

                            if(holderOne.equals("l") || holderOne.equals("r")){
                                System.out.println("Rotate first? (y/n) (Lowercase)");
                                holderTwo = input.next();

                                if(holderTwo.equals("y") || holderTwo.equals("n")){
                                    /*
                                    if(playDomino == false){
                                        System.out.println("Incorrect domino placement counts as invalid input.");
                                        restartTurnText();
                                        takeTurn();
                                    }
                                    else{
                                    -Playing "Insert Domino l/r" at "left or right"
                                    -Computer has "x number" of dominoes.
                                    -Boneyard contains "x number" of dominoes
                                    -Output current board/play-area
                                    if(humanPlayer.humanHand.hand.size() == 0){
                                        System.out.println("Congratulations, you win!");
                                        -End game.
                                    }
                                    computerTurn();
                                    }
                                    */
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
                        restartTurnText();
                        takeTurn();
                    }
                }
                case "d" -> System.out.println("Need to work on draw choice.");
                case "q" -> System.out.println("Need to work on exit choice.");
                default -> {
                    restartTurnText();
                    takeTurn();
                }
            }
        }
        // If false then it is the computer's turn.
        else{
            System.out.println("Insert Disk -Computer Turn Tasks.");

            whosTurn = true;
            System.out.println("\ntesting human so flipping whoturn to human for testing");
            restartTurnText();
            takeTurn();
        }
    }

    public void restartTurnText(){
        System.out.println("""
                Invalid input, restarting turn.
                [p] Play Domino.
                [d] Draw from boneyard.
                [q] Quit.""");
    }
    public void updateText(){
        System.out.print("Dominoes!\nPlayer has " + humanPlayer.humanHand.hand.size() +
                " dominoes.\n"+"Computer has "+computerPlayer.computerHand.hand.size()+" dominoes.\n\n");
    }
}
