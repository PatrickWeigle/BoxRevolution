package client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Principal extends BasicGameState {
    private Socket socket = Cnx.socket; 
    private DataInputStream in; 
    private DataOutputStream out; 
    private boolean net_CIMA = false;
    private boolean net_BAIXO = false;
    private boolean net_TIRO = false;
    private boolean net_PULO = false;
    private boolean jump = false;

   
    private boolean initialized = false; 
    private Image bg; 
    private Image gameOver; 
    private String gameOver_message; 
    private Box player0; 
    private Box player1; 
   
    private int playersSpeed = 5; 
    private TrueTypeFont font2; 
    private boolean flag_gameOver = false; 
    private ArrayList<Tiro> tiros = new ArrayList<Tiro>(); 

    public Principal(int state) {
    }

   
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        if (!initialized) {

            initRedes(); 
            bg = new Image("assets/bg.png"); 

           
            gameOver = new Image("assets/gameOver.png");

            player0 = new Box(0, 0, 330); 
            player1 = new Box(1, 730, 330); 

            initialized = true; 

            font2 = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 28), false); 
        }
    }

  
    public void initRedes() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            new Thread(new EscutaServidor()).start();
        } catch (Exception e) {
        }
    }


  
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (!flag_gameOver) { 
            inputManager(gc.getInput()); 
            netInputManager(gc.getInput()); 

          
            if (player0.x < 60) {
                player0.x = 60;
            }
            if (player0.x > 800) {
                player0.x = 800;
            }

            if (player1.x < 60) {
                player1.x = 60;
            }
            if (player1.x > 800) {
                player1.x = 800;
            }
            
            if (gc.getInput().isKeyPressed(Input.KEY_UP)) {           
                player0.y -= 55;
                
            
            if(!tecla_pulo_flag){
                tecla_pulo_flag = true;
                try {
                    out.writeUTF("pulo");
                } catch (Exception ex) {
                }
            }
            
        }
            
            
            //-------------------------------------------
            player0.update(gc, sbg, delta); 
            player1.update(gc, sbg, delta);

           
            for (int i = 0; i < tiros.size(); i++) {
                tiros.get(i).update(gc, sbg, delta);
            }

          
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).x >= -50 && tiros.get(i).x <= 900) {
                } else {
                    tiros.remove(i);
                    break;
                }
            }

           
            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).body.intersects(player0.body)) { 
                    player0.hp--; 
                    tiros.remove(i); 
                    break;
                }

                if (tiros.get(i).body.intersects(player1.body)) {
                    player1.hp--;
                    tiros.remove(i);
                    break;
                }
            }

            
            if (player0.hp < 0) { 
                flag_gameOver = true; 
                gameOver_message = "Perdeu! Caixa Inutil!"; 
            } else if (player1.hp < 0) { 
                flag_gameOver = true; 
                gameOver_message = "Vencedor!"; 
            }

        } else { 
            if (gc.getInput().isMousePressed(0)) {
                if (gc.getInput().getMouseX() > 540
                        && gc.getInput().getMouseX() < 660
                        && gc.getInput().getMouseY() > 290
                        && gc.getInput().getMouseY() < 322) { 
                    
                    System.gc(); 
                    gc.exit();

                }
            }
        }




    }


    private boolean tecla_cima_flag = false;
    private boolean tecla_baixo_flag = false;
    private boolean tecla_pulo_flag = false;

    public void inputManager(Input input) {
        String fala = "";
        
        if (input.isKeyDown(Input.KEY_RIGHT)) { 
            if (player0.x <= player1.x-50){
            player0.x += playersSpeed; 
             try {
                    out.writeUTF("baixo"); 
                } catch (Exception ex) {
                }
            if(!tecla_baixo_flag){
                tecla_baixo_flag = true;
                try {
                    out.writeUTF("baixo");
                } catch (Exception ex) {
                }
            }
            }
        } else if (input.isKeyDown(Input.KEY_LEFT)) { 
            player0.x -= playersSpeed;

            if(!tecla_cima_flag){
                tecla_cima_flag = true;
                try {
                    out.writeUTF("cima");
                } catch (Exception ex) {
                }
            }
            
        }else if (input.isKeyPressed(Input.KEY_C)){
            ChatMSN c = ChatMSN.getInstance();
                           // c.setConv();
                       c.setVisible(true);
           
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            if (player0.canShoot) { 
                player0.canShoot = false;
                tiros.add(new Tiro(player0.id, player0.x + 50, player0.y + 32)); 

                try {
                    out.writeUTF("tiro");
                } catch (Exception ex) {
                }

            }
        }
        //
        if (!input.isKeyDown(Input.KEY_RIGHT)) { 
            if(tecla_baixo_flag){
                tecla_baixo_flag = false;
                try {
                    out.writeUTF("dbaixo"); 
                } catch (Exception ex) {
                }
            }

        }
        if (!input.isKeyDown(Input.KEY_LEFT)) { 
            if(tecla_cima_flag){
                tecla_cima_flag = false;
                try {
                    out.writeUTF("dcima");
                } catch (Exception ex) {
                }
            }    
        }
        
        if (!input.isKeyDown(Input.KEY_UP)) { 
            if(tecla_pulo_flag){
                tecla_pulo_flag = false;
                player0.y += 55;
                try {
                    out.writeUTF("dpulo");
                } catch (Exception ex) {
                }
            }

        }



    }

 
    public void netInputManager(Input input) {
       
        if (net_BAIXO) { 
            if (player1.x >= player0.x+50){
            player1.x -= playersSpeed;
            }
        } else if (net_CIMA) { 
            player1.x += playersSpeed;
        }else if (net_PULO) {
            player1.y -= 55;
            jump = true;
        }
        

        if (net_TIRO) { 
            if (player1.canShoot) {
                player1.canShoot = false;
                tiros.add(new Tiro(player1.id, player1.x - 30, player1.y + 32));
            }
        }
        
        net_TIRO = false;
        net_PULO = false;
        
        if(jump){
            player1.y += 55;
            jump = false;
        }
        
    }

 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        bg.draw(0, 0); 
        player0.render(gc, sbg, grphcs); 
        player1.render(gc, sbg, grphcs); 

        for (int i = 0; i < tiros.size(); i++) { 
            tiros.get(i).render(gc, sbg, grphcs);
        }

        if (flag_gameOver) { 

            gameOver.draw(0, 0); 
            font2.drawString(300, 175, gameOver_message);

        }

    }

    @Override
    public int getID() {
        return 0;
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    private class EscutaServidor implements Runnable {

     
        @Override
        public void run() {

            String s;

            try {
                while (true) {
                    s = in.readUTF();

                    if (s.equals("baixo")) {
                        net_CIMA = false;
                        net_BAIXO = true;
                    } else if (s.equals("cima")) {
                        net_CIMA = true;
                        net_BAIXO = false;
                    } else if(s.equals("pulo")){
                        net_PULO = true;
                        player1.y -= 55;
                    }else if (s.equals("tiro")) {
                        net_TIRO = true;
                    } else if(s.equals("dcima")){
                        net_CIMA = false;
                    } else if(s.equals("dbaixo")){
                        net_BAIXO = false;
                    }else if(s.equals("dpulo")){
                        net_PULO = false;
                        player1.y += 55;
                    }else{
                        
                         ChatMSN c = ChatMSN.getInstance();
                           c.setConv(s);                         
                       c.setVisible(true);
                    }


                }
            } catch (Exception ex) {
            }
        }
    }
}
