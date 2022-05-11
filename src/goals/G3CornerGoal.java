package goals;

import model.RubiksCubeModel;

public class G3CornerGoal extends Goal {
	G3CornerGoalPermute permuteGoal;
	
	public G3CornerGoal(G3CornerGoalPermute permute) {
		this.permuteGoal = permute;
	}
	
	public boolean isCompleted(RubiksCubeModel cube) {
		return this.permuteGoal.doesPermutationExist(cube);
	}
}
