package com.henry.ui;


import javax.swing.*;
import java.util.Random;

public class JFrameGame extends JFrame {

    int[] ArrIndex = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    public JFrameGame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //随机二维矩阵
        RandomImage();

        //初始化图片
        initImage();

        //显示出界面
        this.setVisible(true);
    }

    private void RandomImage() {
        Random r = new Random();
        for (int i = 0; i < ArrIndex.length; i++) {
            int index = r.nextInt(ArrIndex.length);
            int temp = ArrIndex[i];
            ArrIndex[i] = ArrIndex[index];
            ArrIndex[index] = temp;
        }
    }

    private void initImage() {
        int[][] Data = new int[4][4];
        for (int i = 0; i < ArrIndex.length; i++) {
            Data[i / 4][i % 4] = ArrIndex[i];
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个管理容器存储图片对象
                JLabel jLabel = new JLabel(new ImageIcon("puzzlegame/image/animal/animal1/" + Data[i][j] + ".jpg"));
                //将管理容器放置在指定坐标位置
                jLabel.setBounds(105 * j, 105 * i, 105, 105);
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
                //自增
            }
        }


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
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置，只有取消了才会按照XY指定坐标放置管理容器
        this.setLayout(null);
    }
}
