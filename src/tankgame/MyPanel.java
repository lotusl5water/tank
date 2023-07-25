package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//为了让Panel不停重绘子弹，需要将MyPanel实现Runnable，当作一个线程使用
//绘图区域
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //我的坦克
    Hero hero = null;
    //敌人坦克Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个存放Node对象的Vector，用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes=new Vector<>();
    //定义一个Vector，用于存放炸弹
    //当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    int enemyTankSize =6;
    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        //先判断记录文件是否存在
        //如果在，就正常执行；如果不在，就提示只能开启新游戏，key=“1”
        File file=new File(Recorder.getRecordFile());
        if(file.exists()){
            nodes=Recorder.getNodesAndEnemyTankRec();
        }else{
            System.out.println("文件不存在，只能开新游戏");
            key="1";
        }

        nodes=Recorder.getNodesAndEnemyTankRec();
        //将MyPanel对象的enemyTanks设置给recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(800, 100);//初始化自己坦克
        hero.setSpeed(5);
        switch(key){
            case"1":
                //初始化敌人坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(2);
                    //启动敌人坦克线程，让其动起来
                    new Thread(enemyTank).start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);

                }
                //初始化图片对象
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
                break;
            case"2"://继续上局游戏
                //初始化敌人坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node =nodes.get(i);
                    //创建一个敌人的坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置方向
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克线程，让其动起来
                    new Thread(enemyTank).start();
                    //给该enemyTank加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    //加入enemyTank的Vector成员
                    enemyTank.shots.add(shot);
                    //启动
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);

                }
                //初始化图片对象
                image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
                image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
                image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
                new AePlayWave("src\\111.wav").start();
                break;
                //播放指定音乐

            default:
                System.out.println("输入有误");
        }



    }

    //显示击毁敌方坦克信息
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font=new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累积击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,0);//画出一个地方坦克
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);

    }
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        showInfo(g);
        //画出坦克-封装方法
        if(hero!=null && hero.isLive()){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
            } else {//如果该shot对象已经无效，就从shots集合中去掉
                hero.shots.remove(shot);
            }
        }

        //如果bombs集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果bomb life为0，则从bombs的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }
        //画出敌人坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否还存活
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出enemyTank所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        //子弹从Vector中移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

    }


    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克方向(上下左右)
     * @param type   坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型坦克，设置不同颜色
        switch (type) {
            case 0://敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1://我们的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克方向，绘制坦克 0：向上 1：向右 2：向下 3：向左
        switch (direct) {
            case 0://表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1://表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2://表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3://表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //遍历所有子弹和所有敌方坦克，进行击中判断
    public void hitEnemyTanks(Vector<Shot> Shots, Vector<EnemyTank> enemyTanks) {
        //遍历子弹
        for  (int j = 0; j < Shots.size(); j++){
            Shot s = Shots.get(j);
            if (s != null && s.isLive) {//当我的子弹还存活时

                for  (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank enemytank = enemyTanks.get(i);
                    hitTank(s,enemytank);
                }
            }
        }
    }
    //判断击中坦克
    public void hitTank(Shot s, Tank enemyTank) {

        switch (enemyTank.getDirect()) {
            case 0://向上

            case 2://向下
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.setLive(false);
                    //当我方子弹击中敌人坦克后，将enemyTank从Vector拿掉
                    enemyTanks.remove(enemyTank);
                    //当我方击毁一个敌人坦克时，就对数据allEnemyTankNum++
                    //enemyTank可以是Hero也可以是EnemyTank
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1://向右

            case 3://向左
                if (s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX() + 60
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.setLive(false);
                    //当我方子弹击中敌人坦克后，将enemyTank从Vector拿掉
                    enemyTanks.remove(enemyTank);
                    //当我方击毁一个敌人坦克时，就对数据allEnemyTankNum++
                    //enemyTank可以是Hero也可以是EnemyTank
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入到bombs集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理wdsa键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if(hero.isLive()==false){
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            //修改坦克的坐标y-=1
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

        //如果用户按下J，则发射
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //判断子弹是否销毁
            // if(hero.shot==null|| !hero.shot.isLive){
            //    hero.shotEnemyTank();
            //}
            //发射多颗子弹
            hero.shotEnemyTank();

        }


        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒，重绘区域
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断是否击中己方
            hitHero();
            //判断是否击中敌人坦克
            hitEnemyTanks(hero.shots,enemyTanks);
            this.repaint();
        }
    }
    //判断敌方坦克是否击中我方
    public void hitHero(){
        //遍历敌方坦克
        for(int i=0;i<enemyTanks.size();i++){
            //取出敌人坦克
            EnemyTank enemyTank=enemyTanks.get(i);
            //遍历enemyTank对象所有子弹
            for(int j=0;j<enemyTank.shots.size();j++){
                //取出子弹
                Shot shot=enemyTank.shots.get(j);
                if(hero.isLive() && shot.isLive){
                    hitTank(shot,hero);
                }
            }
        }
    }
}
