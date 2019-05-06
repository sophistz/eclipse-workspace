import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class gBall extends Thread{

	double Xi;
	double Yi;
	double bSize;
	Color bColor;
	double bLoss;
	double bVel;
    public GOval myball;
    
    boolean isRunning=true;
    
	public gBall(double Xi, double Yi, double bSize, Color bColor,double bLoss, double bVel) {
			this.Xi = Xi; 
			this.Yi  = Yi;
			this.bSize = bSize;
			this.bColor = bColor;
			this.bLoss = bLoss;
			this.bVel = bVel;
			myball = new GOval(Xi, Yi, bSize, bSize);
			myball.setFilled(true);
			myball.setFillColor(bColor);
			vt = Math.sqrt(2*G*(bSim.HEIGHT-Yi-bSize)/10);
			t = vt/G;
			//System.out.println(vt);
	}
	
	double G = 9.8;
	double EnergyLoss = bLoss;
    double vt;
	
	double Time = 0;
	double timeout = 80;
	
	double t;
	double interval = 0.1;
	
	public void run() {
		
		while (vt>=0.1) {
			
			if (Yi>bSim.land.getY()-bSize) {
				Yi=bSim.land.getY()-bSize;
				t=0;
				vt=vt*Math.sqrt(1-bLoss);
			}
			
			if(Xi<bSim.wall1.getX())bVel=Math.abs(bVel);
			if(Xi>bSim.wall2.getX()-bSize)bVel=-Math.abs(bVel);
			Xi=Xi+10*bVel*0.01;
			Yi=(bSim.HEIGHT-bSize)-10*(vt*t-0.5*G*t*t);
			Time += 0.01;
			t += 0.01;
			
			myball.setLocation(Xi, Yi);
			
			try {
				Thread.currentThread().sleep(3);
			} 
			catch (Exception e) {}
		}
        
        isRunning=false;
        
        
    }
	
}