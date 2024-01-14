/**
 * This class represent an act that made on the game. previous
 */
public class GameAction {
    private final String act_type;
    private final int previousX;
    private final int previousY;
    private int currentX;
    private int currentY;
    private ConcretePiece killed;

    /**
     * constructs a new act
     *
     * @param at type of act
     * @param x
     * @param y
     * @param p
     */
    public GameAction(String at, int x, int y, ConcretePiece p) {
        act_type = at; //kill
        previousX = x;
        previousY = y;
        killed = p;
    }

    public GameAction(String at, int px, int py, int cx, int cy) {
        act_type = at; //move
        previousX = px;
        previousY = py;
        currentX = cx;
        currentY = cy;
    }

    public String getAct_type() {
        return act_type;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Piece getKilled() {
        return killed;
    }
}
