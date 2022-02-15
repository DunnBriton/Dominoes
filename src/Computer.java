public class Computer {
    public int i;
    public Hand computerHand;

    public Computer() {
        this.computerHand = new Hand();
    }

    @Override
    public String toString(){
        StringBuilder holder = new StringBuilder();

        for(i=0;i<computerHand.hand.size(); i++){
            holder.append("[").append(computerHand.hand.get(i).left).append(",");
            holder.append(computerHand.hand.get(i).right).append("]");
        }

        return holder.toString();
    }
}