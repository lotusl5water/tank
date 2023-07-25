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
        System.out.println("���������ѡ�� 1������Ϸ 2:�����Ͼ�");
        String key=scanner.next();
        mp=new MyPanel(key);
        Thread thread =new Thread(mp);
        thread.start();
        this.add(mp);//������壬��Ϸ�Ļ�ͼ����
        this.setSize(1300,950);
        this.addKeyListener(mp);//��JFrame����mp�ļ����¼�
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //��Ӧ�رմ��ڵĴ���
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
