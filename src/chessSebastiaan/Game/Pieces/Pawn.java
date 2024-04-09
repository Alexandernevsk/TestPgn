package chessSebastiaan.Game.Pieces;

import chessSebastiaan.Game.*;

import java.util.*;
import java.util.function.Function;

public class Pawn implements Piece {

    Scanner scanner = new Scanner(System.in);
    private final String name;
    private Map<String, Function<Integer, Piece>> promotionMap = new HashMap<>();
    public boolean isEnPassantPawn = false;

    @Override
    public String getName() {
        return name;
    }



    public Pawn(String name) {
        this.name = name;
    }


// TODO: Make attacking squares


    @Override
    public boolean isLegalMove(ChessData data) {

        var map = data.getBoardFromChessData().getBoard();
        Point target = data.getTarget();
        Point tileToMoveFrom = data.getTileToMoveFrom();
        Player playerMakesMove = data.getPlayerMakesMove();
        Player opposedPlayer = data.getOpposedPlayer();
        var moves = data.getMoves();

        clearEnPassant(playerMakesMove);

        //White = -1
        //Black = 1
        int direction = getDirection(playerMakesMove);

        if (isBasicMoveForward(tileToMoveFrom, target, direction)) {
            return ifPreventsCollision(map, target, moves);
            //Move pawn 1 tile forward
        }

        if (isInitialDoubleMove(tileToMoveFrom, target, map, direction)) {
            isEnPassantPawn = true;
            return ifPreventsCollision(map, target, moves);
        }

        if (isCapture(tileToMoveFrom, target, direction)) {
            return isRegularOrEnPassantCapture(map, tileToMoveFrom, target, direction, opposedPlayer, moves);
        }

        System.out.println("Illegal move!");
        return false;

    }

    private int getDirection(Player playerMakesMove) {
        return playerMakesMove.getIsWhite() ? -1 : 1;
    }

    private boolean isBasicMoveForward(Point tileToMoveFrom, Point target, int direction) {
        return tileToMoveFrom.x() == target.x() && target.y() - tileToMoveFrom.y() == direction;
    }

    private boolean isInitialDoubleMove(Point tileToMoveFrom, Point target, HashMap<Point, Piece> map, int direction) {
        Point crossingTile = new Point(target.x(), target.y() - direction);
         if(map.get(crossingTile) != null)
             return false;
        int initialRow = direction == -1 ? 6 : 1; // White starts on row 6 (from the bottom), black on row 1
        return tileToMoveFrom.x() == target.x() && Math.abs(target.y() - tileToMoveFrom.y()) == 2 && tileToMoveFrom.y() == initialRow;
    }

    private boolean isCapture(Point tileToMoveFrom, Point target, int direction) {
        return (Math.abs(target.x() - tileToMoveFrom.x()) == 1 && target.y() - tileToMoveFrom.y() == direction);
    }

    private boolean isRegularOrEnPassantCapture(HashMap<Point, Piece> map, Point tileToMoveFrom, Point target, int direction, Player opposedPlayer, ArrayList<String> moves) {
        Point adjacentPawnPoint = new Point(target.x(), target.y() - direction); // Checking for en passant
        Piece possiblePawn = map.get(adjacentPawnPoint);
        storeCaptureMove(tileToMoveFrom, target, moves);
        if (possiblePawn instanceof Pawn pawn && pawn.isEnPassantPawn) {

            return canCapture(map, adjacentPawnPoint, opposedPlayer); // En passant capture
        }
        return canCapture(map, target, opposedPlayer); // Regular capture
    }

    private void storeCaptureMove(Point tileToMoveFrom, Point target, ArrayList<String> moves) {
        String from = pointToChessPosition(tileToMoveFrom);
        char letter = from.charAt(0);
        String to = pointToChessPosition(target);
        moves.add(letter + "x" + to);
    }

    private void clearEnPassant(Player player) {
        player.getPlayerPieces().stream()
                .filter(p -> p instanceof Pawn)
                .forEach(p -> ((Pawn) p).isEnPassantPawn = false);
    }

    private boolean canCapture(HashMap<Point, Piece> map, Point target, Player opposedPlayer) {
        var pieceOnTargetedSquare = map.get(target);
        var playerPieces = opposedPlayer.getPlayerPieces();
        if (playerPieces.contains(pieceOnTargetedSquare)) {
            map.replace(target, null);
            playerPieces.remove(pieceOnTargetedSquare);
            return true;
        } else {
            System.out.println("Illegal move!");
            return false;
        }
    }

    // Check for promotion
    private boolean isPromotion(Point to, Player playerMakesMove) {
        return (playerMakesMove.getIsWhite() && to.y() == 0) || (!playerMakesMove.getIsWhite() && to.y() == 7);
    }

    private void initializePromotionMap(Player player) {
        promotionMap.put("r", number -> new Rook(player.getIsWhite() ? "R" + number : "r" + number));
        promotionMap.put("n", number -> new Knight(player.getIsWhite() ? "N" + number : "n" + number));
        promotionMap.put("b", number -> new Bishop(player.getIsWhite() ? "B" + number : "b" + number));
        promotionMap.put("q", number -> new Queen(player.getIsWhite() ? "Q" + number : "q" + number));
    }
    private void promotePawn(ChessData data) {

        var map = data.getBoardFromChessData().getBoard();
        var playerMakesMove = data.getPlayerMakesMove();
        var chosenPiece = data.getChosenPiece();
        var placeToPromote = data.getTarget();
        var moves = data.getMoves();
        var promotionNumbers = playerMakesMove.getPromotionNumbers();


        boolean isSuccessfulPromotion = false;
        var pieces = playerMakesMove.getPlayerPieces();
        pieces.remove(chosenPiece);
        do{
        System.out.println("Which piece do you want your pawn to promote? Choose r, n, b or q");
        String letter = scanner.nextLine();

            Function<Integer, Piece> pieceCreator = promotionMap.get(letter);

            if (pieceCreator != null) {
                int number = promotionNumbers.get(letter.toUpperCase());
                Piece newPiece = pieceCreator.apply(++number);
                map.replace(placeToPromote, newPiece);
                pieces.add(newPiece);

                // Update promotion numbers and last move
                promotionNumbers.replace(letter.toUpperCase(), number);
                String lastMove = moves.get(moves.size() - 1);
                moves.set(moves.size() - 1, lastMove + "=" + letter.toUpperCase());

                isSuccessfulPromotion = true;
            } else {
                System.out.println("Invalid input. Please enter r, n, b, or q.");
            }
        }while (!isSuccessfulPromotion);
    }


    private boolean ifPreventsCollision(HashMap<Point, Piece> map, Point target, ArrayList<String> moves) {
        if (map.get(target) == null) { //Check if targeted tile is empty (no piece on it)
            moves.add(pointToChessPosition(target));
            return true;
        } else {
            System.out.println("Illegal move!");
            return false;
        }
    }

    private String pointToChessPosition(Point point) {
        char columnLetter = (char) ('a' + point.x());
        int rowNumber = 8 - point.y();
        return String.valueOf(columnLetter) + rowNumber;
    }


    @Override
    public HashMap<Point, Piece> Move(ChessData data) {
        var map = data.getBoardFromChessData().getBoard();
        var target = data.getTarget();
        var tileToMoveFrom = data.getTileToMoveFrom();
        //Removes piece from initial tile
        if(isPromotion(target, data.getPlayerMakesMove())){
            promotePawn(data);
        }
        else{
            map.replace(target, data.getChosenPiece()); //Places piece on target square
        }
        map.replace(tileToMoveFrom, null); //Removes piece from initial tile
        return map;
    }
}


    // 1. Pawn must be on same file while moving.
    // 2. Can move only 1 forward, except at begin, where you can move 1 or 2 tiles
    // (2 tiles mean en passant chance for opposed player, but that for later)
    // 3. Cannot hit or go through your own pieces (can be used as common piece behaviour)
    // 4. When end of board is reached, either by capturing or moving, pawn is promoted. Choose which promotion. Piece gets unique name.


// After execution: Print the done move (complicated, maybe own class?)
// Modify and render the board again.
