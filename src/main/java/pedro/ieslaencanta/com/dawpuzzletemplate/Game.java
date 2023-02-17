/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;


import javafx.geometry.Dimension2D;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


/**
 * Se encarga de leer el teclado,
 * imagen de fondo 
 * además del reloj Hz (parar e iniciar
 * @author Administrador
 * @see Clock Board
 */
public class Game implements IWarnClock, IKeyListener {

    public static final int SCALE = 3;
    public static Image imagenes = null;
    public static Clock clock = new Clock(10);

    private Dimension2D original_size;
    private Board board;
    private GraphicsContext ctx, bg_context;
    /**
     * constructor
     * @param context
     * @param bg_context
     * @param original 
     */
    public Game(GraphicsContext context, GraphicsContext bg_context,
            Dimension2D original) {//, Dimension2D real) {
        this.ctx = context;
        this.bg_context = bg_context;
        this.original_size = original;
        this.initBoard();
        Game.clock.addIWarClock(this);
       
    }
    /**
     * inicia el tablero
     */
    private void initBoard(){
         this.board=new Board(original_size);
         this.board.setBackGroundGraphicsContext(bg_context);
         this.board.setGraphicsContext(ctx);
        
    }
    public void start() {
        this.clock.start();
         this.board.paintBackground();
    }

    public void stop() {
        this.clock.stop();
    }

    @Override
    public synchronized void TicTac() {
        this.board.TicTac();
      
    }

    @Override
    public void onKeyPressed(KeyCode code) {
         this.board.onKeyPressed(code);
        if (code == KeyCode.ADD) {
            Game.clock.incFrecuency();
        }

    }

    @Override
    public void onKeyReleased(KeyCode code) {
         this.board.onKeyReleased(code);
        if (code == KeyCode.SUBTRACT) {
            Game.clock.decFrencuecy();
        }
    }

    private void paint() {
       /* Resources r = Resources.getInstance();
        this.bg_context.drawImage(r.getImage("fondos"), 16, 16, this.original_size.getWidth(), this.original_size.getHeight(),
                0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
  */
    }

}
