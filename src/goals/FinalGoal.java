package goals;

import availableMoves.G3AvailableMoves;
import model.RubiksCubeModel;

public class FinalGoal extends Goal {
	
	public FinalGoal(G3AvailableMoves availableMoves) {
		super(availableMoves);
	}

	public boolean isCompleted(RubiksCubeModel cube) {
		return cube.isSolved();
	}
	
	public void displaySuccess() {
		System.out.println("The final goal has been reached. The cube is now solved.");
		System.out.println();
	}
}
