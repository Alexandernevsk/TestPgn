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
        return true;
    }

    @Override
    public HashMap<Point, Piece> Move(ChessData data) {
        return null;
    }
}