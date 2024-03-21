package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Pawn implements Piece {

    private final String name;

    @Override
    public String getName() {
        return name;
    }
    public boolean isEnPassantPawn = false;

    public Pawn(String name){
        this.name = name;
    }


// TODO: PassingPieceIterator(See Bishop/Rook/Queen);
    // TODO: Promotion
    // TODO: Print move
    @Override
    public boolean isLegalMove(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom, Player playerMakesMove, Player opposedPlayer, ArrayList<String> moves) {
        boolean notLegal = false;

        clearEnPassant(playerMakesMove);

        if(playerMakesMove.getIsWhite()){
            //For white
            if(target.x() == tileToMoveFrom.x() && target.y() == tileToMoveFrom.y() - 1){
                return ifPreventsCollision(map, target, moves);
                //Move pawn 1 tile forward

            }
            else if(target.x() == tileToMoveFrom.x() && target.y() == tileToMoveFrom.y() - 2 && tileToMoveFrom.y() == 6){
                isEnPassantPawn = true;
                return ifPreventsCollision(map, target, moves);
                //Move pawn 2 tiles forward

            }
            else if((target.x() == tileToMoveFrom.x() - 1 && target.y() == tileToMoveFrom.y() - 1)
            || (target.x() == tileToMoveFrom.x() + 1 && target.y() == tileToMoveFrom.y() - 1)){

                Point adjacentPawnPoint = new Point(target.x(), target.y() + 1);

                Piece possiblePawn = map.get(adjacentPawnPoint);
                if(possiblePawn != null){
                    if(possiblePawn instanceof Pawn pawn){
                        if(pawn.isEnPassantPawn){
                            return canCapture(map, adjacentPawnPoint, opposedPlayer, moves);
                            //Capture enemies' pawn by en passant
                        }
                    }
                }
                return canCapture(map, target, opposedPlayer, moves);
                //Capture enemies' piece
            }
            else{
                System.out.println("Illegal move!");
                return notLegal;
            }
        }
        else
        { //For black
            if(target.x() == tileToMoveFrom.x() && target.y() == tileToMoveFrom.y() + 1){
                return ifPreventsCollision(map, target, moves);
            }
            else if(target.x() == tileToMoveFrom.x() && target.y() == tileToMoveFrom.y() + 2 && tileToMoveFrom.y() == 1){
                isEnPassantPawn = true;
                return ifPreventsCollision(map, target, moves);
            }
            else if((target.x() == tileToMoveFrom.x() - 1 && target.y() == tileToMoveFrom.y() + 1)
                    || (target.x() == tileToMoveFrom.x() + 1 && target.y() == tileToMoveFrom.y() + 1)) {

                Point adjacentPawnPoint = new Point(target.x(), target.y() - 1);

                Piece possiblePawn = map.get(adjacentPawnPoint);
                if(possiblePawn != null){
                    if(possiblePawn instanceof Pawn pawn){
                        if(pawn.isEnPassantPawn){
                            return canCapture(map, adjacentPawnPoint, opposedPlayer, moves);
                            //Capture enemies' pawn by en passant
                        }
                    }
                }
                return canCapture(map, target, opposedPlayer, moves);
                //Capture enemies' piece
            }
            else{
                System.out.println("Illegal move!");
                return notLegal;
            }
        }
    }

    private static void clearEnPassant(Player opposedPlayer) {
        ArrayList<Piece> pieces = opposedPlayer.getPlayerPieces();
        pieces.forEach((p) -> {
            if(p instanceof Pawn){
                ((Pawn) p).isEnPassantPawn = false;
            }
        });
    }

    private static boolean canCapture(HashMap<Point, Piece> map, Point target, Player opposedPlayer, ArrayList<String> moves) {
        var pieceOnTargetedSquare = map.get(target);
        var playerPieces = opposedPlayer.getPlayerPieces();
        if(playerPieces.contains(pieceOnTargetedSquare)){
            map.replace(target, null);
            playerPieces.remove(pieceOnTargetedSquare);
            //moves.add()
            return true;
        }
        else{
            System.out.println("Illegal move!");
            return false;
        }
    }

    @Override
    public HashMap<Point, Piece> Move(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom) {
        map.replace(target, chosenPiece);
        map.replace(tileToMoveFrom, null);
        return map;
    }

    private static boolean ifPreventsCollision(HashMap<Point, Piece> map, Point target, ArrayList<String> moves){
        if(map.get(target) == null){ //Check if targeted tile is empty (no piece on it)
            moves.add(pointToChessPosition(target));
            return true;
        }

        else{
            System.out.println("Illegal move!");
            return false;
        }
    }

    private static String pointToChessPosition(Point point) {
        char columnLetter = (char) ('a' + point.x());
        int rowNumber = point.y() + 1;
        return String.valueOf(columnLetter) + rowNumber;
    }
}

    // 1. Pawn must be on same file while moving.
    // 2. Can move only 1 forward, except at begin, where you can move 1 or 2 tiles
    // (2 tiles mean en passant chance for opposed player, but that for later)
    // 3. Cannot hit or go through your own pieces (can be used as common piece behaviour)
    // 4. When end of board is reached, either by capturing or moving, pawn is promoted. Choose which promotion. Piece gets unique name.


// After execution: Print the done move (complicated, maybe own class?)
// Modify and render the board again.
