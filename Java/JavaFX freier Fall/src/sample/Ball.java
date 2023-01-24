package sample;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball {
    public double g; // acceleration of gravity
    public double t; // time
    public double s; // air travel

    public double h; // current height

    public Controller c = null;

    public Ball(double g, double t, double s) {
        this.g = g;
        this.t = t;
        this.s = s;
        this.h = s;
        this.c = Main.loader.getController();
    }

    public void Drop(){
        c.DrawCircle("red", 10, 100, 500 - h * 50); // Draw red circle (the ball)
        while (!TouchedGround()){
            Step(); // calculates the upcoming height of the ball
            Platform.runLater(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    public synchronized void Step(){
        t += 0.01;
        h = s - (g * t*t / 2);

        c.circle.setCenterX(100);
        c.circle.setCenterY(500 - h * 50);

        System.out.println("Current height: " + h);
    }
    private boolean TouchedGround(){
        if (h <= 0) { return true; }
        return false;
    }
}
