import javafx.scene.canvas.Canvas;

public class Tiles {

    public int left, right;
    public Canvas shape;

    public Tiles(int a, int b){
        this.left = a;
        this.right = b;
        shape = new Canvas();
    }
}