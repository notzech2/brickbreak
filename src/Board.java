

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Timer timer;
    ArrayList<Sprite> actors;
    int paddingNum = 25;

    Board(){


        setPreferredSize(new Dimension(600,800));
        setBackground(Color.black);

    }

    public void setup(){
       actors = new ArrayList<>();
       actors.add(new Player(Color.green,getWidth()/2,getHeight()/2,100,100,this));
       for (int i = 0; i < STATS.numFood; i++){
           actors.add(new Food(Color.orange,(int)(Math.random()*(getWidth()-paddingNum)+paddingNum),(int)(Math.random()*(getHeight()-paddingNum)+paddingNum),20,20,this));
       }
       for (int i = 0; i < STATS.numEnemies; i++){
           actors.add(new Enemy(Color.RED,(int)(Math.random()*(getWidth()-paddingNum)+paddingNum),(int)(Math.random()*(getHeight()-paddingNum)+paddingNum),50,50,this));
       }

        timer = new Timer(1000/60,this);
        timer.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Sprite e: actors){
            e.paint(g);
        }




    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(Sprite thisGuy: actors){
            thisGuy.move();
        }
        repaint();
    }
}
