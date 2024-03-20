package chess.fen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PiecePlacement {


    //All ranks of the piece placement part of the fen-notation
    //piece-placement for standard start : rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    //Later on I want to implement 960 rules or for custom positions
    private StringBuilder rank8;
    private StringBuilder rank7;
    private StringBuilder rank6;
    private StringBuilder rank5;
    private StringBuilder rank4;
    private StringBuilder rank3;
    private StringBuilder rank2;
    private StringBuilder rank1;

    public Map<Integer, StringBuilder> getMapOfRanks() {

        return Map.of(
                1, rank1,
                2, rank2,
                3, rank3,
                4, rank4,
                5, rank5,
                6, rank6,
                7, rank7,
                8, rank8
        );
    }


    //Start position for standard game of chess
    public PiecePlacement() {
        //Black pieces (With lower case)
        rank8 = new StringBuilder("rnbqkbnr");
        rank7 = new StringBuilder("pppppppp");

        //Empty ranks
        rank6 = rank5 = rank4 = rank3 = new StringBuilder("8");

        //White pieces
        rank2 = new StringBuilder("PPPPPPPP");
        rank1 = new StringBuilder("RNBQKBNR");
    }

    public List<Character> rankToList(int rankIndex) {
        char[] rank = getMapOfRanks().get(rankIndex).toString().toCharArray();
        List<Character> rankList = new ArrayList<>();
        for (char tile : rank) {
            if (Character.isDigit(tile)) {
                int number = Character.getNumericValue(tile);
                for (int i = 0; i < number; i++) {
                    rankList.add('-');
                }
            } else rankList.add(tile);
        }

        return rankList;
    }


    public void changeRank(int rankIndex, int index, char replace) {
        if (getMapOfRanks().get(rankIndex).length() < 8) {
            var rank = getMapOfRanks().get(rankIndex);
        }
        getMapOfRanks().get(rankIndex).setCharAt(index, replace);
    }


    private char[] rankToCharArray(StringBuilder stringBuilder) {
        char[] rank = new char[8];

        if (stringBuilder.length() < 8) {
            for (int i = 0; i < stringBuilder.length(); i++) {
                if (Character.isDigit(stringBuilder.charAt(i))) {
                    int number = Character.getNumericValue(stringBuilder.charAt(i));
                    for (int j = 0; j < number; j++) {
                        rank[j] = '-';
                    }
                }
            }
        }

        return rank;
    }

    public String printRank(StringBuilder rank) {
        int amountOfEmptyTiles = 0;
        StringBuilder sb = new StringBuilder();

        for (char c : rank.toString().toCharArray()) {

            if (c == '-') {
                amountOfEmptyTiles++;
            }

            if (c != '-') {
                if (amountOfEmptyTiles != 0) {
                    sb.append(amountOfEmptyTiles);
                }
                amountOfEmptyTiles = 0;
                sb.append(c);
            }
        }

        if (amountOfEmptyTiles != 0) {
            sb.append(amountOfEmptyTiles);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return printRank(rank8) +
                '\\' + printRank(rank7) +
                '\\' + printRank(rank6) +
                '\\' + printRank(rank5) +
                '\\' + printRank(rank4) +
                '\\' + printRank(rank3) +
                '\\' + printRank(rank2) +
                '\\' + printRank(rank1);
    }
}
