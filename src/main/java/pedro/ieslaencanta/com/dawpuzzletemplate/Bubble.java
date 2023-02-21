/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author DAWTarde
 */
public class Bubble {
    private enum State {
        PLAY,
        STOP
    }
    private boolean debug = false;
    private State estado;
    private Point2D posicion;
    private float angulo = 0;
    private float velocidad = 1f;
    private BubbleType balltype;
    public static int WIDTH = 16, HEIGHT = 16;
    public Bubble(double x, double y, BubbleType balltype) {
    this.estado = State.STOP;
    this.posicion = new Point2D(x, y);
    this.balltype = balltype;
    }
    public Bubble() {
        this.estado = State.STOP;
    }
    public void init(Point2D p, float angle) {
        int vertical_center = (int) (p.getX());
        int horizontal_center = (int) (p.getY());
        this.angulo = angle;
        this.posicion = new Point2D(vertical_center, horizontal_center);
        BubbleType[] balltypes = BubbleType.values();
        this.setBalltype(balltypes[(int) (Math.random() * balltypes.length)]);
        this.stop();
    }
    public boolean isPlay() {
        return this.estado == State.PLAY;
    }
    public void changeDirectionHorizontal() {
        this.setAngulo(180.0f - this.getAngulo());
    }
    public void changeDirectionVertical() {
        this.setAngulo(360.0f - this.getAngulo());
    }
    public boolean move(Rectangle2D b) {
        boolean pared = false;
        if (this.estado == State.PLAY) {
            float x = (float) ((float) getVelocidad() *
                     Math.cos(Math.toRadians(getAngulo())));
                    float y = (float) ((float) getVelocidad() *
                    Math.sin(Math.toRadians(getAngulo())));
            this.posicion = this.getPosicion().add(x, y);
            //izquierda
            if (this.getPosicion().getX() - (Bubble.WIDTH / 2) < b.getMinX()) {
                pared = true;
                this.changeDirectionHorizontal();
            } else {
                //derecha
                    if (this.getPosicion().getX() + (Bubble.WIDTH / 2) >
                    b.getMinX() + b.getWidth()) {
                    pared = true;
                    this.changeDirectionHorizontal();
                    } else {
                    //parte inferior
                    if ((this.getPosicion().getY() + (Bubble.HEIGHT / 2)) -
                        (b.getMinY() + b.getHeight()) >= 0) {
                        pared = true;
                        this.changeDirectionVertical();
                    } //parte superior, no debe rebotar
                        else if (this.getPosicion().getY() - (Bubble.HEIGHT / 2) <=
                                b.getMinY()) {
                                pared = true;
                                this.changeDirectionVertical();
                            }
                        }
                    }
                }
            return pared;
            }
    public void play() {
        this.estado = State.PLAY;
    }
    public void stop() {
        this.estado = State.STOP;
    }
     /**
     * @return the angulo
     */
    public float getAngulo() {
        return angulo;
    }
    /**
    * @param angulo the angulo to set
    */
    public void setAngulo(float angulo) {
        this.angulo = angulo;
        if (this.angulo < 0) {
        this.angulo += 360;
    }
    if (this.angulo >= 360) {
        this.angulo = this.angulo % 360;
       }
 }
    public void addAngulo(float angulo) {
        this.angulo += angulo;
        if (this.angulo >= 360) {
            this.angulo = this.angulo % 360;
        }
        if (this.angulo < 0) {
            this.angulo += 360;
        }
    }
 /**
 * @return the velocidad
 */
 public float getVelocidad() {
    return velocidad;
 }
 /**
 * @param velocidad the velocidad to set
 */
 public void setVelocidad(float velocidad) {
    this.velocidad = velocidad;
 }
 /**
 * @return the posicion
 */
 public Point2D getPosicion() {
    return posicion;
 }
 /**
 * @param posicion the posicion to set
 */
 public void setPosicion(Point2D posicion) {
    this.posicion = posicion;
 }
 /**
 * @return the balltype
 */
 public BubbleType getBalltype() {
    return balltype;
 }
 /**
 * @param balltype the balltype to set
 */
 public void setBalltype(BubbleType balltype) {
    this.balltype = balltype;
 }
 /**
 * @return the debug
 */
 public boolean isDebug() {
    return debug;
 }
 /**
 * @param debug the debug to set
 */
 public void setDebug(boolean debug) {
    this.debug = debug;
 }
 public void paint(GraphicsContext gc) {
    Resources r = Resources.getInstance();
    gc.drawImage(r.getImage("balls"),
    //inicio de la posicion
    this.getBalltype().getX(),
    this.getBalltype().getY(),
    Bubble.WIDTH,
    Bubble.HEIGHT,
    //dibujar en el lienzo
    (this.posicion.getX() - Bubble.WIDTH / 2) * Game.SCALE,
     (this.posicion.getY() - Bubble.HEIGHT / 2) * Game.SCALE,
    Bubble.WIDTH * Game.SCALE,
    Bubble.HEIGHT * Game.SCALE);
     //si se esta depurando
     if (this.debug) {
        gc.setStroke(Color.RED);
        gc.fillOval(this.getPosicion().getX() * Game.SCALE - 5,(this.getPosicion().getY()) * Game.SCALE - 5, 10, 10);
        gc.setStroke(Color.GREEN);
        gc.strokeText(this.angulo + "º x:" + this.getPosicion().getX() + "y:" + this.getPosicion().getY(), (this.getPosicion().getX() - WIDTH / 2) *
        Game.SCALE, (this.getPosicion().getY() - HEIGHT / 2) * Game.SCALE);
    }
 }
 public boolean collision(Bubble ball) {
    return true;
 }
 public String toString() {
    return "x:" + this.posicion.getX() + " y:" + this.posicion.getY() + "nagulo:" + this.angulo + " w:" + Bubble.WIDTH + " h:" + Bubble.HEIGHT;
 }
}
