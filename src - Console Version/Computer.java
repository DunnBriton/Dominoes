public class Computer {
    // Variable for hand object.
    public Hand computerHand;

    /**
     * Computer creates named Object used to represent nonHuman player.
     * Note: a bit unneeded do to Hand object but used for organization.
     */
    public Computer() {
        this.computerHand = new Hand();
    }

    /**
     * Override used to output the Computer hand.
     * @return - Returns string that should be outputted.
     */
    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for(int i=0;i<computerHand.hand.size(); i++){
            holder.append("[").append(computerHand
                    .hand.get(i).left).append(",");
            holder.append(computerHand.hand.get(i).right).append("]");
        }
        return holder.toString();
    }
}