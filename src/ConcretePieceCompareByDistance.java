import java.util.Comparator;

public class ConcretePieceCompareByDistance implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
        if (o1.getDist() == o2.getDist()) {
            if (o1.getNum() == o2.getNum()) {
                return Boolean.compare(o2.getOwner().equals(GameLogic.winner), o1.getOwner().equals(GameLogic.winner));
            }
            return Integer.compare(o1.getNum(), o2.getNum());
        }
        return -1 * Integer.compare(o1.getDist(), o2.getDist());
    }
}

