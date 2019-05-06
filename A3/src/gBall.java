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
    
	public gBall(double Xi, double Yi, double bSize, Color bColor,
			double bLoss, double bVel) {
			this.Xi = Xi; // Get simulation parameters
			this.Yi = Yi;
			this.bSize = bSize;
			this.bColor = bColor;
			this.bLoss = bLoss;
			this.bVel = bVel;
			// Create instance of GOval with specified parameters
			myball = new GOval(Xi, Yi, bSize, bSize);
			myball.setFilled(true);
			myball.setFillColor(bColor);
			
			}
	
	public void run() {
		double G = 9.8;
		double EnergyLoss = bLoss;
        double vt = Math.sqrt(2*G*(600-Yi-bSize)/10);
		
		double Time = 0;
		double timeout = 80;
		
		
		
		double t = vt/G;
		double interval = 0.1;
		
        while (vt>=0.1) {
			
			if (Yi>600-bSize) {
				Yi=600-bSize;
				t=0;
				vt=vt*Math.sqrt(1-bLoss);
			}
			
			Xi=Xi+10*bVel*0.01;
			Yi=(600-bSize)-10*(vt*t-0.5*G*t*t);
			
			Time += 0.01;
			t += 0.01;
			
			myball.setLocation(Xi, Yi);
			
			try {
				Thread.currentThread().sleep(5);
			} 
			catch (Exception e) {}
		}
        
        isRunning=false;
        
    }
	
}