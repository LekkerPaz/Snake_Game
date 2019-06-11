import org.w3c.dom.events.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Board.State == Board.STATE.MENU){
            if (mx > 30 && mx < 130) {
                if (my > 120 && my < 170) {
                    Board.State = Board.STATE.GAME;
                }
            }
            if (mx > 150 && mx < 250) {
                if (my > 120 && my < 170) {
                    System.exit(1);
                }
            }
            if (mx > 270 && mx < 370) {
                if (my > 120 && my < 170) {
                    Board.player.setName(JOptionPane.showInputDialog(null, "Nombre del jugador"));
                }
            }

        }

        if (Board.State == Board.STATE.GAME_OVER) {
            if (mx > 30 && mx < 130) {
                if (my > 160 && my < 210) {
                    Snake_Board.resetGame();
                }
            }
            if (mx > 150 && mx < 250) {
                if (my > 160 && my < 210) {
                    System.exit(1);
                }
            }
            if (mx > 270 && mx < 370) {
                if (my > 160 && my < 210) {
                    Board.player.setName(JOptionPane.showInputDialog(null, "Nombre del jugador"));
                }
            }
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
