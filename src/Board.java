

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    Timer timer;
    Player p;
    Enemy bad;
    Board(){

        setPreferredSize(new Dimension(600,800));
        setBackground(Color.black);


    }

    public void setup(){
         p = new Player(Color.green, 50,50,10,10,this);
         bad = new Enemy(Color.RED,50,50,20,20,this);
        timer = new Timer(1000/60,this);
        timer.start();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        bad.move();
        p.move();
        repaint();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        p.paint(g);
        bad.paint(g);

    }
}
