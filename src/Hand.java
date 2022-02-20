import java.util.ArrayList;

public class Hand {
    // Variable for hand object.
    public ArrayList<Tiles> hand;

    /**
     * Hand used to store tiles "dominoes" and represent a player's hand.
     */
    public Hand(){
        this.hand = new ArrayList<>();
        fillHand(this.hand);
    }

    /**
     * fillHand used to fill a new Hand with the starting
     * number of tiles "dominoes".
     * @param holder - The Hand that will be filled.
     */
    public void fillHand(ArrayList<Tiles> holder){
        // Takes seven domino tiles from boneyard and puts into new hand.
        for(int i=0;i<7;i++){
            Boneyard.drawDomino(holder);
        }
    }

    /**
     * Override used to output a Hand.
     * @return - Returns string that should be outputted.
     */
    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for (Tiles tiles : hand) {
            holder.append(tiles.left).append("  ");
            holder.append(tiles.right);
        }
        return holder.toString();
    }
}