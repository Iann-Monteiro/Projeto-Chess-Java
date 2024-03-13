package chess.pieces;

import Chess.Color;
import boardgame.Board;
import Chess.ChessPiece;

public class King extends ChessPiece{

    public King(Board board, Color color){
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}

