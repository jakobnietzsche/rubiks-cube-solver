package model;

public class RubiksCubeDefinitions {
	public static enum Color {
		WHITE, GREEN, RED, BLUE, ORANGE, YELLOW;
		
		public final byte mapping;
		private Color() {
			this.mapping = (byte)this.ordinal();
		}
	}
	
	public static enum Face {
		UP, LEFT, FRONT, RIGHT, BACK, DOWN;
		
		public final byte mapping;
		private Face() {
			this.mapping = (byte)this.ordinal();
		}
	}
	
	public static enum Move {
	    L, LPRIME, L2,
	    R, RPRIME, R2,
	    U, UPRIME, U2,
	    D, DPRIME, D2,
	    F, FPRIME, F2,
	    B, BPRIME, B2,
	    Y, YPRIME, Y2,
	    X, XPRIME, X2,
	    Z, ZPRIME, Z2,
	    MID, MIDPRIME, MID2,
	    STAND, STANDPRIME, STAND2,
	    EQUATORIAL, EQUATORIALPRIME, EQUATORIAL2;
		
		public final byte mapping;
		private Move() {
			this.mapping = (byte)this.ordinal();
		}
	}
}
