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
        ArrayList<Piece> whitePieces = player1.getPlayerPieces();

        //Make squares with white pieces

        board.put(new Point(0, 7), whitePieces.get(8));
        board.put(new Point(1, 7), whitePieces.get(10));
        board.put(new Point(2, 7), whitePieces.get(12));
        board.put(new Point(3, 7), whitePieces.get(14));
        board.put(new Point(4, 7), whitePieces.get(15));
        board.put(new Point(5, 7), whitePieces.get(13));
        board.put(new Point(6, 7), whitePieces.get(11));
        board.put(new Point(7, 7), whitePieces.get(9));
        board.put(new Point(0, 6), whitePieces.get(0));
        board.put(new Point(1, 6), whitePieces.get(1));
        board.put(new Point(2, 6), whitePieces.get(2));
        board.put(new Point(3, 6), whitePieces.get(3));
        board.put(new Point(4, 6), whitePieces.get(4));
        board.put(new Point(5, 6), whitePieces.get(5));
        board.put(new Point(6, 6), whitePieces.get(6));
        board.put(new Point(7, 6), whitePieces.get(7));

        //Make empty squares

        for(int rank = 2; rank <= 5; rank++){
            for(int file = 0; file < 8; file++) {
                board.put(new Point(file, rank), null);
            }
        }

        ArrayList<Piece> blackPieces = player2.getPlayerPieces();

        //Make squares with black pieces

        board.put(new Point(0, 0), blackPieces.get(8));
        board.put(new Point(1, 0), blackPieces.get(10));
        board.put(new Point(2, 0), blackPieces.get(12));
        board.put(new Point(3, 0), blackPieces.get(14));
        board.put(new Point(4, 0), blackPieces.get(15));
        board.put(new Point(5, 0), blackPieces.get(13));
        board.put(new Point(6, 0), blackPieces.get(11));
        board.put(new Point(7, 0), blackPieces.get(9));
        board.put(new Point(0, 1), blackPieces.get(0));
        board.put(new Point(1, 1), blackPieces.get(1));
        board.put(new Point(2, 1), blackPieces.get(2));
        board.put(new Point(3, 1), blackPieces.get(3));
        board.put(new Point(4, 1), blackPieces.get(4));
        board.put(new Point(5, 1), blackPieces.get(5));
        board.put(new Point(6, 1), blackPieces.get(6));
        board.put(new Point(7, 1), blackPieces.get(7));
    }

    public void renderBoard(){
        for (int rank = 0; rank < 8; rank++) {
            System.out.print("|");
            for (int file = 0; file < 8; file++) {
                Point point = new Point(file, rank);
                Piece piece = board.get(point);
                if (piece != null) {
                    System.out.print(piece.getName()); // Assuming Piece has a meaningful toString() method
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

