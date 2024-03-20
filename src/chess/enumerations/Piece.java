package chess.enumerations;

import java.util.HashMap;
import java.util.Map;

import static chess.enumerations.Color.BLACK;
import static chess.enumerations.Color.WHITE;
import static chess.enumerations.PieceType.*;

public enum Piece {
  EMPTY('.', null, null),

  WHITE_PAWN('P', PAWN, WHITE),
  WHITE_KNIGHT('N', KNIGHT, WHITE),
  WHITE_BISHOP('B', BISHOP, WHITE),
  WHITE_ROOK('R', ROOK, WHITE),
  WHITE_QUEEN('Q', QUEEN, WHITE),
  WHITE_KING('K', KING, WHITE),

  BLACK_PAWN('p', PAWN, BLACK),
  BLACK_KNIGHT('n', KNIGHT, BLACK),
  BLACK_BISHOP('b', BISHOP, BLACK),
  BLACK_ROOK('r', ROOK, BLACK),
  BLACK_QUEEN('q', QUEEN, BLACK),
  BLACK_KING('k', KING, BLACK);

  public final char fen;
  public final PieceType type;
  public final Color color;

  public static final Map<Character, Piece> char_to_piece = new HashMap<>();

  static {

    // White Pieces
    char_to_piece.putAll(Map.of(
            WHITE_PAWN.fen, WHITE_PAWN,
            WHITE_KNIGHT.fen, WHITE_KNIGHT,
            WHITE_BISHOP.fen, WHITE_BISHOP,
            WHITE_ROOK.fen, WHITE_ROOK,
            WHITE_QUEEN.fen, WHITE_QUEEN,
            WHITE_KING.fen, WHITE_KING
    ));

    // Black Pieces
    char_to_piece.putAll(Map.of(

            BLACK_PAWN.fen, BLACK_PAWN,
            BLACK_KNIGHT.fen, BLACK_KNIGHT,
            BLACK_BISHOP.fen, BLACK_BISHOP,
            BLACK_ROOK.fen, BLACK_ROOK,
            BLACK_QUEEN.fen, BLACK_QUEEN,
            BLACK_KING.fen, BLACK_KING
    ));

  }

  Piece(char FEN_PIECE_NOTATION, PieceType type, Color color) {
    this.fen = FEN_PIECE_NOTATION;
    this.type = type;
    this.color = color;
  }



  public static Piece getPiece(char c) {
    return char_to_piece.get(c);
  }

  public static Piece getPiece(String c) {
    if (c.length() == 1) {
      return getPiece(c.charAt(0));
    }
    return null;
  }

}
