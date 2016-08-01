package game;

import jplay.Window;

public class Main {


	
	public static void main(String[] args) {

		Window janela = new Window(640, 480);
		new Login(janela);
		//GameImage menu = new GameImage(URL.sprite("Menu_Box.png"));
		janela.update();
	}

}
