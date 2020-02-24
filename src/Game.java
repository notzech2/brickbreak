import javax.swing.*;

public class Game extends JFrame {
    Board board;

    public Game() {
        setTitle("ShapeyShapes");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        board = new Board();
        add(board);
        board.setup();
        pack();

        setLocationRelativeTo(null);

    }


    public static void main(String[] args) {
        Game game = new Game();
    }
}

