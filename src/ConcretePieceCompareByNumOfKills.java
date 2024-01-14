import java.util.Comparator;

public class ConcretePieceCompareByNumOfKills implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.getNumOfKills() == o2.getNumOfKills()) {
            if (o1.getNum() == o2.getNum()) {
                return Boolean.compare(o1.getOwner().equals(GameLogic.winner), o2.getOwner().equals(GameLogic.winner));
            }
            return Integer.compare(o1.getNum(), o2.getNum());
        }
        return -1 * Integer.compare(o1.getNumOfKills(), o2.getNumOfKills());
    }
}
