import java.util.ArrayList;
import java.util.Stack;

/**
 * this class represent the game logic according to the game rules
 */
public class GameLogic implements PlayableLogic {
    /**
     * BOARD_SIZE represent the height and the width of the board
     */
    private final ArrayList<Position> positions = new ArrayList<>();
    private static final int BOARD_SIZE = 11;
    private static ConcretePlayer winner;

    /**
     * getter  for the winner
     * @return the winner
     */
    public static ConcretePlayer getWinner() {
        return winner;
    }
    private final ConcretePiece[][] board = new ConcretePiece[BOARD_SIZE][BOARD_SIZE]; //represent the game board
    private final Stack<GameAction> actions = new Stack<>();

    /**
     * constructs a new game logic
     */
    public GameLogic() {
        resetFunc();
    }

    private final ConcretePlayer player1 = new ConcretePlayer(true);
    private final ConcretePlayer player2 = new ConcretePlayer(false);
    private Player currentTurn = player2;     //saves the player who owns the turn

    /**
     * The method checks if the asked move is legal and if the move kills another piece.
     *
     * @param a The source position for the piece.
     * @param b The destination position for the piece.
     * @return true if this move was OK
     */
    @Override
    public boolean move(Position a, Position b) {
        if (!board[a.getX()][a.getY()].getOwner().equals(currentTurn)) {    //check that the moving piece owner equals to the turn's owner
            return false;
        }
        if (a.equals(b) || (a.getX() != b.getX() && a.getY() != b.getY())) {     //illegal move
            return false;
        }
        if (getPieceAtPosition(b) != null) {               //position not valid
            return false;
        }
        Piece p;
        if (b.isCorner()) {                              //only the king can move to a corner
            p = getPieceAtPosition(a);
            if (!p.getType().equals("♔")) {
                return false;
            }
        }
        //check if the way from the source to the destination is clear
        if (a.getX() < b.getX()) {                                 //check right
            for (int i = a.getX() + 1; i < b.getX(); i++) {
                if (board[i][a.getY()] != null) {
                    return false;
                }
            }
        }
        if (a.getX() > b.getX()) {                                //check left
            for (int i = a.getX() - 1; i > b.getX(); i--) {
                if (board[i][b.getY()] != null) {
                    return false;
                }
            }
        }
        if (a.getY() < b.getY()) {                               //check down
            for (int i = a.getY() + 1; i <= b.getY(); i++) {
                if (board[a.getX()][i] != null) {
                    return false;
                }
            }
        }
        if (a.getY() > b.getY()) {                             //check up
            for (int i = a.getY() - 1; i > b.getY(); i--) {
                if (board[b.getX()][i] != null) {
                    return false;
                }
            }
        }
        board[b.getX()][b.getY()] = (ConcretePiece) getPieceAtPosition(a);    //place the piece from the source in the destination
        board[a.getX()][a.getY()] = null;                                    //remove the piece from the previous position

        //the following commands are related to the second part of the assignment (the comparators)
        if (a.getX() == b.getX()) {
            board[b.getX()][b.getY()].setDist(Math.abs(a.getY() - b.getY())); //add the distance that the piece moved to the piece's dist variable
        } else {
            board[b.getX()][b.getY()].setDist(Math.abs(a.getX() - b.getX()));
        }
        board[b.getX()][b.getY()].addStep(positions.get(BOARD_SIZE * b.getX() + b.getY())); //add the destination position the steps list of the moving piece
        actions.push(new GameAction("move", a.getX(), a.getY(), b.getX(), b.getY())); //add the action to the game actions stuck

        //check if the move killed another piece according to the game rules
        kill(0, -1, getPieceAtPosition(b).getType(), b);  //check up
        kill(0, 1, getPieceAtPosition(b).getType(), b);   //check down
        kill(-1, 0, getPieceAtPosition(b).getType(), b);  //check left
        kill(1, 0, getPieceAtPosition(b).getType(), b);   //check right

        if (isGameFinished()) {        //check if the move end the game
            actions.clear();          //empty the moves stack
            //add 1 to the winner's number of wins
            if (winner.isPlayerOne()) {
                player1.setWins();
            } else {
                player2.setWins();
            }
            comp_and_print();         //call the func of part 2 of this assignment
        }
        //gives the turn to the other player
        if (currentTurn.isPlayerOne()) {
            currentTurn = player2;
        } else {
            currentTurn = player1;
        }
        return true;
    }

    private void kill(int delX, int delY, String type, Position killerP) {
        if (board[killerP.getX()][killerP.getY()] instanceof King)  //the king can not kill
        {
            return;
        }
        //define the piece that can be killed because of the move
        int killedY = killerP.getY() + delY;
        int killedX = killerP.getX() + delX;
        if (killedX < 0 || killedX >= BOARD_SIZE || killedY < 0 || killedY >= BOARD_SIZE) {         //check if the killed piece is inside the game board
            return;
        }
        if (board[killedX][killedY] == null) {                  //if there isn't killed piece
            return;
        }
        if (board[killedX][killedY] != null && board[killedX][killedY].getType().equals(type)) {    //if the killed piece is from the same type of the killer return
            return;
        }
        if (board[killedX][killedY] instanceof King) {                                              //king can be killed in a different way from a pawn
            killTheKing(killedX, killedY);
            return;
        }
        //define the piece that help rhe killer according to the game rules
        int neighbor_x = killerP.getX() + 2 * delX;
        int neighbor_y = killerP.getY() + 2 * delY;

        //if the neighbor piece is not inside the game board, or the neighbor is a corner,
        // or the neighbor is on the same group of the killer, kill the piece

        if ((neighbor_x < 0 || neighbor_x >= BOARD_SIZE || neighbor_y < 0 || neighbor_y >= BOARD_SIZE) ||
                (positions.get(BOARD_SIZE * neighbor_x + neighbor_y).isCorner()) ||
                (board[neighbor_x][neighbor_y] != null && board[neighbor_x][neighbor_y].getType().equals(type))) {
            actions.push(new GameAction("kill", killedX, killedY, board[killedX][killedY]));
            board[killedX][killedY] = null;
            board[killerP.getX()][killerP.getY()].setNumOfKills(1);
        }
    }
    private void killTheKing(int x, int y) {
        if (x < BOARD_SIZE - 1) {
            //check that the king is surrounded by the enemy pawns from all directions
            if (board[x + 1][y] == null || board[x + 1][y].getType().equals("♙")) {
                return;
            }
        }
        if (x > 0) {
            if (board[x - 1][y] == null || board[x - 1][y].getType().equals("♙")) {
                return;
            }
        }
        if (y < BOARD_SIZE - 1) {
            if (board[x][y + 1] == null || board[x][y + 1].getType().equals("♙")) {
                return;
            }
        }
        if (y > 0) {
            if (board[x][y - 1] == null || board[x][y - 1].getType().equals("♙")) {
                return;
            }
        }
        board[x][y] = null;      //kill the king
    }

    /**
     * return the piece at a given position
     * @param position The position for which to retrieve the piece.
     * @return the piece at a given position
     */
    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.getX()][position.getY()];
    }

    /**
     * return the first player
     * @return the first player
     */
    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    /**
     * the second player
     * @return the second player
     */
    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    /**
     * check if the game is finished
     * @return true if the game is finished
     */
    @Override
    public boolean isGameFinished() {
        //check if the king is in one of the corners
        if (board[0][0] instanceof King) {
            winner = player1;
            return true;
        }
        if (board[0][BOARD_SIZE - 1] instanceof King) {
            winner = player1;
            return true;
        }
        if (board[BOARD_SIZE - 1][0] instanceof King) {
            winner = player1;
            return true;
        }
        if (board[BOARD_SIZE - 1][BOARD_SIZE - 1] instanceof King) {
            winner = player1;
            return true;
        }
        boolean isKingAlive = false;
        //check if the king is alive
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] instanceof King) {
                    isKingAlive = true;
                    break;
                }
            }
            if (isKingAlive) {
                break;
            }
        }
        if (!isKingAlive) {
            winner = player2;
        }
        return !isKingAlive;
    }

    /**
     * returns  true if this is player 2's turn
     * @return true if this is player 2's turn
     */
    @Override
    public boolean isSecondPlayerTurn() {
        return !currentTurn.isPlayerOne();
    }

    /**
     * reset the game board to the start mode
     */
    @Override
    public void reset() {
        resetFunc();
    }

    /**
     * undo the last move made on the game board
     */
    @Override
    public void undoLastMove() {
         if (actions.isEmpty())
         {
             return;
         }
        boolean end = false;
        boolean killedAPiece = false;
        while (!end) {
            if (actions.isEmpty()) {      //no moves were done
                return;
            }
            GameAction act = actions.pop();     //the last move
            if (act.getAct_type().equals("kill")) {
                killedAPiece = true;
                board[act.getPreviousX()][act.getPreviousY()] = act.getKilled();
            }
            if (act.getAct_type().equals("move")) {
                board[act.getPreviousX()][act.getPreviousY()] = board[act.getCurrentX()][act.getCurrentY()];
                board[act.getCurrentX()][act.getCurrentY()] = null;
                board[act.getPreviousX()][act.getPreviousY()].removeStep();           //remove the last step from the piece's step list
                if (act.getCurrentX() == act.getPreviousX()) {
                    board[act.getPreviousX()][act.getPreviousY()].setDist(-1 * Math.abs(act.getPreviousY() - act.getCurrentY()));
                } else {
                    board[act.getPreviousX()][act.getPreviousY()].setDist(-1 * Math.abs(act.getPreviousX() - act.getCurrentX()));
                }
                if (currentTurn.isPlayerOne()) {     //give the turn to the player that did the last move
                    currentTurn = player2;
                } else {
                    currentTurn = player1;
                }
                if (killedAPiece) {
                    board[act.getPreviousX()][act.getPreviousY()].setNumOfKills(-1);
                    killedAPiece = false;
                }
                end = true;
            }
        }
    }

    /**
     * returns the height and the width of the board
     * @return the height and the width of the board
     */
    @Override
    public int getBoardSize() {
        return BOARD_SIZE;
    }
    public static int getBoardSizeStatic() {return BOARD_SIZE;}

    private void resetFunc() {
        player1.resetPieces();
        player2.resetPieces();
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                board[x][y] = null;
                positions.add(new Position(x, y));
            }
        }
        resetPiece(3, 0, 1, player2);
        resetPiece(4, 0, 2, player2);
        resetPiece(5, 0, 3, player2);
        resetPiece(6, 0, 4, player2);
        resetPiece(7, 0, 5, player2);
        resetPiece(0, 3, 7, player2);
        resetPiece(0, 4, 9, player2);
        resetPiece(0, 5, 11, player2);
        resetPiece(0, 6, 15, player2);
        resetPiece(0, 7, 17, player2);
        resetPiece(10, 3, 8, player2);
        resetPiece(10, 4, 10, player2);
        resetPiece(10, 5, 14, player2);
        resetPiece(10, 6, 16, player2);
        resetPiece(10, 7, 18, player2);
        resetPiece(3, 10, 20, player2);
        resetPiece(4, 10, 21, player2);
        resetPiece(5, 10, 22, player2);
        resetPiece(6, 10, 23, player2);
        resetPiece(7, 10, 24, player2);
        resetPiece(5, 1, 6, player2);
        resetPiece(5, 9, 19, player2);
        resetPiece(1, 5, 12, player2);
        resetPiece(9, 5, 13, player2);

        resetPiece(5, 3, 1, player1);
        resetPiece(4, 4, 2, player1);
        resetPiece(5, 4, 3, player1);
        resetPiece(6, 4, 4, player1);
        resetPiece(3, 5, 5, player1);
        resetPiece(4, 5, 6, player1);
        resetPiece(6, 5, 8, player1);
        resetPiece(7, 5, 9, player1);
        resetPiece(4, 6, 10, player1);
        resetPiece(5, 6, 11, player1);
        resetPiece(6, 6, 12, player1);
        resetPiece(5, 7, 13, player1);

        resetPiece(5, 5, 7, player1);
        currentTurn = player2;
        winner = null;
    }

    private void resetPiece(int x, int y, int num, ConcretePlayer owner) {
        if (num == 7 && owner.isPlayerOne()) {
            board[x][y] = new King(player1, num);
        } else {
            board[x][y] = new Pawn(owner, num);
        }
        board[x][y].addStep(positions.get(BOARD_SIZE * x + y));
        owner.addPiece(board[x][y]);
    }

    private void comp_and_print() {
        player1.getList_of_pieces().sort(new ConcretePieceCompareByNumOfSteps());
        player2.getList_of_pieces().sort(new ConcretePieceCompareByNumOfSteps());
        ConcretePlayer loser = (winner.isPlayerOne() ? player2 : player1);
        comp_by_steps(winner);
        comp_by_steps(loser);
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
        ///////////////
        ArrayList<ConcretePiece> allPieces = new ArrayList<>(player1.getList_of_pieces());
        allPieces.addAll(new ArrayList<>(player2.getList_of_pieces()));
        allPieces.sort(new ConcretePieceCompareByNumOfKills());
        comp_by_kills(allPieces);
        //////////////////////
        allPieces.sort(new ConcretePieceCompareByDistance());
        comp_by_dist(allPieces);
        /////////////
        for (Position pos : positions) { //iterates on every position
            for (ConcretePiece cp : allPieces) {   //iterates on every piece
                if (cp.getSteps().contains(pos)) {  //if the position is in the steps list of the piece,
                    pos.setDiffPiecesStepped();    // increase by 1 the number of different pieces who stepped on this position
                }
            }
        }

        positions.sort(new PositionCompareByDifferentPieceStepped());
        squares_comp(positions);
    }

    private void comp_by_steps(ConcretePlayer player) {
        for (int i = 0; i < player.getList_of_pieces().size(); i++) {
            StringBuilder str;
            ConcretePiece cp = player.getList_of_pieces().get(i);
            ArrayList<Position> steps = new ArrayList<>(cp.getSteps());
            if (steps.size() > 1) {
                if (player.isPlayerOne()) {
                    if (cp.getType().equals("♔")) {
                        str = new StringBuilder("K");
                    } else {
                        str = new StringBuilder("D");
                    }
                } else {
                    str = new StringBuilder("A");
                }
                str.append(cp.getNum()).append(": [");
                for (int j = 0; j < steps.size(); j++) {
                    str.append("(").append(steps.get(j).getX()).append(", ").append(steps.get(j).getY()).append(")");
                    if (j < steps.size() - 1) {
                        str.append(", ");
                    }
                }
                str.append("]");
                System.out.println(str);
            }
        }
    }

    private void comp_by_kills(ArrayList<ConcretePiece> pieces) {
        for (ConcretePiece piece : pieces) {
            String str;
            if (piece.getNumOfKills() > 0) {
                if (piece.getOwner().equals(player1)) {
                    if (piece.getType().equals("♔")) {
                        str = "K";
                    } else {
                        str = "D";
                    }
                } else {
                    str = "A";
                }
                str += piece.getNum() + ": " + piece.getNumOfKills() + " kills";
                System.out.println(str);
            }
        }
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private void comp_by_dist(ArrayList<ConcretePiece> pieces) {
        for (int i = 0; i < pieces.size() && pieces.get(i).getDist() > 0; i++) {
            String str;
            ConcretePiece cp = pieces.get(i);
            if (cp.getOwner().equals(player1)) {
                if (cp.getType().equals("♔")) {
                    str = "K";
                } else {
                    str = "D";
                }
            } else {
                str = "A";
            }
            str += cp.getNum() + ": " + cp.getDist() + " squares";
            System.out.println(str);
        }
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private void squares_comp(ArrayList<Position> positions) {
        for (int i = 0; i < positions.size() && positions.get(i).getDiffPiecesStepped() > 1; i++) {
            String str = "(";
            Position pos = positions.get(i);
            str += pos.getX() + ", " + pos.getY() + ")" + pos.getDiffPiecesStepped() + " pieces";
            System.out.println(str);
        }
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}