/**
 * represents the king piece from the game
 */
public class Pawn extends ConcretePiece{
    /**
     * Constructs a new pawn with a given parameters
     * @param o the pawn's owner
     * @param num the pawn ID
     */
    public Pawn(ConcretePlayer o, int num) {
        super( o,o.isPlayerOne()? "♙":"♟", num);
    }
}
