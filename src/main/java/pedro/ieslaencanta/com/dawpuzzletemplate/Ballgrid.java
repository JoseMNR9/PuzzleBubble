/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author DAWTarde
 */
public class Ballgrid {

    private int startx;
    private int starty;
    private static final int ROWS = 12;
    private static final int COLS = 8;
    private static final int MIN_BALLS_CONECT = 3;
    private Bubble bubblegrid[][];

    public Ballgrid() {
        this.bubblegrid = new Bubble[Ballgrid.ROWS][Ballgrid.COLS];
    }

    public Ballgrid(int startx, int starty) {
        this.startx = startx;
        this.starty = starty;
        this.bubblegrid = new Bubble[Ballgrid.ROWS][Ballgrid.COLS];
    }

    public Ballgrid(BubbleType matrix[][], int startx, int starty) {
        this.startx = startx;
        this.starty = starty;
        this.bubblegrid = new Bubble[Ballgrid.ROWS][Ballgrid.COLS];
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

    public boolean colision(Bubble b) {
        int f = 0, c = 0;
        boolean colision = false;
        if (b.getPosicion().getY() - (Bubble.WIDTH / 2) <= this.starty) {
            b.stop();
            f = (int) ((b.getPosicion().getY() - this.starty) / Bubble.HEIGHT);
            c = (int) ((b.getPosicion().getX() - this.startx) / Bubble.WIDTH);
            this.bubblegrid[f][c] = b;
            //calculamos la posicion de la bola donde tenemos que moverla(dist*pos+1/2de la dist)
            //La primera burbuja de la matriz
            b.setPosicion(new Point2D(this.startx + Bubble.WIDTH * c + Bubble.WIDTH / 2,
                    this.starty + Bubble.HEIGHT * f + Bubble.HEIGHT / 2));
            return true;
        } else {
            //Recorremos la matriz de burbujas
            for (int i = 0; i < this.bubblegrid.length && !colision; i++) {
                for (int j = 0; j < this.bubblegrid[i].length && !colision; j++) {
                    //Le damos posicion  a las demas burbujas
                    if (this.bubblegrid[i][j] != null && b.getPosicion().distance(this.bubblegrid[i][j].getPosicion()) <= 16) {
                        b.stop();
                        colision = true;
                        f = (int) ((b.getPosicion().getY() - this.starty) / Bubble.HEIGHT);
                        c = (int) ((b.getPosicion().getX() - this.startx) / Bubble.WIDTH);
                        this.bubblegrid[f][c] = b;
                        //Las burbuja en las filas pares
                        if (j % 2 == 0) {
                            //c = (int)((b.getPosicion().getX() - this.startx - Bubble.WIDTH / 2) / Bubble.WIDTH);
                            b.setPosicion(new Point2D( this.startx + Bubble.WIDTH * c + Bubble.WIDTH / 2,
                        this.starty + Bubble.HEIGHT * f + Bubble.HEIGHT / 2));
                        //Las burbujas en las filas impares    
                        } else {
                           c = (int)((b.getPosicion().getX() - this.startx - Bubble.WIDTH / 2) / Bubble.WIDTH);
                            
                            b.setPosicion(new Point2D( this.startx + Bubble.WIDTH * c + Bubble.WIDTH,
                        this.starty + Bubble.HEIGHT * f + Bubble.HEIGHT / 2));
                        }
                    }

                }
            }
            return colision;
        }
    }
    public void explosion (Bubble e){
        
    }
    //Metodo para pintar las burbujas
    public void paint(GraphicsContext gc) {
        for (int i = 0; i < this.bubblegrid.length; i++) {
            for (int j = 0; j < this.bubblegrid[i].length; j++) {
                if (this.bubblegrid[i][j] != null) {
                    this.bubblegrid[i][j].paint(gc);
                }

            }
        }
    }
}
