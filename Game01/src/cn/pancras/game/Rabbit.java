package cn.pancras.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * @author pancras
 * @version 1.0
 */
public class Rabbit extends GameObject {
    //兔子的运动方向
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public Rabbit(Image img, int posX, int posY) {
        super(img, posX, posY, 3, img.getWidth(null), img.getHeight(null));
    }

    /**
     * 按下某个键，增加相应的方向
     *
     * @param e 按键操作
     */
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    /**
     * 松开某个键，减去相应的方向
     *
     * @param e 按键操作
     */
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    @Override
    public void drawSelf(Graphics g) {
        g.drawImage(img, posX, posY, null);

        if (left) {
            posX -= speed;
        }
        if (right) {
            posX += speed;
        }
        if (up) {
            posY -= speed;
        }
        if (down) {
            posY += speed;
        }
    }
}