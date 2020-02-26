

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Timer timer;
    ArrayList<Sprite> actors;
    int paddingNum = 25;
    Game game;
    Board(Game game){

        this.game = game;
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.black);

    }

    public void setup(){
       actors = new ArrayList<>();
       actors.add(new Player(Color.green,getWidth()/2,getHeight()/2,70,70,this,game));
       for (int i = 0; i < STATS.getNumFood(); i++){
           actors.add(new Food(Color.orange,(int)(Math.random()*(getWidth()-paddingNum)+paddingNum),(int)(Math.random()*(getHeight()-paddingNum)+paddingNum),20,20,this));
       }
       for (int i = 0; i < STATS.getNumEnemies(); i++){
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
    public void checkCollisions(){
        for (int i =1; i<actors.size(); i++){
            if(actors.get(0).collidesWith(actors.get(i))){

                if(actors.get(i) instanceof Enemy){
                    actors.get(0).setRemove();
                }
                else
                    actors.get(i).setRemove();
            }
        }
        for (int i = 1; i < actors.size(); i++){
            if(actors.get(i).isRemove()){
                actors.remove(i);
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollisions();

        for(Sprite thisGuy: actors){
            thisGuy.move();
        }

        if(actors.size() <= STATS.getNumEnemies() + 1){
            System.out.println("THEY DED");
        }

        repaint();
    }
}
