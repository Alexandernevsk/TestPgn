package pgnExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PgnFileFormatter {
    private String line;

    //Format Fields
    private Player whitePlayer;
    private Player blackPlayer;
    private String opening;
    private final StringBuilder pgn;

    private String result;

    public void setLine(String line) {
        this.line = line;
    }

    public PgnFileFormatter() {
        this.line = "";
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.pgn = new StringBuilder();
        this.opening = "";
        this.result = "";
    }

    public boolean checkLineForResult() {
        return (line.contains("1-0") || line.contains("0-1") || line.contains("1/2-1/2")) && checkForBracketedLine();
    }

    public boolean checkCondition() {
        return checkForBracketedLine() || containsWhiteOrBlack() || containECO();
    }

    public boolean containECO() {
        return line.contains("ECO");
    }

    //returns true if no line brackets {} are found in a line
    public boolean checkForBracketedLine() {
        String regex = "^\\[.*]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return !matcher.matches();
    }

    public boolean containsWhiteOrBlack() {
        return (line.contains("White") || line.contains("Black")) && !line.contains("Elo");
    }

    public void addPlayer() {
        if (!line.contains("Elo")) {
            if (line.contains("Black")) {
                blackPlayer = new Player();
                splitFullName(filterName("Black"), blackPlayer);
            }
            if (line.contains("White")) {
                whitePlayer = new Player();
                splitFullName(filterName("White"), whitePlayer);

            }
        }
    }


    public void splitFullName(String name, Player player) {
        var firstAndLast = name.split(",");
        if (firstAndLast.length <= 1) {
            firstAndLast[0] = firstAndLast[0].trim();
            player.setLastName(firstAndLast[0]);
        } else {
            player.setFirstName(firstAndLast[1]);
            player.setLastName(firstAndLast[0]);
        }
    }

    public String filterName(String color) {
        var arr = line.split("\\[");
        return arr[1].split("]")[0].split(color)[1].replace("\"", "");
    }

    public void addOpening() {
        if (line.contains("ECO")) {
            var arr = line.split("\\[");
            opening = arr[1].split("]")[0].split("ECO")[1].replaceAll("\"", "").replaceAll(" ", "");
        }
    }

    public void addPgn(String line) {

        if (checkForBracketedLine()) {
            pgn.append(" ").append(line);
        }

    }

    public void splitEndResultFromPgn(String line) {
        String regex = "\\b\\d+\\.\\S+(?:\\s+\\S+)*\\s+(1-0|0-1|1/2-1/2)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            var endPgn = line.split(matcher.group(1));
            pgn.append(" " + endPgn[0]);
            result = matcher.group(1);
        }
    }


    public String getOpening() {
        return opening;
    }

    public StringBuilder getPgn() {
        return pgn;
    }

    public String getResult() {
        return result;
    }

    public String getLastName(String player) {
        return player.split(",")[0];
    }

    @Override
    public String toString() {
        return String.format("Black : %s\nWhite : %s\nResult : %s\nOpening: %s\npgnExtractor.PgnFileReader: %s\n\nSurname (white) : %s.\t\tSurname (black) : %s.\n\n",
                blackPlayer, whitePlayer, result, opening, pgn, whitePlayer, blackPlayer);
    }
}
