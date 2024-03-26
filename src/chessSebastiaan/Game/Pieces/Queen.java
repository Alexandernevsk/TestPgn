package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Queen implements Piece {

    private final String name;

    public String getName() {
        return name;
    }
    public Queen(String name){
        this.name = name;
    }
    @Override
    public boolean isLegalMove(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();

        if (isLegalQueenMove(map, tileToMoveFrom, target)) {
            return PieceHelper.ifPreventsCollisionOrCanCapture(data);
        }

        System.out.println("Illegal move!");
        return false;
    }

    private boolean isLegalQueenMove(HashMap<Point, Piece> map, Point tileToMoveFrom, Point target) {
        // Check if the move is along a straight line (either horizontally, vertically, or diagonally)
        if (isStraightLineMove(tileToMoveFrom, target) || isDiagonalMove(tileToMoveFrom, target)) {
            // Check if the path is clear between the starting and ending points
            return PieceHelper.isPathClear(map, tileToMoveFrom, target);
        }
        return false;
    }

    // Helper method to check if the move is along a straight line (either horizontally or vertically)
    private boolean isStraightLineMove(Point from, Point to) {
        return from.x() == to.x() || from.y() == to.y();
    }

    // Helper method to check if the move is along a diagonal line
    private boolean isDiagonalMove(Point from, Point to) {
        return Math.abs(from.x() - to.x()) == Math.abs(from.y() - to.y());
    }
}