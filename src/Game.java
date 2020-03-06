import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class Game extends JFrame {
    Board board;
    int posX, posY;
    long moment;
    boolean mouseCLicked = false;

    public Game() {
        setTitle("ShapeyShapes");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        board = new Board(this);
        add(board);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3,3,2),new Point(0,0),null));
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void  mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                posX = e.getX();
                posY = e.getY();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseCLicked = true;
                moment = System.currentTimeMillis();
            }
        });

        pack();
        board.setup();
        setLocationRelativeTo(null);

    }
    public boolean getIsClicked(){
        return mouseCLicked;
    }
    public void setClicked(){
         mouseCLicked = false;
    }
    public long getMoment(){
        return moment;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }


    public static void main(String[] args) {
        Game game = new Game();
    }
}

