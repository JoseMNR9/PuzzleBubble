/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawpuzzletemplate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Reloj, cada cierto tiempo (Hz lanza un evento a las 
 * clases que implementne IWarnClock
 * @author Administrador
 * @see IWarnClock
 * 
 */
public class Clock implements Runnable {

    private int frecuency;
    private float delta;

    private long time;
    private Thread t;
    private ArrayList<IWarnClock> observers;
    /**
     * estados en los que se puede encontrar el reloj
     */
    public enum ClockState {
        STARTED,
        STOPED,
        PAUSED,
        CLOSED
    }
    private ClockState state;
    /**
     * constructor
     * @param frecuency 
     */
    public Clock(int frecuency) {
        this.frecuency = frecuency;
        this.delta = (1.0f / (float) frecuency) * 100;
        this.time = System.currentTimeMillis();
        this.observers = new ArrayList<IWarnClock>();
        this.state = ClockState.STOPED;
        this.t = null;
    }
    /**
     * 
     * @param frecuency 
     */
    public void setFrecuency(int frecuency) {
        this.frecuency = frecuency;
        this.delta = (1.0f / (float) frecuency) * 100;
    }
    /***
     * 
     * @return 
     */
    public int getFrecuency() {
        return this.frecuency;
    }
    /**
     * incrementa la frecuencia
     */
    public void incFrecuency() {
        this.frecuency++;
        this.delta = (1.0f / (float) frecuency) * 100;
    }
    /**
     * decrementa la frecuenca
     */
    public void decFrencuecy() {
        this.frecuency--;
        this.delta = (1.0f / (float) frecuency) * 100;
    }
    /**
     * añade un escuchador
     * @param element 
     */
    public void addIWarClock(IWarnClock element) {
        this.observers.add(element);
    }
    /**
     * elimina un escuchador
     * @param index 
     */
    public void removeIWarClock(int index) {
        if (this.observers.get(index) != null) {
            this.observers.remove(index);
        }
    }
    /**
     * lanza los eventos a los escuchadores
     */
    private void update() {

        for (Iterator<IWarnClock> i = this.observers.iterator(); i.hasNext();) {
            i.next().TicTac();

        }
    }
    /**
     * inicia el reloj
     */
    public void start() {
        if (this.t == null) {
            this.t = new Thread(this);
            this.t.start();
            this.state = ClockState.STARTED;
        }
    }
    /**
     * parar el reloj
     */
    public void stop() {
        this.state = ClockState.STOPED;
    }
    /**
     * pausar el reloj
     */
    public void pause() {
        this.state = ClockState.PAUSED;
    }
    /**
     * cerrar el reloj
     */
    public void close() {
        this.state = ClockState.CLOSED;
    }
    /**
     * 
     * @return 
     */
    public ClockState state() {
        return this.state;
    }
    /**
     * inicial el funcionamiento del reloj, con un hilo
     */
    @Override
    public void run() {
        long actual;
        while (this.state != ClockState.CLOSED) {
            //  if (this.state == ClockState.STARTED) {
            actual = System.currentTimeMillis();
            if (actual - this.time > this.delta) {

                try {
                    this.update();
                    this.time = actual;
                    Thread.sleep((long) this.delta);
                } catch (InterruptedException ex) {
                    // Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //  }
        }

    }

    public float getDelta() {
        return delta;
    }

}
