package controller;

import java.util.LinkedList;
import java.util.List;

import availableMoves.AvailableMoves;
import goals.Goal;
import model.RubiksCubeDefinitions.Move;
import model.RubiksCubeModel;

public class IterativeDeepening {
	public MovePruner pruner;
	
	public IterativeDeepening() {
		this.pruner = new MovePruner();
	}
	
	public List<Move> findGoal(Goal goal, RubiksCubeModel cube, AvailableMoves moves) {
		int maxDepth = 0;
		List<Integer> moveInds = new LinkedList<>();
		
		while (!findGoal(goal, cube, moves, 0, maxDepth, moveInds)) {
			maxDepth++;
		}
		
		return convertMoves(moveInds, moves);
	}
	
	private boolean findGoal(Goal goal, RubiksCubeModel cube, AvailableMoves moves,
							 int depth, int maxDepth, List<Integer> moveInds) {
		boolean solved = false;
		int numMoves = moves.getNumMoves();
		if (depth == maxDepth) {
			//goal.index(cube, depth);
			System.out.println(numMoves);
			return goal.isCompleted(cube);
		}
		
		for (int i = 0; i < numMoves && !solved; i++) {
			if (moveInds.size() == 0 ||
			    !this.pruner.prune(moves.getMove(i), moves.getMove(moveInds.get(moveInds.size()-1)))) {
			    	moveInds.add(i);
			    	moves.move(cube, i);
			    	if (this.findGoal(goal, cube, moves, depth+1, maxDepth, moveInds)) {
			    		solved = true;
			    	} else {
			    		moveInds.remove(moveInds.size()-1);
			    	}
			    	moves.invert(cube, i);
			    }
		}
		return solved;
	}
	
	
	private List<Move> convertMoves(List<Integer> moveInds, AvailableMoves availableMoves) {
		List<Move> moves = new LinkedList<>();
		
		for (Integer moveInd: moveInds) {
			moves.add(availableMoves.getMove(moveInd));
		}
		return moves;
	}
}
