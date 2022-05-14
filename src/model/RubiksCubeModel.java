package model;
import java.nio.ByteBuffer;
import java.util.Map;
import model.RubiksCubeDefinitions.*;

public class RubiksCubeModel {
	public Color[] cube;
	Color[] centers;
	RubiksCubeMover mover;
	private static ByteBuffer bb = ByteBuffer.allocate(Long.BYTES);
	private static byte[] bytes = new byte[8];
	private static final byte HASH = 1;
	
	private static Map<Byte, Color> colorMap = Map.of(
			(byte)0, Color.WHITE,
			(byte)1, Color.GREEN,
			(byte)2, Color.RED,
			(byte)3, Color.BLUE,
			(byte)4, Color.ORANGE,
			(byte)5, Color.YELLOW
		);
	
	public RubiksCubeModel() {
		this.cube = new Color[48];
		this.centers = new Color[6];
		this.mover = new RubiksCubeMover(this);
		initializeCube();
	}
	
	private void initializeCube() {
		int index = 0;
		// initialize edge and corner cubies
		for (Color color: Color.values()) {
			for (int i = 0; i < 8; i++) {
				cube[index] = color;
				index++;
			}
		}
		
		//initialize center cubies
		index = 0;
		for (Color color: Color.values()) {
			centers[index] = color;
			index++;
		}
	}
	
	public void move(Move m) {
		this.mover.move(m);
	}
	
	public void invert(Move m) {
		this.mover.invert(m);
	}
	
	public void roll90(int face) {
		for (int i = 0; i < 8; i++) {
			bytes[i] = cube[face*8+i].mapping;
		}
		
		bb.put(bytes, 0, bytes.length);
		bb.putLong(0, rotateRight(bb.getLong(0), 16));
		bytes = bb.array();

		for (int i = 0; i < 8; i++) {
			cube[face*8+i] = colorMap.get(bytes[i]);
		}
		bb.clear();
	}
	
	public void roll180(int face) {
		for (int i = 0; i < 8; i++) {
			bytes[i] = cube[face*8+i].mapping;
		}
		
		bb.put(bytes, 0, bytes.length);
		bb.putLong(0, rotateRight(bb.getLong(0), 32));
		bytes = bb.array();
		
		for (int i = 0; i < 8; i++) {
			cube[face*8+i] = colorMap.get(bytes[i]);
		}
		bb.clear();
	}
	
	public void roll270(int face) {
		for (int i = 0; i < 8; i++) {
			bytes[i] = cube[face*8+i].mapping;
		}
		
		bb.put(bytes, 0, bytes.length);
		bb.putLong(0, rotateLeft(bb.getLong(0), 16));
		bytes = bb.array();
		
		for (int i = 0; i < 8; i++) {
			cube[face*8+i] = colorMap.get(bytes[i]);
		}
		bb.clear();
	}
	
	public void rotateSides90(int si0, int si1, int si2, int si3,
			                  int si4, int si5, int si6, int si7) {
		Color[] temp = new Color[] {cube[si0], cube[si0+1]};
		cube[si0] = cube[si1]; cube[si0+1] = cube[si1+1];
		cube[si1] = cube[si2]; cube[si1+1] = cube[si2+1];
		cube[si2] = cube[si3]; cube[si2+1] = cube[si3+1];
		cube[si3] = temp[0];   cube[si3+1] = temp[1];   
		
		Color tempColor = cube[si4];
		cube[si4] = cube[si5];
		cube[si5] = cube[si6];
		cube[si6] = cube[si7];
		cube[si7] = tempColor;
	}
	
	public void rotateSides180(int i0, int i1, int i2, int i3,
                               int i4, int i5, int i6, int i7) {
		
		swapShort(i0, i1);
		swapShort(i2, i3);
		
		swapByte(i4, i5);
		swapByte(i6, i7);
	}
	
	public void rollSlice90(
			int fi0, int fi1, int fi2, int fi3,
			int fi4, int fi5, int fi6, int fi7,
			int ci0, int ci1, int ci2, int ci3)
	{
		Color temp = cube[fi0];
		cube[fi0] = cube[fi1];
		cube[fi1] = cube[fi2];
		cube[fi2] = cube[fi3];
		cube[fi3] = temp;
		
		temp = cube[fi4];
		cube[fi4] = cube[fi5];
		cube[fi5] = cube[fi6];
		cube[fi6] = cube[fi7];
		cube[fi7] = temp;
		
		temp = centers[ci0];
		centers[ci0] = centers[ci1];
		centers[ci1] = centers[ci2];
		centers[ci2] = centers[ci3];
		centers[ci3] = temp;
	}
	
	public void rollSlice180(
			int fi0, int fi1, int fi2, int fi3,
			int fi4, int fi5, int fi6, int fi7,
			int ci0, int ci1, int ci2, int ci3) {
		swapByte(fi0, fi1);
		swapByte(fi2, fi3);
		swapByte(fi4, fi5);
		swapByte(fi6, fi7);
		
		swapC(ci0, ci1);
		swapC(ci2, ci3);
	}
	
	public Color getColor (Face face, int row, int col) {
		if (row == 1 && col == 1) {
			return centers[face.mapping];
		} else {
			int[] unfoldedFace = new int[] {0, 1, 2, 7, 0, 3, 6, 5, 4};
			int index = unfoldedFace[row * 3 + col];
			return cube[face.mapping * 8 + index];
		}
	}
	
	public Color getColor(int index) {
		return cube[index];
	}
	
	public long getFace(Face face) {
		int start = ((int)face.mapping * 8);
		int index = 0;
		for (int i = start; i < start+8; i++) {
			bytes[index] = cube[i].mapping;
		}
		bb.put(bytes, 0, bytes.length);
		long res = bb.getLong(0);
		bb.clear();
		return res;
	}
	
	public boolean isSolved() {
		Color[] solvedColors = new Color[] {Color.RED, Color.BLUE, Color.WHITE,
											Color.GREEN, Color.YELLOW, Color.ORANGE};
		
		for (int f = 0; f < 6; f++) {
			for (int i = 0; i < 8; i++) {
				if (cube[f*8+i] != solvedColors[f]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public byte getCornerIndex(Color[] corners) {
		int hashSum = (HASH << corners[0].mapping + HASH) +
					  (HASH << corners[1].mapping + HASH) +
					  (HASH << corners[2].mapping + HASH);
		
		switch(hashSum) {
		case 88:
			return 0;
		case 76:
			return 1;
		case 14:
			return 2;
		case 26:
			return 3;
		case 50:
			return 4;
		case 112:
			return 5;
		case 100:
			return 6;
		default:
			return 7;	
		}
	}
	
	public byte getCornerOrientation(Color[] corners) {
		switch(corners[0]) {
		case RED:
		case ORANGE:
			return 0;
		case WHITE:
		case YELLOW:
			return 1;
		default:
			return 2;
		}
	}
	
	public byte getEdgeIndex(Color[] edgeColors) {
		int edgeSum = (HASH << edgeColors[0].mapping + HASH) +
				      (HASH << edgeColors[1].mapping + HASH);
		
		switch(edgeSum) {
		case 72:
			return 0;
		case 12:
			return 1;
		case 10:
			return 2;
		case 24:
			return 3;
		case 6:
			return 4;
		case 18:
			return 5;
		case 80:
			return 6;
		case 68:
			return 7;
		case 34:
			return 8;
		case 48:
			return 9;
		case 96:
			return 10;
		default:
			return 11;
		}
	}
	
	public byte getEdgeOrientation(Color[] edgeColors) {
		Color col1 = edgeColors[0], col2 = edgeColors[1];
		if (col1 == Color.BLUE || col1 == Color.GREEN) {
			return 1;
		}
		
		if (col1 == Color.WHITE || col1 == Color.YELLOW) {
			if (col2 == Color.RED || col2 == Color.ORANGE) {
				return 1;
			}
		}
		return 0;
	}
	
	public void u() {
		this.roll90(Face.UP.mapping);
		this.rotateSides90(8, 16, 24, 32, 10, 18, 26, 34);
	}
	
	public void uPrime() {
		this.roll270(Face.UP.mapping);
		this.rotateSides90(32, 24, 16, 8, 34, 26, 18, 10);
	}
	
	public void u2() {
		this.roll180(Face.UP.mapping);
		this.rotateSides180(8, 24, 16, 32, 10, 26, 18, 34);
	}
	
	public void l() {
		this.roll90(Face.LEFT.mapping);
		this.rotateSides90(6, 34, 46, 22, 0, 36, 40, 16);
	}
	
	public void lPrime() {
		this.roll270(Face.LEFT.mapping);
		this.rotateSides90(22, 46, 34, 6, 16, 40, 36, 0);
	}
	
	public void l2() {
		this.roll180(Face.LEFT.mapping);
		this.rotateSides180(6, 46, 34, 22, 0, 40, 36, 16);
	}
	
	public void f() {
		this.roll90(Face.FRONT.mapping);
		this.rotateSides90(4, 10, 40, 30, 6, 12, 42, 24);
	}
	
	public void fPrime() {
		this.roll270(Face.FRONT.mapping);
		this.rotateSides90(30, 40, 10, 4, 24, 42, 12, 6);
	}
	
	public void f2() {
		this.roll180(Face.FRONT.mapping);
		this.rotateSides180(4, 40, 10, 30, 6, 42, 12, 24);
	}
	
	public void r() {
		this.roll90(Face.RIGHT.mapping);
		this.rotateSides90(2, 18, 42, 38, 4, 20, 44, 32);
	}
	
	public void rPrime() {
		this.roll270(Face.RIGHT.mapping);
		this.rotateSides90(38, 42, 18, 2, 32, 44, 20, 4);
	}
	
	public void r2() {
		this.roll180(Face.RIGHT.mapping);
		this.rotateSides180(2, 42, 18, 38, 4, 44, 20, 32);
	}
	
	public void b() {
		this.roll90(Face.BACK.mapping);
		this.rotateSides90(0, 26, 44, 14, 2, 28, 46, 8);
	}
	
	public void bPrime() {
		this.roll270(Face.BACK.mapping);
		this.rotateSides90(14, 44, 26, 0, 8, 46, 28, 2);
	}
	
	public void b2() {
		this.roll180(Face.BACK.mapping);
		this.rotateSides180(0, 44, 26, 14, 2, 46, 28, 8);
	}
	
	public void d() {
		this.roll90(Face.DOWN.mapping);
		this.rotateSides90(12, 36, 28, 20, 14, 38, 30, 22);
	}
	
	public void dPrime() {
		this.roll270(Face.DOWN.mapping);
		this.rotateSides90(20, 28, 36, 12, 22, 30, 38, 14);
	}
	
	public void d2() {
		this.roll180(Face.DOWN.mapping);
		this.rotateSides180(12, 28, 36, 20, 14, 30, 38, 22);
	}
	
	public void mid() {
		this.rollSlice90(1, 37, 41, 17, 5, 33, 45, 21, 0, 4, 5, 2);
	}
	
	public void midPrime() {
		this.rollSlice90(17, 41, 37, 1, 21, 45, 33, 5, 2, 5, 4, 0);
	}
	
	public void mid2() {
		this.rollSlice180(1, 41, 37, 17, 5, 45, 33, 21, 0, 5, 4, 2);
	}
	
	public void equatorial() {
		this.rollSlice90(15, 39, 31, 23, 11, 35, 27, 19, 1, 4, 3, 2);
	}
	
	public void equatorialPrime() {
		this.rollSlice90(23, 31, 39, 15, 19, 27, 35, 11, 2, 3, 4, 1);
	}
	
	public void equatorial2() {
		this.rollSlice180(15, 31, 39, 23, 11, 27, 35, 19, 1, 3, 4, 2);
	}
	
	public void stand() {
		this.rollSlice90(3, 9, 47, 29, 7, 13, 43, 25, 0, 1, 5, 3);
	}
	
	public void standPrime() {
		this.rollSlice90(29, 47, 9, 3, 25, 43, 13, 7, 3, 5, 1, 0);
	}
	
	public void stand2() {
		this.rollSlice180(3, 47, 9, 29, 7, 43, 13, 25, 0, 5, 1, 3);
	}
	
	public void xAxis() {
		this.lPrime();
		this.midPrime();
		this.r();
	}
	
	public void xAxisPrime() {
		this.l();
		this.mid();
		this.rPrime();
	}
	
	public void xAxis2() {
		this.xAxis();
		this.xAxis();
	}
	
	public void yAxis() {
		this.u();
		this.dPrime();
		this.equatorialPrime();
	}
	
	public void yAxisPrime() {
		this.uPrime();
		this.d();
		this.equatorial();
	}
	
	public void yAxis2() {
		this.yAxis();
		this.yAxis();
	}
	
	public void zAxis() {
		this.f();
		this.stand();
		this.bPrime();
	}
	
	public void zAxisPrime() {
		this.fPrime();
		this.standPrime();
		this.b();
	}
	
	public void zAxis2() {
		this.zAxis();
		this.zAxis();
	}
	
	private void swapShort(int i1, int i2) {
		Color temp = cube[i1];
		cube[i1] = cube[i2];
		cube[i2] = temp;
		temp = cube[i1+1];
		cube[i1+1] = cube[i2+1];
		cube[i2+1] = temp;
	}
	
	private void swapByte(int i1, int i2) {
		Color temp = cube[i1];
		cube[i1] = cube[i2];
		cube[i2] = temp;
	}
	
	private void swapC(int i1, int i2) {
		Color temp = centers[i1];
		centers[i1] = centers[i2];
		centers[i2] = temp;
	}
	
	private static long rotateRight(long num, int numBits) {
		return (num >> numBits) | (num << (Long.SIZE - numBits));
	}
	
	private static long rotateLeft(long num, int numBits) {
		return (num << numBits) | (num >> (Long.SIZE - numBits));
	}
	
	public void displayCube() {
		for (int i = 0; i < cube.length; i++) {
			if (i % 8 == 0) System.out.println();
			System.out.print(cube[i] + " ");
		}
		System.out.println();
		for (Color color: centers) {
			System.out.print(color + " ");
		}
	}
}
