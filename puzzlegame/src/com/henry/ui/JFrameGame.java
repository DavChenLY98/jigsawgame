package com.henry.ui;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class JFrameGame extends JFrame implements KeyListener {

    int[][] data = new int[4][4];
    //记录空白方块在data中的位置
    int x = 0;
    int y = 0;
    String path = "puzzlegame/image/animal/animal3/";

    public JFrameGame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //随机二维矩阵
        initDate();

        //初始化图片
        initImage();

        //显示出界面
        this.setVisible(true);
    }

    private void initDate() {

        int[] ArrIndex = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < ArrIndex.length; i++) {
            int index = r.nextInt(ArrIndex.length);
            int temp = ArrIndex[i];
            ArrIndex[i] = ArrIndex[index];
            ArrIndex[index] = temp;
        }

        for (int i = 0; i < ArrIndex.length; i++) {
            if (ArrIndex[i] == 0) {
                x = i / 4;
                y = i % 4;
            } else {
                data[i / 4][i % 4] = ArrIndex[i];
            }

        }

    }

    private void initImage() {
        //细节：先加载的图片在上方，后加载的图片在下方。
        //清楚已有的图片
        this.getContentPane().removeAll();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个管理容器存储图片对象
                JLabel jLabel = new JLabel(new ImageIcon(path
                        + data[i][j] + ".jpg"));
                //将管理容器放置在指定坐标位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //把管理容器添加到界面中
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景边框
        ImageIcon bg = new ImageIcon("puzzlegame/image/background.png");
        //利用容器存储图片对象
        JLabel background = new JLabel(bg);
//        JLabel background =new JLabel(new ImageIcon("puzzlegame/image/background.png"));
        //设置背景对象对的边框大小和位置
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);


        this.getContentPane().repaint();

    }


    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        //创建选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutUsJMenu = new JMenu("关于我们");
        //创建条目
        JMenuItem replayJMenuItem = new JMenuItem("重新游戏");
        JMenuItem reLoginJMenuItem = new JMenuItem("重新登录");
        JMenuItem closegameJMenuItem = new JMenuItem("关闭游戏");

        JMenuItem publicAccountJMenuItem = new JMenuItem("公众号");
        //将条目添加到选项中
        functionJMenu.add(replayJMenuItem);
        functionJMenu.add(reLoginJMenuItem);
        functionJMenu.add(closegameJMenuItem);

        aboutUsJMenu.add(publicAccountJMenuItem);
        //将选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutUsJMenu);
        //显示菜单栏
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY指定坐标放置管理容器
        this.setLayout(null);
        //添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 65) {
            this.getContentPane().removeAll();
            JLabel jLabel = new JLabel(new ImageIcon(path + "all.jpg"));
            jLabel.setBounds(83, 134, 420, 420);
            //这句话不知道为什么无法正常显示图片位置
//        jLabel.setLocation(0,0);
            this.getContentPane().add(jLabel);
            //添加背景边框
            ImageIcon bg = new ImageIcon("puzzlegame/image/background.png");
            //利用容器存储图片对象
            JLabel background = new JLabel(bg);
//        JLabel background =new JLabel(new ImageIcon("puzzlegame/image/background.png"));
            //设置背景对象对的边框大小和位置
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //对上、下、左、右进行判断
        //左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        if (code == 37) {
            //如果已经靠左边界
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            initImage();
        } else if (code == 38) {
            //已经靠上边界
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            initImage();
        } else if (code == 39) {
            //已经靠右边界
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            initImage();
        } else if (code == 40) {
            //已经靠下边界
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            initImage();
        } else if (code == 65) {    //当松开a键时，图片重新转换为分割后的随即图片
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
            x = 3;
            y = 3;
        }
    }
}
