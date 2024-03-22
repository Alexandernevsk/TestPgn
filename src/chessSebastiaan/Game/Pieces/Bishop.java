package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Bishop implements Piece {

    private final String name;

    public String getName() {
        return name;
    }

    public Bishop(String name){
        this.name = name;
    }

    @Override
    public boolean isLegalMove(ChessData data) {
        return false;
    }

    @Override
    public HashMap<Point, Piece> Move(ChessData data) {
        return null;
    }


}
