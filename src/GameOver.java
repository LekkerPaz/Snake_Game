import java.awt.*;

public class GameOver {

    public Rectangle playButton = new Rectangle(30, 160, 100, 50);
    public Rectangle quitButton = new Rectangle(150, 160, 100, 50);
    public Rectangle nameButton = new Rectangle(270, 160, 100, 50);

    public void menu (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font small = new Font("Helvetica", Font.BOLD, 20);
        g.setFont(small);
        g.setColor(Color.green);
        g.drawString("Jugar", playButton.x + 19, playButton.y + 30);
        g2d.draw(playButton);
        g.drawString("Salir", quitButton.x + 25, quitButton.y + 30);
        g2d.draw(quitButton);
        g.drawString("Nombre", nameButton.x + 15, nameButton.y + 30);
        g2d.draw(nameButton);
    }
    public void message(Graphics g) {

        String msg = "Game Over";
        Font big = new Font("Helvetica", Font.BOLD, 50);

        g.setColor(Color.RED);
        g.setFont(big);
        g.drawString(msg, 70, 70);
    }
    public void lastScore(Graphics g) {

        String msg = "Hiciste: " + Board.player.getScore() + " puntos.";
        Font small = new Font("Helvetica", Font.BOLD, 14);

        g.setColor(Color.yellow);
        g.setFont(small);
        g.drawString(msg, 140, 100);
    }
    public void bestScore(Graphics g) {

        String msg = "Tu maximo fue: " + Board.player.getBestScore() + " puntos.";
        Font small = new Font("Helvetica", Font.BOLD, 14);

        g.setColor(Color.yellow);
        g.setFont(small);
        g.drawString(msg, 110, 120);
    }
}

