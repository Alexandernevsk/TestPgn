package chess.chessgame.board;

import java.util.Objects;

public record Square(int rank, char file) {

    public Square(int rank, int file) {
        this(rank, (char) (file + 'a'));
    }

    public Square(char rank, char file){
        this(Character.getNumericValue(rank), file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return rank == square.rank && file == square.file;
    }

    public static Square stringToSquare(String square) {
        return new Square(Character.getNumericValue(square.charAt(0)), square.charAt(1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return file + "" + rank;
    }
}
