import java.util.ArrayList;
import javax.swing.*;

public class Apple {
    Coordenadas location;
    boolean eaten = false;
    public int timer = 1000;

    public Apple(int x, int y) {
        this.location = new Coordenadas(x,y);
    }


    public boolean isEaten() {
        return this.eaten;
    }

    public void setEaten (boolean bool) {
        this.eaten = bool;
    }

    public Coordenadas getLocation() {
        return location;
    }

    public void setLocation(Coordenadas location) {
        this.location = location;
    }


    public Apple(int rand, int dot_size, ArrayList<Coordenadas> body) {
        int r = (int) (Math.random() * rand);
        int apple_x = ((r * dot_size));

        r = (int) (Math.random() * rand);
        int apple_y = ((r * dot_size) + 70);

        for (int i = 0; i < body.size(); i++) {
            if (body.get(i).getX() == apple_x) {
                if (body.get(i).getY() == apple_y) {
                    r = (int) (Math.random() * rand);
                    apple_x = ((r * dot_size));

                    r = (int) (Math.random() * rand);
                    apple_y = ((r * dot_size) + 70);

                    i = 0;
                }
            }
        }
    }
}
