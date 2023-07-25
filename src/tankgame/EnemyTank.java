package tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //����̹��Vector
    Vector<EnemyTank> EnemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.EnemyTanks = enemyTanks;
    }

    //�жϵ�ǰ���̹�ˣ��Ƿ��enemyTank�е�����̹�˷����ص�����ײ
    public boolean isTouchEnemyTank() {
        switch (this.getDirect()) {
            //��ǰ̹�����������е���̹�˱Ƚ�
            case 0://��
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //����̹��Ϊ��/�£�x��Χ[enemyTank.getX(),enemyTank.getX()+40]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //��ǰ̹�����Ͻ�����[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //��ǰ̹�����Ͻ�����[this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //����̹��Ϊ��/��x��Χ[enemyTank.getX(),enemyTank.getX()+60]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //��ǰ̹�����Ͻ�����[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //��ǰ̹�����Ͻ�����[this.getX(),this.getY()]
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
            case 1://��
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //����̹��Ϊ��/�£�x��Χ[enemyTank.getX(),enemyTank.getX()+40]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //��ǰ̹�����Ͻ�����[this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //����̹��Ϊ��/��x��Χ[enemyTank.getX(),enemyTank.getX()+60]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //��ǰ̹�����Ͻ�����[this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX()+60,this.getY()+40]
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
            case 2://��
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //����̹��Ϊ��/�£�x��Χ[enemyTank.getX(),enemyTank.getX()+40]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //��ǰ̹�����½�����[this.getX(),this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //����̹��Ϊ��/��x��Χ[enemyTank.getX(),enemyTank.getX()+60]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //��ǰ̹�����½�����[this.getX(),this.getY()+60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX()+40,this.getY()+60]
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
            case 3://��
                for (int i = 0; i < EnemyTanks.size(); i++) {
                    EnemyTank enemyTank = EnemyTanks.get(i);
                    if (enemyTank != this) {
                        //����̹��Ϊ��/�£�x��Χ[enemyTank.getX(),enemyTank.getX()+40]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //��ǰ̹�����Ͻ�����[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX(),this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() +40 >= enemyTank.getY()
                                    && this.getY() +40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //����̹��Ϊ��/��x��Χ[enemyTank.getX(),enemyTank.getX()+60]
                        //y��Χ[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //��ǰ̹�����Ͻ�����[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //��ǰ̹�����½�����[this.getX()+40,this.getY()+40]
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
            //���shots.size()=0,˵��û���ӵ�����Ҫ����һ���ӵ�������shots���ϣ�������
            if (shots.size() < 1 && isLive) {
                //�ж�̹�˷��򣬴�����Ӧ�ӵ�
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
                case 0: {//����
                    //��̹�˱���һ��������30��
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
                case 1: {//����
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
                case 2: {//����
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
                case 3: {//����
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

            //����ı�̹�˷���0-3
            setDirect((int) (Math.random() * 4));
            //�߳̽���
            if (!isLive) {
                break;
            }
        }
    }
}
