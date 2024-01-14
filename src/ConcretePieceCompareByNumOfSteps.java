import java.util.ArrayList;
import java.util.Comparator;

public class ConcretePieceCompareByNumOfSteps implements Comparator<ConcretePiece> {
    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {
       if (o1.getNumOfSteps()==o2.getNumOfSteps())
       {
           if (o1.getOwner().equals(o2.getOwner()))
           {
               return Integer.compare(o1.getNum(), o2.getNum());
           }
           return Boolean.compare(o1.getOwner().equals(GameLogic.winner), o2.getOwner().equals(GameLogic.winner));
       }
      return Integer.compare(o1.getNumOfSteps(),o2.getNumOfSteps());
    }
}
