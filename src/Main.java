import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int boardWidth = 600;
        int boardHeight = 600;

      JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);


        snakeGame game = new snakeGame(boardHeight, boardWidth);
        frame.add(game);
        frame.pack(); //adjust frame size to fit to panel

        frame.setVisible(true);


    }
}