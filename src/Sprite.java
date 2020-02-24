import java.awt.*;

public abstract class Sprite {

        Color color;
        int x,y,width,height;
        double dx,dy;
        final int SPEED = 5;
        Board board;


        public Sprite(Color color, int x, int y, int width, int height, Board board){

            this.color = color;
            this.x=x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.board = board;

            while((int) dx ==0 || (int) dy ==0){
                double angle = 2 *Math.PI * (Math.random() + 1);
                double speed = (SPEED * Math.random() +1);
                dx = Math.cos(angle) * speed;
                dy = Math.sin(angle) *speed;
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

        public abstract void paint(Graphics g);


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
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
}
