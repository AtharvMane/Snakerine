import java.awt.event.*;
import java.util.Random;
import java.awt.*;

public class Snakes {
    private int length = 1;
    public int[] xPos = new int[40];
    public int[] yPos = new int[40];
    Random random = new Random();
    private Character direction = 'R';

    Snakes(int a, int b) {
        xPos[0] = a;
        yPos[0] = b;
    }

    public void move() {
        for (int i = length; i > 0; i--) {
            xPos[i] = xPos[i - 1];
            yPos[i] = yPos[i - 1];
            if (xPos[0] > GamePanel.MAX_WIDTH-25) {
                xPos[0] = 0;
                
            }
            if (xPos[0] < 0) {
                xPos[0] = (GamePanel.MAX_WIDTH-25);

            }
            if (yPos[0] > GamePanel.MAX_HEIGHT-25) {
                yPos[0] = 0;
            }
            if (yPos[0] < 0) {
                yPos[0] = (GamePanel.MAX_HEIGHT-25);

            }
            System.out.println(xPos[0]);

        }
        switch (this.direction) {
        case 'R':
            xPos[0] += GamePanel.TILE_SIZE;
            break;
        case 'L':
            xPos[0] -= GamePanel.TILE_SIZE;
            break;
        case 'U':
            yPos[0] -= GamePanel.TILE_SIZE;
            break;
        case 'D':
            yPos[0] += GamePanel.TILE_SIZE;
            break;
        default:
            break;
        }
    }

    public void checkEat(Snakes snake) {
        for (int i = 1; i < this.length; i++) {
            if (snake.xPos[0] == xPos[i] && snake.yPos[0] == yPos[i]) {
                System.out.println("collided self");
                this.reduceLength(i);
            }
        }
    }

    private void reduceLength(int i) {
        this.length = i;
    }

    public boolean checkFood(int a, int b) {
        if (xPos[0] == a && yPos[0] == b) {
            this.length++;
            return true;
        } else {
            return false;
        }
    }

    public int length() {
        return length;
    }

    public void setDir(char a) {
        if (!((a == 'L' && this.direction == 'R') || (a == 'R' && this.direction == 'L')
                || (a == 'U' && this.direction == 'D') || (a == 'D' && this.direction == 'U'))) {
            direction = a;
        }
    }

}
