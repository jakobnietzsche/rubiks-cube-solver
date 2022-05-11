package availableMoves;

import model.RubiksCubeDefinitions.Move;

public class AvailableTwistMoves extends AvailableMoves {
	Move[] moves;
	
	public AvailableTwistMoves() {
		this.moves = new Move[] {
			      Move.L, Move.LPRIME, Move.L2,
			      Move.R, Move.RPRIME, Move.R2,
			      Move.U, Move.UPRIME, Move.U2,
			      Move.D, Move.DPRIME, Move.D2,
			      Move.F, Move.FPRIME, Move.F2,
			      Move.B, Move.BPRIME, Move.B2
		};
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
}
