import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Boneyard {
    static int i, j;
    static ArrayList<Tiles> boneyard = new ArrayList<>();

    public static void main(){
        for(i=0; i<=6; i++){
            for(j=i; j<=6; j++){
                Tiles tile = new Tiles(i,j);
                boneyard.add(tile);
            }
        }
        Collections.shuffle(boneyard);
    }

    public static void drawDomino(ArrayList<Tiles> hand){
        Random random = new Random();
        int x = random.nextInt(0, boneyard.size());
        Tiles holder = boneyard.get(x);

        hand.add(holder);
        boneyard.remove(x);
    }
}
