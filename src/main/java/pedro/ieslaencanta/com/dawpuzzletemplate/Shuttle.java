/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author DAWTarde
 */
public class Shuttle {

    private float angle;
    private static final float MIN_ANGLE = 0f;
    private static final float MAX_ANGLE = 180.0f;
    private static final int WIDTH = 64;
    private static final int HEIGTH = 64;
    private Bubble actual;
    private Bubble next;
    private float incr = 180f / 128f;
    private Point2D center;
    private boolean debug;

  

    public Shuttle(Point2D center) {
        this.center = center;
         this.actual = new Bubble();
        this.angle = 90.0f;
        this.next = new Bubble();
        
        for(float i=90; i<0; i= i - this.incr){
            
        }
    }

    /**
     * @return the angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * @return the actual
     */
    public Bubble getActual() {
        return actual;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    private Bubble generateBall() {
        Bubble temp;
        temp = new Bubble();
        temp.setBalltype(BubbleType.values()[(int) (Math.random() * BubbleType.values().length)]);
        return temp;
    }

    public Bubble shoot() {
        Bubble temp = this.actual;
        temp.setAngulo(360 - this.angle);
        temp.setPosicion(center);
        this.actual = this.next;
        this.next = this.generateBall();
        
        
        return temp;
    }
    public Point2D calcArrow(){
        Point2D p=null;
        if (this.getAngle()>=90 && this.getAngle()<=90){
            p= new Point2D(0,0);
        }
        int imagen = (int)((90.0f - this.angle)/this.incr);
        if(this.angle < 90){
           int f = imagen/16;
           int c = imagen%16;
          
           if(imagen > 63){
               f--;
               c--;
           }
            p = new Point2D(c,f);
           
        }
        if(this.angle > 90){
            imagen = (int)((180.0f - this.angle)/this.incr);
            int f= 3 - imagen/16 ;
            int c= 15 - imagen%16 ;
            if(imagen > 63){
               f--;
               c--;
            }
            p = new Point2D(c,f);
            
            
        }
       
        return p;
    }
    public void paint(GraphicsContext gc) {
        Resources r = Resources.getInstance();
        gc.drawImage(r.getImage("spriters"),
                1,
                1805,
                61,
                41,
                //dibujar en el lienzo
                (this.center.getX() - 61 / 2) * Game.SCALE,
                (this.center.getY() - 41 / 2) * Game.SCALE,
                61 * Game.SCALE,
                41 * Game.SCALE);
        Image todos= Resources.getInstance().getImage("spriters");
        Point2D p = this.calcArrow();
        if(p != null){
            if(this.angle <= 90){
                gc.drawImage(r.getImage("spriters"),
                        65 * p.getX(),
                        1545 + (65 * p.getY()),
                        Shuttle.WIDTH,
                        Shuttle.HEIGTH,
                        //dibujar en el lienzo
                        (this.center.getX() - Shuttle.WIDTH / 2) * Game.SCALE,
                        (this.center.getY() - Shuttle.HEIGTH / 2) * Game.SCALE,
                        Shuttle.WIDTH * Game.SCALE,
                        Shuttle.HEIGTH * Game.SCALE);
            } else{
                if(this.angle > 90){
                    gc.drawImage(r.getImage("spriters"),
                            65 * p.getX(),
                            1545 + (65 * p.getY()),
                            Shuttle.WIDTH,
                            Shuttle.HEIGTH,
                            //dibujar en el lienzo
                            (this.center.getX() - Shuttle.WIDTH / 2+ Shuttle.WIDTH) * Game.SCALE ,
                            (this.center.getY() - Shuttle.HEIGTH / 2) * Game.SCALE,
                            -Shuttle.WIDTH * Game.SCALE,
                            Shuttle.HEIGTH * Game.SCALE);
                }
            }
        }
    }

    public void moveLeft() {
        this.angle = this.angle - this.incr;
        if (this.angle < Shuttle.MIN_ANGLE) {
            this.angle=Shuttle.MIN_ANGLE;
        }
    }

    public void moveRight() {
        this.angle = this.angle + this.incr;
        if (this.angle > Shuttle.MAX_ANGLE) {
            this.angle=Shuttle.MAX_ANGLE;
        }
    }
}
