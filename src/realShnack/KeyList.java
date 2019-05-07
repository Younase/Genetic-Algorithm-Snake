package realShnack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyList implements KeyListener{
	Handler handler;
	Game g;
	public KeyList(Game g) {
		this.g=g;
		this.handler=g.getHandler();
	}

	public void keyPressed(KeyEvent ev) {
		// TODO Auto-generated method stub
		if(handler.size()>0)
		switch(ev.getKeyCode()) {
		case KeyEvent.VK_UP:handler.get(0).setDir("up");break;
		case KeyEvent.VK_DOWN:handler.get(0).setDir("down");break;
		case KeyEvent.VK_RIGHT:handler.get(0).setDir("right");break;
		case KeyEvent.VK_LEFT:handler.get(0).setDir("left");break;
		case KeyEvent.VK_SPACE:g.step();if(handler.size()!=0)handler.get(0).getenv();break;
		case KeyEvent.VK_ESCAPE:g.running=false;break;
		case KeyEvent.VK_ENTER:g.running=true;break;
		
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
