package availableMoves;

import model.RubiksCubeDefinitions.Move;

public class G3AvailableMoves extends AvailableMoves {
	Move[] moves;
	
	public G3AvailableMoves() {
		this.moves = new Move[] {
		      Move.L2,
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
