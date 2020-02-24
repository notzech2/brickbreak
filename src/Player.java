import java.awt.*;

public class Player extends Sprite{

    public Player(Color color, int x, int y, int width, int height, Board board) {
        super(color, x, y, width, height, board);
    }




    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}