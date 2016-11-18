package client;



import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Box {
    
    public int id;
    public int x; 
    public int y; 
    public int hp = 10; 
    public boolean canShoot = true;
    private int shootTime = 20; 
    public Shape body; 
    
    private Image grafico;
    private Animation anima;
    private int bodyWidth = 50; 
    private int bodyHeight = 80;
    
    public Box(int id, int x, int y){
        this.id = id;
        this.x = x + 32;
        this.y = y + 8;
        try {
            init(); 
        } catch (Exception e) {
        }
    }
    
    public void init() throws SlickException{
        if(id == 0){ 
            grafico = new Image("assets/nave0.png"); 
            Image image = new Image("assets/box.png");
            SpriteSheet sprite = new SpriteSheet(image, 40, 64);
            anima = new Animation(sprite, 30);
        } else { 
            grafico = new Image("assets/nave1.png");
            Image image = new Image("assets/box.png");
            SpriteSheet sprite = new SpriteSheet(image, 40, 64);
            anima = new Animation(sprite, 30);
        }
        
        body = new Rectangle(x, y, bodyWidth, bodyHeight);
        
    }
    
    int counter = 0; 
    public void update(GameContainer gc, StateBasedGame sbg, int delta){
       
        if(!canShoot){  
            counter++; 
            
            if(counter > shootTime){ 
                canShoot = true; 
                counter = 0; 
            }
            
        }
        //---------------------------
    
        body.setX(x);
        body.setY(y);
    }
    

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
        g.setColor(Color.red);
        grafico.draw(x-32, y - 8);
        //g.draw(body);
    }
    
}
