package chessSebastiaan.Game;

import java.util.ArrayList;

public class Game {

    public void run(){

        //Write only object?
        ChessData data = new ChessData();

        data.setPlayerMakesMove(new Player(1));
        data.setOpposedPlayer(new Player(2));
        Player currentPlayer = data.getPlayerMakesMove();
        Player opposedPlayer = data.getOpposedPlayer();

        data.setBoardFromChessData(new Board());
        data.getBoardFromChessData().initBoard(currentPlayer, opposedPlayer);
        data.getBoardFromChessData().renderBoard();
        var board = data.getBoardFromChessData();
        var map = board.getBoard();

        Piece pieceSelected;
        Point target;
        Point tileToMoveFrom;

        boolean legalMove = false;

        do {
            do {
                do {
                    //Choose piece and the tile to target.
                    pieceSelected = currentPlayer.choosePiece();
                    data.setChosenPiece(pieceSelected);

                    target = currentPlayer.makeMove(board);
                    data.setTarget(target);
                }while(target == null);

                //Get the tile where the piece moves from.
                tileToMoveFrom = pieceSelected.getTileToMoveFrom(map, pieceSelected);
                data.setTileToMoveFrom(tileToMoveFrom);

                //Check if move is legal.
                legalMove = pieceSelected.isLegalMove(data);

            }while(!legalMove);

            var newMap = pieceSelected.Move(data);
            board.setBoard(newMap);

            //Swap turn
            if(currentPlayer == data.getPlayerMakesMove()){
                data.setPlayerMakesMove(opposedPlayer);
                data.setOpposedPlayer(currentPlayer);
                var swap = currentPlayer;
                 currentPlayer = opposedPlayer;
                 opposedPlayer = swap;
            }
            else{
                data.setPlayerMakesMove(currentPlayer);
                data.setOpposedPlayer(opposedPlayer);
                var swap = currentPlayer;
                currentPlayer = opposedPlayer;
                opposedPlayer = swap;
            }

            board.renderBoard();

            //Print move
            for(String move : data.getMoves()){
                System.out.print(move + " ");
            }
            System.out.println();
        }
        while(true);
    }
}
