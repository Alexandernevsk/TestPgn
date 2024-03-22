package chessSebastiaan.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessData {

    private Board board;
    private Piece chosenPiece;
    private Point target;
    private Point tileToMoveFrom;
    private Player playerMakesMove;
    private Player opposedPlayer;
    private final ArrayList<String> moves = new ArrayList<>();


    public Board getBoardFromChessData(){
        return board;
    }
    public void setBoardFromChessData(Board board){
        this.board = board;
    }

    public Piece getChosenPiece(){
        return chosenPiece;
    }
    public void setChosenPiece(Piece chosenPiece){
        this.chosenPiece = chosenPiece;
    }

    public Point getTarget(){
        return target;
    }
    public void setTarget(Point target){
        this.target = target;
    }

    public Point getTileToMoveFrom(){
        return tileToMoveFrom;
    }
    public void setTileToMoveFrom(Point tileToMoveFrom){
        this.tileToMoveFrom = tileToMoveFrom;
    }

    public Player getPlayerMakesMove(){
        return playerMakesMove;
    }
    public void setPlayerMakesMove(Player playerMakesMove){
        this.playerMakesMove = playerMakesMove;
    }

    public Player getOpposedPlayer(){
        return opposedPlayer;
    }
    public void setOpposedPlayer(Player opposedPlayer){
        this.opposedPlayer = opposedPlayer;
    }

    public ArrayList<String> getMoves(){
        return moves;
    }
    //public void setMoves(ArrayList<String> moves){this.moves = moves;}

}


