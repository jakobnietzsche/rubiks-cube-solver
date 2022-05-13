package model;

import model.RubiksCubeDefinitions.Move;

public class RubiksCubeMover {
	private RubiksCubeModel model;
	
	public RubiksCubeMover(RubiksCubeModel model) {
		this.model = model;
	}
	
	public void move(Move m) {
		switch(m) {
		case L:
			model.l();
			break;
		case LPRIME:
			model.lPrime();
			break;
		case L2:
			model.l2();
			break;
		case R:
			model.r();
			break;
		case RPRIME:
			model.rPrime();
			break;
		case R2:
			model.r2();
			break;
		case U:
			model.u();
			break;
		case UPRIME:
			model.uPrime();
			break;
		case U2:
			model.u2();
			break;
		case D:
			model.d();
			break;
		case DPRIME:
			model.dPrime();
			break;
		case D2:
			model.d2();
			break;
		case F:
			model.f();
			break;
		case FPRIME:
			model.fPrime();
			break;
		case F2:
			model.f2();
			break;
		case B:
			model.b();
			break;
		case BPRIME:
			model.bPrime();
			break;
		case B2:
			model.b2();
			break;
		case X:
			model.xAxis();
			break;
		case XPRIME:
			model.xAxisPrime();
			break;
		case X2:
			model.xAxis2();
			break;
		case Y:
			model.yAxis();
			break;
		case YPRIME:
			model.yAxisPrime();
			break;
		case Y2:
			model.yAxis2();
			break;
		case Z:
			model.zAxis();
			break;
		case ZPRIME:
			model.zAxisPrime();
			break;
		case Z2:
			model.zAxis2();
			break;
		case MID:
			model.mid();
			break;
		case MIDPRIME:
			model.midPrime();
			break;
		case MID2:
			model.mid2();
			break;
		case STAND:
			model.stand();
			break;
		case STANDPRIME:
			model.standPrime();
			break;
		case STAND2:
			model.stand2();
			break;
		case EQUATORIAL:
			model.equatorial();
			break;
		case EQUATORIALPRIME:
			model.equatorialPrime();
			break;
		case EQUATORIAL2:
			model.equatorial2();
			break;
		default:
			System.out.println("Error: The move " + m + " is incorrect. Please try again.");
			break;
		}
	}
	
	public void invert(Move m) {
		switch(m) {
		  case L:
	        model.lPrime();
	        break;
	      case LPRIME:
	        model.l();
	        break;
	      case L2:
	        model.l2();
	        break;
	      case R:
	        model.rPrime();
	        break;
	      case RPRIME:
	        model.r();
	        break;
	      case R2:
	        model.r2();
	        break;
	      case U:
	        model.uPrime();
	        break;
	      case UPRIME:
	        model.u();
	        break;
	      case U2:
	        model.u2();
	        break;
	      case D:
	        model.dPrime();
	        break;
	      case DPRIME:
	        model.d();
	        break;
	      case D2:
	        model.d2();
	        break;
	      case F:
	        model.fPrime();
	        break;
	      case FPRIME:
	        model.f();
	        break;
	      case F2:
	        model.f2();
	        break;
	      case B:
	        model.bPrime();
	        break;
	      case BPRIME:
	        model.b();
	        break;
	      case B2:
	        model.b2();
	        break;
	      case Y:
	        model.yAxisPrime();
	        break;
	      case YPRIME:
	        model.yAxis();
	        break;
	      case Y2:
	        model.yAxis2();
	        break;
	      case X:
	        model.xAxisPrime();
	        break;
	      case XPRIME:
	        model.xAxis();
	        break;
	      case X2:
	        model.xAxis2();
	        break;
	      case Z:
	        model.zAxisPrime();
	        break;
	      case ZPRIME:
	        model.zAxis();
	        break;
	      case Z2:
	        model.zAxis2();
	        break;
	      case MID:
	        model.midPrime();
	        break;
	      case MIDPRIME:
	        model.mid();
	        break;
	      case MID2:
	        model.mid2();
	        break;
	      case EQUATORIAL:
	        model.equatorialPrime();
	        break;
	      case EQUATORIALPRIME:
	        model.equatorial();
	        break;
	      case EQUATORIAL2:
	        model.equatorial2();
	        break;
	      case STAND:
	        model.standPrime();
	        break;
	      case STANDPRIME:
	        model.stand();
	        break;
	      case STAND2:
	        model.stand2();
	        break;
	      default:
	    	  System.out.println("Error: The invert " + m + " is incorrect. Please try again.");
	    	  break;
		}
	}
}
