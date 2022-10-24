package availableMoves;

import model.RubiksCubeDefinitions.Move;

public class InitialAvailableMoves extends AvailableMoves {
	Move[] moves;
	
	public InitialAvailableMoves() {
		this.moves = new Move[] {
				Move.X,
				Move.XPRIME,
				Move.X2,
				Move.Y,
				Move.YPRIME,
				Move.Y2,
				Move.Z,
				Move.ZPRIME,
				Move.Z2
		};
	}
	
	public Move[] getMoves() {
		return this.moves;
	}
}
