package goals;

import availableMoves.G2AvailableMoves;
import model.RubiksCubeModel;

public class G3CornerGoal extends Goal {
	G3CornerGoalPermute permuteGoal;
	
	public G3CornerGoal(G3CornerGoalPermute permute, G2AvailableMoves availableMoves) {
		super(availableMoves);
		this.permuteGoal = permute;
	}
	
	public boolean isCompleted(RubiksCubeModel cube) {
		return this.permuteGoal.doesPermutationExist(cube);
	}
	
	public void displaySuccess() {
		System.out.println("Part 1 of the G3 goal has been reached.");
		System.out.println();
	}
}
