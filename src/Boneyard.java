import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Boneyard {
    // Variable used to store domino tiles in a draw pile.
    static ArrayList<Tiles> boneyard = new ArrayList<>();

    /**
     * main creates set of dominoes and puts them into the boneyard.
     * Return void.
     */
    public static void main(){
        // Creates all needed domino tile combination without duplicates.
        for(int i=0; i<=6; i++){
            for(int j=i; j<=6; j++){
                Tiles tile = new Tiles(i,j);
                boneyard.add(tile);
            }
        }
        // Shuffles draw pile/
        Collections.shuffle(boneyard);
    }

    /**
     * drawDomino draws a domino from the boneyard.
     * @param hand - What hand to add to. (Human/Computer)
     * @return     - Returns domino drawn.
     */
    public static Tiles drawDomino(ArrayList<Tiles> hand){
        Random random = new Random();
        int i;
        // Makes sure correct call is used based on boneyard size.
        if(boneyard.size() > 1){
            i = random.nextInt(0, boneyard.size()-1);
        }
        else{
            i = random.nextInt(0, boneyard.size());
        }
        // Pulls domino, adds to hand, removes from boneyard, returns hand.
        Tiles holder = boneyard.get(i);
        hand.add(holder);
        boneyard.remove(i);
        return holder;
    }
}
