import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class MainGameLoop extends Application {
    Human humanPlayer;
    Computer computerPlayer;

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

        Random coinFlip = new Random();
        if(coinFlip.nextInt(2) == 0){
            System.out.println("Human goes first.\n");
            humanTurn();
        }
        else{
            System.out.println("Computer goes first.\n");
            computerTurn();
        }

        System.out.println("PROGRAM END");
    }

    public void updateText(){
        System.out.print("Dominos!\nPlayer has " + humanPlayer.humanHand.hand.size() +
                " dominos.\n"+"Computer has "+computerPlayer.computerHand.hand.size()+" dominos.\n\n");
    }

    public void humanTurn(){
        Scanner input = new Scanner(System.in);
        System.out.println("Human's Turn\nTray: "+humanPlayer.humanHand.hand.toString());
        System.out.println("[p] Play Domino\n[d] Draw from boneyard\n[q] Quit\n");

        String inputString = input.nextLine();
        switch (inputString) {
            case "p" -> System.out.println("Need to work on play choice.");
            case "d" -> System.out.println("Need to work on draw choice.");
            case "q" -> System.out.println("Need to work on exit choice.");
            default  -> System.out.println("Need to work on invalid input.");
        }
    }

    public void computerTurn(){
        System.out.println("Need to work on Computer turn.");
    }
}