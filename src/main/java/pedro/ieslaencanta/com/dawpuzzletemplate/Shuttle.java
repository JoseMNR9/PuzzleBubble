/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Point2D;

/**
 *
 * @author DAWTarde
 */
public class Shuttle {
    private float angle;
    private static final float MIN_ANGLE=0f;
    private static final float MAX_ANGLE=180.0f;
    private Bubble actual;
    private Bubble next;
    private float incr;
    private Point2D center;
    private boolean debug;
    
    public Shuttle(){
        this.actual = actual;
        this.angle = angle;
        this.next = next;
        this.debug = debug;
        
    }
    public Shuttle(Point2D center){
        this.center = center;
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
    private Bubble generateBall(){
        this.actual.init(center, angle);
        return actual;
    }
}
