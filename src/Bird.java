import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Bird {
    private static final Image birdWingDown = new Image("res/birdWingDown.png");
    private static final Image birdWingUp = new Image("res/birdWingUp.png");

    private static final Point START_POINT = new Point(200, 350);
    private static final int FLAP_FRAME = 10;
    // gravity adjusted so that the game is more playable on my machine
    private static final double GRAVITY = -0.1;
    private static final double MAX_FALL_VELOCITY = -10;
    private static final double FLIGHT_SPEED = 6;
    private static final Rectangle hitbox = birdWingDown.getBoundingBoxAt(START_POINT);

    private double x;
    private double y;
    private double velocity;
    private int frameCount = 1;

    public Bird() {
        x = START_POINT.x;
        y = START_POINT.y;
        velocity = 0;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void update(Input input) {
        // set the velocity of the bird to be 6 pixels upwards whenever space bar is pressed
        if (input.wasPressed(Keys.SPACE)) {
            velocity = FLIGHT_SPEED;
        }
        // draw the bird with wings up every 10th frame to simulate the flapping of its wings
        if (frameCount == FLAP_FRAME) {
            birdWingUp.draw(x, y);
            // move the bird hitbox to keep it aligned with the bird image
            hitbox.moveTo(new Point(x - birdWingDown.getWidth() / 2.0, y - birdWingDown.getHeight() / 2.0));
            frameCount = 1;
        } else {
            birdWingDown.draw(x, y);
            // move the bird hitbox to keep it aligned with the bird image
            hitbox.moveTo(new Point(x - birdWingDown.getWidth() / 2.0, y - birdWingDown.getHeight() / 2.0));
            frameCount++;
        }
        // increase the rate at which the bird is falling every frame until the maximum fall velocity is reached
        velocity = Math.max(MAX_FALL_VELOCITY, velocity + GRAVITY);
        y -= velocity;


    }

}
