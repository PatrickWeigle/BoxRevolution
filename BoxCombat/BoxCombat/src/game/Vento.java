package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import jplay.Keyboard;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

public class Vento extends Sprite {

	private int direcao = 3;
	private Keyboard input;
	private boolean move = false;
	private boolean volta = false;
	private boolean reinicia = false;

	public Vento() {
		super(URL.sprite(null), 1);
		this.setTotalDuration(3000);

		// TODO Auto-generated constructor stub
	}

	public void ventar(Window janela) {
		if (input == null) {
			input = janela.getKeyboard();
			//input.setBehavior(Keyboard.SPACE_KEY, Keyboard.DETECT_EVERY_PRESS);
		}
		// System.out.println(x+ " ,"+ y);
		if (input.keyDown(Keyboard.SPACE_KEY) ) {
			
		}

		if (move) {
			update();
			move = false;
		}

		// this.x = x;
		Color c = Color.getHSBColor(5f, 0.6f, 0.3f);
		janela.drawText("Vento: ", 560, 70, c, new Font("Fipps", Font.PLAIN, 20));
		//janela.drawText("", 350, 660, c, new Font("Fipps", Font.PLAIN, 20));

		// System.out.println(getCurrFrame()); -- mostrar o frame atual
	}

}
