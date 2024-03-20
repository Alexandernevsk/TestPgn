package chess.chessgame.board;

import chess.chessgame.move.Move;
import chess.enumerations.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Square, PiecePosition> board = new HashMap<>();

    public Board() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i > 2 && i < 7) {
                    putPiece(i, j, new PiecePosition(Piece.EMPTY, new Square(i, j)));
                } else {
                    if (i == 2) {
                        putPiece(i, j, new PiecePosition(Piece.WHITE_PAWN, new Square(i, j)));
                    } else if (i == 7) {
                        putPiece(i, j, new PiecePosition(Piece.BLACK_PAWN, new Square(i, j)));
                    } else {
                        switch (j) {
                            case 0, 7 -> putPiece(i, j, i == 1 ? new PiecePosition(Piece.WHITE_ROOK, new Square(i, j))
                                    : new PiecePosition(Piece.BLACK_ROOK, new Square(i, j)));
                            case 1, 6 -> putPiece(i, j, i == 1 ? new PiecePosition(Piece.WHITE_KNIGHT, new Square(i, j))
                                    : new PiecePosition(Piece.BLACK_KNIGHT, new Square(i, j)));
                            case 2, 5 -> putPiece(i, j, i == 1 ? new PiecePosition(Piece.WHITE_BISHOP, new Square(i, j))
                                    : new PiecePosition(Piece.BLACK_BISHOP, new Square(i, j)));
                            case 3 -> putPiece(i, j, i == 1 ? new PiecePosition(Piece.WHITE_QUEEN, new Square(i, j))
                                    : new PiecePosition(Piece.BLACK_QUEEN, new Square(i, j)));
                            case 4 -> putPiece(i, j, i == 1 ? new PiecePosition(Piece.WHITE_KING, new Square(i, j))
                                    : new PiecePosition(Piece.BLACK_KING, new Square(i, j)));
                        }
                    }
                }
            }
        }
    }

    public void putPiece(int rank, int file, PiecePosition piece) {
        board.put(new Square(rank, (char) (file + 'a')), piece);
    }

    public void replacePiece(int rank, char file, PiecePosition piece){

    }

    public PiecePosition empty(Square square){
        return new PiecePosition(Piece.EMPTY, square);
    }

    public Map<Square, PiecePosition> getBoard() {
        return board;
    }

    public void printBoard() {
        for (int i = 8; i >= 1; i--) {
            for (char j = 'a'; j < (8 + 'a'); j++) {
                System.out.printf("%s ", board.get(new Square(i, j)).getPiece().fen);
            }
            System.out.println();
        }
    }
}
