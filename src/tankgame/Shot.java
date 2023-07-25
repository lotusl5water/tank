package tankgame;

public class Shot implements Runnable {
    private int x, y;
    private int direct = 0;//����
    private int speed = 8;//�ٶ�
    boolean isLive=true;//�Ƿ���
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            //��Ҫ���ӵ�����50����
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0://��
                    y -= speed;
                    break;
                case 1://��
                    x += speed;
                    break;
                case 2://��
                    y += speed;
                    break;
                case 3://��
                    x -= speed;
                    break;
            }
            //System.out.println(x + " " + y);
            //���ӵ���������̹��ʱ���ͱ�����
            //���ӵ���������̹��ʱ��Ҳ�����߳�
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 &&  isLive)) {
                //���ӵ��ƶ������߽磬���ӵ��߳�����
                isLive=false;
                break;
            }
        }
    }
}
