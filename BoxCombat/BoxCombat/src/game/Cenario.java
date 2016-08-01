package game;
import java.awt.event.KeyEvent;

import jplay.*;
public class Cenario {
	private Window janela;
	private Scene cena;
	private Jogador jogador;
	
	public Cenario(Window window){
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("cenario.scn"));
		jogador = new Jogador(1159, 520);
		
		run();
	}

	private void run() {
		// TODO Auto-generated method stub
		Keyboard input = janela.getKeyboard();
		while(true){
			cena.draw();
			jogador.draw();
			jogador.mover(janela);
			janela.update();
			
			if(input.keyDown(KeyEvent.VK_ESCAPE)){
				janela.setSize(640, 480);
				new Login(janela);
				}
		}
		
	}

}
