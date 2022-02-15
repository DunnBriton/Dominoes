import java.util.ArrayList;

public class Hand {
    public int i;
    public ArrayList<Tiles> hand;

    public Hand(){
        this.hand = new ArrayList<>();
        fillHand(this.hand);
    }

    public void fillHand(ArrayList<Tiles> holder){
        for(i=0;i<7;i++){
            Boneyard.drawDomino(holder);
        }
    }

    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for(i=0;i<hand.size(); i++){
            holder.append("[").append(hand.get(i).left).append(",");
            holder.append(hand.get(i).right).append("]");
        }

        return holder.toString();
    }
}