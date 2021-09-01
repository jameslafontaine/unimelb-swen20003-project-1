import bagel.*;
import bagel.util.Point;

/**
 * Please fill in your name below
 * @author: James La Fontaine
 */
public class ShadowFlap extends AbstractGame {
    private final Image background = new Image("res/background.png");
    private final Bird bird = new Bird();
    private final Pipe pipes = new Pipe();
    private static final int FONT_SIZE = 48;
    private final Font font = new Font("res/slkscr.ttf", FONT_SIZE);

    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String START_MESSAGE = "PRESS SPACE TO START";
    private static final Point SCORE_POINT = new Point(100, 100);

    private boolean gameStarted = false;
    private int score = 0;

    public ShadowFlap() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, "ShadowFlap");

    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {

        background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        // display the starting message until the player presses space bar for the first time and starts the game
        if (!gameStarted) {
            font.drawString(START_MESSAGE, Window.getWidth() / 2.0 - font.getWidth(START_MESSAGE) / 2.0,
                    Window.getHeight() / 2.0 + FONT_SIZE / 2.0);

            if (input.wasPressed(Keys.SPACE)) {
                gameStarted = true;
            }
        // otherwise, the game has started, and we must constantly update and draw the bird and pipes' positions, and
        // also the score counter
        } else {
            bird.update(input);
            pipes.update();
            font.drawString("SCORE: " + score, SCORE_POINT.x, SCORE_POINT.y);
        }
    }

}
