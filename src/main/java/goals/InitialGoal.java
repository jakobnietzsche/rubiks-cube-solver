package goals;

import availableMoves.InitialAvailableMoves;
import model.RubiksCubeDefinitions.Color;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class InitialGoal extends Goal {
	
	public InitialGoal(InitialAvailableMoves availableMoves) {
		super(availableMoves);
	}
	
	public boolean isCompleted(RubiksCubeModel cube) { 
		return cube.getColor(Face.UP,    1, 1) == Color.RED &&
			   cube.getColor(Face.FRONT, 1, 1) == Color.WHITE;
	}
	
	public void displaySuccess() {
		System.out.println("Initial orientation goal now complete. The cube will now use the following moves: L, L', L2, " +
				                                                                                             "R, R', R2, " +
				                                                                                             "U, U', U2, " + 
				                                                                                             "D, D', D2, " +
				                                                                                             "F, F', F2, " +
				                                                                                 			 "B, B', B2");
		System.out.println();
	}
}
