import java.awt.EventQueue;
import javax.swing.*;

public class Snake_Game extends JFrame {

    public Snake_Game() {

        initUI();
    }

    private void initUI() {

        add(new Snake_Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake_Game();
            ex.setVisible(true);
        });
    }
}