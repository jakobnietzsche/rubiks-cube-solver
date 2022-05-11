package availableMoves;

import model.RubiksCubeDefinitions.Move;
import model.RubiksCubeModel;
import model.RubiksCubeMover;

public abstract class AvailableMoves {
	RubiksCubeMover mover = new RubiksCubeMover();
	
	public abstract Move[] getMoves();
	
	public Move getMove(int idx) {
		return this.getMoves()[idx];
	}
	
	public int getNumMoves() {
		return this.getMoves().length;
	}
	
	public void move(RubiksCubeModel cube, int ind) {
		System.out.println(" +++++++++++++++++++++++++++++++++++++++++++++++ ");
		cube.displayCube();
		System.out.println("MOVE BEING APPLIED: " + this.getMove(ind));
		System.out.println();
		cube = mover.move(this.getMove(ind), cube);
		cube.displayCube();
		System.out.println();
		System.out.println(" ----------------------------------------------- ");
	}
	
	public void invert(RubiksCubeModel cube, int ind) {
		cube = mover.invert(this.getMove(ind), cube);
	}
}
