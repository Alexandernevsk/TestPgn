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
    boolean isLegalMove(ChessData data);

    default HashMap<Point, Piece> Move(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();;
        map.replace(data.getTarget(), data.getChosenPiece()); //Places piece on target square
        map.replace(data.getTileToMoveFrom(), null); //Removes piece from initial tile
        return map;
    }
}
