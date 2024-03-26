package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Knight implements Piece {

    private final String name;

    public String getName() {
        return name;
    }
    public Knight(String name){
        this.name = name;
    }
    @Override
    public boolean isLegalMove(ChessData data) {
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();

        if (isLegalKnightMove(tileToMoveFrom, target)) {
            return PieceHelper.ifPreventsCollisionOrCanCapture(data);
        }

        System.out.println("Illegal move!");
        return false;
    }
    public static boolean isLegalKnightMove(Point tileToMoveFrom, Point target) {
        int dx = Math.abs(tileToMoveFrom.x() - target.x());
        int dy = Math.abs(tileToMoveFrom.y() - target.y());
        return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
    }
}
