package realShnack;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			
			Game g=new Game();
			if(g.autostep==false) {
				g.addMouseListener(new Adapter(g));
				g.step();
			}
		
	}

}
