import java.util.Comparator;

/**
 * this class implements comparator that compare between two Pieces by the number of different pieces stepped on each one
 */
public class PositionCompareByDifferentPieceStepped implements Comparator<Position>{
        @Override
        public int compare(Position o1, Position o2) {
            if (o1.getDiffPiecesStepped()==o2.getDiffPiecesStepped()) {  //if the same num of pieces stepped on them compare by their x's coordinate
                if (o1.getX()==o2.getX()) { //if they have the same x coordinate, compare by the y coordinate
                    return Integer.compare(o1.getY(),o2.getY());
                }
                return Integer.compare(o1.getX(),o2.getX());
            }
            return -1*Integer.compare(o1.getDiffPiecesStepped(),o2.getDiffPiecesStepped());
        }
    }
