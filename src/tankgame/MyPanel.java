package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//Ϊ����Panel��ͣ�ػ��ӵ�����Ҫ��MyPanelʵ��Runnable������һ���߳�ʹ��
//��ͼ����
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //�ҵ�̹��
    Hero hero = null;
    //����̹��Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //����һ�����Node�����Vector�����ڻָ�����̹�˵�����ͷ���
    Vector<Node> nodes=new Vector<>();
    //����һ��Vector�����ڴ��ը��
    //���ӵ�����̹��ʱ������һ��Bomb����bombs
    Vector<Bomb> bombs = new Vector<>();

    int enemyTankSize =6;
    //��������ը��ͼƬ��������ʾ��ըЧ��
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        //���жϼ�¼�ļ��Ƿ����
        //����ڣ�������ִ�У�������ڣ�����ʾֻ�ܿ�������Ϸ��key=��1��
        File file=new File(Recorder.getRecordFile());
        if(file.exists()){
            nodes=Recorder.getNodesAndEnemyTankRec();
        }else{
            System.out.println("�ļ������ڣ�ֻ�ܿ�����Ϸ");
            key="1";
        }

        nodes=Recorder.getNodesAndEnemyTankRec();
        //��MyPanel�����enemyTanks���ø�recorder��enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(800, 100);//��ʼ���Լ�̹��
        hero.setSpeed(5);
        switch(key){
            case"1":
                //��ʼ������̹��
                for (int i = 0; i < enemyTankSize; i++) {
                    //����һ�����˵�̹��
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //��enemyTanks���ø�enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //���÷���
                    enemyTank.setDirect(2);
                    //��������̹���̣߳����䶯����
                    new Thread(enemyTank).start();
                    //����enemyTank����һ���ӵ�
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //����enemyTank��Vector��Ա
                    enemyTank.shots.add(shot);
                    //����
                    new Thread(shot).start();
                    //����
                    enemyTanks.add(enemyTank);

                }
                //��ʼ��ͼƬ����
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
                break;
            case"2"://�����Ͼ���Ϸ
                //��ʼ������̹��
                for (int i = 0; i < nodes.size(); i++) {
                    Node node =nodes.get(i);
                    //����һ�����˵�̹��
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //��enemyTanks���ø�enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //���÷���
                    enemyTank.setDirect(node.getDirect());
                    //��������̹���̣߳����䶯����
                    new Thread(enemyTank).start();
                    //����enemyTank����һ���ӵ�
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //����enemyTank��Vector��Ա
                    enemyTank.shots.add(shot);
                    //����
                    new Thread(shot).start();
                    //����
                    enemyTanks.add(enemyTank);

                }
                //��ʼ��ͼƬ����
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
                new AePlayWave("src\\111.wav").start();
                break;
                //����ָ������

            default:
                System.out.println("��������");
        }



    }

    //��ʾ���ٵз�̹����Ϣ
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font=new Font("����",Font.BOLD,25);
        g.setFont(font);
        g.drawString("���ۻ����ٵз�̹��",1020,30);
        drawTank(1020,60,g,0,0);//����һ���ط�̹��
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);

    }
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//�����Σ�Ĭ�Ϻ�ɫ
        showInfo(g);
        //����̹��-��װ����
        if(hero!=null && hero.isLive()){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
            } else {//�����shot�����Ѿ���Ч���ʹ�shots������ȥ��
                hero.shots.remove(shot);
            }
        }

        //���bombs�������ж��󣬾ͻ���
        for (int i = 0; i < bombs.size(); i++) {
            //ȡ��ը��
            Bomb bomb = bombs.get(i);
            //���ݵ�ǰ���bomb�����lifeֵȥ������Ӧ��ͼƬ
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //�����ը��������ֵ����
            bomb.lifeDown();
            //���bomb lifeΪ0�����bombs�ļ�����ɾ��
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        //��������̹�ˣ�����Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //ȡ��̹��
            EnemyTank enemyTank = enemyTanks.get(i);
            //�жϵ�ǰ̹���Ƿ񻹴��
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //����enemyTank�����ӵ�
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //ȡ���ӵ�
                    Shot shot = enemyTank.shots.get(j);
                    //����
                    if (shot.isLive) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        //�ӵ���Vector���Ƴ�
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

    }


    /**
     * @param x      ̹�˵����Ͻ�x����
     * @param y      ̹�˵����Ͻ�y����
     * @param g      ����
     * @param direct ̹�˷���(��������)
     * @param type   ̹������
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //���ݲ�ͬ����̹�ˣ����ò�ͬ��ɫ
        switch (type) {
            case 0://���˵�̹��
                g.setColor(Color.cyan);
                break;
            case 1://���ǵ�̹��
                g.setColor(Color.yellow);
                break;
        }
        //����̹�˷��򣬻���̹�� 0������ 1������ 2������ 3������
        switch (direct) {
            case 0://��ʾ����
                g.fill3DRect(x, y, 10, 60, false);//����̹���������
                g.fill3DRect(x + 30, y, 10, 60, false);//����̹���ұ�����
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//����̹�˸���
                g.fillOval(x + 10, y + 20, 20, 20);//����Բ�θ���
                g.drawLine(x + 20, y + 30, x + 20, y);//������Ͳ
                break;
            case 1://��ʾ����
                g.fill3DRect(x, y, 60, 10, false);//����̹���ϱ�����
                g.fill3DRect(x, y + 30, 60, 10, false);//����̹���±�����
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//����̹�˸���
                g.fillOval(x + 20, y + 10, 20, 20);//����Բ�θ���
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//������Ͳ
                break;
            case 2://��ʾ����
                g.fill3DRect(x, y, 10, 60, false);//����̹���������
                g.fill3DRect(x + 30, y, 10, 60, false);//����̹���ұ�����
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//����̹�˸���
                g.fillOval(x + 10, y + 20, 20, 20);//����Բ�θ���
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//������Ͳ
                break;
            case 3://��ʾ����
                g.fill3DRect(x, y, 60, 10, false);//����̹���ϱ�����
                g.fill3DRect(x, y + 30, 60, 10, false);//����̹���±�����
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//����̹�˸���
                g.fillOval(x + 20, y + 10, 20, 20);//����Բ�θ���
                g.drawLine(x + 30, y + 20, x, y + 20);//������Ͳ
                break;
            default:
                System.out.println("��ʱû�д���");
        }
    }

    //���������ӵ������ез�̹�ˣ����л����ж�
    public void hitEnemyTanks(Vector<Shot> Shots, Vector<EnemyTank> enemyTanks) {
        //�����ӵ�
        for  (int j = 0; j < Shots.size(); j++){
            Shot s = Shots.get(j);
            if (s != null && s.isLive) {//���ҵ��ӵ������ʱ

                for  (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank enemytank = enemyTanks.get(i);
                    hitTank(s,enemytank);
                }
            }
        }
    }
    //�жϻ���̹��
    public void hitTank(Shot s, Tank enemyTank) {

        switch (enemyTank.getDirect()) {
            case 0://����

            case 2://����
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.setLive(false);
                    //���ҷ��ӵ����е���̹�˺󣬽�enemyTank��Vector�õ�
                    enemyTanks.remove(enemyTank);
                    //���ҷ�����һ������̹��ʱ���Ͷ�����allEnemyTankNum++
                    //enemyTank������HeroҲ������EnemyTank
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //����Bomb���󣬼��뵽bombs����
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://����

            case 3://����
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 60
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.setLive(false);
                    //���ҷ��ӵ����е���̹�˺󣬽�enemyTank��Vector�õ�
                    enemyTanks.remove(enemyTank);
                    //���ҷ�����һ������̹��ʱ���Ͷ�����allEnemyTankNum++
                    //enemyTank������HeroҲ������EnemyTank
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //����Bomb���󣬼��뵽bombs����
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //����wdsa�����µ����
    @Override
    public void keyPressed(KeyEvent e) {
        if(hero.isLive()==false){
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            //�޸�̹�˵�����y-=1
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }

        //����û�����J������
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //�ж��ӵ��Ƿ�����
            // if(hero.shot==null|| !hero.shot.isLive){
            //    hero.shotEnemyTank();
            //}
            //�������ӵ�
            hero.shotEnemyTank();

        }


        //������ػ�
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//ÿ��100���룬�ػ�����
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //�ж��Ƿ���м���
            hitHero();
            //�ж��Ƿ���е���̹��
            hitEnemyTanks(hero.shots,enemyTanks);
            this.repaint();
        }
    }
    //�жϵз�̹���Ƿ�����ҷ�
    public void hitHero(){
        //�����з�̹��
        for(int i=0;i<enemyTanks.size();i++){
            //ȡ������̹��
            EnemyTank enemyTank=enemyTanks.get(i);
            //����enemyTank���������ӵ�
            for(int j=0;j<enemyTank.shots.size();j++){
                //ȡ���ӵ�
                Shot shot=enemyTank.shots.get(j);
                if(hero.isLive() && shot.isLive){
                    hitTank(shot,hero);
                }
            }
        }
    }
}
