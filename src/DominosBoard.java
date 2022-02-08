import java.util.ArrayList;

public class DominosBoard{
    static int i, j;
    static ArrayList<Tiles> dominosSet = new ArrayList<Tiles>();

    public static void main(){
        for(i=0; i<=6; i++){
            for(j=i; j<=6; j++){
                Tiles tile = new Tiles(i,j);
                dominosSet.add(tile);
                System.out.println(tile.left+","+tile.right);
            }
        }
    }
}
