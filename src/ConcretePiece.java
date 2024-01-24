import java.util.Stack;

/**
 * This class represents a piece in the game
 */
public abstract class ConcretePiece implements Piece {

    private final Stack<Position> steps=new Stack<>();      //contains the positions that the piece stepped on
    private int dist=0;
    private final int pieceNum;
    private final ConcretePlayer owner;
    private final String type;
    private int numOfKills=0;

    /**
     * construct a new ConcretePiece in the game
     * @param p the piece's owner
     * @param s the piece's type
     * @param num the piece's number
     */
    public ConcretePiece(ConcretePlayer p, String s, int num) {
        owner = p;
        type = s;
        pieceNum = num;
    }

    /**
     * returns the piece's owner
     * @return the piece's owner
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * returns  the piece's type
     * @return  the piece's type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * returns  the piece's number
     * @return  the piece's number
     */
    public int getNum() {

        return pieceNum;
    }

    /**
     * return the list of the position that the piece stepped on
     * @return the list of the position that the piece stepped on
     */
    public Stack<Position> getSteps() {
        return steps;
    }

    /**
     * add a position to the list of the position that the piece stepped on
     * @param p position to add to the list
     */
    public void addStep(Position p)
    {
        steps.push(p);
    }

    /**
     *remove the last step from the steps stack (in case of undo)
     */
    public void removeStep() {steps.pop();}

    /**
     * return the number of pieces that this piece killed
     * @return the number of pieces that this piece killed
     */
    public int getNumOfKills() {
        return numOfKills;
    }

    /**
     * add 1 or sub 1 from the number of pieces that this piece killed
     * @param num 1 in case of a kill, -1 in case of undo a kill
     */
    public void setNumOfKills(int num) {
        this.numOfKills+=num;
    }

    /**
     * returns the distance (in squares) that this piece moved on the game board during the game
     * @return the distance (in squares) that this piece moved
     */
    public int getDist() {
        return dist;
    }

    /**
     * add the distance this piece moved in a current move to the total distance this piece moved during the game
     * @param dist the distance this piece moved in a current move
     */
    public void setDist(int dist) {
        this.dist += dist;
    }
}