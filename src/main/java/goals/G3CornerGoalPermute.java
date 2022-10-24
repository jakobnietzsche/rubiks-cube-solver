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
		
        sb.append(cube.getColor(Face.UP,    0, 0));
        sb.append(cube.getColor(Face.LEFT,  0, 0));

        sb.append(cube.getColor(Face.UP,    2, 0));
        sb.append(cube.getColor(Face.LEFT,  0, 2));

        sb.append(cube.getColor(Face.UP,    0, 2));
        sb.append(cube.getColor(Face.RIGHT, 0, 2));

        sb.append(cube.getColor(Face.UP,    2, 2));
        sb.append(cube.getColor(Face.RIGHT, 0, 0));

        sb.append(cube.getColor(Face.DOWN,  2, 0));
        sb.append(cube.getColor(Face.LEFT,  2, 0));

        sb.append(cube.getColor(Face.DOWN,  0, 0));
        sb.append(cube.getColor(Face.LEFT,  2, 2));

        sb.append(cube.getColor(Face.DOWN,  2, 2));
        sb.append(cube.getColor(Face.RIGHT, 2, 2));

        sb.append(cube.getColor(Face.DOWN,  0, 2));
        sb.append(cube.getColor(Face.RIGHT, 2, 0));
        
		return sb.toString();
	}
	
	public boolean isCompleted(RubiksCubeModel cube) {
		String perm = permStringGenerator(cube);
		
		if (!perms.containsKey(perm)) {
			perms.put(perm, true);
			if (perms.size() == 96) return true;
		}
		return false;
	}
	
	public boolean doesPermutationExist(RubiksCubeModel cube) {
		String perm = permStringGenerator(cube);
		return perms.containsKey(perm);
	}
	
	public void displaySuccess() {}
}