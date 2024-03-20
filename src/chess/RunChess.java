package chess;


import chess.chessgame.board.Board;
import chess.chessgame.board.PiecePosition;
import chess.chessgame.board.Square;
import chess.chessgame.move.Move;
import chess.enumerations.Color;
import chess.enumerations.Piece;
import chess.enumerations.PieceType;

public class RunChess {

    public static void main(String[] args) {
        Move move = new Move();
        var board = new Board();
        var list = Color.getPieces(board, Color.WHITE, PieceType.PAWN);
        System.out.println(move.getFen().getEnPassant());
        board.printBoard();
        var possibleMoves = list.stream().filter(piece -> move.canReach(piece, new Square(4, 'e'))).toList();
        System.out.println(possibleMoves);
        board.getBoard().replace(possibleMoves.get(0).getSquare(), new PiecePosition(Piece.EMPTY,possibleMoves.get(0).getSquare()));
        board.getBoard().replace(new Square(4, 'e'), possibleMoves.get(0));
        System.out.println(move.getFen().getEnPassant());
        board.printBoard();
    }

}

