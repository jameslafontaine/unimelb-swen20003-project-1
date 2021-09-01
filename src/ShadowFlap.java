import bagel.*;

// maybe make a Message class for all the different messages
// maybe make a class for GameStart
/**
 * Please fill in your name below
 * @author: James La Fontaine
 */
public class ShadowFlap extends AbstractGame {
    private final Image background = new Image("res/background.png");
    private static final int FONT_SIZE = 48;
    private final Font font = new Font("res/slkscr.ttf", FONT_SIZE);

    private final String startMessage = "PRESS SPACE TO START";


    private boolean gameStarted = false;


    public ShadowFlap() {
        super(1024, 768, "ShadowFlap");

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
        if (input.wasPressed(Keys.SPACE)) {
            gameStarted = true;
        }
        if (!gameStarted) {
            font.drawString(startMessage, Window.getWidth() / 2.0 - font.getWidth(startMessage) / 2.0,
                    Window.getHeight() / 2.0);
        }
    }
}
