package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class King implements Piece {

    private final String name;

    public String getName() {
        return name;
    }
    public King(String name){
        this.name = name;
    }
    @Override
    public boolean isLegalMove(ChessData data) {
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();

        if (isLegalKingMove(tileToMoveFrom, target)) {
            return PieceHelper.ifPreventsCollisionOrCanCapture(data);
        }

        System.out.println("Illegal move!");
        return false;
    }
    public static boolean isLegalKingMove(Point tileToMoveFrom, Point target) {
        int dx = Math.abs(tileToMoveFrom.x() - target.x());
        int dy = Math.abs(tileToMoveFrom.y() - target.y());
        return (dx <= 1 && dy <= 1); // King can move one square in any direction. Don't worry, king can't capture itself.
    }

}
//Program attacking squares(for check and checkmate)
