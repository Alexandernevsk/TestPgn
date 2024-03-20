package chess.chessgame.move;


import chess.chessgame.board.PiecePosition;
import chess.chessgame.board.Square;
import chess.enumerations.Color;
import chess.enumerations.Piece;
import chess.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PgnMove {

    //Pawn moves
    //Forms :
    /*
     * simplest form forward move --> file + rank example : e4
     * capture move --> source_target + capture notation + file and rank  example : exd5
     * promotion --> file + rank + promote notation + target promotion (Queen Knight Bishop Rook) : e8=Q
     *
     * Other combinations
     */

    private boolean capture;
    private Castle castles;

    private enum Castle {
        QUEENSIDE("O-O-O"), KINGSIDE("O-O"), NON("-");

        public final String pgnNotation;

        Castle(String pgnNotation) {
            this.pgnNotation = pgnNotation;
        }
    }

    private char disambiguation;
    private boolean promotes;
    private char pawnCaptureSource;
    private PieceType pieceType;
    private Color color;
    private Square to;
    private boolean check;
    private boolean checkMate;

    public PgnMove() {
    }

    public PgnMove(Color color, String move) {

        this.color = color;
        pieceType = getPieceType(move);
        promotes = checkForPromote(move);
        capture = checkForCaptures(move);
        initPawnCaptureSource(move);
        findTargetSquare(move);
        isCheckOrMate(move);
    }

    public static final Pattern PGN = Pattern.compile(
            "(?<piecemove>(?<piece>[RNBQK])(?<piecefrom>[a-h]|[1-8]|[a-h][1-8])?x?(?<pieceto>[a-h][1-8])([+#])?)|" +
                    "(?<pawncapture>(?<pawncapturefile>[a-h])[1-8]?x(?<pawncaptureto>[a-h][1-8])=?(?<pawncapturepromotion>[RNBQ]?))([+#])?|" +
                    "(?<pawnpush>(?<pawnto>[a-h][1-8])=?(?<pawnpushpromotion>[RNBQ]?))([+#])?|" +
                    "(?<queencastling>O-O-O)([+#])?|(?<kingcastling>O-O)([+#])?");
    public static final Pattern TARGET_MOVE = Pattern.compile("[a-h][1-8]");
    public static final Pattern PIECE_OR_PAWN = Pattern.compile("(?<pawn>^[a-h])|" + "(?<piece>^[RNBQK])");
    public static final Pattern CAPTURE = Pattern.compile("x");
    public static final Pattern PROMOTE = Pattern.compile("=");
    public static final Pattern CHECK_OR_MATE = Pattern.compile("[+#]");
    public static final Pattern CASTLING = Pattern.compile("(?<queencastling>O-O-O)([+#])?|(?<kingcastling>O-O)([+#])?");


    public void findTargetSquare(String move) {
        Matcher target = TARGET_MOVE.matcher(move);
        Matcher castling = CASTLING.matcher(move);

        if (target.find()) {
            if (target.group().length() == 2) {
                castles = Castle.NON;
                to = new Square(target.group().charAt(1), target.group().charAt(0));
            }
        } else if (castling.find()) {
            if (castling.group("queencastling") != null) {
                castles = Castle.QUEENSIDE;
            } else if (castling.group("kingcastling") != null) {
                castles = Castle.KINGSIDE;
            }
        }

    }


    public List<PiecePosition> castlingPiecePositions() {

        Function<Character, Piece> fenToPieceFunction = (fenNotation ->
                color.equals(Color.WHITE) ? Piece.getPiece(fenNotation)
                        : Piece.getPiece(Character.toLowerCase(fenNotation)));

        Function<PieceType, Square> typeSquare = (type -> {
            int rank = this.color.equals(Color.WHITE) ? 1 : 8;

            if (type.equals(PieceType.ROOK)) {
                return new Square(rank, castles.equals(Castle.KINGSIDE) ? 'f' : castles.equals(Castle.QUEENSIDE) ? 'd' : null);
            } else if (type.equals(PieceType.KING)) {
                return new Square(rank, castles.equals(Castle.KINGSIDE) ? 'g' : castles.equals(Castle.QUEENSIDE) ? 'c' : null);
            } else return null;
        });


        return new ArrayList<>(List.of(
                new PiecePosition(fenToPieceFunction.apply('R'), typeSquare.apply(PieceType.ROOK)),
                new PiecePosition(fenToPieceFunction.apply('K'), typeSquare.apply(PieceType.KING))
        ));
    }


    public List<PiecePosition> getCastledPieces() {
        if (castles.equals(Castle.NON)) {
            return null;
        } else return castlingPiecePositions();
    }

    public PieceType getPieceType(String move) {
        Matcher matcher = PIECE_OR_PAWN.matcher(move);

        if (matcher.find()) {
            String piece = matcher.group("piece");
            String pawn = matcher.group("pawn");
            if (piece != null) {
                return Piece.getPiece(piece).type;
            } else if (pawn != null) {
                return Piece.getPiece('p').type;
            }
        }
        return null;
    }

    public boolean checkForCaptures(String move) {
        Matcher matcher = CAPTURE.matcher(move);
        return matcher.find();
    }

    public boolean checkForPromote(String move) {
        Matcher matcher = PROMOTE.matcher(move);
        return matcher.find();
    }

    public void initPawnCaptureSource(String move) {
        Matcher matcher = PGN.matcher(move);
        if (matcher.find()) {
            if (matcher.group("pawncapturefile") != null) {
                this.pawnCaptureSource = matcher.group("pawncapturefile").charAt(0);
            }
        }
    }

    public void isCheckOrMate(String move) {
        Matcher matcher = CHECK_OR_MATE.matcher(move);
        if (matcher.find()) {
            String check_or_mate = matcher.group();
            check = check_or_mate.contains("+");
            checkMate = check_or_mate.contains("#");
        }
    }

    @Override
    public String toString() {

        if (!castles.equals(Castle.NON)) {
            return castles.pgnNotation;
        }

        //Piece, disambiguation, capture, target, if pawn promote, and at last check
        return String.format("%s%s%s%s%s",
                pieceType.equals(PieceType.PAWN) ? capture ? pawnCaptureSource : "" : pieceType.pgnNotation,
                capture ? "x" : "",
                to,
                promotes ? "=" : "",
                check ? "+" : checkMate ? "#" : "");
    }

    public PiecePosition convertToPiecePosition() {
        var piece = PieceType.typeToPiece(this.pieceType, this.color);
        return new PiecePosition(piece, this.to);
    }

    public boolean isCastled(){
        return !castles.equals(Castle.NON);
    }

    public Castle getCastles() {
        return castles;
    }

    public Color getColor() {
        return color;
    }
}
