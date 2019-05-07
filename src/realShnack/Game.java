package realShnack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Game extends Canvas implements Runnable{
	private realShnack.Handler handler;
	//private Foreground fg;
	public static int w=100,h=100,step=20;
	public boolean running=true,auto=true;
	private Thread thread;
	static ArrayList<Snake>  s,cimtiere;
	static int[] scores;
	static realShnack.Window window;
	Snake tempo;
	double threshold=2.8,thresholdinc=0.3;//////////increase size based on length (add length (&more colors))
	boolean solo=true,autostep=true;
	int currFPS,FPS=10,kids=1000,gen=0,maxGen=100,epoch=10,epochafter100=10,ce=0;//current epoch
	int j,i,er=40,maxScore=0;//elimination rate(er%)
	//er=40 lr=0.001 OG
	///////76783 er=20 kids=100 maxGen=100 epoch=100 1 hidden 10 neurons
	//////74706  same same same 2 hidden 10 neurons
	///////63163  2 hidden 5 neurons
	///////66755     5 hidden 5 neurons
	///////        25 random kids 1 hidden 20 neurons
	///////   200 kids 25 rand 1h 20 n
	
	
	

	public void start() {
		thread=new Thread(this);
		thread.start();
		//running=true;
		
	}
	public void run() {
		
		long lft;
		currFPS=0;
		while(running) {
		long lastTime= System.nanoTime();
		i=epoch;
		if(gen%epoch!=0) {
		while(i!=0) {tick();i--;}
		}
	
		else {
			handler.get(0).getenv();////////////////////////////////////////////////////////////////////////////////////////////////////<----
			tick();
			render();
			////////////////////////////////fps control
			currFPS++;
			long delta=System.nanoTime()-lastTime;
			delta/=1000000;
			if(FPS-delta<=0)
				delta=FPS-1000;
			try {
				Thread.sleep(1000/FPS-delta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//////////////////////////////
		}
		if(gen==100) {
			epoch=epochafter100;
		//	autostep=false;
		System.out.println(maxScore);	
		}
		

		
		
		
		///////////////////////////////////////////////////////////////////////////////
		
		
		
		
		}
	}
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void tick(){
		for(int i=handler.size()-1;i>=0;i--) {
			tempo=handler.get(i);
			//System.out.println(tempo.x+" : "+tempo.y);
			if(tempo.dead()) {
				cimtiere.add(tempo);
				//System.out.println(tempo.score);
				handler.remove(i);
			}
			else
				if(auto==true) {
			
					tempo.think();
				}
				
			tempo.tick();
			//System.out.println(tempo.x+" : "+tempo.y);
			tempo.getenv();
			
			
		}
		if(handler.size()==0&&auto) {
			cimtiere.sort(null);
			maxScore+=cimtiere.get(cimtiere.size()-1).score;
			
			
			
			
//			int score=0;
//			for (int i=cimtiere.size()-1;i>=0;i--) {
//				score+=cimtiere.get(i).score;
//			}
//			int e;
//			for(int i=0;i<kids;i++) {
//				e=score*(int)Math.random();
//				//System.out.println(cimtiere.size());
//				j=cimtiere.size()-1;
//				
//				while(j>0&&cimtiere.get(j).score>e) {
//					j--;}
//				if(cimtiere.size()-1>=j+1)
//					handler.add(cimtiere.get(j+1).evolve());
//				else
//					handler.add(cimtiere.get(j).evolve());
//			}
			
			
			
			
			
			for(int i=0;i<kids;i++) 
				handler.add(cimtiere.get(cimtiere.size()-1-i/2).evolve());
//			
			
			
			//////////////////////////////////////////////////////////////////
//			for(int i=0;i<kids/4;i++) 
//				handler.add(cimtiere.get(cimtiere.size()-1).evolve());
//			for(int i=kids/4;i<kids/2;i++) 
//				handler.add(cimtiere.get(cimtiere.size()-2).evolve());
//			for(int i=kids/2;i<3*kids/4;i++) 
//				handler.add(cimtiere.get(cimtiere.size()-3).evolve());
//			for(int i=3*kids/4;i<kids-10;i++) 
//				handler.add(cimtiere.get(cimtiere.size()-4).evolve());
//			for(int i=0;i<10;i++)
//				handler.add(cimtiere.get((int)(cimtiere.size()*Math.random())).evolve());
		/////////////////////////////////////////////////////////////////////////////////////////
			
			
			
			//gen 50 1072
//			for(int i=0;i<kids-25;i++) 
//				handler.add(cimtiere.get(cimtiere.size()-1-i).evolve());
//			for(int i=0;i<22;i++)
//			handler.add(cimtiere.get((int)(cimtiere.size()*Math.random())).evolve());
//			for(int i=0;i<3;i++)
//				handler.add(new Snake());			
//			
			
			////////////////////////////////////////////////////////////////////////////////////////////
			

			
			
			
			System.out.println("gen:"+gen);
			scores[gen%scores.length]=cimtiere.get(cimtiere.size()-1).score;
			if(gen%scores.length!=0) {
				System.out.println(scores[gen%scores.length]+"  "+scores[gen%scores.length-1]);
			
			//	System.out.println("Imp="+(double)scores[gen%scores.length]/(double)scores[gen%scores.length-1]);
				}
			
			System.out.println("top score:"+cimtiere.get(cimtiere.size()-1).score);
			System.out.println("!top score:"+cimtiere.get(0).score);
			maxScore+=cimtiere.get(cimtiere.size()-1).score;
			
			
			
			/////////////////progressive evolution
			if(cimtiere.get(cimtiere.size()-1).score>500*(w/step)*(h/step)/threshold) {
				threshold+=thresholdinc;
				h+=50;w+=50;
				this.setSize(h, w);
				this.setMaximumSize(new Dimension(h, w));
				this.setMinimumSize(new Dimension(h, w));
				window.setSize(w,h);
			}
			cimtiere.clear();
			i=1;//end epoch
			gen++;
				//handler.add(new Snake());
		}
//		for(int i=0;i<kids;i++) {
//		s.tick();
//		}
	}
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g= bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		
		if(solo) {
			if(handler.size()>0)
				handler.get(0).render(g);
		}
		else
			for(int i=handler.size()-1;i>=0;i--) 
				handler.get(i).render(g);
		
//		for(int i=0;i<kids;i++) {
//			
//			s.render(g);
//		}
		//handler.render(g);
		//fg.render(g);
		g.dispose();
		bs.show();
	}
	
	public realShnack.Handler getHandler() {
		return handler;
	}
	public void step() {
		tick();
		render();
	}
	public Game() {
		scores=new int[1000];
		handler=new realShnack.Handler();
		cimtiere=new ArrayList<Snake>();
		if(auto==false)
			kids=1;
		Snake sn;
		for(int i=0;i<kids;i++) {
			sn=new Snake();
			sn.brain.setLR(0.001);
			handler.add(sn);
		}
		System.out.println(new Snake().brain.toString());
			
		window=new realShnack.Window(w,h,"",this);
		addKeyListener(new KeyList(this));
		this.setSize(h, w);
		this.setMaximumSize(new Dimension(h, w));
		this.setMinimumSize(new Dimension(h, w));
		running=true;
	}
	

}
