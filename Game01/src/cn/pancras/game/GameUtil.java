package cn.pancras.game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 游戏工具类
 *
 * @author pancras
 * @version 1.0
 */
public class GameUtil {
    private GameUtil() {
    }

    /**
     * 返回指定路径文件的图片对象
     *
     * @param path 图片路径
     * @return Image对象
     */
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getResource(path);
            if (u == null)
                System.out.println(path);
            else
                bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }

    /**
     * 实现物体的旋转
     *
     * @param degree 弧度制表示的旋转角度
     */
    public static BufferedImage rotate(BufferedImage bufferedImage, double degree) {

        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(degree, w / 2, h / 2);
        graphics2d.drawImage(bufferedImage, 0, 0, null);
        graphics2d.dispose();

        return img;
    }
}
