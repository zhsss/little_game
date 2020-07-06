package cn.pancras.game;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 背景图片类
 *
 * @author pancras
 * @version 1.0
 */
public class Background extends GameObject {

    public Background(Image img, int posX, int posY) {
        super(img, posX, posY);
    }

    /**
     * 绘制背景图片
     *
     * @param g      绘制窗口
     * @param width  窗口的宽度
     * @param height 窗口的高度
     */
    public void draw(Graphics g, int width, int height) {
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);

        //将背景图片铺满整个窗口
        for (int i = 0; i <= height / imgHeight; i++) {
            for (int j = 0; j <= width / imgWidth; j++) {
                posX = j * imgWidth;
                drawSelf(g);
            }
            posY = i * imgHeight;
        }
    }
}
