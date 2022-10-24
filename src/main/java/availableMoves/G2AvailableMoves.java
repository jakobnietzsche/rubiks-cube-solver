package availableMoves;

import model.RubiksCubeDefinitions.Move;

public class G2AvailableMoves extends AvailableMoves {
	Move[] moves;
	
	public G2AvailableMoves() {
		this.moves = new Move[] {
		      Move.L,
		      Move.LPRIME,
		      Move.L2,
		      Move.R,
		      Move.RPRIME,
		      Move.R2,
		      Move.U2,
		      Move.D2,
		      Move.F2,
		      Move.B2
		};
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
}
