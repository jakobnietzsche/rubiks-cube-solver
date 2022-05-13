package goals;

import model.RubiksCubeDefinitions.Color;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class G1Goal extends Goal {
	public boolean isCompleted(RubiksCubeModel cube) {
	    Color UB = cube.getColor(Face.UP, 0, 1);
	    Color UL = cube.getColor(Face.UP, 1, 0);
	    Color UR = cube.getColor(Face.UP, 1, 2);
	    Color UF = cube.getColor(Face.UP, 2, 1);

	    Color LU = cube.getColor(Face.LEFT, 0, 1);
	    Color LB = cube.getColor(Face.LEFT, 1, 0);
	    Color LF = cube.getColor(Face.LEFT, 1, 2);
	    Color LD = cube.getColor(Face.LEFT, 2, 1);

	    Color FU = cube.getColor(Face.FRONT, 0, 1);
	    Color FL = cube.getColor(Face.FRONT, 1, 0);
	    Color FR = cube.getColor(Face.FRONT, 1, 2);
	    Color FD = cube.getColor(Face.FRONT, 2, 1);

	    Color RU = cube.getColor(Face.RIGHT, 0, 1);
	    Color RF = cube.getColor(Face.RIGHT, 1, 0);
	    Color RB = cube.getColor(Face.RIGHT, 1, 2);
	    Color RD = cube.getColor(Face.RIGHT, 2, 1);

	    Color BU = cube.getColor(Face.BACK, 0, 1);
	    Color BL = cube.getColor(Face.BACK, 1, 0);
	    Color BR = cube.getColor(Face.BACK, 1, 2);
	    Color BD = cube.getColor(Face.BACK, 2, 1);

	    Color DF = cube.getColor(Face.DOWN, 0, 1);
	    Color DL = cube.getColor(Face.DOWN, 1, 0);
	    Color DR = cube.getColor(Face.DOWN, 1, 2);
	    Color DB = cube.getColor(Face.DOWN, 2, 1);
	    
	    return
	    		(UF == Color.BLUE  || UF == Color.GREEN || FU == Color.WHITE || FU == Color.YELLOW) &&
	    		(UB == Color.BLUE  || UB == Color.GREEN || BU == Color.WHITE || BU == Color.YELLOW) &&
	    		(DF == Color.BLUE  || DF == Color.GREEN || FD == Color.WHITE || FD == Color.YELLOW) &&
	    		(DB == Color.BLUE  || DB == Color.GREEN || BD == Color.WHITE || BD == Color.YELLOW) &&
	    		(LU == Color.BLUE  || LU == Color.GREEN || UL == Color.WHITE || UL == Color.YELLOW) &&
	    		(LD == Color.BLUE  || LD == Color.GREEN || DL == Color.WHITE || DL == Color.YELLOW) &&
	    		(RU == Color.BLUE  || RU == Color.GREEN || UR == Color.WHITE || UR == Color.YELLOW) &&
	    		(RD == Color.BLUE  || RD == Color.GREEN || DR == Color.WHITE || DR == Color.YELLOW) &&
	    		(LF == Color.BLUE  || LF == Color.GREEN || FL == Color.WHITE || FL == Color.YELLOW) &&
	    		(LB == Color.BLUE  || LB == Color.GREEN || BL == Color.WHITE || BL == Color.YELLOW) &&
	    		(RF == Color.BLUE  || RF == Color.GREEN || FR == Color.WHITE || FR == Color.YELLOW) &&
	    		(RB == Color.BLUE  || RB == Color.GREEN || BR == Color.WHITE || BR == Color.YELLOW);
	}
}
