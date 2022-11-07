package graphics;

import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import peasy.*;

import java.util.HashMap;
import java.util.List;

import controller.CubeSolver;
import model.RubiksCubeDefinitions.Move;
import model.RubiksCubeModel;

import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Visualize extends PApplet {

    public PeasyCam getCam() {
        return cam;
    }

    PeasyCam cam;
Cubie[] cube = new Cubie[27];
Button scrambleButton = new Button("Click me to scramble!", 460, 50);
Button solveButton    = new Button("Click me to solve!", 300, 50);
RubiksCubeModel model = new RubiksCubeModel();
CubeSolver solver = new CubeSolver(model);
private boolean doScramble = false;
private boolean doSolve = false;
private boolean displaySolve = false;
private int index = 0;
private List<Move> moves;
private AnimatedMove animatedMove;
private final int interval = 3000; // 3 seconds
private int time; // milliseconds
private String textToDisplay = "";

private PFont lucidaSansRegular;

 public void setup() {
	 animatedMove = new AnimatedMove(0, 0, 0, 0);
	 time = millis();
  cam = new PeasyCam(this, 400);
     lucidaSansRegular = createFont("Lucida Sans Regular", 12);

  int index = 0;
  for (int i = -1; i <= 1; i++) {
    for (int j = -1; j <= 1; j++) {
      for (int k = -1; k <= 1; k++) {
        PMatrix3D matrix = new PMatrix3D();
        matrix.translate(i, j, k);
        cube[index] = new Cubie(matrix, i, j, k);
        index++;
      }
    }
  }
}

 public void draw() {
	  animatedMove.update();
	  background(200);
	  scale(50);
	  this.solveButton.show();
	  this.scrambleButton.show();

      if (millis() - time > interval) {
          textToDisplay = "";
      }

      cam.beginHUD();
      textFont(lucidaSansRegular, 20);
      fill(0);
      text(textToDisplay, 15, 40, 500, 200);
      cam.endHUD();

     for (Cubie qb : cube) {
         push();
         if (abs(qb.z) > 0 && qb.z == this.animatedMove.z) {
             rotateZ(animatedMove.angle);
         } else if (abs(qb.x) > 0 && qb.x == this.animatedMove.x) {
             rotateX(animatedMove.angle);
         } else if (abs(qb.y) > 0 && qb.y == this.animatedMove.y) {
             rotateY(-animatedMove.angle);
         }
         qb.show();
         pop();
     }
  if (doScramble) {

	  if (frameCount % 2 == 0) {
		  if (index < this.moves.size()) {
			  applyMove(moves.get(index));
			  index++;
		  } else {
			  doScramble = false;
			  index = 0;
			  this.moves.clear();
              time = millis();
              textToDisplay = "The cube has been scrambled!";
		  }
	  }
  }
  
  if (doSolve) {
	  if (frameCount % 3 == 0) {
		  if (index < this.moves.size()) {
			  applyMove(moves.get(index));
			  index++;
		  } else {
			  doSolve = false;
			  index = 0;
              time = millis();
              textToDisplay = "The cube has been solved! Here are the moves it took to solve it: ";
              for (Move move : moves) {
                  textToDisplay += " " + move.name();
              }
              this.moves.clear();
		  }
	  }
  }
}

 public void mouseClicked() {
  if (doScramble || doSolve) return;
  if (mouseX >= 90 && mouseX <= 230 &&
      mouseY >= 500 && mouseY <= 550) {
	  	this.moves = this.solver.scrambleCube();
        doScramble = true;
      } else if (mouseX >= 250 && mouseX <= 390 &&
    		     mouseY >= 500 && mouseY <= 550) {
    	  this.moves = this.solver.solveCube();
    	  doSolve = true;
      }
}

public void applyMove(Move move) {
	switch(move) {
	case L:
		l();
		break;
	case LPRIME:
		lPrime();
		break;
	case L2:
		l2();
		break;
	case R:
		r();
		break;
	case RPRIME:
		rPrime();
		break;
	case R2:
		r2();
		break;
	case U:
		u();
		break;
	case UPRIME:
		uPrime();
		break;
	case U2:
		u2();
		break;
	case D:
		d();
		break;
	case DPRIME:
		dPrime();
		break;
	case D2:
		d2();
		break;
	case F:
		f();
		break;
	case FPRIME:
		fPrime();
		break;
	case F2:
		f2();
		break;
	case B:
		b();
		break;
	case BPRIME:
		bPrime();
		break;
	case B2:
		b2();
		break;
	case X:
		xAxis();
		break;
	case XPRIME:
		xAxisPrime();
		break;
	case X2:
		xAxis2();
		break;
	case Y:
		yAxis();
		break;
	case YPRIME:
		yAxisPrime();
		break;
	case Y2:
		yAxis2();
		break;
	case Z:
		zAxis();
		break;
	case ZPRIME:
		zAxisPrime();
		break;
	case Z2:
		zAxis2();
		break;
	case MID:
		mid();
		break;
	case MIDPRIME:
		midPrime();
		break;
	case MID2:
		mid2();
		break;
	case STAND:
		stand();
		break;
	case STANDPRIME:
		standPrime();
		break;
	case STAND2:
		stand2();
		break;
	case EQUATORIAL:
		equatorial();
		break;
	case EQUATORIALPRIME:
		equatorialPrime();
		break;
	case EQUATORIAL2:
		equatorial2();
		break;
	}
}

 public void turnX(int index, int direction) {
  for (int i = 0; i < cube.length; i++) {
	Cubie c = cube[i];
    if (c.x == index) {
      PMatrix2D matrix = new PMatrix2D();
      matrix.rotate(direction*HALF_PI);
      matrix.translate(c.y, c.z);
      c.update(c.x, round(matrix.m02), round(matrix.m12));
      c.turnFacesX(direction);
    }
  }
}

 public void turnY(int index, int direction) {
     for (Cubie c : cube) {
         if (c.y == index) {
             PMatrix2D matrix = new PMatrix2D();
             matrix.rotate(direction * HALF_PI);
             matrix.translate(c.x, c.z);
             c.update(round(matrix.m02), c.y, round(matrix.m12));
             c.turnFacesY(direction);
         }
     }
}

 public void turnZ(int index, int direction) {
     for (Cubie c : cube) {
         if (c.z == index) {
             PMatrix2D matrix = new PMatrix2D();
             matrix.rotate(direction * HALF_PI);
             matrix.translate(c.x, c.y);
             c.update(round(matrix.m02), round(matrix.m12), c.z);
             c.turnFacesZ(direction);
         }
     }
}
 
 public void xAxis() {
	 lPrime();
	 midPrime();
	 r();
 }
 
 public void xAxisPrime() {
	 l();
	 mid();
	 rPrime();
 }
 
 public void xAxis2() {
	 xAxis();
	 xAxis();
 }
 
 public void yAxis() {
	u();
	dPrime();
	equatorialPrime();
 }
 
 public void yAxisPrime() {
	uPrime();
	d();
	equatorial();
 }
 
 public void yAxis2() {
	 yAxis();
	 yAxis();
 }
 
 public void zAxis() {
	f();
	stand();
	bPrime();
 }
 
 public void zAxisPrime() {
	fPrime();
	standPrime();
	b();
 }
 
 public void zAxis2() {
	 zAxis();
	 zAxis();
 }

 public void u() {
  turnY(-1, 1);
}

 public void uPrime() {
  turnY(-1, -1);
}

 public void u2() {
  u();
  u();
}

 public void r() {
  turnX(1, 1);
}

 public void rPrime() {
  turnX(1, -1);
}

 public void r2() {
  r();
  r();
}

 public void l() {
	 turnX(-1, -1);
}

 public void lPrime() {
  turnX(-1, 1);
}

 public void l2() {
  l();
  l();
}

 public void f() {
  turnZ(1, 1);
}

 public void fPrime() {
  turnZ(1, -1);
}

 public void f2() {
  f();
  f();
}

 public void b() {
  turnZ(-1, -1);
}

 public void bPrime() {
  turnZ(-1, 1);
}

 public void b2() {
  b();
  b();
}

 public void d() {
  turnY(1, -1);
}

 public void dPrime() {
  turnY(1, 1);
}

 public void d2() {
  d();
  d();
}

 public void mid() {
  turnX(0, -1);
}

 public void midPrime() {
  turnX(0, 1);
}

 public void mid2() {
  mid();
  mid();
}

 public void equatorial() {
  turnY(0, -1);
}

 public void equatorialPrime() {
  turnY(0, 1);
}

 public void equatorial2() {
  equatorial();
  equatorial();
}

 public void stand() {
  turnZ(0, 1);
}

 public void standPrime() {
  turnZ(0, -1);
}

 public void stand2() {
  stand();
  stand();
}

class Button {
   String buttonText;
   int xOffset;
   int yOffset;
   
   public Button(String buttonText, int xOffset, int yOffset) {
	   this.buttonText = buttonText;
	   this.xOffset = xOffset;
	   this.yOffset = yOffset;
   }
   
   public void show() {
    cam.beginHUD();
    fill(11, 11, 69);
    rect(width-xOffset, height-yOffset, 140, 50);
    fill(255, 255, 255);
    textSize(12);
    textFont(lucidaSansRegular);
    text(this.buttonText, width-xOffset+10, height-yOffset+30);
    text("x: "+mouseX+" y: "+mouseY, 10, 15);
    cam.endHUD();
  }
}

class Cubie {
  PMatrix3D matrix;
  int x = 0;
  int y = 0;
  int z = 0;
  int c;
  Face[] faces = new Face[6];
  
  Cubie(PMatrix3D mat, int x, int y, int z) {
    this.matrix = mat;
    this.x = x;
    this.y = y;
    this.z = z;
    
    faces[0] = new Face(new PVector(0, 0, -1), color(255,85,37));
    faces[1] = new Face(new PVector(0, 0, 1),  color(137,18,20));
    faces[2] = new Face(new PVector(0, 1, 0),  color(254,213,47));
    faces[3] = new Face(new PVector(0, -1, 0), color(255,255,255));
    faces[4] = new Face(new PVector(1, 0, 0),  color(13,72,172));
    faces[5] = new Face(new PVector(-1, 0, 0), color(25,155,76));
  }
  
   public void turnFacesX(int direction) {
    for (Face f: faces) {
      f.turn(direction*HALF_PI, "x", f.normal.y, f.normal.z);
    }
  }

   public void turnFacesY(int direction) {
    for (Face f: faces) {
      f.turn(direction*HALF_PI, "y", f.normal.x, f.normal.z);
    }
  }
  
   public void turnFacesZ(int direction) {
    for (Face f: faces) {
      f.turn(direction*HALF_PI, "z", f.normal.x, f.normal.y);
    }
  }
  
   public void update(int x, int y, int z) {
    matrix.reset();
    matrix.translate(x, y, z);
    this.x = x;
    this.y = y;
    this.z = z;
    
  }
  
   public void show() {
    //fill(c);
    noFill();
    stroke(0);
    strokeWeight(0.1f);
    pushMatrix();
    applyMatrix(this.matrix);
    
    box(1);
    for (Face f: faces) {
      f.show();
    }
    
    popMatrix();
  }
}
class Face {
  PVector normal;
  int c;
  
  Face(PVector normal, int c) {
    this.normal = normal;
    this.c = c;
  }
  
   public void turn(float angle, String turnAx, float ax1, float ax2) {
    PVector v2 = new PVector();
    float newAx1 = round(ax1 * cos(angle) - ax2 * sin(angle)); 
    float newAx2 = round(ax1 * sin(angle) - ax2 * cos(angle));
    
    switch(turnAx) {
      case "x":
        v2.y = newAx1;
        v2.z = newAx2;
        v2.x = normal.x;
        break;
      case "y":
        v2.x = newAx1;
        v2.z = newAx2;
        v2.y = normal.y;
        break;
      default:
        v2.x = newAx1;
        v2.y = newAx2;
        v2.z = normal.z;
        break;
    }
    normal = v2;
  }
  
   public void show() {
    pushMatrix();
    fill(c);
    noStroke();
    rectMode(CENTER);
    //rotate(HALF_PI, normal.x, normal.y, normal.y);
    translate(0.5f*normal.x, 0.5f*normal.y, 0.5f*normal.z);
    if (abs(normal.x) > 0) {
      rotateY(HALF_PI);
    } else if (abs(normal.y) > 0) {
      rotateX(HALF_PI);
    }
    square(0, 0, 1);
    popMatrix();
  }
}

class AnimatedMove {
	float angle;
	int x, y, z;
	int direction;
	boolean animating = false;
	boolean isDone = false;
	
	public AnimatedMove(int x, int y, int z, int direction) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}
	
	public void start() {
		this.animating = true;
	}
	
	public void update() {
		if (this.animating) {
			angle += direction * 0.1;
			if (abs(angle) > HALF_PI) {
				angle = 0;
				this.animating = false;
				this.isDone = true;
				
				if (abs(this.z)> 0) {
					turnZ(this.z, this.direction);
				} else if (abs(this.x) > 0) {
					turnX(this.x, this.direction);
				} else if (abs(this.y) > 0) {
					turnY(this.y, this.direction);
				}
			}
		}
	}
}



  public void settings() { size(550, 550, P3D); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "graphics.Visualize" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}