package pgnExtractor;

import chess.chessgame.move.PgnMove;
import chess.enumerations.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PgnFileReader {

    private final ArrayList<PgnFileFormatter> pgnList;
    private final static String REGEX_MOVE = "([RBNQK]?[a-hx]*\\d\\+?#?|O(?:-O){1,2})";
    private final static Pattern MOVE = Pattern.compile("(?<turn>(\\d+\\.))\\s" +
            "(?<whitemove>[RBNQK]?[a-hx]*(\\d\\+?#?)|O(?:-O){1,2})*" +
            "\\s(?<blackmove>[RBNQK]?[a-hx]*\\d(\\+?#?)|O(?:-O){1,2})*"
    );
    private final static String filename = "C:\\Users\\Alexa\\Desktop\\chess\\src\\files\\karpov\\pgn\\karpovpgn\\Karpov.pgn";

    private ArrayList<PgnFileFormatter> readFile() {

        PgnFileFormatter pgn = new PgnFileFormatter();
        ArrayList<PgnFileFormatter> games = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filename)) {

            String line;
            BufferedReader reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                pgn.setLine(line);


                if (pgn.checkLineForResult()) {
                    pgn.splitEndResultFromPgn(line);
                    games.add(pgn);
                    pgn = new PgnFileFormatter();
                } else if (pgn.checkCondition()) {
                    pgn.addPlayer();
                    pgn.addOpening();
                    pgn.addPgn(line);
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return games;
    }

    public PgnFileReader() {
        this.pgnList = readFile();
    }

    public String getPgn(int index) {
        var pgn = pgnList.get(index).getPgn().toString().trim();

        // Using capturing groups to extract and format moves
        Pattern pattern = Pattern.compile(String.format("(\\d+\\.)%s\\s?%s*", REGEX_MOVE, REGEX_MOVE));
        Matcher matcher = pattern.matcher(pgn);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String moveNumber = matcher.group(1);
            String whiteMove = matcher.group(2);
            String blackMove = matcher.group(3);
            sb.append(String.format("%s %s %s\n", moveNumber, whiteMove, blackMove == null ? "" : blackMove));
        }
        return sb.toString();
    }

    public String parseMovesFromPgnGame(int gameIndex, int moveIndex, Color color) {
        var game = getPgn(gameIndex);
        Matcher matcher = MOVE.matcher(game);
        while (matcher.find()) {
            if (moveIndex <= gameLength(gameIndex)) {
                String turn = matcher.group("turn");
                int move = Integer.parseInt(turn.split("\\.")[0]);
                if (move == moveIndex) {
                    if (color.equals(Color.WHITE)) {
                        return matcher.group("whitemove");
                    } else return matcher.group("blackmove");
                }
            }
        }
        return null;
    }


    public PgnMove stringToPgnMove(int gameIndex, int moveIndex, Color color) {
        String move = parseMovesFromPgnGame(gameIndex, moveIndex, color);
        if (move != null) {
            return new PgnMove(color, move);
        } else throw new IllegalArgumentException("Move not found!");
    }

    public List<PgnMove> getTurn(int gameIndex, int moveIndex) {
        var white = stringToPgnMove(gameIndex, moveIndex, Color.WHITE);
        var black = stringToPgnMove(gameIndex, moveIndex, white.getColor().toggle());
        return List.of(white, black);
    }

    public void printTurnPgn(List<PgnMove> turn) {
        if (turn.size() == 2) {
            System.out.printf("White{%s} Black{%s}\n", turn.get(0), turn.get(1));
        }
    }

    public void printTurn(int gameIndex, int moveIndex) {
        Color color = Color.WHITE;
        String white = parseMovesFromPgnGame(gameIndex, moveIndex, color);
        String black = parseMovesFromPgnGame(gameIndex, moveIndex, color.toggle());
        System.out.printf("%d.%s %s\n", moveIndex, white, black);
    }

    public int gameLength(int gameIndex) {
        var game = getPgn(gameIndex);
        Matcher matcher = MOVE.matcher(game);
        int move = 0;
        while (matcher.find()) {
            String turn = matcher.group("turn");
            move = Integer.parseInt(turn.split("\\.")[0]);
        }
        return move;
    }

}
