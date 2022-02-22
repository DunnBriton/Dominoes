import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Display {
    // doubles to store screen sizes.
    static final double SCREEN_HEIGHT = Screen.getPrimary()
            .getBounds().getHeight();
    static final double SCREEN_WIDTH  = Screen.getPrimary()
            .getBounds().getWidth();
    // Buttons for drawing and unable to play.
    static Button drawButton, noPlayButton;
    // Label for game info at the top of GUI.
    static Label gameInfo;
    // Vbox, Tilepane, and HBox variables.
    static VBox topItems;
    static TilePane gamePane;
    static HBox playerItems;

    /**
     * main method where the GUI is created.
     * @param primaryStage - Stage to put visuals on.
     */
    public static void main(Stage primaryStage){
        // mainPane used as base for sections.
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(
                Color.BURLYWOOD,CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setPrefSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        // Text for the top of the GUI that says Dominoes.
        Label topText = new Label("Dominoes!");
        topText.setStyle("-fx-font: 24 arial");

        // Info for player put at top of gamePane
        gameInfo = new Label("Boneyard contains " + Boneyard.boneyard.size()
                + " dominoes.\n" + "Computer has " + MainGameLoop
                .computerPlayer.computerHand.hand.size() + " dominoes.");
        gameInfo.setStyle("-fx-border-color:black; " +
                "-fx-background-color: white;");

        // Vbox used to put topText and gameInfo into top of mainPane.
        topItems = new VBox(topText, gameInfo);
        topItems.setAlignment(Pos.CENTER);
        topItems.setSpacing(5);

        // TilePane used for where played dominoes will go.
        gamePane = new TilePane();
        gamePane.setBackground(new Background(new BackgroundFill(
                Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Adjust some settings for gamePane.
        gamePane.setPrefSize(100,100);
        gamePane.setVgap(3);
        gamePane.setHgap(3);
        gamePane.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT/2);
        gamePane.setAlignment(Pos.CENTER);


        // Button that causes you to draw from Boneyard.
        drawButton = new Button("Draw from Boneyard.");
        noPlayButton = new Button("Click if no moves possible.");

        //Vbox used to hold buttons in mainPane's bottom area.
        VBox twoButtons = new VBox();
        twoButtons.getChildren().addAll(drawButton, noPlayButton);
        twoButtons.setAlignment(Pos.CENTER);

        // Hbox used to hold hand's dominoes and Vbox of buttons.
        playerItems = new HBox();
        playerItems.setSpacing(2);
        for(int i=0;i<MainGameLoop.humanPlayer.humanHand.hand.size();i++){
            playerItems.getChildren().add(MainGameLoop.humanPlayer
                    .humanHand.hand.get(i).dominoShape);
        }
        playerItems.getChildren().add(twoButtons);
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
