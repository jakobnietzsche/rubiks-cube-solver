package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import availableMoves.*;
import goals.*;
import model.RubiksCubeDefinitions.Move;
import model.RubiksCubeModel;

public class CubeSolver {
	Random rand;
	RubiksCubeModel model;
	G3CornerGoalPermute permutes;
	IterativeDeepening searcher;
	List<Goal> goalList;
	List<Move> goalMoves;
	
	public CubeSolver(RubiksCubeModel model) {
		this.rand = new Random();
		this.model = model;
		this.permutes = new G3CornerGoalPermute();
		this.searcher = new IterativeDeepening();
		this.goalList = new LinkedList<>();
		this.goalMoves = new LinkedList<>();
		initializeDatabase();
		initialize();
	}
	
	private void initializeDatabase() {
		System.out.println("Initializing...");
		RubiksCubeModel solvedState = new RubiksCubeModel();
		solvedState.move(Move.LPRIME);
		solvedState.move(Move.MIDPRIME);
		solvedState.move(Move.R);
		solvedState.move(Move.U2);
		solvedState.move(Move.EQUATORIALPRIME);
		solvedState.move(Move.EQUATORIALPRIME);
		solvedState.move(Move.D2);
		this.searcher.findGoal(this.permutes, solvedState, new G3AvailableMoves());
		System.out.println("Finished initializing.");
		System.out.println();
	}
	
	private void initialize() {
		Goal initGoal     = new InitialGoal(new InitialAvailableMoves());
		Goal g1Goal       = new G1Goal(new AvailableTwistMoves());
		Goal g2Goal       = new G2Goal(new G1AvailableMoves());
		Goal g3CornerGoal = new G3CornerGoal(permutes, new G2AvailableMoves());
		Goal g3EdgeGoal   = new G3EdgeGoal(permutes, new G2AvailableMoves());
		Goal finGoal      = new FinalGoal(new G3AvailableMoves());
		
		goalList.add(initGoal);
		goalList.add(g1Goal);
		goalList.add(g2Goal);
		goalList.add(g3CornerGoal);
		goalList.add(g3EdgeGoal);
		goalList.add(finGoal);
	}
	
	public List<Move> scrambleCube() {
		List<Move> moves = new LinkedList<>();
		MovePruner pruner = new MovePruner();
		moves.add(Move.values()[rand.nextInt(17)]);
		
		while(moves.size() < 100) {
			Move move = Move.values()[rand.nextInt(17)];
			if (!pruner.prune(moves.get(moves.size()-1), move)) {
				moves.add(move);
			}
		}
		
		for (Move move: moves) {
			this.model.move(move);
		}
		return moves;
	}
	
	public List<Move> solveCube() {
		List<Move> allMoves = new LinkedList<>();
	
		for (int i = 0; i < goalList.size(); ++i) {
			Goal currGoal = goalList.get(i);
			goalMoves = this.searcher.findGoal(currGoal, model, currGoal.getAvailableMoves());
			currGoal.displaySuccess();
			this.processGoalMoves(currGoal, i+1, allMoves);
		}
		
		System.out.println("Here are the moves that were used to solve the cube: ");
		for (Move move: allMoves) {
			System.out.print(move.name() + " ");
		}
		return allMoves;
	}
	
	private void processGoalMoves(Goal goal, int goalNum, List<Move> allMoves) {
		allMoves.addAll(goalMoves);
		
		for (Move move: goalMoves) {
			this.model.move(move);
		}
		goalMoves.clear();
	}
}
