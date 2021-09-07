import bagel.*;
import bagel.util.Colour;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Pipe {
    private static final Image pipe = new Image("res/pipe.png");

    private static final int PIPE_GAP = 168;
    // step size adjusted so that the game is more playable on my machine
    private static final int STEP_SIZE = 3;
    private static final Point START_POINT_TOP = new Point(ShadowFlap.getWindowWidth(),
                                       ShadowFlap.getWindowHeight() / 2.0 - pipe.getHeight() / 2.0 - PIPE_GAP / 2.0);
    private static final Point START_POINT_BOTTOM = new Point(ShadowFlap.getWindowWidth(),
                                       ShadowFlap.getWindowHeight() / 2.0 + pipe.getHeight() / 2.0 + PIPE_GAP / 2.0);
    private final Rectangle hitboxTop = pipe.getBoundingBoxAt(START_POINT_TOP);
    private final Rectangle hitboxBottom = pipe.getBoundingBoxAt(START_POINT_BOTTOM);

    private double xTop;
    private double yTop;
    private double xBottom;
    private double yBottom;

    private DrawOptions drawOptions = new DrawOptions();

    public Pipe() {
        xTop = START_POINT_TOP.x;
        yTop = START_POINT_TOP.y;
        xBottom = START_POINT_BOTTOM.x;
        yBottom = START_POINT_BOTTOM.y;
    }

    public Rectangle getHitBoxTop() {
        return hitboxTop;
    }

    public Rectangle getHitboxBottom() {
        return hitboxBottom;
    }

    public double getRightX() {
        return hitboxTop.right();
    }

    public void update() {
        // move the pipes towards the left border of the screen
        xTop -= STEP_SIZE;
        xBottom -= STEP_SIZE;

        // draw the top and bottom pipes
        pipe.draw(xTop, yTop);
        pipe.draw(xBottom, yBottom, drawOptions.setRotation(Math.PI));

        // move the pipe hitboxes to keep them aligned with the pipes
        hitboxTop.moveTo(new Point(xTop - pipe.getWidth() / 2.0, yTop - pipe.getHeight() / 2.0));
        hitboxBottom.moveTo(new Point(xBottom - pipe.getWidth() / 2.0, yBottom - pipe.getHeight() / 2.0));

        // visualise the pipe hitboxes

        // Drawing.drawRectangle(new Point(xTop - pipe.getWidth() / 2.0, yTop - pipe.getHeight() / 2.0),
        //         pipe.getWidth(), pipe.getHeight(), Colour.WHITE);
        // Drawing.drawRectangle(new Point(xBottom - pipe.getWidth() / 2.0, yBottom - pipe.getHeight() / 2.0),
        //         pipe.getWidth(), pipe.getHeight(), Colour.WHITE);




    }
}
