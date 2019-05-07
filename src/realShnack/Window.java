package realShnack;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
public class Window extends Canvas{

	private static final long serialVersionUID = 9034494958129720942L;
	JFrame frame;
	public Window(int width,int height,String title,Game game) {
		frame=new JFrame(title);
		frame.setPreferredSize(new Dimension((int)(width+0.1*width),(int)(height+0.2*height)));
		//frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension((int)(width+0.1*width),(int)(height+0.2*height)));
		frame.setDefaultCloseOperation(3);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		if(game.autostep)
			game.start();
//addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				switch(e.getKeyCode()) {
//				case 38:Game.s.get(0).setDir("up") ;break;
//				case 40:Game.s.get(0).setDir("down");break;
//				case 39 :Game.s.get(0).setDir("right"); break;
//				case 37 :Game.s.get(0).setDir("left"); break;
//				}		
//			}
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
	}
	public void setSize(int width,int height) {
		frame.setPreferredSize(new Dimension((int)(width+0.1*width),(int)(height+0.2*height)));
		//frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension((int)(width+0.1*width),(int)(height+0.2*height)));
	}
}

