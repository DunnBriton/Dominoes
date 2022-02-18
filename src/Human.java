public class Human {
    public int i;
    public Hand humanHand;

    public Human() {
        this.humanHand = new Hand();
    }

    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for(i=0;i<humanHand.hand.size(); i++){
            holder.append("[").append(humanHand.hand
                    .get(i).left).append("  ");
            holder.append(humanHand.hand.get(i).right).append("]");
        }
        return holder.toString();
    }
}