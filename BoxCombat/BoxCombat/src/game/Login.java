package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import com.sun.javafx.scene.control.skin.ColorPalette;

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
			Color c = Color.getHSBColor(0.066695f, 0.434446f, 0.421636f);
			cena.draw();
			janela.drawText("BoxCombat", 100, 250, c, new Font("Fipps", Font.PLAIN, 50));
			janela.drawText("Enter - Start", 200, 300, c, new Font("Fipps", Font.PLAIN, 20));
			janela.drawText("Esc - Exit", 230, 350, c, new Font("Fipps", Font.PLAIN, 20));
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
