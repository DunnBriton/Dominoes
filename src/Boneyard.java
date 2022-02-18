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

    public static Tiles drawDomino(ArrayList<Tiles> hand){
        Random random = new Random();
        if(boneyard.size() > 1){
            i = random.nextInt(0, boneyard.size()-1);
        }
        else{
            i = random.nextInt(0, boneyard.size());
        }
        Tiles holder = boneyard.get(i);

        hand.add(holder);
        boneyard.remove(i);
        return holder;
    }
}
