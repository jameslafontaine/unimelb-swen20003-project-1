import bagel.*;
import bagel.util.Point;

public class Pipe {
    private static final Image pipe = new Image("res/pipe.png");

    private static final int PIPE_GAP = 168;
    // step size adjusted so that the game is more playable on my machine
    private static final int STEP_SIZE = 3;

    private double x;
    private double y;

    private DrawOptions drawOptions = new DrawOptions();

    public Pipe() {
        x = ShadowFlap.getWindowWidth() - pipe.getWidth() / 2.0;
        y = ShadowFlap.getWindowHeight() / 2.0 - pipe.getHeight() / 2.0 - PIPE_GAP / 2.0;
    }

    public void update() {
        // draw the top pipe and then the bottom pipe based off the top pipe's coordinates
        pipe.draw(x, y);
        pipe.draw(x, y + pipe.getHeight() + PIPE_GAP, drawOptions.setRotation(Math.PI));
        // move the pipes towards the left border of the screen
        x -= STEP_SIZE;

    }
}
