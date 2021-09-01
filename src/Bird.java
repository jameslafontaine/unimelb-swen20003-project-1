import bagel.*;
import bagel.util.Point;

public class Bird {
    private static final Image birdWingDown = new Image("res/birdWingDown.png");
    private static final Image birdWingUp = new Image("res/birdWingUp.png");
    private static final Point START_POINT = new Point(200, 350);
    private int frameCount = 1;

    private double x;
    private double y;

    public Bird() {
        x = START_POINT.x;
        y = START_POINT.y;
    }
    public void update(Input input) {
        // draw the bird with wings up every 10th frame to simulate the flapping of its wings
        if (frameCount == 10) {
            birdWingUp.draw(x, y);
            frameCount = 1;
        } else {
            birdWingDown.draw(x, y);
            frameCount++;
        }



    }

}
