package sample;

import static sample.Controller.imageView;

public class BackgroundImageScalingLoop implements Runnable {
    @Override
    public void run() {
        int direction = 1;
        while (true){

            for (int i = 0; i < 1000; i++) {
                imageView.setFitHeight(imageView.getFitHeight() + 0.2 * 0.7 * direction);
                imageView.setFitWidth(imageView.getFitWidth() + 0.2 * 1.2 * direction);
                imageView.setY(imageView.getY() + 0.05 * 1.2 * direction);
                imageView.setX(imageView.getX() + 0.05 * 0.7 * direction);
                try {
                    Thread.sleep((long)(1/1000*Math.pow(i-500, 2)+10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            direction *= -1;
        }
    }
}