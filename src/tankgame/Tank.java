package tankgame;

public class Tank {
    private int x,y;//坦克横纵坐标
    private int direct;
    private int speed=1;
    private boolean isLive=true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX(){
        return x;
    }
    public int getY(){

        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    //上下左右移动方法
    public void moveUp(){
        y-=speed;
    }
    public void moveRight(){
        x+=speed;
    }
    public void moveDown(){
        y+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }
}
