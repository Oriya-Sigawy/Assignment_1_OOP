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
     * @param actionType type of act (kill)
     * @param x x coordinate of the killed piece's position
     * @param y y coordinate of the killed piece's position
     * @param piece the killed piece
     */
    public GameAction(String actionType, int x, int y, ConcretePiece piece) {
        act_type = actionType; //kill
        previousX = x;
        previousY = y;
        killed = piece;
    }

    /**
     *  constructs a new move act
     * @param actionType type of act (move)
     * @param previous_x the x coordinate of the previous position of the piece
     * @param previous_y the y coordinate of the previous position of the piece
     * @param current_x the x coordinate of the current position of the piece
     * @param current_y the y coordinate of the current position of the piece
     */
    public GameAction(String actionType, int previous_x, int previous_y, int current_x, int current_y) {
        act_type = actionType; //move
        previousX = previous_x;
        previousY = previous_y;
        currentX = current_x;
        currentY = current_y;
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
