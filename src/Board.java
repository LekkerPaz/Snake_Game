import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    public final int B_WIDTH = 400;
    public final int B_HEIGHT = 400;
    public static final int DOT_SIZE = 10;
    public static final int RAND_POS = 29;
    protected final int DELAY = 100;

    public static boolean leftDirection = false;
    public static boolean rightDirection = true;
    public static boolean upDirection = false;
    public static boolean downDirection = false;

    protected Menu menu = new Menu();
    protected GameOver gameOver = new GameOver();
    public static STATE State = STATE.MENU;

    protected Timer timer;
    protected Image ball;
    protected Image apple;
    protected Image goldApple;
    protected Image head;

    public static Snake snake = new Snake();
    public static Apple active = locateApple();
    protected Apple last = active;
    protected Apple golden;
    public static Player player = new Player();

    public enum STATE{
        MENU,
        GAME,
        GAME_OVER
    };

    public Board (){
        player.setName(JOptionPane.showInputDialog(null, "Nombre del jugador"));
        initBoard();
    }

    private void initGame() {

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        addMouseListener(new MouseInput());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iig = new ImageIcon("src/resources/gold-apple.png");
        goldApple = iig.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();
    }





    public static Apple locateApple() {
        int x = 0;
        int y = 0;
        boolean same = false;
        do {
            int r = (int) (Math.random() * RAND_POS);
            x = ((r * DOT_SIZE));

            r = (int) (Math.random() * RAND_POS);
            y = ((r * DOT_SIZE) + 70);
            for (int i = 0; i < snake.getBody().size(); i++) {
                if (x == snake.getBody().get(i).getX() && y == snake.getBody().get(i).getY()) {
                    same = true;
                    break;
                } else {
                    same = false;
                }
            }
        } while (same);


        return new Apple(x, y);

    }

    private void checkApple() {

        if ((snake.getBody().get(0).getX() == active.location.getX()) && (snake.getBody().get(0).getY() == active.location.getY())) {

            this.player.setScore(this.player.getScore() + 10);
            last = new Apple(active.location.getX(), active.location.getY());
            active = locateApple();
            snake.setGrow(true);
            if (golden == null || golden.isEaten()) {
                createGoldenApple();
            }
        }

        if (snake.isGrow()) {
            if( last.location.getX() == snake.getBody().get(snake.getBody().size()-1).getX()) {
                if (last.location.getY() == snake.getBody().get(snake.getBody().size()-1).getY()) {
                    snake.nextTail.add(last.location);
                    snake.growNext = true;
                    snake.setGrow(false);
                }
            }
        }
        if (snake.isGrowNext()) {
            if (snake.nextTail.size() > 1) {
                snake.getBody().add(new Coordenadas(snake.getBody().get(snake.getBody().size()-1).getX(), snake.getBody().get(snake.getBody().size() - 1).getY()));
            } else {
                snake.getBody().add(new Coordenadas(snake.nextTail.get(0).getX(), snake.nextTail.get(0).getY()));
            }
            snake.nextTail.remove(0);
            snake.growNext = false;
        }

    }
    private void checkGolden() {

        if(golden != null && !golden.isEaten()){
            if ((snake.getBody().get(0).getX() == golden.location.getX()) && (snake.getBody().get(0).getY() == golden.location.getY())) {
                this.player.setScore(this.player.getScore() + 100);
                golden.setEaten(true);
            }
        }
    }

    private void move() {

        if (State == STATE.GAME) {
            for (int z = snake.getBody().size() - 1; z > 0; z--) {
                snake.getBody().get(z).setX(snake.getBody().get(z-1).getX());
                snake.getBody().get(z).setY(snake.getBody().get(z-1).getY());
            }

            if (leftDirection) {
                snake.getBody().get(0).setX(snake.getBody().get(0).getX() - DOT_SIZE);
            }

            if (rightDirection) {
                snake.getBody().get(0).setX(snake.getBody().get(0).getX() + DOT_SIZE);
            }

            if (upDirection) {
                snake.getBody().get(0).setY(snake.getBody().get(0).getY() - DOT_SIZE);
            }
            if (downDirection) {
                snake.getBody().get(0).setY(snake.getBody().get(0).getY() + DOT_SIZE);
            }
        }

    }

    private void createGoldenApple() {
        boolean r = (Math.random() > 0.9);
        if (r) {
            golden = locateApple();
        }
    }

    private void checkCollision() {

        for (int z = snake.getBody().size() - 1; z > 0; z--) {

            if ((z > 4) && (snake.getBody().get(0).getX() == snake.getBody().get(z).getX()) && (snake.getBody().get(0).getY() == snake.getBody().get(z).getY())) {
                snake.setAlive(false);
            }
        }

        if (snake.getBody().get(0).getY() >= B_HEIGHT) {
            snake.setAlive(false);
        }

        if (snake.getBody().get(0).getY() < 70) {
            snake.setAlive(false);
        }

        if (snake.getBody().get(0).getX() >= B_WIDTH) {
            snake.setAlive(false);
        }

        if (snake.getBody().get(0).getX() < 0) {
            snake.setAlive(false);
        }

        if (!snake.isAlive()) {
            if (golden != null && !golden.isEaten()) {
                golden.setEaten(true);
            }
            if (player.getScore() > player.getBestScore()) {
                player.setBestScore(player.getScore());
            }
            State = STATE.GAME_OVER;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (State == STATE.GAME) {
            if (snake.isAlive()) {
                if(golden != null && !golden.isEaten() ) {
                    golden.timer -= timer.getDelay() /10;
                    if (golden.timer < 0) {
                        golden.setEaten(true);
                    }
                }
                checkApple();
                checkGolden();
                checkCollision();
                move();
            }
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if(State == STATE.GAME) {
                if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                    leftDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                }

                if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                    upDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                    downDirection = true;
                    rightDirection = false;
                    leftDirection = false;
                }

                if (!snake.isAlive()) {
                    if (key == KeyEvent.VK_ENTER) {
                        State = STATE.MENU;
                    }
                }
            }

            if (State == STATE.MENU) {
                if ((key == KeyEvent.VK_ENTER)) {
                    State = STATE.GAME;
                }
            }
            if (State == STATE.GAME_OVER){
                if ((key == KeyEvent.VK_ENTER)) {
                    rightDirection = true;
                    upDirection = false;
                    downDirection = false;
                    leftDirection = false;
                    player.setScore(0);
                    snake = new Snake();
                    active = locateApple();
                    State = STATE.GAME;
                }
            }
        }
    }
}
