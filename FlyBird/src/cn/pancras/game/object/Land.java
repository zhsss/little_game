package cn.pancras.game.object;

import cn.pancras.game.Config;
import cn.pancras.game.GameUtil;

import java.awt.Graphics;

/**
 * @author pancras
 * @version 1.0
 */
public class Land extends GameObject {

    public Land() {
        super(GameUtil.getImage(Config.LAND), 0, Config.FRAME_H - 112, Config.LAND_SPEED);
    }

    @Override
    public void move() {
        x -= speed;
        //循环移动地面
        if (img.getWidth(null) + x < Config.FRAME_W) {
            x = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }
}
