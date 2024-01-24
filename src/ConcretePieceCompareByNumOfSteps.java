import java.util.Comparator;

/**
 * this class implements comparator that compare between two ConcretePieces by the number of steps each ConcretePiece
 * made during a game
 */
public class ConcretePieceCompareByNumOfSteps implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.getSteps().size() == o2.getSteps().size()) { //if they made the same number of steps, compare by their ID
            if (o1.getOwner().equals(o2.getOwner())) {  //if their IDs are equal, compare by belonging to the winner group
                return Integer.compare(o1.getNum(), o2.getNum());
            }
            return Boolean.compare(o1.getOwner().equals(GameLogic.getWinner()), o2.getOwner().equals(GameLogic.getWinner()));
        }
        return Integer.compare(o1.getSteps().size(), o2.getSteps().size());
    }
}
