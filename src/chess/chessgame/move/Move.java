package chess.chessgame.move;

import chess.chessgame.board.PiecePosition;
import chess.chessgame.board.Square;
import chess.enumerations.Color;
import chess.enumerations.PieceType;
import chess.fen.Fen;

public class Move {

    private final Fen fen = new Fen();

    public boolean canReach(PiecePosition piecePosition, Square target) {
        if (piecePosition.getPiece().type == PieceType.PAWN) {
            return legalPawnMove(piecePosition, target);
        }
        return false;
    }

    public boolean legalPawnMove(PiecePosition piecePosition, Square target) {
        switch (piecePosition.getPiece().color) {
            case BLACK -> {
                return pawnPush(piecePosition, 7, target);
            }
            case WHITE -> {
                return pawnPush(piecePosition, 2, target);
            }
            default -> {
                return false;
            }
        }
    }

    /**
     * @param piecePosition checks positioning of the piece
     * @param startingRank  checks whether en passant is possible
     * @param target        target square
     * @return is true when conditions for pawn have been met.
     */
    private boolean pawnPush(PiecePosition piecePosition, int startingRank, Square target) {
        if (piecePosition.getSquare().file() == target.file()) {
            int steps = Math.abs(piecePosition.getSquare().rank() - target.rank());

            if (steps > 2) {
                throw new IllegalArgumentException("cannot move: " + steps + " steps.");
            } else if (piecePosition.getSquare().rank() == startingRank && steps == 2) {
                String enPassantTile = target.file() + "" + (piecePosition.getPiece().color == Color.WHITE ? target.rank() - 1 : target.rank() + 1);
                fen.setEnPassant(enPassantTile);
                return true;
            } else {
                fen.setEnPassant("-");
                return Math.abs(piecePosition.getSquare().rank() - target.rank()) == 1;
            }
        }
        return false;
    }


    public Fen getFen() {
        return fen;
    }
}


