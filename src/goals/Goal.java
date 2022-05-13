package goals;

import model.RubiksCubeModel;

public abstract class Goal {
	public abstract boolean isCompleted(RubiksCubeModel cube);
	
	public boolean index(RubiksCubeModel cube, int numMoves) {
		return true;
	}
}
