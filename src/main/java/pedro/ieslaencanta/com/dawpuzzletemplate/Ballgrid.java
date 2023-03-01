/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

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
        if(b.getPosicion().getY() - (Bubble.WIDTH / 2) <= this.starty){
            b.stop();
            return true;
        }else
            return false;
    }
}
