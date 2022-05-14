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
			++maxDepth;
		}
		
		return convertMoves(moveInds, moves);
	}
	
	private boolean findGoal(Goal goal, RubiksCubeModel cube, AvailableMoves moves,
							 int depth, int maxDepth, List<Integer> moveInds) {
		boolean solved = false;
		int numMoves = moves.getNumMoves();
		
		if (depth == maxDepth) {
			goal.index(cube, depth);
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
	
/*
	public List<Move> doIDDFS(AvailableMoves availableMoves, Goal goal, RubiksCubeModel cube) {
		List<Move> moveList = new LinkedList<>();
		int searchDepth = 1;
		while(!doDFS(goal, searchDepth, moveList, availableMoves, null, cube)) {
			++searchDepth;
		}
		return moveList;
	}
	
	private boolean doDFS(Goal goal, int searchDepth, List<Move> moveList, 
						  AvailableMoves availableMoves, Move lastMove, RubiksCubeModel cube) {
		if (searchDepth == 0) return false;
		
		for (Move m: availableMoves.getMoves()) {
			if (pruner.prune(m, lastMove)) continue;
			
			cube.move(m);
			
			if (goal.isCompleted(cube)) {
				moveList.add(0, m);
				return true;
			}
			
			if (doDFS(goal, searchDepth-1, moveList, availableMoves, m, cube)) {
				moveList.add(0, m);
				return true;
			}
		}
		return false;
	}
	*/
	
	private List<Move> convertMoves(List<Integer> moveInds, AvailableMoves availableMoves) {
		List<Move> moves = new LinkedList<>();
		
		for (Integer moveInd: moveInds) {
			moves.add(availableMoves.getMove(moveInd));
		}
		return moves;
	}
}
