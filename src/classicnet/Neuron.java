package classicnet;
import java.util.ArrayList;

public class Neuron {
	public ArrayList<Cell> in,out;
	public double val;
	public Neuron() {
		in=new ArrayList<Cell>();
		out=new ArrayList<Cell>();
	}
	public void addInput(Neuron n,double w) {
		in.add(new Cell(n,w));
	}
	public void addOutput(int w,Neuron n) {
		out.add(new Cell(n,w));
	}
	public void eval() {
		double s=0;
		Cell c;
		int size=in.size();
		for(int i=0;i<size;i++) {
			c=in.get(i);
			s+=c.n.val*c.weight;
		}
		val=s/in.size();
		//System.out.println(in.size());	
	}
	public int inputs() {
		return in.size();
	}
	public int outputs() {
		return out.size();
	}
	public double inweight(int i) {
		return in.get(i).weight;
	}
	public double outweight(int i) {
		return out.get(i).weight;
	}
	public void evolve(int i) {
		double w=in.get(i).weight;
		if(Math.random()>1/2)
		w+=Math.random()/10;
		else
		w-=Math.random()/10;
		if(w>1)
			w=1;
		if(w<=0)
			w=0;
		
	}
	public void evolve(int i,double lr) {
		double w=in.get(i).weight;
		if(Math.random()>1/2)
		w+=lr*Math.random()/10;
		else
		w-=lr*Math.random()/10;
		if(w>1)
			w=1;
		if(w<=0)
			w=0;
		
	}
}
