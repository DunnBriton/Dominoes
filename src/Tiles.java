import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tiles {
    // Variables to store left and right side of domino tile values.
    public int left, right;
    // Hboxes used to create dots on dominoes.
    public HBox dominoShape, rowOne, rowTwo, rowThree;

    /**
     * Tiles used to create a new domino tile.
     * @param a - The value for the left side.
     * @param b - The value for the right side.
     */
    public Tiles(int a, int b){
        left = a;
        right = b;

        dominoShape = drawDomino(a, b);
    }

    /**
     * setRowValues used to clean code up a bit and let the
     * function be called instead of putting the code twice.
     * Return void.
     */
    public void setRowValues(){
        // Reset box values.
        rowOne = new HBox();
        rowTwo = new HBox();
        rowThree = new HBox();
        // Reset alignment.
        rowOne.setAlignment(Pos.TOP_CENTER);
        rowTwo.setAlignment(Pos.BOTTOM_CENTER);
        rowThree.setAlignment(Pos.CENTER);
    }

    public HBox drawDomino(int a, int b){
        Rectangle recA = new Rectangle(40, 40);
        Rectangle recB = new Rectangle(40,40);
        Rectangle recC = new Rectangle(0, 40);

        VBox leftDots = new VBox();
        VBox rightDots = new VBox();
        leftDots.setAlignment(Pos.CENTER);
        rightDots.setAlignment(Pos.CENTER);

        setRowValues();
        switch (a){
            case 1 -> {
                rowOne.getChildren().add(new Circle(5));
                leftDots.getChildren().add(rowOne);
            }
            case 2 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                leftDots.getChildren().add(rowOne);
            }
            case 3 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5),new Circle(5));
                leftDots.getChildren().add(rowOne);
            }
            case 4 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5),new Circle(5));
                leftDots.getChildren().addAll(rowOne,rowTwo);
            }
            case 5 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5));
                rowThree.getChildren().addAll(new Circle(5), new Circle(5));
                leftDots.getChildren().addAll(rowOne,rowTwo,rowThree);
            }
            case 6 -> {
                rowOne.getChildren().addAll(new Circle(5), new Circle(5), new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5), new Circle(5), new Circle(5));
                leftDots.getChildren().addAll(rowOne,rowTwo);
            }
        }

        setRowValues();
        switch (b){
            case 1 -> {
                rowOne.getChildren().add(new Circle(5));
                rightDots.getChildren().add(rowOne);
            }
            case 2 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                rightDots.getChildren().add(rowOne);
            }
            case 3 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5),new Circle(5));
                rightDots.getChildren().add(rowOne);
            }
            case 4 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5),new Circle(5));
                rightDots.getChildren().addAll(rowOne,rowTwo);
            }
            case 5 -> {
                rowOne.getChildren().addAll(new Circle(5),new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5));
                rowThree.getChildren().addAll(new Circle(5), new Circle(5));
                rightDots.getChildren().addAll(rowOne,rowTwo,rowThree);
            }
            case 6 -> {
                rowOne.getChildren().addAll(new Circle(5), new Circle(5), new Circle(5));
                rowTwo.getChildren().addAll(new Circle(5), new Circle(5), new Circle(5));
                rightDots.getChildren().addAll(rowOne,rowTwo);
            }
        }

        StackPane leftHalf = new StackPane(recA, leftDots);
        StackPane rightHalf = new StackPane(recB, rightDots);

        recA.setFill(Paint.valueOf("#FFFDD0"));
        recA.setStroke(Paint.valueOf("#084600"));
        recB.setFill(Paint.valueOf("#FFFDD0"));
        recB.setStroke(Paint.valueOf("#084600"));

        return new HBox(leftHalf, recC, rightHalf);
    }
}