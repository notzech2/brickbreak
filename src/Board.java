import com.sun.org.glassfish.external.statistics.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Timer timer;
    ArrayList<Sprite> actors;
    int paddingNum = 25;
    Game game;


    long nextMoment;
    Board(Game game){

        this.game = game;
        setPreferredSize(new Dimension(600,800));
        setBackground(Color.black);

    }

    public void setup(){
       actors = new ArrayList<>();
       actors.add(new Player(Color.green,getWidth()/2,getHeight()/2,30,30,this,game));

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
        for (int i =1; i<actors.size()-1; i++){
            if(actors.get(i).collidesWith(actors.get(i+1))){
                if (actors.get(i) instanceof Enemy && actors.get(i+1) instanceof Enemy || actors.get(i) instanceof Food && actors.get(i+1) instanceof Food || actors.get(i) instanceof Food && actors.get(i+1) instanceof Enemy){
                        if (actors.get(i).getX() < actors.get(i+1).getX() && actors.get(i).getY() > actors.get(i+1).getY()){
                            actors.get(i).collides(1); actors.get(i+1).collides(1);
                            actors.get(i).collides(1); actors.get(i+1).collides(1);
                        }
                        if (actors.get(i).getX() > actors.get(i+1).getX() && actors.get(i).getY() > actors.get(i+1).getY()){
                            actors.get(i).collides(1); actors.get(i+1).collides(1);
                            actors.get(i).collides(2); actors.get(i+1).collides(2);
                        }
                        if (actors.get(i).getX() > actors.get(i+1).getX() && actors.get(i).getY() < actors.get(i+1).getY()){
                            actors.get(i).collides(1); actors.get(i+1).collides(1);
                            actors.get(i).collides(2); actors.get(i+1).collides(2);
                        }
                        if (actors.get(i).getX() < actors.get(i+1).getX() && actors.get(i).getY() < actors.get(i+1).getY()){
                            actors.get(i).collides(1); actors.get(i+1).collides(1);
                            actors.get(i).collides(2); actors.get(i+1).collides(2);
                        }
                }

            }
        }

        for (int i = 1; i < actors.size(); i++){
            if(actors.get(0).collidesWith(actors.get(i))){

                if(actors.get(i) instanceof Enemy){
                  if(actors.get(0).getMass() > actors.get(i).getMass()){

                      actors.get(i).setRemove();
                      actors.get(0).setMass(actors.get(0).getMass() - 20);
                      actors.get(0).setHeight(actors.get(0).getHeight() - 20);
                      actors.get(0).setWidth(actors.get(0).getWidth() - 20);
                      STATS.setNumEnemies(STATS.getNumEnemies() - 1);
                      System.out.println(actors.get(0).getMass());
                  }
                  else {
                      game.setClicked();
                      setup();
                  }
                }

                 else if (actors.get(i) instanceof Food){

                    actors.get(i).setRemove();
                    actors.get(0).setMass(actors.get(0).getMass()+10);
                    actors.get(0).setHeight(actors.get(0).getHeight()+10);
                    actors.get(0).setWidth(actors.get(0).getWidth()+10);
                    System.out.println(actors.get(0).getMass());
                 }
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
        nextMoment = System.currentTimeMillis();
        if ((nextMoment-game.getMoment()) >= 1500){
            checkCollisions();
        }

        if(game.getIsClicked()){
          for(Sprite thisGuy: actors){
              thisGuy.move();
          }

          if(actors.size() <= STATS.getNumEnemies() + 1){
              System.out.println("THEY DED");
              game.setClicked();
              STATS.setLevel(2);
              STATS.updateLevel();
              setup();

          }

        }


        repaint();
    }
}
