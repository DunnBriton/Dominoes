public class Human {
    // Variable for hand object.
    public Hand humanHand;

    /**
     * Human used to create named Object to represent player.
     * Note: a bit unneeded do to Hand object but used for organization.
     */
    public Human() {
        this.humanHand = new Hand();
    }

    /**
     * Override used to output the Human hand.
     * @return - Returns string that should be outputted.
     */
    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for(int i=0;i<humanHand.hand.size(); i++){
            holder.append("[").append(humanHand.hand
                    .get(i).left).append("  ");
            holder.append(humanHand.hand.get(i).right).append("]");
        }
        return holder.toString();
    }
}