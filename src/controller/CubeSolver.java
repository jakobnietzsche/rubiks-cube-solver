package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	Map<Goal, AvailableMoves> goalMap;
	List<Goal> goalList;
	List<Move> goalMoves;
	
	public CubeSolver(RubiksCubeModel model) {
		this.rand = new Random();
		this.model = model;
		this.permutes = new G3CornerGoalPermute();
		this.searcher = new IterativeDeepening();
		this.goalMap = new HashMap<>();
		this.goalList = new LinkedList<>();
		this.goalMoves = new LinkedList<>();
		initializeDatabase();
		initialize();
	}
	
	/*
	private void initializeDatabase() {
		Color[] centers = new Color[] {Color.RED, Color.BLUE, Color.WHITE,
									   Color.GREEN, Color.YELLOW, Color.ORANGE};
		RubiksCubeModel solvedState = new RubiksCubeModel();
		for (int i = 0; i < 6; i++) {
			int face = 0;
			for (int j = 0; j < 8; j++) {
				face = (face<<4) | centers[i].mapping;
			}
			
			solvedState.cube
		}
	}
	*/
	
	private void initializeDatabase() {
		System.out.println("Initializing database...");
		RubiksCubeModel solvedState = new RubiksCubeModel();
		solvedState.move(Move.LPRIME);
		solvedState.move(Move.MIDPRIME);
		solvedState.move(Move.R);
		solvedState.move(Move.U2);
		solvedState.move(Move.EQUATORIALPRIME);
		solvedState.move(Move.EQUATORIALPRIME);
		solvedState.move(Move.D2);
		this.searcher.findGoal(this.permutes, solvedState, new G3AvailableMoves());
		System.out.println("Finished initializing database.");
	}
	
	private void initialize() {
		Goal initGoal     = new InitialGoal();
		Goal g1Goal       = new G1Goal();
		Goal g2Goal       = new G2Goal();
		Goal g3CornerGoal = new G3CornerGoal(permutes);
		Goal g3EdgeGoal   = new G3EdgeGoal(permutes);
		Goal finGoal      = new FinalGoal();
		
		goalMap.put(initGoal,     new InitialAvailableMoves());
		goalMap.put(g1Goal,       new AvailableTwistMoves());
		goalMap.put(g2Goal,       new G1AvailableMoves());
		goalMap.put(g3CornerGoal, new G2AvailableMoves());
		goalMap.put(g3EdgeGoal,   new G2AvailableMoves());
		goalMap.put(finGoal,      new G3AvailableMoves());
		
		goalList.add(initGoal);
		goalList.add(g1Goal);
		goalList.add(g2Goal);
		goalList.add(g3CornerGoal);
		goalList.add(g3EdgeGoal);
		goalList.add(finGoal);
	}
	
	public void scrambleCube() {
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
	}
	
	public void solveCube() {
		List<Move> allMoves = new LinkedList<>();
	
		for (int i = 0; i < goalList.size(); ++i) {
			Goal currGoal = goalList.get(i);
			System.out.println("GOAL STEP: " + i);
			goalMoves = this.searcher.findGoal(currGoal, model, goalMap.get(currGoal));
			this.processGoalMoves(currGoal, i+1, allMoves);
		}
		
		for (Move move: allMoves) {
			System.out.print(move.name() + " ");
		}
		System.out.println("Done solving.");
	}
	
	private void processGoalMoves(Goal goal, int goalNum, List<Move> allMoves) {
		allMoves.addAll(goalMoves);
		
		for (Move move: goalMoves) {
			this.model.move(move);
		}
		goalMoves.clear();
	}
}
