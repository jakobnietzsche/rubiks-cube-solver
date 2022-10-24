package availableMoves;

import model.RubiksCubeDefinitions.Move;

public class G1AvailableMoves extends AvailableMoves {
	Move[] moves;
	
	public G1AvailableMoves() {
		this.moves = new Move[] {
		      Move.L,
		      Move.LPRIME,
		      Move.L2,
		      Move.R,
		      Move.RPRIME,
		      Move.R2,
		      Move.U2,
		      Move.D2,
		      Move.F,
		      Move.FPRIME,
		      Move.F2,
		      Move.B,
		      Move.BPRIME,
		      Move.B2
		};
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
}
