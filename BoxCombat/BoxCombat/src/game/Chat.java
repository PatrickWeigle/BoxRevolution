package game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import jplay.Keyboard;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

public class Chat extends Sprite {

	
	private Keyboard input;
	private boolean move = false;
	
	

	public Chat() {
		super(URL.sprite(null), 1);
		this.setTotalDuration(3000);

		// TODO Auto-generated constructor stub
	}

	public void printar(Window janela) {
		if (input == null) {
			input = janela.getKeyboard();
			//input.setBehavior(Keyboard.SPACE_KEY, Keyboard.DETECT_EVERY_PRESS);
		}
		// System.out.println(x+ " ,"+ y);
		if (input.keyDown(Keyboard.A_KEY) ) {
			ChatMSN ch = new ChatMSN();
			ch.setVisible(true);
			
		}

		if (move) {
			update();
			move = false;
		}

		// this.x = x;
		Color c = Color.getHSBColor(4f, 0.6f, 0.3f);
		janela.drawText("Chat: AA", 1050, 660, c, new Font("Fipps", Font.PLAIN, 20));
		//janela.drawText(fc, 350, 660, c, new Font("Fipps", Font.PLAIN, 20));

		// System.out.println(getCurrFrame()); -- mostrar o frame atual
	}

}
