package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Rook implements Piece {

    private final String name;

    public String getName() {
        return name;
    }

    public Rook(String name) {
        this.name = name;
    }


    @Override
    public boolean isLegalMove(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();

        if (isLegalRookMove(map, tileToMoveFrom, target)) {
            return PieceHelper.ifPreventsCollisionOrCanCapture(data);
        }

        System.out.println("Illegal move!");
        return false;
    }

    private boolean isLegalRookMove(HashMap<Point, Piece> map, Point tileToMoveFrom, Point target) {
        // Check if the move is along a straight line (either horizontally or vertically)
        if (tileToMoveFrom.x() == target.x() || tileToMoveFrom.y() == target.y()) {
            // Check if the path is clear between the starting and ending points
            return PieceHelper.isPathClear(map, tileToMoveFrom, target);
        }
        return false;
    }
}