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
}