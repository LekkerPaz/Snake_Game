import java.util.ArrayList;

public class Snake {
    public ArrayList<Coordenadas> body = new ArrayList<Coordenadas>();
    boolean alive;
    boolean grow = false;
    public boolean growNext = false;
    public ArrayList<Coordenadas> nextTail = new ArrayList<Coordenadas>();

    public Snake() {
        for (int i = 0; i < 3; i ++) {
            body.add(new Coordenadas(100 - i * 10, 100));
        }
        this.alive = true;
    }

    public boolean isGrow() {
        return grow;
    }
    public boolean isGrowNext() {
        return growNext;
    }

    public void setGrow(boolean grow) {
        this.grow = grow;
    }

    public ArrayList<Coordenadas> getBody() {
        return body;
    }

    public void setBody(ArrayList<Coordenadas> body) {
        this.body = body;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
