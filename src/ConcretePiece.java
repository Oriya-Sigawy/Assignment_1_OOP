import java.util.ArrayList;

/**
 * This class represents a piece in the game
 */
public abstract class ConcretePiece implements Piece {

    private final ArrayList<Position> steps=new ArrayList<>();
    private int dist=0;
    private int numOfSteps=0;
    private final int pieceNum;
    private final ConcretePlayer owner;
    private final String type;
    private int numOfKills=0;

    /**
     * construct a new piece in the game
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
     * returns  the piece's owner
     * @return  the piece's owner
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
    public ArrayList<Position> getSteps() {
        return steps;
    }
    public void addStep(Position p)
    {
        steps.add(p);
    }
    public void removeStep() {steps.remove(steps.size()-1);}
    public int getNumOfSteps() {
        return numOfSteps;
    }

    public void setNumOfSteps(int num) {
        this.numOfSteps+=num;
    }
    public int getNumOfKills() {
        return numOfKills;
    }
    public void setNumOfKills(int num) {
        this.numOfKills+=num;
    }
    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist += dist;
    }
}