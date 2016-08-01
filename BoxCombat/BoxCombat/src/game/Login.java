package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jplay.*;

public class Login {
	private Window janela;
	private Scene cena;

	public Login(Window window) {
		janela = window;

		cena = new Scene();
		cena.loadFromFile(URL.scenario("login.scn"));

		run();
	}

	private void run() {
		// TODO Auto-generated method stub
		Keyboard input = janela.getKeyboard();
		while (true) {
			cena.draw();
			janela.drawText("BoxCombat", 100, 100, Color.BLACK, new Font("Arial", Font.PLAIN, 20));
			janela.update();
		
			if (input.keyDown(KeyEvent.VK_ENTER)) {
				janela.setSize(1280, 720);
				new Cenario(janela);
			} else if (input.keyDown(KeyEvent.VK_ESCAPE)) {
				System.exit(0);
			}
		}

	}

}
