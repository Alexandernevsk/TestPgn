package chessSebastiaan.Game;

import java.util.ArrayList;
import java.util.Optional;

public class ChessData {

    private Board board;

    private Square square;
    private Optional<Piece> chosenPiece;
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

    public Square getSquare(){
        return square;
    }
    public void setSquare(Square square){
        this.square= square;
    }

    public Optional<Piece> getChosenPiece(){
        return chosenPiece;
    }
    public void setChosenPiece(Optional<Piece> chosenPiece){
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


