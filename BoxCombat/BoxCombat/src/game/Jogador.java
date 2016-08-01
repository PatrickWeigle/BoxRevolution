package game;

import jplay.Keyboard;
import jplay.Sprite;
import jplay.URL;
import jplay.Window;

public class Jogador extends Sprite{
	
	private double velocity = 0.3;
	private int direcao = 3;
	private Keyboard input;
	private boolean move = false;

	public Jogador(int x, int y) {
		super(URL.sprite("box.png"), 6);
		this.x = x;
		this.y = y;
		this.setTotalDuration(700);
		
		
		// TODO Auto-generated constructor stub
	}
	public void mover(Window janela){
		if (input == null){
			input = janela.getKeyboard();
		}
		//System.out.println(x+ " ,"+ y);
		if (input.keyDown(Keyboard.LEFT_KEY)){
			if (this.x > 27){
				this.x -= velocity;
			}
			if (direcao != 1){
				setSequence(0, 6);
				direcao = 1;
			}
			move = true;
		}else if (input.keyDown(Keyboard.RIGHT_KEY)){
			if (this.x < janela.getWidth()-121){
				this.x += velocity;
			}
			if (direcao != 2){
				setSequence(0, 6);
				direcao = 2;
			}
			move = true;
		}
		
		if (move){
			update();
			move = false;
		}
	}

}
