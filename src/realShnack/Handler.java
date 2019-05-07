package realShnack;



import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{
	
	LinkedList<Snake> object=new LinkedList<Snake>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			Snake tempObject=object.get(i);
			tempObject.tick();
		}
	}
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			Snake tempObject=object.get(i);
			tempObject.render(g);
		}
	}
	public void add(Snake object) {
		this.object.add(object);
	}
	public void removeObject(Snake object) {
		this.object.remove(object);
	}
	public Snake get(int i) {
		return object.get(i);
	}
	public int size() {
		return object.size();
	}
	public void remove(int i) {
		object.remove(i);
		//System.out.println(size());
	}
}
