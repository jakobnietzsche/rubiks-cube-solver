package goals;

import model.RubiksCubeModel;
import availableMoves.AvailableMoves;

public abstract class Goal {
	protected AvailableMoves availableMoves;
	
	public Goal(AvailableMoves availableMoves) {
		this.availableMoves = availableMoves;
	}
	
	public Goal() {};
	
	public abstract boolean isCompleted(RubiksCubeModel cube);
	
	public abstract void  displaySuccess();
	
	public boolean index(RubiksCubeModel cube, int numMoves) {
		return true;
	}
	
	public AvailableMoves getAvailableMoves() {
		return this.availableMoves;
	}
}
