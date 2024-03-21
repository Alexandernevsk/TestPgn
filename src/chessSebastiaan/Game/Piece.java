package chessSebastiaan.Game;

import java.util.ArrayList;
import java.util.HashMap;

public interface Piece {
    String getName();

    default Point getTileToMoveFrom(HashMap<Point, Piece> map, Piece chosenPiece) {
        for (Point tile : map.keySet()) {
            Piece piece = map.get(tile);
            if (piece != null) {
                if (piece.equals(chosenPiece)) {
                    return tile;
                }
            }
        }
        return null;
    }
    boolean isLegalMove(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom, Player playerMakesMove, Player opposedPlayer, ArrayList<String> moves);

    HashMap<Point, Piece> Move(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom);
}
