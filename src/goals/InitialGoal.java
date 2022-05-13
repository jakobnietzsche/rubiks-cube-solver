package goals;

import model.RubiksCubeDefinitions.Color;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class InitialGoal extends Goal {
	
	public boolean isCompleted(RubiksCubeModel cube) { 
		return cube.getColor(Face.UP,    1, 1) == Color.RED &&
			   cube.getColor(Face.FRONT, 1, 1) == Color.WHITE;
	}
}
