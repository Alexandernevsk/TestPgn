package chess.enumerations;

public enum PieceType {
    PAWN('P'), ROOK('R'), KNIGHT('N'), BISHOP('B'), QUEEN('Q'), KING('K');

    public final char pgnNotation;

    PieceType(char fenNotation) {
        this.pgnNotation = fenNotation;
    }

    public static Piece typeToPiece(PieceType type, Color color) {

        if (color != null) {
            return color == Color.WHITE ? Piece.getPiece(type.pgnNotation) : Piece.getPiece(Character.toLowerCase(type.pgnNotation));
        }

        return null;
    }
}
