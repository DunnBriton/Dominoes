import javafx.scene.canvas.Canvas;

public class Tiles {

    public int left, right;
    public Canvas shape;

    public Tiles(int a, int b){
        left = a;
        right = b;
        shape = new Canvas();
    }

    @Override
    public String toString() {
        return "[" + left + "  " + right + "]";
    }
}