package tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //敌人坦克Vector
    Vector<EnemyTank> EnemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.EnemyTanks = enemyTanks;
    }

    //判断当前这个坦克，是否和enemyTank中的其他坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            //当前坦克与其他所有敌人坦克比较
            case 0://上
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //敌人坦克为上/下，x范围[enemyTank.getX(),enemyTank.getX()+40]
                        //y范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克左上角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克右上角坐标[this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克为右/左，x范围[enemyTank.getX(),enemyTank.getX()+60]
                        //y范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克左上角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克右上角坐标[this.getX(),this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //敌人坦克为上/下，x范围[enemyTank.getX(),enemyTank.getX()+40]
                        //y范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克右上角坐标[this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克右下角坐标[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克为右/左，x范围[enemyTank.getX(),enemyTank.getX()+60]
                        //y范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克右上角坐标[this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克右下角坐标[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //敌人坦克为上/下，x范围[enemyTank.getX(),enemyTank.getX()+40]
                        //y范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克左下角坐标[this.getX(),this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克右下角坐标[this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克为右/左，x范围[enemyTank.getX(),enemyTank.getX()+60]
                        //y范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克左下角坐标[this.getX(),this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克右下角坐标[this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //敌人坦克为上/下，x范围[enemyTank.getX(),enemyTank.getX()+40]
                        //y范围[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //当前坦克左上角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前坦克左下角坐标[this.getX(),this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() +40 >= enemyTank.getY()
                                    && this.getY() +40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克为右/左，x范围[enemyTank.getX(),enemyTank.getX()+60]
                        //y范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //当前坦克左上角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前坦克左下角坐标[this.getX()+40,this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 60
                                    && this.getY() + 40>= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }


    @Override
    public void run() {
        while (true) {
            //如果shots.size()=0,说明没有子弹，需要创建一颗子弹，放入shots集合，并启动
            if (shots.size() < 1 && isLive) {
                //判断坦克方向，创建相应子弹
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }
            switch (getDirect()) {
                case 0: {//向上
                    //让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0&&!isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 1: {//向右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000&&!isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 2: {//向下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750&&!isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case 3: {//向左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0&&!isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

            }

            //随机改变坦克方向0-3
            setDirect((int) (Math.random() * 4));
            //线程结束
            if (!isLive) {
                break;
            }
        }
    }
}
