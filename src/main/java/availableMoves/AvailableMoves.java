package availableMoves;

import model.RubiksCubeDefinitions.Move;
import model.RubiksCubeModel;

public abstract class AvailableMoves {
	
	public abstract Move[] getMoves();
	
	public Move getMove(int idx) {
		return this.getMoves()[idx];
	}
	
	public int getNumMoves() {
		return this.getMoves().length;
	}
	
	public void move(RubiksCubeModel cube, int ind) {
		cube.move(this.getMove(ind));
	}
	
	public void invert(RubiksCubeModel cube, int ind) {
		cube.invert(this.getMove(ind));
	}
}
