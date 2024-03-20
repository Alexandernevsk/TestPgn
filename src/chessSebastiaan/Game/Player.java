package chessSebastiaan.Game;

import chessSebastiaan.Game.Pieces.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private final ArrayList<Piece> playerPieces = new ArrayList<>();

    public ArrayList<Piece> getPlayerPieces() {
        return playerPieces;
    }

    Scanner scanner = new Scanner(System.in);

    public Player(int x) {
        if (x == 1) {
            makeWhitePieces();
        } else if (x == 2) {
            makeBlackPieces();
        }
    }

    public Piece choosePiece() {

        do {
            System.out.println("What piece do you want to move?");
            String pieceToMove = scanner.nextLine();
            for (Piece piece : playerPieces) {
                if (piece.getName().equals(pieceToMove)) {
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
        playerPieces.add(new Pawn("P1"));
        playerPieces.add(new Pawn("P2"));
        playerPieces.add(new Pawn("P3"));
        playerPieces.add(new Pawn("P4"));
        playerPieces.add(new Pawn("P5"));
        playerPieces.add(new Pawn("P6"));
        playerPieces.add(new Pawn("P7"));
        playerPieces.add(new Pawn("P8"));
        playerPieces.add(new Rook("R1"));
        playerPieces.add(new Rook("R2"));
        playerPieces.add(new Knight("N1"));
        playerPieces.add(new Knight("N2"));
        playerPieces.add(new Bishop("B1"));
        playerPieces.add(new Bishop("B2"));
        playerPieces.add(new Queen("Q"));
        playerPieces.add(new King("K"));
    }

    private void makeBlackPieces() {
        playerPieces.add(new Pawn("p1"));
        playerPieces.add(new Pawn("p2"));
        playerPieces.add(new Pawn("p3"));
        playerPieces.add(new Pawn("p4"));
        playerPieces.add(new Pawn("p5"));
        playerPieces.add(new Pawn("p6"));
        playerPieces.add(new Pawn("p7"));
        playerPieces.add(new Pawn("p8"));
        playerPieces.add(new Rook("r1"));
        playerPieces.add(new Rook("r2"));
        playerPieces.add(new Knight("n1"));
        playerPieces.add(new Knight("n2"));
        playerPieces.add(new Bishop("b1"));
        playerPieces.add(new Bishop("b2"));
        playerPieces.add(new Queen("q"));
        playerPieces.add(new King("k"));
    }




}
