import java.awt.*;

public class Player extends Sprite{

    Game game;
    public Player(Color color, int x, int y, int width, int height, Board board,Game game) {
        super(color, x, y, width, height, board);
        this.game = game;
    }

    @Override
    public void move(){
        x = game.getPosX();
        y = game.getPosY();

    }


    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }
}