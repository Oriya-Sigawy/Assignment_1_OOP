import java.util.Comparator;

/**
 * this class implements comparator that compare between two ConcretePieces by the number of squares each ConcretePiece
 * moved during a game
 */
public class ConcretePieceCompareByDistance implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.getDist() == o2.getDist()) { //if they moved the same distance, compare by their ID
            if (o1.getNum() == o2.getNum()) { //if their IDs are equal, compare by belonging to the winner group
                return Boolean.compare(o2.getOwner().equals(GameLogic.getWinner()), o1.getOwner().equals(GameLogic.getWinner()));
            }
            return Integer.compare(o1.getNum(), o2.getNum());
        }
        return -1 * Integer.compare(o1.getDist(), o2.getDist());
    }
}

