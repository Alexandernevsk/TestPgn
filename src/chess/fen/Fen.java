package chess.fen;


public class Fen {
    private PiecePlacement piecePlacement;
    private Character activeColor;
    private String castling;
    private String enPassant;
    private int halfMove;
    private int fullMove;

    //Standard start position
    public Fen() {
        piecePlacement = new PiecePlacement();
        activeColor = 'w';
        castling = "KQkq";
        enPassant = "-";
        halfMove = 0;
        fullMove = 1;
    }


    public PiecePlacement getPiecePlacement() {
        return piecePlacement;
    }

    public void toggleActiveColor() {
        activeColor = activeColor == 'w' ? 'b' : 'w';
    }

    public void incrementFullMove() {
        fullMove++;
    }

    public String getEnPassant() {
        return enPassant;
    }

    public void setEnPassant(String enPassantTile) {
        enPassant = enPassantTile;
    }

    public Character getActiveColor() {
        return activeColor;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %d %d", piecePlacement, activeColor, castling, enPassant, halfMove, fullMove);
    }
}
