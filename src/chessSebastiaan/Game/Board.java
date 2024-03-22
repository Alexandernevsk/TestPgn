package chessSebastiaan.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private HashMap<Point, Piece> board = new HashMap<Point, Piece>();

    public HashMap<Point, Piece> getBoard(){
        return board;
    }
    public void setBoard(HashMap<Point, Piece> board){
        this.board = board;
    }

    public void initBoard(Player player1, Player player2) {

        //Make squares with white pieces
        ArrayList<Piece> whitePieces = player1.getPlayerPieces();
        placePiecesOnBoard(7, whitePieces, 6);

        //Make empty squares
        for(int rank = 2; rank <= 5; rank++){
            for(int file = 0; file < 8; file++) {
                board.put(new Point(file, rank), null);
            }
        }
        //Make squares with black pieces
        ArrayList<Piece> blackPieces = player2.getPlayerPieces();
        placePiecesOnBoard(0, blackPieces, 1);
    }

    private void placePiecesOnBoard(int y, ArrayList<Piece> pieces, int y1) {
        board.put(new Point(0, y), pieces.get(8));
        board.put(new Point(1, y), pieces.get(10));
        board.put(new Point(2, y), pieces.get(12));
        board.put(new Point(3, y), pieces.get(14));
        board.put(new Point(4, y), pieces.get(15));
        board.put(new Point(5, y), pieces.get(13));
        board.put(new Point(6, y), pieces.get(11));
        board.put(new Point(7, y), pieces.get(9));
        board.put(new Point(0, y1), pieces.get(0));
        board.put(new Point(1, y1), pieces.get(1));
        board.put(new Point(2, y1), pieces.get(2));
        board.put(new Point(3, y1), pieces.get(3));
        board.put(new Point(4, y1), pieces.get(4));
        board.put(new Point(5, y1), pieces.get(5));
        board.put(new Point(6, y1), pieces.get(6));
        board.put(new Point(7, y1), pieces.get(7));
    }

    public void renderBoard(){
        for (int rank = 0; rank < 8; rank++) {
            System.out.print("|");
            for (int file = 0; file < 8; file++) {
                Point point = new Point(file, rank);
                Piece piece = board.get(point);
                if (piece != null) {
                    System.out.print(piece.getName());
                    if(piece.getName().length() == 2){
                        System.out.print("|");
                    }
                    else{
                        System.out.print(" |");
                    }
                } else {
                    System.out.print("  |"); // Placeholder for empty square
                }
            }
            System.out.println(); // Move to the next line after printing each rank
            System.out.println("|--|--|--|--|--|--|--|--|");
        }
    }
    }

