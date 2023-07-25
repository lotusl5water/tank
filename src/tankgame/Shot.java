package tankgame;

public class Shot implements Runnable {
    private int x, y;
    private int direct = 0;//方向
    private int speed = 8;//速度
    boolean isLive=true;//是否存活
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
            //先要让子弹休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://上
                    x -= speed;
                    break;
            }
            //System.out.println(x + " " + y);
            //当子弹碰到敌人坦克时，就被销毁
            //当子弹碰到敌人坦克时，也结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 &&  isLive)) {
                //当子弹移动到面板边界，将子弹线程销毁
                isLive=false;
                break;
            }
        }
    }
}
