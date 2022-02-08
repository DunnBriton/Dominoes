import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Display {
    static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    static final double SCREEN_WIDTH  = Screen.getPrimary().getBounds().getWidth();

    public static void main(Stage primaryStage){
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setPrefSize(SCREEN_WIDTH/2, SCREEN_HEIGHT/2);

        Text topText = new Text("Dominos");
        topText.setStyle("-fx-font: 24 arial");
        StackPane topPane = new StackPane(topText);
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
        topPane.setPrefHeight(SCREEN_HEIGHT/30);

        Button btn = new Button("Draw from Boneyard.");
        HBox bottomPane = new HBox(btn);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);

        mainPane.setBottom(bottomPane);
        mainPane.setTop(topPane);

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }
}
