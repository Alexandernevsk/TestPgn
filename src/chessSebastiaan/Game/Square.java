package chessSebastiaan.Game;

import java.util.Optional;

public class Square {

    private Optional<Piece> piece;

    private boolean isAttackedByWhite = false;
    private boolean isAttackedByBlack = false;

    public Optional<Piece> getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece == null ? Optional.empty() : Optional.of(piece);
    }


    public boolean getIsAttackedByWhite(){
        return isAttackedByWhite;
    }
    public void setIsAttackedByWhite(boolean isAttackedByWhite){
        this.isAttackedByWhite = isAttackedByWhite;
    }

    public boolean getIsAttackedByBlack(){
        return isAttackedByBlack;
    }
    public void setIsAttackedByBlack(boolean isAttackedByBlack){
        this.isAttackedByBlack = isAttackedByBlack;
    }

    public Square(Optional<Piece> piece, boolean isAttackedByWhite, boolean isAttackedByBlack){
        this.piece = piece;
        this.isAttackedByWhite = isAttackedByWhite;
        this.isAttackedByBlack = isAttackedByBlack;
    }
}
