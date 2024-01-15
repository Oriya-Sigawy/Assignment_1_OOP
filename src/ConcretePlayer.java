import java.util.ArrayList;

/**
 * This class represents a player of the game
 */
public class ConcretePlayer implements Player{
    private final ArrayList<ConcretePiece> list_of_pieces= new ArrayList<>();
    private int wins;
    private final boolean is_player_one;

    /**
     * Construct a new player of the game
     * @param one_or_two true if this is player 1
     */
    public ConcretePlayer(boolean one_or_two)
    {
        wins=0;
        is_player_one=one_or_two;
    }

    /**
     * returns true if the player is player 1 and false if the player is player 2
     * @return true if the player is player 1 and false if the player is player 2
     */
    @Override
    public boolean isPlayerOne() {
        return is_player_one;
    }

    /**
     * return the number of wins that this player won
     * @return the number of wins that this player won
     */
    @Override
    public int getWins() {
        return wins;
    }

    /**
     * increase the number of wins by 1
     */
    public void setWins(){wins++;}

    /**
     * add a piece to the player's list of pieces
     * @param p the piece to add
     */
    public void addPiece(ConcretePiece p)
    {
        list_of_pieces.add(p);
    }

    /**
     * returns the player's list of pieces
     * @return the player's list of pieces
     */
    public ArrayList<ConcretePiece> getList_of_pieces() {
        return list_of_pieces;
    }

    /**
     *clear the player's list of pieces
     */
    public void resetPieces() {
        list_of_pieces.clear();
    }
}
