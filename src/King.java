/**
 * represents the king piece from the game
 */
public class King extends ConcretePiece{
    /**
     * Constructs a new king with a given parameters
     * @param o the king's owner
     * @param num the king ID
     */
    public King(ConcretePlayer o, int num) {
        super(o,"♔",num);
    }
}
