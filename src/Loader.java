import chess.enumerations.Color;
import pgnExtractor.PgnFileReader;


public class Loader {

    public static void main(String[] args) {
        PgnFileReader pgnFileReader = new PgnFileReader();
        for (int i = 1; i < 40; i++) {
            pgnFileReader.printTurn(0, i);
            pgnFileReader.printTurnPgn(pgnFileReader.getTurn(0, i));


            var whiteMove = pgnFileReader.stringToPgnMove(0, i, Color.WHITE);
            var blackMove = pgnFileReader.stringToPgnMove(0, i, Color.BLACK);
            System.out.println(!whiteMove.isCastled() ? whiteMove.convertToPiecePosition() : "castled");
            System.out.println(!blackMove.isCastled() ? blackMove.convertToPiecePosition() : "castled");
        }
    }
}

