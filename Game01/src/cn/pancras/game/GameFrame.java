package cn.pancras.game;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 游戏主窗口类
 *
 * @author pancras
 * @version 1.0
 */
public class GameFrame extends Frame {
    /**
     * 保存程序用到的图片对象
     */
    private HashMap<String, Image> gameImg = new HashMap<>();

    /**
     * 游戏物体
     */
    //背景图片
    private Background bg;
    //兔子
    private Rabbit rabbit;

    public GameFrame() throws HeadlessException {
        //加载图片资源
        for (Entry<String, String> img : Config.imgPath.entrySet()) {
            gameImg.put(img.getKey(), GameUtil.getImage((img.getValue())));
        }
        //@TODO 加载音频资源

        //创建游戏物体对象
        bg = new Background(gameImg.get("grass"), 0, 0);
        rabbit = new Rabbit(gameImg.get("rabbit"), 100, (Config.WINDOW_HEIGHT - 50) / 2);
    }


    /**
     * 初始化游戏
     */
    public void initGame() {
        //初始化窗口
        this.setTitle("保卫城堡");
        this.setVisible(true);
        this.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        this.setLocation(Config.WINDOW_LOCATION);

        //监听游戏窗口关闭
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //启动重画窗口线程
        PaintThread paintThread = new PaintThread();
        paintThread.start();

        //启动键盘监听线程
        this.addKeyListener(new KeyMonitor());

        //@TODO 加载音频素材
    }

    @Override
    public void paint(Graphics g) {
        //绘制背景图片
        bg.draw(g, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        //绘制兔子
        rabbit.drawSelf(g);
    }


    private Image offScreenImage = null;
    /**
     * 双缓冲区的实现
     *
     * @param g 绘制窗口
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 窗口重画
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                //调用外部类的重画方法
                GameFrame.this.repaint();

                try {
                    Thread.sleep(1000 / Config.FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 键盘监听
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            rabbit.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            rabbit.minusDirection(e);
        }
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.initGame();
    }
}
