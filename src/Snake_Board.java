import java.awt.*;

public class Snake_Board extends Board {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (State == STATE.MENU) {
            menu.menu(g);
        } else if (State == STATE.GAME) {
            if (snake.isAlive()) {

                if (golden != null && !golden.isEaten()) {
                    g.drawImage(goldApple, golden.location.getX(), golden.location.getY(), this);
                }
                g.drawImage(apple, active.location.getX(), active.location.getY(), this);

                for (int z = 0; z < snake.getBody().size(); z++) {
                    if (z == 0) {
                        g.drawImage(head, snake.getBody().get(z).getX(), snake.getBody().get(z).getY(), this);
                    } else {
                        g.drawImage(ball, snake.getBody().get(z).getX(), snake.getBody().get(z).getY(), this);
                    }
                }

                Toolkit.getDefaultToolkit().sync();

                score(g);
                if (golden != null && !golden.isEaten()) {
                    golden(g);
                }
                lastScore(g);
                player(g);
                ui(g);
            }
        } else if (State == STATE.GAME_OVER) {
            gameOver.message(g);
            gameOver.lastScore(g);
            gameOver.bestScore(g);
            gameOver.menu(g);
        }

    }

    private void ui(Graphics g) {
        String msg = "--------------------------";
        Font small = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 0, 70);
    }

    private void player(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Player: " + player.getName(), 280,20);
    }

    private void score(Graphics g) {
        String msg = "Puntaje " + this.player.getScore();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 10 , 20);
    }

    private void lastScore(Graphics g) {
        String msg = "Mejor puntaje " + this.player.getBestScore();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 10 , 40);
    }
    private void golden(Graphics g) {
        String msg = "Dorada " + golden.timer / 100;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 160 , 20);
    }

    public static void resetGame() {

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
