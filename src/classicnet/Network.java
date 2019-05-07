package classicnet;
import realShnack.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;





////////////////////////////////   NEAT	 RECURSIVITE (EVAL)     THEN    CLEAR    VALUES   
public class Network {
	public LinkedList<Neuron[]> hidden;
	public Neuron in[];
	public Neuron out[];
	Random r;
	Neuron bias;
	double newn,lr,mr;//mutation rate
//	public Network(int in,int out,int numh,int nph) {//number of hidden layers //neuron per hidden
//		r=new Random();
//		lr=1;
//		mr=0.05;
//		bias=new Neuron();
//		bias.val=1;
//		hidden=new Neuron[numh][nph];
//		this.in=new Neuron[in];
//		this.out=new Neuron[out];
//		///////////INITIALISING NEURONS
//		for(int i=this.out.length-1;i>=0;i--) {
//			this.out[i]=new Neuron();
//			this.out[i].addInput(bias,r.nextDouble());
//		}
//			
//		
//		for(int i=this.hidden.length-1;i>=0;i--) 
//			for(int j=this.hidden[i].length-1;j>=0;j--) {
//				//System.out.println(j);
//				this.hidden[i][j]=new Neuron();
//				this.hidden[i][j].addInput(bias, r.nextDouble());
//			}
//		
//		for(int i=this.in.length-1;i>=0;i--) 
//			this.in[i]=new Neuron();
//		///////MAKING LINKS
//		if(hidden.length!=0) {
//		for(int i=this.out.length-1;i>=0;i--) {
//			for(int j=this.hidden[hidden.length-1].length-1;j>=0;j--)
//			this.out[i].addInput(this.hidden[hidden.length-1][j],r.nextDouble() );
//		}
//		
//		for(int i=hidden.length-1;i>0;i--) 
//			for(int j=this.hidden[i].length-1;j>=0;j--)
//				for(int k=this.hidden[i-1].length-1;k>=0;k--)
//					this.hidden[i][j].addInput(hidden[i-1][k], r.nextDouble());
//			
//		
//		
//		for(int i=hidden[0].length-1;i>=0;i--) 
//			for(int j=this.in.length-1;j>=0;j--)
//			this.hidden[0][i].addInput(this.in[j],r.nextDouble() );}
//		else {
//			for(int i=this.out.length-1;i>=0;i--)
//				for(int j=this.in.length-1;j>=0;j--)
//					this.out[i].addInput(this.in[j],r.nextDouble() );
//		}
//		
//		
//	}
//	
	
	public Network(int in,int out,int[] h) {//number of hidden layers //neuron per hidden
		r=new Random();
		lr=1;
		mr=0.05;
		bias=new Neuron();
		bias.val=1;
		hidden=new LinkedList<>();
		this.in=new Neuron[in];
		this.out=new Neuron[out];
		///////////INITIALISING NEURONS
		for(int i=this.out.length-1;i>=0;i--) {
			this.out[i]=new Neuron();
			this.out[i].addInput(bias,r.nextDouble());
		}
			
		
		for(int i=h.length-1;i>=0;i--) {
			hidden.addFirst(new Neuron[h[i]]);
			Neuron[] t=hidden.get(0);
			for(int j=t.length-1;j>=0;j--) {
				//System.out.println(j);
				t[j]=new Neuron();
				t[j].addInput(bias, r.nextDouble());
			}}
		
		for(int i=this.in.length-1;i>=0;i--) 
			this.in[i]=new Neuron();
		///////MAKING LINKS
		if(hidden.size()!=0) {
		for(int i=this.out.length-1;i>=0;i--) {
			Neuron[] t=hidden.get(hidden.size()-1);
			for(int j=t.length-1;j>=0;j--)
			this.out[i].addInput(t[j],r.nextDouble() );
		}
		r=new Random();
		for(int i=hidden.size()-1;i>0;i--) {
			Neuron[] t1=hidden.get(i);
			for(int j=t1.length-1;j>=0;j--) {
				Neuron[] t2=hidden.get(i-1);
				for(int k=t2.length-1;k>=0;k--) {
					t1[j].addInput(t2[k], r.nextDouble());
				}
			}
		}
			
		
		Neuron[] t=hidden.get(0);
		for(int i=t.length-1;i>=0;i--) 
			for(int j=this.in.length-1;j>=0;j--)
			t[i].addInput(this.in[j],r.nextDouble() );}
		else {
			for(int i=this.out.length-1;i>=0;i--)
				for(int j=this.in.length-1;j>=0;j--)
					this.out[i].addInput(this.in[j],r.nextDouble() );
		}
		
		
	}
	public Network() {
		
	}
	public void eval(double[] M) {
		int i;
		//System.out.println(M);
		for(i=in.length-1;i>=0;i--) {
			
			in[i].val=M[i];}
		//for(i=stray.size()-1;i>=0;i--)
			//stray.get(i).eval();
		for(i=hidden.size()-1;i>=0;i--) {
			Neuron[] t=hidden.get(i);
			for(int j=t.length-1;j>=0;j--)
				t[j].eval();
		}
	
//		for(i=0;i<hidden.length;i++)
//			for(int j=0;j<hidden[i].length;j++)
//				hidden[i][j].eval();
	
		for(i=out.length-1;i>=0;i--) {
			
			out[i].eval();
			//System.out.println(out[i].val);
		}
			
	}
	public void evolve() {
		int i,j,k;
	//	System.out.println(hidden.size());
		for(int s=0;s<=mr*hidden.size();s++) {
			i=r.nextInt(hidden.size());
		if(i>=hidden.size()-1) {
			i=r.nextInt(this.out.length);
		this.out[i].evolve(r.nextInt(this.out[i].inputs()),lr);
		}
		else {
		//for(i=this.hidden.length-1;i>=0;i--) {
			
			//for(j=this.hidden[i].length-1;j>-0;j--) {
			//k=r.nextInt(this.hidden[i].length-1);
			Neuron[] t=hidden.get(i);
			j=r.nextInt(t.length);	
			t[j].evolve(r.nextInt(t[j].inputs()),lr);}
		}
	}


	public Network clone() {
		//Network net=new Network(in.length,out.length,hidden.size(),hidden.size()==0?0:hidden.get(0).length);
		int[] n=new int[hidden.size()];
		for(int i=0;i<hidden.size();i++) {
			n[i]=hidden.get(i).length;
		}
			Network net=new Network(in.length,out.length,n);
			net.out=this.out.clone();
			net.in=this.in.clone();
			net.hidden=new LinkedList<>();
			for(int i=hidden.size()-1;i>=0;i--)
				net.hidden.add(hidden.get(i).clone());
			net.lr=this.lr;
		return net;
	}
	public void setLR(double lr) {
		this.lr=lr;
	}
	public String toString() {
		return "in:"+in.length+"	hidden:"+hidden.size()+"	out:"+out.length;	}
}
