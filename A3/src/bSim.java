import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

public class bSim extends GraphicsProgram{
	private static final int WIDTH = 1200; // n.b. screen coords
	private static final int HEIGHT = 600;
	private static final int OFFSET = 200;
	private static final int NUMBALLS = 100; // # balls to simulation
	private static final double MINSIZE = 1; // Min ball size
	private static final double MAXSIZE = 5; // Max ball size
	private static final double XMIN = 10; // Min X start location
	private static final double XMAX = 50; // Max X start location
	private static final double YMIN = 50; // Min Y start location
	private static final double YMAX = 100; // Max Y start location
	private static final double EMIN = 0.4; // Min loss coefficient
	private static final double EMAX = 0.9; // Max loss coefficient
	private static final double VMIN = 0.5; // Min X velocity
	private static final double VMAX = 3.0; // Max Y velocity
	RandomGenerator rgen = new RandomGenerator();
	public GLine land;
	
	gBall[] Balls;
	
	Boolean isRunning=true;
	
	String CR="CR to continue";
	GLabel hint=new GLabel(CR);
	
	bTree myTree=new bTree();
	
	public void run() {
		
		
		this.resize(WIDTH,HEIGHT);
		Balls = new gBall[NUMBALLS];
		

		
		hint.setLocation(WIDTH-150,580);
		hint.setColor(Color.RED);
		add(hint);
		
		for (int i = 0;i<Balls.length;i++) {
			double Xi = rgen.nextDouble(XMIN,XMAX);
			double Yi = rgen.nextDouble(YMIN,YMAX);
			double bSize = rgen.nextDouble(10*MINSIZE,10*MAXSIZE);
			Color bColor = rgen.nextColor();
			double bloss = rgen.nextDouble(EMIN,EMAX);
			double bVel = rgen.nextDouble(VMIN,VMAX);
			
			Balls[i] = new gBall(Xi,Yi,bSize,bColor,bloss,bVel);
			
			myTree.addNode(Balls[i]);
			
		}
		
		land = new GLine(0,600,10000,600);
		add(land);
		
		for (int i = 0;i<Balls.length;i++) {
			add(Balls[i].myball);
			Balls[i].start();
		}
		
		while(isRunning) {
			isRunning=false;
			for(int i=0;i<NUMBALLS;i++) {
				if(Balls[i].isRunning)isRunning=true;
			}
		}
		
		hint.setLabel("Click mouse to continue");
		waitForClick();
		hint.setLabel("All sorted!");
		myTree.moveSort();
		
	}
	
}