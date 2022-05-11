package goals;

import java.util.HashMap;
import java.util.Map;

import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeModel;

public class G3CornerGoalPermute extends Goal {
	Map<String, Boolean> perms;
	StringBuilder sb;
	
	public G3CornerGoalPermute() {
		this.perms = new HashMap<>();
		this.sb = new StringBuilder();
	}
	
	public String permStringGenerator(RubiksCubeModel cube) {		
		sb.setLength(0);
		
        sb.append(cube.getColor(Face.UP.mapping,    0, 0));
        sb.append(cube.getColor(Face.LEFT.mapping,  0, 0));

        sb.append(cube.getColor(Face.UP.mapping,    2, 0));
        sb.append(cube.getColor(Face.LEFT.mapping,  0, 2));

        sb.append(cube.getColor(Face.UP.mapping,    0, 2));
        sb.append(cube.getColor(Face.RIGHT.mapping, 0, 2));

        sb.append(cube.getColor(Face.UP.mapping,    2, 2));
        sb.append(cube.getColor(Face.RIGHT.mapping, 0, 0));

        sb.append(cube.getColor(Face.DOWN.mapping,  2, 0));
        sb.append(cube.getColor(Face.LEFT.mapping,  2, 0));

        sb.append(cube.getColor(Face.DOWN.mapping,  0, 0));
        sb.append(cube.getColor(Face.LEFT.mapping,  2, 2));

        sb.append(cube.getColor(Face.DOWN.mapping,  2, 2));
        sb.append(cube.getColor(Face.RIGHT.mapping, 2, 2));

        sb.append(cube.getColor(Face.DOWN.mapping,  0, 2));
        sb.append(cube.getColor(Face.RIGHT.mapping, 2, 0));
        
		return sb.toString();
	}
	
	public boolean isCompleted(RubiksCubeModel cube) {
		String perm = permStringGenerator(cube);
		
		if (perms.get(perm) == null) {
			perms.put(perm, true);
			if (perms.size() == 96) return true;
		}
		return false;
	}
	
	public boolean doesPermutationExist(RubiksCubeModel cube) {
		String perm = permStringGenerator(cube);
		return perms.containsKey(perm);
	}
}
