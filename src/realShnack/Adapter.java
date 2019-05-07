package realShnack;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Adapter extends MouseAdapter{
	Game g;
	int i=0;
	public Adapter(Game g) {
		this.g=g;
	}

	public void mouseClicked(MouseEvent a) {
		// TODO Auto-generated method stub
		System.out.println("step"+(++i));
		g.step();
		
	}
}
