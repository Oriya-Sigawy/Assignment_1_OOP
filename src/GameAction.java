/**
 * This class represent an act that made on the game. previous
 */
public class Act {
    private String act_type;
   private int previousX;
   private int previousY;
   private int currentX;
   private int currentY;
    private Piece killed;
    public Act(String at, int x, int y, Piece p)
    {
        act_type=at; //kill
        previousX=x;
        previousY=y;
        killed=p;
    }

    public Act(String at, int px, int py, int cx, int cy)
    {
        act_type=at; //move
        previousX=px;
        previousY=py;
        currentX=cx;
        currentY=cy;
    }

    public String getAct_type() {
        return act_type;
    }
    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getCurrentX() {
        return currentX;
    }
    public int getCurrentY() {
        return currentY;
    }
    public Piece getKilled() {
        return killed;
    }
}
