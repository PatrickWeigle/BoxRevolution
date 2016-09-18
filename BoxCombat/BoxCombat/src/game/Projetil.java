package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import jplay.Keyboard;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

public class Projetil extends Sprite {

	private boolean fire = false;
	private Keyboard input;
	private boolean move = true;
	private double velocity = 1.9;
	private int direcao = 0;

	public Projetil(int x, int y) {
		super(URL.sprite("axe_gold.png"), 7);
		this.x = x;
		this.y = y;
		
		this.setTotalDuration(700);

		// TODO Auto-generated constructor stub
	}

	public void atirar(Window janela, boolean fire, int x, int y) {
		
		
		
		this.x += velocity;
		setSequence(0, 7);
		move = true;
		//direcao = 1;
		
				
		//if (move){
			update();
			//move = false;
		//}
		
		// this.x = x;
		Color c = Color.getHSBColor(5f, 0.6f, 0.3f);
		//janela.drawText("Vento: ", 560, 70, c, new Font("Fipps", Font.PLAIN, 20));
		//janela.drawText("", 350, 660, c, new Font("Fipps", Font.PLAIN, 20));

		// System.out.println(getCurrFrame()); -- mostrar o frame atual
	}

}