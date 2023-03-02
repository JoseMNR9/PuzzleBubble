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
public class Ballgrid {
    private int startx;
    private int starty;
    private static final int ROWS= 12;
    private static final int COLS= 8;
    private static final int MIN_BALLS_CONECT= 3;
    private Bubble bubblegrid[][];
    
    public Ballgrid(){
        this.bubblegrid = new Bubble[Ballgrid.ROWS][Ballgrid.COLS];
    }
    public Ballgrid(int startx, int starty){
        this.startx = startx;
        this.starty = starty;
    }
    public Ballgrid(BubbleType matrix[][],int startx, int starty){
        this.startx = startx;
        this.starty = starty;
        //matrix = BubbleType.values();
    }

    /**
     * @return the startx
     */
    public int getStartx() {
        return startx;
    }

    /**
     * @param startx the startx to set
     */
    public void setStartx(int startx) {
        this.startx = startx;
    }

    /**
     * @return the starty
     */
    public int getStarty() {
        return starty;
    }

    /**
     * @param starty the starty to set
     */
    public void setStarty(int starty) {
        this.starty = starty;
    }
    
    public boolean colision(Bubble b){
        int f,c;
        if(b.getPosicion().getY() - (Bubble.WIDTH / 2) <= this.starty){
            b.stop();
            f=(int)((b.getPosicion().getY()-this.starty)/Bubble.HEIGHT);
            c=(int)((b.getPosicion().getX()-this.startx)%Bubble.WIDTH);
           // this.bubblegrid[Ballgrid.ROWS][Ballgrid.COLS]= b;
            //b.getPosicion(new Point2D(b.getPosicion().getY()/16 - (Bubble.WIDTH / 2),b.getPosicion().getX()%16 - (Bubble.WIDTH / 2)));
            return true;
        }else
            return false;
    }
}
