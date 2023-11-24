package com.henry.ui;

import javax.swing.*;

public class JFrameLogin extends JFrame {
    public JFrameLogin(){
        //设置界面的宽高
        this.setSize(488,430);
        //设置界面的标题
        this.setTitle("登录界面");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面剧中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //显示出界面
        this.setVisible(true);
    }
}
