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
    private Bubble actual;
    private Bubble next;
    private float incr = 180f / 128f;
    private Point2D center;
    private boolean debug;

  

    public Shuttle(Point2D center) {
        this.center = center;
         this.actual = new Bubble();
        this.angle = 0.0f;
        this.next = new Bubble();
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
        this.actual = this.next;
        this.next = this.generateBall();
        temp.setPosicion(center);
        temp.setAngulo(angle);
        return temp;
    }
    public Point2D calcArrow(){
        Point2D p=null;
        if (this.angle>=90 && this.angle<=90){
            p= new Point2D(o,o);
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
        gc.drawImage(r.getImage("spriters"),
                2,
                1545,
                62,
                41,
                //dibujar en el lienzo
                (this.center.getX() - 61 / 2) * Game.SCALE,
                (this.center.getY() - 41 / 2) * Game.SCALE,
                61 * Game.SCALE,
                41 * Game.SCALE);
    }

    public void moveLeft() {
        this.angle -= this.incr;
        if (this.angle > Shuttle.MIN_ANGLE) {
            this.angle = Shuttle.MIN_ANGLE;
        }
    }

    public void moveRight() {
        this.angle += this.incr;
        if (this.angle < Shuttle.MAX_ANGLE) {
            this.angle = Shuttle.MAX_ANGLE;
        }
    }
}
