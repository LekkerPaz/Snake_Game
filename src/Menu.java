import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(30, 120, 100, 50);
    public Rectangle quitButton = new Rectangle(150, 120, 100, 50);
    public Rectangle nameButton = new Rectangle(270, 120, 100, 50);

    public void menu (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font big = new Font("Helvetica", Font.BOLD, 50);
        g.setFont(big);
        g.setColor(Color.WHITE);
        g.drawString("Snake Game", 50, 100);

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
}
