package controller;

import model.RubiksCubeModel;
import model.RubiksCubeMover;

public class Driver {

	public static void main(String[] args) {
		RubiksCubeModel testModel  = new RubiksCubeModel();
		CubeSolver      testSolver = new CubeSolver(testModel);
		RubiksCubeMover testMover  = new RubiksCubeMover();
		
		
		//testModel.displayCube();
		System.out.println(testModel.isSolved());
		testSolver.scrambleCube();
		System.out.println(testModel.isSolved());
		//testModel.displayCube();
		testSolver.solveCube();
		System.out.println(testModel.isSolved());
		testModel.displayCube();
	}
}
