import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements ActionListener{
    public static final int MAX_HEIGHT=800, MAX_WIDTH=800,TILE_SIZE=25,DELAY=100;
    private static int tileNumber= MAX_HEIGHT*MAX_WIDTH/TILE_SIZE;
    private Timer timer;
    Random random;
    protected char direction='R';
    private boolean running=false;
    Snakes[] snakes= new Snakes[2];
    private int fruitPosX,fruitPosY;
    public int playerNumber;
    GamePanel(){
        this.setPreferredSize(new Dimension(MAX_WIDTH,MAX_HEIGHT));;
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        random=new Random();
        startGame(1);
        
    }
    public void startGame(int i) {
        this.running=true;
        spawnFruit();
        if(i==1){
            playerNumber=1;
            timer=new Timer(DELAY, this);
            timer.start();
            snakes[0]= new Snakes();
        }
        else if(i>1){
            for (int j=0;j<i;j++){
                snakes[j]=new Snakes();
            }
            timer=new Timer(DELAY, this);
            timer.start();
        }
    }
    public void move(){
        for (int j=0;j<playerNumber;j++){
            snakes[j].move();
        }
    }
    public void spawnFruit(){
        fruitPosY=random.nextInt((int)(MAX_HEIGHT/TILE_SIZE))*TILE_SIZE;
        fruitPosX=random.nextInt((int)(MAX_WIDTH/TILE_SIZE))*TILE_SIZE;

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        for (int i=0;i<(MAX_WIDTH/TILE_SIZE);i++){
            g.drawLine(i*TILE_SIZE, 0, i*TILE_SIZE, MAX_HEIGHT);
        }
        for (int i=0;i<(MAX_HEIGHT/TILE_SIZE);i++){
            g.drawLine(0,i*TILE_SIZE, MAX_HEIGHT,  i*TILE_SIZE);
        }
        
        for (int i=0;i<snakes[0].length();i++){
            if(i==0){
            g.setColor(Color.red);
            g.fillRect(snakes[0].xPos[i], snakes[0].yPos[i], TILE_SIZE, TILE_SIZE);
            }
            else{g.setColor(Color.green);
            g.fillRect(snakes[0].xPos[i], snakes[0].yPos[i], TILE_SIZE, TILE_SIZE);}

            
        }
        g.setColor(Color.blue);
        g.fillOval(fruitPosX, fruitPosY, TILE_SIZE,TILE_SIZE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.move();
        snakes[0].checkSelfEat();

        if(snakes[0].checkFood(fruitPosX,fruitPosY)){
            this.spawnFruit();
        };        
        repaint();
    }
    public class myKeyAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    snakes[0].setDir('L');
                    break;
                case KeyEvent.VK_RIGHT:
                snakes[0].setDir('R');
                    break;
                case KeyEvent.VK_UP:
                snakes[0].setDir('U');   
                    break;
                case KeyEvent.VK_DOWN:
                snakes[0].setDir('D');      
                    break;
            
                default:
                    break;
            }
        }
    }

}
