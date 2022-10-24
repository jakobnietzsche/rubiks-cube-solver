package controller;

import model.RubiksCubeModel;

public class Driver {

	public static void main(String[] args) {
		RubiksCubeModel testModel  = new RubiksCubeModel();
		
		CubeSolver testSolver = new CubeSolver(testModel);
		//testSolver.scrambleCube();
		testSolver.solveCube();
		
	}
}
