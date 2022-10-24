package controller;

import model.RubiksCubeDefinitions.Move;

public class MovePruner {
	public boolean prune(String move, String[] moves) {
		if (moves.length == 0) return false;
		String lastMove = moves[moves.length-1];
		return this.prune(move, lastMove);
	}
	
	public boolean prune(String move, String lastMove) {
		char first1 = move.charAt(0);
		char first2 = lastMove.charAt(0);
		if (first1 == first2 ||
		   (first1 == 'F' && first2 == 'B') ||
		   (first1 == 'L' && first2 == 'R') ||
		   (first1 == 'U' && first2 == 'D')) {
			return true;
		}
		return false;
	}
	
	public boolean prune(Move move, Move lastMove) {
	    if ((move == Move.L || move == Move.LPRIME || move == Move.L2) &&
            (lastMove == Move.L || lastMove == Move.LPRIME || lastMove == Move.L2))
          return true;

        if ((move == Move.R || move == Move.RPRIME || move == Move.R2) &&
            (lastMove == Move.R || lastMove == Move.RPRIME || lastMove == Move.R2))
          return true;

        if ((move == Move.U || move == Move.UPRIME || move == Move.U2) &&
            (lastMove == Move.U || lastMove == Move.UPRIME || lastMove == Move.U2))
          return true;

        if ((move == Move.D || move == Move.DPRIME || move == Move.D2) &&
            (lastMove == Move.D || lastMove == Move.DPRIME || lastMove == Move.D2))
          return true;

        if ((move == Move.F || move == Move.FPRIME || move == Move.F2) &&
            (lastMove == Move.F || lastMove == Move.FPRIME || lastMove == Move.F2))
          return true;

        if ((move == Move.B || move == Move.BPRIME || move == Move.B2) &&
            (lastMove == Move.B || lastMove == Move.BPRIME || lastMove == Move.B2))
          return true;

        if ((move == Move.F || move == Move.FPRIME || move == Move.F2) &&
            (lastMove == Move.B || lastMove == Move.BPRIME || lastMove == Move.B2))
          return true;

        if ((move == Move.L || move == Move.LPRIME || move == Move.L2) &&
            (lastMove == Move.R || lastMove == Move.RPRIME || lastMove == Move.R2))
          return true;

        if ((move == Move.U || move == Move.UPRIME || move == Move.U2) &&
            (lastMove == Move.D || lastMove == Move.DPRIME || lastMove == Move.D2))
          return true;

        return false;
	}
}
