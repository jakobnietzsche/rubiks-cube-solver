package goals;

import model.RubiksCubeDefinitions.Color;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class G3EdgeGoal extends Goal {
	G3CornerGoalPermute perms;
	
	public G3EdgeGoal(G3CornerGoalPermute perms) {
		this.perms = perms;
	}
	
	public boolean isCompleted(RubiksCubeModel cube) {
	    Color UL = cube.getColor(Face.UP,    1, 0);
	    Color LU = cube.getColor(Face.LEFT,  0, 1);

	    Color UR = cube.getColor(Face.UP,    1, 2);
	    Color RU = cube.getColor(Face.RIGHT, 0, 1);

	    Color DL = cube.getColor(Face.DOWN,  1, 0);
	    Color LD = cube.getColor(Face.LEFT,  2, 1);

	    Color DR = cube.getColor(Face.DOWN,  1, 2);
	    Color RD = cube.getColor(Face.RIGHT, 2, 1);

	    Color LB = cube.getColor(Face.LEFT,  1, 0);
	    Color BL = cube.getColor(Face.BACK,  1, 2);

	    Color LF = cube.getColor(Face.LEFT,  1, 2);
	    Color FL = cube.getColor(Face.FRONT, 1, 0);

	    Color RB = cube.getColor(Face.RIGHT, 1, 2);
	    Color BR = cube.getColor(Face.BACK,  1, 0);

	    Color RF = cube.getColor(Face.RIGHT, 1, 0);
	    Color FR = cube.getColor(Face.FRONT, 1, 2);
	    
	    return this.perms.doesPermutationExist(cube) &&
		        (UL == Color.RED   || UL == Color.ORANGE) && (LU == Color.BLUE || LU == Color.GREEN)  &&
		        (UR == Color.RED   || UR == Color.ORANGE) && (RU == Color.BLUE || RU == Color.GREEN)  &&
		        (DL == Color.RED   || DL == Color.ORANGE) && (LD == Color.BLUE || LD == Color.GREEN)  &&
		        (DR == Color.RED   || DR == Color.ORANGE) && (RD == Color.BLUE || RD == Color.GREEN)  &&
		        (BL == Color.WHITE || BL == Color.YELLOW) && (LB == Color.BLUE || LB == Color.GREEN)  &&
		        (FL == Color.WHITE || FL == Color.YELLOW) && (LF == Color.BLUE || LF == Color.GREEN)  &&
		        (BR == Color.WHITE || BR == Color.YELLOW) && (RB == Color.BLUE || RB == Color.GREEN)  &&
		        (FR == Color.WHITE || FR == Color.YELLOW) && (RF == Color.BLUE || RF == Color.GREEN);
	    
	}
}
