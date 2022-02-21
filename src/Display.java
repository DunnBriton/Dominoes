import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    public static void main(Stage primaryStage){
        // mainPane used as base for sections.
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setPrefSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        // Text for the top of the GUI that says Dominoes.
        Label topText = new Label("Dominoes");
        topText.setStyle("-fx-font: 24 arial");


        // gamePane used in center for played dominoes.
        BorderPane gamePane = new BorderPane();
        // Info for player put at top of gamePane
        gameInfo = new Label("Boneyard contains "+Boneyard.boneyard.size()+" dominoes.\n" +
                "Computer has "+MainGameLoop.computerPlayer.computerHand.hand.size()+" dominoes.");
        gameInfo.setTextAlignment(TextAlignment.CENTER);

        // Create center area for dominoes.
        gamePane.setTop(gameInfo);
        BorderPane.setAlignment(gameInfo, Pos.CENTER);
        gamePane.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));

        // HBOX FOR PLAYED DOMINOES.
        HBox playedDominoes = new HBox();
        playedDominoes.getChildren().add(new Rectangle(50,50));
        gamePane.setCenter(playedDominoes);


        // Button that causes you to draw from Boneyard.
        drawButton = new Button("Draw from Boneyard.");
        noPlayButton = new Button("Click if no moves possible.");


        //Vbox used to hold draw and pass turn buttons.
        VBox twoButtons = new VBox();
        twoButtons.getChildren().addAll(drawButton, noPlayButton);
        twoButtons.setAlignment(Pos.CENTER);

        // Hbox for items relevant to player. Ex: Buttons/Dominoes.
        HBox playerItems = new HBox(new Rectangle(50,50), twoButtons);
        playerItems.setAlignment(Pos.BOTTOM_RIGHT);

        // Add topText, gamePane, and bottomPane to mainPane.
        mainPane.setTop(topText);
        mainPane.setCenter(gamePane);
        mainPane.setBottom(playerItems);
        //mainPane.getChildren().add(noPlayButton);

        // Set some alignments for mainPane BorderPane.
        BorderPane.setAlignment(topText, Pos.CENTER);
        BorderPane.setAlignment(playerItems, Pos.CENTER);

        // Set scene and show stage.
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }
}
