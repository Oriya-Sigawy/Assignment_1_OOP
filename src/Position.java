
/**
 * This class represents a position in the game board
 */
public class Position {
    private static final int BOARD_SIZE = GameLogic.BOARD_SIZE;
    private final int x;
    private final int y;
    private int diffPiecesStepped=0;
    /**
     * constructs a new position with the given coordinates
     * @param new_x the x value for the created position
     * @param new_y the y value for the created position
     * @throws RuntimeException when the coordinates are out of the game board borders
     */
    public Position(int new_x, int new_y) {
        if (new_x < 0 || new_y < 0 || new_x >= BOARD_SIZE || new_y >= BOARD_SIZE)
        {
            throw new RuntimeException("position out of board bounds");
        }
        x = new_x;
        y = new_y;
    }

    /**
     * checks if the position is a corner of the game board
     * @return true if the position is a corner of the game board
     */
    public boolean isCorner() {
        return (y % (BOARD_SIZE - 1) == 0 && x % (BOARD_SIZE - 1) == 0);
    }

    /**
     * returns  the x coordinate of the position
     * @return the x coordinate of the position
     */
    public int getX() {
        return x;
    }
    /**
     * returns  the y coordinate of the position
     * @return the y coordinate of the position
     */
    public int getY() {
        return y;
    }
    public int getDiffPiecesStepped() {
        return diffPiecesStepped;
    }

    public void setDiffPiecesStepped(int diffPiecesStepped) {
        this.diffPiecesStepped += diffPiecesStepped;
    }
}
