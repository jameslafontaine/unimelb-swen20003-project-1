import bagel.*;
import bagel.util.Point;

// could make a superclass GameEntity for Bird and Pipe

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
    private static final Point CENTRE_SCREEN = new Point (WINDOW_WIDTH / 2.0, WINDOW_HEIGHT / 2.0);
    private static final String START_MESSAGE = "PRESS SPACE TO START";
    private static final String WIN_MESSAGE = "CONGRATULATIONS!";
    private static final String LOSS_MESSAGE = "GAME OVER";
    private static final Point SCORE_POINT = new Point(100, 100);
    private static final int CENTRE_SCORE_GAP = 75;

    private boolean gameStarted = false;
    private boolean winDetected = false;
    private boolean lossDetected = false;
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

    // check if the bird has surpassed the right x coordinate of the pipes and thus if the player has won
    private void winDetection() {
        if (bird.getPosition().x > pipes.getRightX()) {
            winDetected = true;
            score++;
        }
    }

    private void lossDetection() {
        // check for collision between the bird and the pipes
        if (bird.getHitbox().intersects(pipes.getHitBoxTop()) || bird.getHitbox().intersects(pipes.getHitboxBottom())) {
            lossDetected = true;
        }
        // check if the bird has moved out-of-bounds
        if (bird.getPosition().y < -bird.getHeight() / 2.0 || bird.getPosition().y > WINDOW_HEIGHT +
                                                                                               bird.getHeight() / 2.0) {
            lossDetected = true;
        }
    }

    private void drawWin() {
        background.draw(CENTRE_SCREEN.x, CENTRE_SCREEN.y);
        font.drawString(WIN_MESSAGE, CENTRE_SCREEN.x - font.getWidth(WIN_MESSAGE) / 2.0, CENTRE_SCREEN.y);
        font.drawString("FINAL SCORE: " + score, CENTRE_SCREEN.x -
                font.getWidth("FINAL SCORE: k") / 2.0, CENTRE_SCREEN.y + CENTRE_SCORE_GAP);
    }

    private void drawLoss() {
        background.draw(CENTRE_SCREEN.x, CENTRE_SCREEN.y);
        font.drawString(LOSS_MESSAGE, CENTRE_SCREEN.x - font.getWidth(LOSS_MESSAGE) / 2.0, CENTRE_SCREEN.y);
        font.drawString("FINAL SCORE: " + score, CENTRE_SCREEN.x -
                font.getWidth("FINAL SCORE: k") / 2.0, CENTRE_SCREEN.y + CENTRE_SCORE_GAP);

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

        background.draw(CENTRE_SCREEN.x, CENTRE_SCREEN.y);

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        // display the starting message until the player presses space bar for the first time and starts the game
        if (!gameStarted) {
            font.drawString(START_MESSAGE, CENTRE_SCREEN.x - font.getWidth(START_MESSAGE) / 2.0,
                    CENTRE_SCREEN.y + FONT_SIZE / 2.0);

            if (input.wasPressed(Keys.SPACE)) {
                gameStarted = true;
            }
        // otherwise, the game has started, and we must constantly update and draw the bird and pipes' positions.
        // we must also draw the score counter and detect a win or a loss
        } else {
            if (!winDetected && !lossDetected) {
                bird.update(input);
                pipes.update();
                font.drawString("SCORE: " + score, SCORE_POINT.x, SCORE_POINT.y);
                winDetection();
                lossDetection();
            } else if (winDetected) {
                drawWin();
            } else {
                drawLoss();
            }
        }
    }

}
