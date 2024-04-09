package chessSebastiaan.Game;

import chessSebastiaan.Game.Pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class Player {

    private final ArrayList<Optional<Piece>> playerPieces = new ArrayList<>();
    private boolean isWhite;
    private HashMap<String, Integer> promotionNumbers = new HashMap<>();;

    public ArrayList<Optional<Piece>>  getPlayerPieces() {
        return playerPieces;
    }

    public boolean getIsWhite(){
        return isWhite;
    }

    public HashMap<String, Integer> getPromotionNumbers(){
        return promotionNumbers;
    }
//    public void setPromotionNumbers(HashMap<String, Integer> promotionNumbers){
//        this.promotionNumbers = promotionNumbers;
//    }

    Scanner scanner = new Scanner(System.in);

    public Player(int x) {
        if (x == 1) {
            isWhite = true;
            makeWhitePieces();
        } else if (x == 2) {
            isWhite = false;
            makeBlackPieces();
        }
        promotionNumbers.put("R", 3);
        promotionNumbers.put("K", 3);
        promotionNumbers.put("B", 3);
        promotionNumbers.put("Q", 2);

    }

    public Optional<Piece>  choosePiece() {

        do {
            System.out.println("What piece do you want to move?");
            String pieceToMove = scanner.nextLine();
            for (Optional<Piece>  piece : playerPieces) {
                if(piece.isPresent())
                    if (piece.get().getName().equals(pieceToMove)) {
                         return piece;
                }
            }
            System.out.println("Not a legal piece.");

        } while (true);
    }

    public Point makeMove(Board board) {

        System.out.println("Where? Type rank letter and tile number");
        String pieceToTarget = scanner.nextLine();
        Point targetPoint = checkLegalTarget(pieceToTarget, board);
        if (targetPoint != null) {
            return targetPoint;
        } else {
            System.out.println("This tile doesn't exist!");
        }
        return null;
    }



    private Point checkLegalTarget(String pieceToTarget, Board board) {
        var map = board.getBoard();

        Point pointToCheck = convertChessCoordinatesToIndices(pieceToTarget);

        if(pointToCheck == null){
            return null;
        }

        for(Point point : map.keySet())
            if(point.equals(pointToCheck)){
                return point;
        }

        return null;
    }

    private Point convertChessCoordinatesToIndices(String coordinates) {
        if (coordinates.length() != 2) {
            return null;
        }

        char rankChar = coordinates.charAt(0); //rank = letter (horizontal)
        char fileChar = coordinates.charAt(1); //file = digit (vertical)

        int rankIndex = rankChar - 'a';
        int fileIndex = '8' - fileChar;


        // Check if the coordinates are valid
        if (fileIndex < 0 || fileIndex > 7 || rankIndex < 0 || rankIndex > 7) {
            return null;
        }

        return new Point(rankIndex, fileIndex);
    }



    private void makeWhitePieces() {
        playerPieces.add(Optional.of(new Pawn("P1")));
        playerPieces.add(Optional.of(new Pawn("P2")));
        playerPieces.add(Optional.of(new Pawn("P3")));
        playerPieces.add(Optional.of(new Pawn("P4")));
        playerPieces.add(Optional.of(new Pawn("P5")));
        playerPieces.add(Optional.of(new Pawn("P6")));
        playerPieces.add(Optional.of(new Pawn("P7")));
        playerPieces.add(Optional.of(new Pawn("P8")));
        playerPieces.add(Optional.of(new Rook("R1")));
        playerPieces.add(Optional.of(new Rook("R2")));
        playerPieces.add(Optional.of(new Knight("N1")));
        playerPieces.add(Optional.of(new Knight("N2")));
        playerPieces.add(Optional.of(new Bishop("B1")));
        playerPieces.add(Optional.of(new Bishop("B2")));
        playerPieces.add(Optional.of(new Queen("Q")));
        playerPieces.add(Optional.of(new King("K")));
    }

    private void makeBlackPieces() {
        playerPieces.add(Optional.of(new Pawn("p1")));
        playerPieces.add(Optional.of(new Pawn("p2")));
        playerPieces.add(Optional.of(new Pawn("p3")));
        playerPieces.add(Optional.of(new Pawn("p4")));
        playerPieces.add(Optional.of(new Pawn("p5")));
        playerPieces.add(Optional.of(new Pawn("p6")));
        playerPieces.add(Optional.of(new Pawn("p7")));
        playerPieces.add(Optional.of(new Pawn("p8")));
        playerPieces.add(Optional.of(new Rook("r1")));
        playerPieces.add(Optional.of(new Rook("r2")));
        playerPieces.add(Optional.of(new Knight("n1")));
        playerPieces.add(Optional.of(new Knight("n2")));
        playerPieces.add(Optional.of(new Bishop("b1")));
        playerPieces.add(Optional.of(new Bishop("b2")));
        playerPieces.add(Optional.of(new Queen("q")));
        playerPieces.add(Optional.of(new King("k")));
    }




}
