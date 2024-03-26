package chessSebastiaan.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class PieceHelper {

    public static boolean isPathClear(HashMap<Point, Piece> map, Point from, Point to) {
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

    public static boolean ifPreventsCollisionOrCanCapture(ChessData data) {

        var map = data.getBoardFromChessData().getBoard();
        Point tileToMoveFrom = data.getTileToMoveFrom();
        Point target = data.getTarget();
        Player opposedPlayer = data.getOpposedPlayer();
        var moves = data.getMoves();

        if (map.get(target) != null) {
            var pieceOnTargetedSquare = map.get(target);
            var playerPieces = opposedPlayer.getPlayerPieces();
            if (playerPieces.contains(pieceOnTargetedSquare)) {
                map.replace(target, null);
                playerPieces.remove(pieceOnTargetedSquare);
                moves.add(storeCaptureMove(map, tileToMoveFrom, target));
                return true;
            } else {
                System.out.println("Illegal move!");
                return false;
            }
        } else {
            moves.add(pointToChessPosition(map, tileToMoveFrom, target));
            return true;
        }
    }

    private static String storeCaptureMove(HashMap<Point, Piece> map, Point tileToMoveFrom, Point target) {

        Piece piece = map.get(tileToMoveFrom);
        String namePiece = piece.getName();
        String letter = String.valueOf(namePiece.charAt(0));

        String to = pointToChessPosition(map, tileToMoveFrom, target);
        String tile = to.substring(1);

        return letter.toUpperCase() + "x" + tile;
    }

    private static String pointToChessPosition(HashMap<Point, Piece> map, Point tileToMoveFrom, Point point) {

        Piece piece = map.get(tileToMoveFrom);
        String namePiece = piece.getName();
        String letter = String.valueOf(namePiece.charAt(0));

        char columnLetter = (char) ('a' + point.x());
        int rowNumber = 8 - point.y();
        return letter.toUpperCase() + columnLetter + rowNumber;
    }
}
