import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Display {
    static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    static final double SCREEN_WIDTH  = Screen.getPrimary().getBounds().getWidth();
    static Button drawButton, noPlayButton;
    static Label gameInfo;
    static VBox topItems;
    static Pane gamePane;

    public static void main(Stage primaryStage){
        // mainPane used as base for sections.
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setPrefSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        // Code for top text. Using code folding while I'm coding.
        {
            // Text for the top of the GUI that says Dominoes.
            Label topText = new Label("Dominoes!");
            topText.setStyle("-fx-font: 24 arial");
            // Info for player put at top of gamePane
            gameInfo = new Label("Boneyard contains " + Boneyard.boneyard.size() + " dominoes.\n" +
                    "Computer has " + MainGameLoop.computerPlayer.computerHand.hand.size() + " dominoes.");
            //gameInfo.setTextAlignment(TextAlignment.CENTER);
            gameInfo.setStyle("-fx-border-color:black; -fx-background-color: white;");
            // Vbox used to put topText and gameInfo into top of mainPane.
            topItems = new VBox(topText, gameInfo);
            topItems.setAlignment(Pos.CENTER);
            topItems.setSpacing(5);
        }


        gamePane = new Pane();
        Tiles test = new Tiles(3,3);
        gamePane.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
        gamePane.getChildren().add(test.dominoShape);



        // Button that causes you to draw from Boneyard.
        drawButton = new Button("Draw from Boneyard.");
        noPlayButton = new Button("Click if no moves possible.");
        //Vbox used to hold buttons in mainPane's bottom area.
        VBox twoButtons = new VBox();
        twoButtons.getChildren().addAll(drawButton, noPlayButton);
        twoButtons.setAlignment(Pos.CENTER);

        // Hbox used to hold hand's dominos and Vbox of buttons.
        HBox playerItems = new HBox(new Rectangle(50,50), twoButtons);
        playerItems.setAlignment(Pos.BOTTOM_RIGHT);

        // Add topText, gamePane, and bottomPane to mainPane.
        mainPane.setTop(topItems);
        mainPane.setCenter(gamePane);
        mainPane.setBottom(playerItems);

        // Set scene and show stage.
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }
}
