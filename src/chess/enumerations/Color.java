package chess.enumerations;

import chess.chessgame.board.Board;
import chess.chessgame.board.PiecePosition;

import java.util.ArrayList;
import java.util.List;

public enum Color {
    WHITE, BLACK;

    public Color toggle() {
        return WHITE.equals(this) ?
                Color.BLACK : Color.WHITE;
    }

    public static List<PiecePosition> getPieces(Board board, Color color, PieceType pieceType) {
        List<PiecePosition> pieces = new ArrayList<>();
        for (var piece : board.getBoard().values()) {
            if (piece.getPiece().color == color && piece.getPiece().type == pieceType) {
                pieces.add(piece);
            }
        }
        return pieces;
    }
}
