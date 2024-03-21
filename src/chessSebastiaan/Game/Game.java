package chessSebastiaan.Game;

import java.util.ArrayList;

public class Game {

    public void run(){

        ArrayList<String> moves = new ArrayList<>();
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Board board = new Board();
        board.initBoard(player1, player2);
        board.renderBoard();
        var map = board.getBoard();

        Player currentPlayer = player1;
        Player opposedPlayer = player2;

        Piece pieceSelected;
        Point target;
        Point tileToMoveFrom;

        boolean legalMove = false;

        do {
            do {
                //Choose piece and the tile to target.
                pieceSelected = currentPlayer.choosePiece();
                target = currentPlayer.makeMove(board);

                //Get the tile where the piece moves from.
                tileToMoveFrom = pieceSelected.getTileToMoveFrom(map, pieceSelected);

                //Check if move is legal.
                legalMove = pieceSelected.isLegalMove(map, pieceSelected, target, tileToMoveFrom, currentPlayer, opposedPlayer, moves);

            }while(!legalMove);
            var newMap = pieceSelected.Move(map, pieceSelected, target, tileToMoveFrom);
            board.setBoard(newMap);

            //moves with round number
//            if(currentPlayer == player1){
//                moves.add(String.valueOf(round));
//                round++;
//            }
            currentPlayer = currentPlayer == player1 ? player2 : player1;
            opposedPlayer = opposedPlayer == player1 ? player2 : player1;

            board.renderBoard();
        }
        while(true);
    }
}
