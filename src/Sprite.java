import java.awt.*;


public abstract class Sprite {
    Color color;
    int x,y,width,height;
    double dx,dy;
    int mass;
    Board board;
    boolean remove = false;



    public Sprite(Color color, int x, int y, int width, int height, Board board){

        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.board = board;
        mass = height;


        while((int) dx == 0 || (int) dy == 0){
            double angle = 2 * Math.PI * (Math.random() + 1);
            double speed = (STATS.getRangeSpeed() * Math.random() + STATS.getLowSpeed());
            dx = Math.cos(angle) * speed;
            dy = Math.sin(angle) * speed;
            }

        }



    public void move(){
        double nextLeft = x + dx;
        double nexRight=(x + width) + dx;
        double nextTop= y + dy;
        double nextBottom=(y + height) + dy;

        if (nextTop < 0 || nextBottom >(double)board.getHeight()){

            dy *=-1;
        }

        if (nextLeft < 0 || nexRight >(double)board.getWidth()){

            dx *=-1;
        }

        x+=dx;
        y+=dy;

        }

        public void collides(int i){
          if (i == 1){
              dx*=-1;
          }
          else if(i == 2){
              dy*=-1;
          }

          x+=dx;
          y+=dy;
        }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean collidesWith(Sprite other){
        return getBounds().intersects(other.getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }

    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public boolean isRemove(){
        return remove;
    }

    public void setRemove(){
        remove =true;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMass(int mass){
        this.mass = mass;
    }

    public int getMass(){
        return mass;
    }
}
