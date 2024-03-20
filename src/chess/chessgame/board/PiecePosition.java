package chess.chessgame.board;

import chess.enumerations.Piece;

import java.util.Objects;

public class PiecePosition {
    public PiecePosition(Piece piece, Square square) {
        this.piece = piece;
        this.square = square;
    }

    private Piece piece;
    private Square square;

    public Piece getPiece() {
        return piece;
    }

    public Square getSquare() {
        return square;
    }


    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecePosition position = (PiecePosition) o;
        return piece == position.piece && square.equals(position.square);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, square);
    }

    @Override
    public String toString() {
        return "piece: " + piece + ", square: " + square;
    }
}
