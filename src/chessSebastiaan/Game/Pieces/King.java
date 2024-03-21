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
    public boolean isLegalMove(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom, Player playerMakesMove, Player opposedPlayer, ArrayList<String> moves) {
        return true;
    }

    @Override
    public HashMap<Point, Piece> Move(HashMap<Point, Piece> map, Piece chosenPiece, Point target, Point tileToMoveFrom) {
        return map;
    }
}
//Program attacking squares(for check and checkmate)
