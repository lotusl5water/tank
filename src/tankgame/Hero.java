package tankgame;

import java.util.Vector;

public class Hero extends Tank {
        Shot shot=null;//�������
        //���Է������ӵ���ʹ��Vector�洢
        Vector<Shot> shots=new Vector<>();
        public Hero(int x,int y){
            super(x,y);
        }
        public void shotEnemyTank(){
            //����Shot���󣬸��ݵ�ǰHero�����λ�úͷ���ȷ���ӵ�λ��
            switch (getDirect()){
                case 0://����
                    shot =new Shot(getX()+20,getY(),0);
                    break;
                case 1://����
                    shot =new Shot(getX()+60,getY()+20,1);
                    break;
                case 2://����
                    shot =new Shot(getX()+20,getY()+60,2);
                    break;
                case 3://����
                    shot =new Shot(getX(),getY()+20,3);
                    break;
             }
            //���½���shot���뵽shots
            shots.add(shot);

            //����Shot�߳�
            new Thread(shot).start();
        }
}
