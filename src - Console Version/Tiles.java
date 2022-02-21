import javafx.scene.canvas.Canvas;

public class Tiles {
    // Variables to store left and right side of domino tile values.
    public int left, right;
    // Currently, unused. Intended for GUI use.
    public Canvas shape;

    /**
     * Tiles used to create a new domino tile.
     * @param a - The value for the left side.
     * @param b - The value for the right side.
     */
    public Tiles(int a, int b){
        left = a;
        right = b;
        shape = new Canvas();
    }

    /**
     * Override used to output a domino tile.
     * @return - Returns string that should be outputted.
     */
    @Override
    public String toString() {
        return "[" + left + "  " + right + "]";
    }
}