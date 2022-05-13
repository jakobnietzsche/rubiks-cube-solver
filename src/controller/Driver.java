package controller;

import model.RubiksCubeModel;
import goals.FinalGoal;
import goals.Goal;
import model.RubiksCubeDefinitions.Face;
import model.RubiksCubeDefinitions.Move;

public class Driver {

	public static void main(String[] args) {
		RubiksCubeModel testModel  = new RubiksCubeModel();
		
		CubeSolver testSolver = new CubeSolver(testModel);
		testSolver.scrambleCube();
		//testModel.displayCube();
		testSolver.solveCube();
		System.out.println(testModel.isSolved());
		testModel.displayCube();
	}
}
