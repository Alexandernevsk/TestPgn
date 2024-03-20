package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.Piece;

public record King(String name)  implements Piece {

    public String getName() {
        return name;
    }
    @Override
    public void Move() {

    }
}
