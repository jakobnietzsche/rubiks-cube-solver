package goals;

import model.RubiksCubeModel;

public class FinalGoal extends Goal {

	public boolean isCompleted(RubiksCubeModel cube) {
		return cube.isSolved();
	}
}
