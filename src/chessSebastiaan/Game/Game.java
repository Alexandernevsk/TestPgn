package chessSebastiaan.Game;

public class Game {

    public void run(){
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Board board = new Board();
        board.initBoard(player1, player2);
        board.renderBoard();

        Player currentPlayer = player1;

        Piece pieceSelected;
        Point pointTargeted;
        do {
            pieceSelected = currentPlayer.choosePiece();
            pointTargeted = currentPlayer.makeMove(board);
        }
        while(pointTargeted == null);
        System.out.println(pieceSelected);
        System.out.println(pointTargeted);
    }
}
