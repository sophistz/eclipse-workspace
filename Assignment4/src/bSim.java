import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import acm.graphics.*;
import acm.gui.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.event.*;

public class bSim extends GraphicsProgram implements ChangeListener{
	static int WIDTH = 1500; // n.b. screen coords
	static int HEIGHT = 600;
	static final int OFFSET = 200;
	static int NUMBALLS = 15; // # balls to simulation
	static double MINSIZE = 1; // Min ball size
	static double MAXSIZE = 5; // Max ball size
	static double XMIN = 10; // Min X start location
	static double XMAX = 50; // Max X start location
	static double YMIN = 50; // Min Y start location
	static double YMAX = 100; // Max Y start location
	static double EMIN = 0.4; // Min loss coefficient
	static double EMAX = 0.9; // Max loss coefficient
	static double VMIN = 0.5; // Min X velocity
	static double VMAX = 10.0; // Max Y velocity
	
	RandomGenerator rgen = new RandomGenerator();
	
	gBall Balls;
	
	public static Boolean isRunning=true;
	
	bTree myTree=new bTree();
	
	JSlider sizeSlider=new JSlider(10,80,45);
	JLabel size=new JLabel("bSize");
	DoubleField bVelText=new DoubleField(10);
	JLabel bVelT=new JLabel("bVel");
	DoubleField bLossText=new DoubleField(0.5);
	JLabel bLossT=new JLabel("bLoss");
	JComboBox<String> bC=new JComboBox();
	
	JButton start=new JButton("start");
	JButton sort=new JButton("sort");
	JButton clear=new JButton("clear");
	JButton quit=new JButton("quit");
	
	static GLine land = new GLine(0,HEIGHT,10000,HEIGHT);
	static GLine wall2=new GLine(WIDTH-500,0,WIDTH-500,HEIGHT);
	static GLine wall1=new GLine(50,0,50,HEIGHT);
	JLabel instruction1=new JLabel("I have changed some functions from the orignal code and hope it would be more fun.");
	JLabel instruction2=new JLabel("You can click start button the generate as many balls as you wish, balls will bounce back when thet hit the walls,");
	JLabel instruction3=new JLabel("and you can sort the balls whenever its correspending thread is ended");
	
	sliderBox NumballsSlider=new sliderBox("NUMBALLS",1,15,25);
	sliderBox MinsizeSlider=new sliderBox("MIN SIZE",1,1,15);
	sliderBox MaxsizeSlider=new sliderBox("MAX SIZE",1,15,15);
	sliderBox XminSlider=new sliderBox("X MIN",1,1,200);
	sliderBox XmaxSlider=new sliderBox("X MAX",1,200,200);
	sliderBox YminSlider=new sliderBox("Y MIN",1,1,100);
	sliderBox YmaxSlider=new sliderBox("Y MAX",1,100,100);
	sliderBox LossminSlider=new sliderBox("LOSS MIN",0,5,10);
	sliderBox LossmaxSlider=new sliderBox("LOSS MAX",0,10,10);
	sliderBox XvelminSlider=new sliderBox("X VEL MIN",0,0,10);
	sliderBox XvelmaxSlider=new sliderBox("X VEL MAX",0,10,10);


	
	public void init() {
		
		add(new JLabel("General Simulation Parameters"),EAST);
		add(new JLabel(" "),EAST);
		
		add(NumballsSlider.myPanel,EAST);
		add(MinsizeSlider.myPanel,EAST);
		add(MaxsizeSlider.myPanel,EAST);
		add(XminSlider.myPanel,EAST);
		add(XmaxSlider.myPanel,EAST);
		add(YminSlider.myPanel,EAST);
		add(YmaxSlider.myPanel,EAST);
		add(LossminSlider.myPanel,EAST);
		LossminSlider.sReadout.setText((double)LossminSlider.mySlider.getValue()/10+"");
		add(LossmaxSlider.myPanel,EAST);
		LossmaxSlider.sReadout.setText((double)LossmaxSlider.mySlider.getValue()/10+"");
		add(XvelminSlider.myPanel,EAST);
		add(XvelmaxSlider.myPanel,EAST);
		
		add(new JLabel(" "),EAST);
		add(new JLabel(" "),EAST);
		
		add(new JLabel("Single Ball Instance Parameters"),EAST);
		
		add(new JLabel(" "),EAST);
		
		add(size,EAST);
		add(sizeSlider,EAST);		
		add(bVelT,EAST);
		add(bVelText,EAST);
		add(bLossT,EAST);
		add(bLossText,EAST);
		add(bC,EAST);

		add(start,NORTH);
		add(clear,NORTH);
		add(sort,NORTH);
		add(quit,NORTH);
		
		bC.addItem("red");
		bC.addItem("orange");
		bC.addItem("yellow");
		bC.addItem("green");
		bC.addItem("cyan");
		bC.addItem("blue");
		bC.addItem("pink");
		
		NumballsSlider.mySlider.addChangeListener((ChangeListener)this);
		MinsizeSlider.mySlider.addChangeListener((ChangeListener)this);
		MaxsizeSlider.mySlider.addChangeListener((ChangeListener)this);
		XminSlider.mySlider.addChangeListener((ChangeListener)this);
		XmaxSlider.mySlider.addChangeListener((ChangeListener)this);
		YminSlider.mySlider.addChangeListener((ChangeListener)this);
		YmaxSlider.mySlider.addChangeListener((ChangeListener)this);
		LossminSlider.mySlider.addChangeListener((ChangeListener)this);
		LossmaxSlider.mySlider.addChangeListener((ChangeListener)this);
		XvelminSlider.mySlider.addChangeListener((ChangeListener)this);
		XvelmaxSlider.mySlider.addChangeListener((ChangeListener)this);
		
		LossminSlider.minLabel.setText("0");
		LossminSlider.maxLabel.setText("1");
		LossmaxSlider.minLabel.setText("0");
		LossmaxSlider.maxLabel.setText("1");
		
		addMouseListeners();
		addActionListeners();
		
		this.resize(WIDTH+100,HEIGHT+100);
		add(land);
		add(wall1);
		add(wall2);
		add(instruction1,200,HEIGHT+10);
		add(instruction2,200,HEIGHT+25);
		add(instruction3,200,HEIGHT+40);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("start")) {
			
			NUMBALLS=NumballsSlider.getISlider();
			MINSIZE=MinsizeSlider.getISlider();
			MAXSIZE=MaxsizeSlider.getISlider();
			XMIN=XminSlider.getISlider();
			XMAX=XmaxSlider.getISlider();
			YMIN=YminSlider.getISlider();
			YMAX=YmaxSlider.getISlider();
			EMIN=(double)LossminSlider.getISlider()/10;
			EMAX=(double)LossmaxSlider.getISlider()/10;
			VMIN=XvelminSlider.getISlider();
			VMAX=XvelmaxSlider.getISlider();
			
			for (int i = 0;i<NUMBALLS;i++) {
				double bSize = rgen.nextDouble(10*MINSIZE,10*MAXSIZE);
				double Xi = rgen.nextDouble(XMIN,XMAX);
				double Yi = rgen.nextDouble(XMIN,YMAX);
				Color bColor = rgen.nextColor();
				double bloss = rgen.nextDouble(EMIN,EMAX);
				double bVel = rgen.nextDouble(VMIN,VMAX);
				
				Balls = new gBall(Xi,Yi,bSize,bColor,bloss,bVel);
				
				myTree.addNode(Balls);
				add(Balls.myball);
				Balls.start();
			}
			
		}
		
		if(e.getActionCommand().equals("sort")) {
			myTree.ballLocation=50;
			try {
				myTree.moveSort();
			}
			catch(Exception ex) {
				println("Please click the starting button first.");
			}
		}
		
		if(e.getActionCommand().equals("clear")) {
			removeAll();
			myTree=new bTree();
			add(land);
			add(wall1);
			add(wall2);
			add(instruction1,200,HEIGHT+10);
			add(instruction2,200,HEIGHT+25);
			add(instruction3,200,HEIGHT+40);
		}
		
		if(e.getActionCommand().equals("quit")) {
			System.exit(0);
		}
		
	}
	
	public GObject gobj;
	public GPoint last;
	public double lastX;
	public double lastY;
	public GOval clickedOval;
	public bNode clickedNode;
	gBall temp;
	bNode tempNode;
	
	public static bTree tempTree=new bTree();
	
	public void mousePressed(MouseEvent e) {
		lastX=e.getX();
		lastY=e.getY();
		last=new GPoint(e.getPoint());
		gobj=getElementAt(lastX,lastY);
		
		if(gobj==land||gobj==wall1||gobj==wall2)gobj=null;
		
		if(!(gobj==null)) {
			
			clickedOval=(GOval)gobj;
			clickedNode = myTree.findNode(clickedOval);
			temp=clickedNode.data;
			
			try {
				temp.suspend();
			}
			catch (Exception q) {}
		}
		else {
			
			clickedOval=null;
			clickedNode=null;
			temp=null;
			println("Null Click!");
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(!(gobj==null)) {
			
			clickedOval.move(e.getX()-last.getX(), e.getY()-last.getY());
			last=new GPoint(e.getPoint());
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(temp!=null) {
			
			String color=(String)bC.getSelectedItem();
			if(color.equals("red"))temp.bColor=Color.red;
			if(color.equals("orange"))temp.bColor=Color.ORANGE;
			if(color.equals("yellow"))temp.bColor=Color.YELLOW;
			if(color.equals("green"))temp.bColor=Color.green;
			if(color.equals("cyan"))temp.bColor=Color.cyan;
			if(color.equals("blue"))temp.bColor=Color.BLUE;
			if(color.equals("pink"))temp.bColor=Color.pink;
			temp.bSize=sizeSlider.getValue();
			temp.bVel=bVelText.getValue();
			temp.bLoss=bLossText.getValue();
			
			if((clickedOval.getY()+clickedOval.getHeight())>=land.getY()-1) {
				clickedOval.setLocation(clickedOval.getX(),land.getY()-clickedOval.getHeight()-1);
				temp.bVel=0;
			}
			
			myTree.reorder(myTree.root,temp);
			myTree=tempTree;
			tempTree=new bTree();
			
			temp.Xi=clickedOval.getX();
			temp.Yi=clickedOval.getY();
			
			remove(temp.myball);
			gBall newgBall=new gBall(temp.Xi,temp.Yi,temp.bSize,temp.bColor,temp.bLoss,temp.bVel);
			add(newgBall.myball);
			newgBall.start();
			myTree.addNode(newgBall);
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source=(JSlider)e.getSource();
		
		if(source==NumballsSlider.mySlider) {
			NumballsSlider.setISlider(NumballsSlider.getISlider());
		}
		if(source==MinsizeSlider.mySlider) {
			MinsizeSlider.setISlider(MinsizeSlider.getISlider());
		}
		if(source==MaxsizeSlider.mySlider) {
			MaxsizeSlider.setISlider(MaxsizeSlider.getISlider());
		}
		if(source==XminSlider.mySlider) {
			XminSlider.setISlider(XminSlider.getISlider());
		}
		if(source==XmaxSlider.mySlider) {
			XmaxSlider.setISlider(XmaxSlider.getISlider());
		}
		if(source==YminSlider.mySlider) {
			YminSlider.setISlider(YminSlider.getISlider());
		}
		if(source==YmaxSlider.mySlider) {
			YmaxSlider.setISlider(YmaxSlider.getISlider());
		}
		if(source==LossminSlider.mySlider) {
			LossminSlider.sReadout.setText(((double)LossminSlider.getISlider()/10)+"");
		}
		if(source==LossmaxSlider.mySlider) {
			LossmaxSlider.sReadout.setText(((double)LossmaxSlider.getISlider()/10)+"");
		}
		if(source==XvelminSlider.mySlider) {
			XvelminSlider.setISlider(XvelminSlider.getISlider());
		}
		if(source==XvelmaxSlider.mySlider) {
			XvelmaxSlider.setISlider(XvelmaxSlider.getISlider());
		}
	}
	
}
