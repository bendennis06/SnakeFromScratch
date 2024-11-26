import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class snakeGame extends JPanel implements ActionListener, KeyListener {

    int boardHeight, boardWidth;

    private Timer timer;

    int rows = 20, cols = 20;

    //starting point
    int snakeHeadX = 5;
    int snakeHeadY = 5;

    //speed of the snake
    int velocityX = 0;
    int velocityY = 0;

    //food tile
    Random rand = new Random();
    int randomRowFood = rand.nextInt(rows);
    int randomColFood = rand.nextInt(cols);

    //snakeBody array
    ArrayList<Point> snakeBody = new ArrayList<>();



    snakeGame(int boardHeight, int getBoardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = getBoardWidth;

        setPreferredSize(new Dimension(getBoardWidth, boardHeight));

        timer = new Timer(100, this);
        timer.start();

        //add action listener and allow panel to detect keyEvents
        setFocusable(true);
        addKeyListener(this);

    }

    //game loop
    @Override
    public void actionPerformed(ActionEvent e){

        updateGame();
        repaint();
        snakeBody();
    }

    private void updateGame(){
        //movement
        snakeHeadX += velocityX;
        snakeHeadY += velocityY;

    }

    private void snakeBody(){
        //check snakeHead and food tile collision
        if(snakeHeadX == randomColFood && snakeHeadY == randomRowFood) {
            //create new body tile
            snakeBody.add(new Point(snakeHeadX, snakeHeadY));

            //respawn food
            randomRowFood = rand.nextInt(rows);
            randomColFood = rand.nextInt(cols);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); //make sure its repainted properly
        draw(g); //call the draw method
    }

    public void draw(Graphics g){

        //horizontal(rows)
        for(int x = 0; x <= rows; x++ ){
            g.drawLine(0, x * boardHeight/rows, boardWidth, x * boardHeight/rows);
        }
        //vertical lines
        for(int y = 0; y <= cols; y++){
            g.drawLine(y * boardWidth / cols, 0, y * boardWidth/cols, boardHeight);
        }

        // Calculate the pixel position of the snake head
        int headPixelX = snakeHeadX * (boardWidth/ cols); //col * (cell width)
        int headPixelY = snakeHeadY * (boardHeight/ rows);// row * (cell height)

        g.setColor(Color.GREEN);
        g.fillRect(headPixelX, headPixelY, boardWidth/ cols, boardHeight / rows);

        int foodPixelX = randomColFood * (boardWidth/ cols);
        int foodPixelY =    randomRowFood * (boardHeight/ rows);

        g.setColor(Color.MAGENTA);
        g.fillRect(foodPixelX, foodPixelY, boardWidth/cols, boardHeight/rows);

        //draw snake body
        g.setColor(Color.GREEN);
        for(Point bodyPart : snakeBody){
            int bodyPixelX = bodyPart.x * (boardWidth/ cols);
            int bodyPixelY =  bodyPart.y * (boardHeight / rows);
            g.fillRect(bodyPixelX, bodyPixelY, boardWidth/cols, boardHeight/rows);

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //movement
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()  == KeyEvent.VK_UP){
            velocityX = 0;
            velocityY = -1;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            velocityX = 0;
            velocityY = 1;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            velocityX = -1;
            velocityY = 0;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
