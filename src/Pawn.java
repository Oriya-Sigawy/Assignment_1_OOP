/**
 * represents the king piece from the game
 */
public class Pawn extends ConcretePiece{
    /**
     * Constructs a new pawn with a given parameters
     * @param p the pawn's owner
     * @param num the pawn ID
     */
    public Pawn(ConcretePlayer p, int num) {
        super( p,p.isPlayerOne()? "♙":"♟", num);
    }
}
