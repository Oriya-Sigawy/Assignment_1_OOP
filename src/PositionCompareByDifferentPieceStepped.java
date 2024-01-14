import java.util.Comparator;

public class PositionCompareByDifferentPieceStepped implements Comparator<Position>{
        @Override
        public int compare(Position o1, Position o2) {
            if (o1.getDiffPiecesStepped()==o2.getDiffPiecesStepped())
            {
                if (o1.getX()==o2.getX())
                {
                    return Integer.compare(o1.getY(),o2.getY());
                }
                return Integer.compare(o1.getX(),o2.getX());
            }
            return -1*Integer.compare(o1.getDiffPiecesStepped(),o2.getDiffPiecesStepped());
        }
    }
