import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;

import static java.awt.Color.green;

public class Tiles {
    // Variables to store left and right side of domino tile values.
    public int left, right;
    // Currently, unused. Intended for GUI use.
    public Canvas dominoShape;

    /**
     * Tiles used to create a new domino tile.
     * @param a - The value for the left side.
     * @param b - The value for the right side.
     */
    public Tiles(int a, int b){
        left = a;
        right = b;
        dominoShape = new Canvas();
        dominoShape.setWidth(Display.SCREEN_WIDTH);
        dominoShape.setHeight(Display.SCREEN_HEIGHT);

        GraphicsContext gc = dominoShape.getGraphicsContext2D();
        gc.beginPath();
        gc.moveTo(0,0);
        gc.lineTo(100,100);
        gc.stroke();

    }
}