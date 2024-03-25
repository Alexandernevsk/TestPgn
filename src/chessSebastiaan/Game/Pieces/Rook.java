package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Rook implements Piece {

    private final String name;

    public String getName() {
        return name;
    }
    public Rook(String name){
        this.name = name;
    }


    @Override
    public boolean isLegalMove(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();
        Player opposedPlayer = data.getOpposedPlayer();
        var moves = data.getMoves();

        if (isLegalRookMove(map, tileToMoveFrom, target)) {
            return ifPreventsCollisionOrCanCapture(map, target, opposedPlayer, moves);
        }

        System.out.println("Illegal move!");
        return false;
    }

    private boolean isLegalRookMove(HashMap<Point, Piece> map, Point tileToMoveFrom, Point target) {
        // Check if the move is along a straight line (either horizontally or vertically)
        if (tileToMoveFrom.x() == target.x() || tileToMoveFrom.y() == target.y()) {
            // Check if the path is clear between the starting and ending points
            return isPathClear(map, tileToMoveFrom, target);
        }
        return false;
    }

    // Helper method to check if the path between two points is clear
    private boolean isPathClear(HashMap<Point, Piece> map, Point from, Point to) {
        int dx = Integer.compare(to.x(), from.x());
        int dy = Integer.compare(to.y(), from.y());

        // Determine the direction of movement
        int x = from.x() + dx;
        int y = from.y() + dy;

        // Iterate over the tiles between 'from' and 'to'
        while (x != to.x() || y != to.y()) {
            Point crossTile = new Point(x, y);
            if (map.get(crossTile) != null) {
                return false; // Path is blocked by a piece
            }
            x += dx;
            y += dy;
        }
        return true; // Path is clear
    }

    private void storeCaptureMove(Point target, ArrayList<String> moves) {
        String to = pointToChessPosition(target);
        String tile = to.substring(1);
        moves.add("Rx" + tile);
    }
    private boolean ifPreventsCollisionOrCanCapture(HashMap<Point, Piece> map, Point target, Player opposedPlayer, ArrayList<String> moves) {
        if (map.get(target) != null) {
            var pieceOnTargetedSquare = map.get(target);
            var playerPieces = opposedPlayer.getPlayerPieces();
            if (playerPieces.contains(pieceOnTargetedSquare)) {
                map.replace(target, null);
                playerPieces.remove(pieceOnTargetedSquare);
                storeCaptureMove(target, moves);
                return true;
            }
            else{
                System.out.println("Illegal move!");
                return false;
            }
        }
        else{
            moves.add(pointToChessPosition(target));
            return true;
        }
    }
        private String pointToChessPosition (Point point){
            char columnLetter = (char) ('a' + point.x());
            int rowNumber = 8 - point.y();
            return "R" + columnLetter + rowNumber;
        }

    @Override
    public HashMap<Point, Piece> Move(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();;
        map.replace(data.getTarget(), data.getChosenPiece()); //Places piece on target square
        map.replace(data.getTileToMoveFrom(), null); //Removes piece from initial tile
        return map;
    }
}