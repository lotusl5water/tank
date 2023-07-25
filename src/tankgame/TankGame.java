package tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {

    MyPanel mp=null;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args){

        TankGame tankGame03 =new TankGame();
    }
    public TankGame(){
        System.out.println("请输入你的选择 1：新游戏 2:继续上句");
        String key=scanner.next();
        mp=new MyPanel(key);
        Thread thread =new Thread(mp);
        thread.start();
        this.add(mp);//这是面板，游戏的绘图区域
        this.setSize(1300,950);
        this.addKeyListener(mp);//让JFrame监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
