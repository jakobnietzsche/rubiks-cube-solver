package goals;

import model.RubiksCubeDefinitions.Color;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class G2Goal extends Goal {
	public boolean isCompleted(RubiksCubeModel cube) {
	    Color LUB = cube.getColor(Face.LEFT, 0, 0);
	    Color LUF = cube.getColor(Face.LEFT, 0, 2);
	    Color LDB = cube.getColor(Face.LEFT, 2, 0);
	    Color LDF = cube.getColor(Face.LEFT, 2, 2);
	
	    Color RUB = cube.getColor(Face.RIGHT, 0, 2);
	    Color RUF = cube.getColor(Face.RIGHT, 0, 0);
	    Color RDB = cube.getColor(Face.RIGHT, 2, 2);
	    Color RDF = cube.getColor(Face.RIGHT, 2, 0);
	
	    // Edges in the M slice (between R and L).
	    Color UF = cube.getColor(Face.UP,    2, 1);
	    Color FU = cube.getColor(Face.FRONT, 0, 1);
	
	    Color UB = cube.getColor(Face.UP,    0, 1);
	    Color BU = cube.getColor(Face.BACK,  0, 1);
	
	    Color DF = cube.getColor(Face.DOWN,  0, 1);
	    Color FD = cube.getColor(Face.FRONT, 2, 1);
	
	    Color DB = cube.getColor(Face.DOWN,  2, 1);
	    Color BD = cube.getColor(Face.BACK,  2, 1);
	    
	    return
	    		// All left/right corner facets either blue or green.
	    		(LUB == Color.BLUE || LUB == Color.GREEN) &&
    	      	(LUF == Color.BLUE || LUF == Color.GREEN) &&
    	      	(LDB == Color.BLUE || LDB == Color.GREEN) &&
    	      	(LDF == Color.BLUE || LDF == Color.GREEN) &&
    	      	(RUB == Color.BLUE || RUB == Color.GREEN) &&
    	      	(RUF == Color.BLUE || RUF == Color.GREEN) &&
    	      	(RDB == Color.BLUE || RDB == Color.GREEN) &&
    	      	(RDF == Color.BLUE || RDF == Color.GREEN) &&

    	      	// UF, UB, DF, DB in the M slice.  Note that the edges
    	      	// are already oriented.
    	      	(UF == Color.RED   || UF == Color.ORANGE)  &&
    	      	(FU == Color.WHITE || FU == Color.YELLOW)  &&

    	      	(UB == Color.RED   || UB == Color.ORANGE)  &&
    	      	(BU == Color.WHITE || BU == Color.YELLOW)  &&

    	      	(DF == Color.RED   || DF == Color.ORANGE)  &&
    	      	(FD == Color.WHITE || FD == Color.YELLOW)  &&

    	      	(DB == Color.RED   || DB == Color.ORANGE)  &&
    	      	(BD == Color.WHITE || BD == Color.YELLOW);
	}

}
