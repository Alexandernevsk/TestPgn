package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.Piece;

public record Pawn(String name) implements Piece {

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void Move() {

    }
}
