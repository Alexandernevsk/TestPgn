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
        for (int rank = 1; rank <= 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if (rank > 2 && rank < 7) {
                    putPiece(rank, file, new PiecePosition(Piece.EMPTY, new Square(rank, file)));
                } else {
                    if (rank == 2) {
                        putPiece(rank, file, new PiecePosition(Piece.WHITE_PAWN, new Square(rank, file)));
                    } else if (rank == 7) {
                        putPiece(rank, file, new PiecePosition(Piece.BLACK_PAWN, new Square(rank, file)));
                    } else {
                        switch (file) {
                            case 0, 7 -> putPiece(rank, file, rank == 1 ? new PiecePosition(Piece.WHITE_ROOK, new Square(rank, file))
                                    : new PiecePosition(Piece.BLACK_ROOK, new Square(rank, file)));
                            case 1, 6 -> putPiece(rank, file, rank == 1 ? new PiecePosition(Piece.WHITE_KNIGHT, new Square(rank, file))
                                    : new PiecePosition(Piece.BLACK_KNIGHT, new Square(rank, file)));
                            case 2, 5 -> putPiece(rank, file, rank == 1 ? new PiecePosition(Piece.WHITE_BISHOP, new Square(rank, file))
                                    : new PiecePosition(Piece.BLACK_BISHOP, new Square(rank, file)));
                            case 3 -> putPiece(rank, file, rank == 1 ? new PiecePosition(Piece.WHITE_QUEEN, new Square(rank, file))
                                    : new PiecePosition(Piece.BLACK_QUEEN, new Square(rank, file)));
                            case 4 -> putPiece(rank, file, rank == 1 ? new PiecePosition(Piece.WHITE_KING, new Square(rank, file))
                                    : new PiecePosition(Piece.BLACK_KING, new Square(rank, file)));
                        }
                    }
                }
            }
        }
    }

    public void putPiece(int rank, int file, PiecePosition piece) {
        board.put(new Square(rank, (char) (file + 'a')), piece);
    }

    public void replacePiece(int rank, char file, PiecePosition piece) {

    }

    public PiecePosition empty(Square square) {
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
