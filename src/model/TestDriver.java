package model;

import model.RubiksCubeDefinitions.*;

public class TestDriver {

  /*              0  1  2
   *              7     3
   *              6  5  4
   *
   *   8  9 10   16 17 18   24 25 26   32 33 34
   *  15    11   23    19   31    27   39    35
   *  14 13 12   22 21 20   30 29 28   38 37 36
   *
   *             40 41 42
   *             47    43
   *             46 45 44
   *    0
   *  1 2 3 4
   *    5
	public static void main(String[] args) {
		RubiksCubeModel testCube = new RubiksCubeModel();
		testCube.cube[0] = Color.WHITE;
		testCube.cube[1] = Color.GREEN;
		testCube.cube[2] = Color.RED;
		testCube.cube[3] = Color.BLUE;
		testCube.cube[4] = Color.ORANGE;
		testCube.cube[5] = Color.YELLOW;
		testCube.cube[6] = Color.WHITE;
		testCube.cube[7] = Color.GREEN;
		testCube.displayCube();
		testCube.rollSlice90(7, 13, 43, 25,
				             3, 9, 47, 29,
				             0, 1, 5, 3);
		System.out.println();
		testCube.displayCube();
	}
	*/
	
	public static void main(String[] args) {
		String testString = "" + Color.WHITE + Color.BLUE + Color.GREEN;
		System.out.println(testString);
	}
}
