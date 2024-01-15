/**
 * This class represent an act that made on the game.
 */
public class GameAction {
    private final String act_type;
    private final int previousX;
    private final int previousY;
    private int currentX;
    private int currentY;
    private ConcretePiece killed;

    /**
     * constructs a new kill act
     * @param at type of act (kill)
     * @param x x coordinate of the killed piece's position
     * @param y y coordinate of the killed piece's position
     * @param p the killed piece
     */
    public GameAction(String at, int x, int y, ConcretePiece p) {
        act_type = at; //kill
        previousX = x;
        previousY = y;
        killed = p;
    }

    /**
     *  constructs a new move act
     * @param at type of act (move)
     * @param px the x coordinate of the previous position of the piece
     * @param py the y coordinate of the previous position of the piece
     * @param cx the x coordinate of the current position of the piece
     * @param cy the y coordinate of the current position of the piece
     */
    public GameAction(String at, int px, int py, int cx, int cy) {
        act_type = at; //move
        previousX = px;
        previousY = py;
        currentX = cx;
        currentY = cy;
    }

    /**
     * returns the type of the act (move or kill)
     * @return the type of the act (move or kill)
     */
    public String getAct_type() {
        return act_type;
    }

    /**
     * returns the x coordinate of the moving piece's previous position
     * @return the x coordinate of the moving piece's previous position
     */
    public int getPreviousX() {
        return previousX;
    }

    /**
     * returns the y coordinate of the moving piece's previous position
     * @return the y coordinate of the moving piece's previous position
     */
    public int getPreviousY() {
        return previousY;
    }

    /**
     * returns the x coordinate of the moving piece's current position
     * @return the x coordinate of the moving piece's current position
     */
    public int getCurrentX() {
        return currentX;
    }

    /**
     *  returns the y coordinate of the moving piece's current position
     * @return the y coordinate of the moving piece's current position
     */
    public int getCurrentY() {
        return currentY;
    }

    /**
     * returns the killed piece (in case the game act was a kill)
     * @return the killed piece (in case the game act was a kill)
     */
    public ConcretePiece getKilled() {
        return killed;
    }
}
