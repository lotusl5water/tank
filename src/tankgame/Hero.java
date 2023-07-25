package tankgame;

import java.util.Vector;

public class Hero extends Tank {
        Shot shot=null;//射击对象
        //可以发射多颗子弹，使用Vector存储
        Vector<Shot> shots=new Vector<>();
        public Hero(int x,int y){
            super(x,y);
        }
        public void shotEnemyTank(){
            //创建Shot对象，根据当前Hero对象的位置和方向确定子弹位置
            switch (getDirect()){
                case 0://向上
                    shot =new Shot(getX()+20,getY(),0);
                    break;
                case 1://向右
                    shot =new Shot(getX()+60,getY()+20,1);
                    break;
                case 2://向下
                    shot =new Shot(getX()+20,getY()+60,2);
                    break;
                case 3://向左
                    shot =new Shot(getX(),getY()+20,3);
                    break;
             }
            //把新建的shot放入到shots
            shots.add(shot);

            //启动Shot线程
            new Thread(shot).start();
        }
}
