
import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class Bouncing extends GraphicsProgram {
	
	double x=0;
	double y=0;
	double vx=1.5;
	double h0=40;
	double g=9.8;
	double vy=Math.sqrt(2*g*h0);
	double t=vy/g;
	double d=1;
	double k=0.5;
	double time=0;
	double timeout=30;

	public void run() {
		
		resize(600,800);
		GOval ball=new GOval(0,400,60,60);
		GLine horizon=new GLine(0,600,800,600);
		add(horizon);
		add(ball);
		ball.setFilled(true);
		ball.setColor(Color.RED);
		while (time<=timeout) {
			
			if (y>540) {
				y=540;
				t=0;
				vy=vy*Math.sqrt(k);
			}
			
			x=x+10*vx*0.01;
			y=540-10*(vy*t-0.5*g*t*t);
			t=t+0.01;
			time=time+0.01;
			
			add(new GOval(x+30,y+30,1,1));
			ball.setLocation(x, y);
			
			try {
				Thread.currentThread().sleep(5);
			} 
			catch (Exception e) {}
		}
	}
}