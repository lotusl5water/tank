package tankgame;

public class Bomb {
    int x,y;
    int life=9;//��������
    boolean isLive=true;//�Ƿ񻹴��
    public Bomb(int x,int y){
        this.x=x;
        this.y=y;
    }
    //��������ֵ
    public void lifeDown(){//��ϳ���ͼƬ�ı�ըЧ��
        if(life>0){
            life--;
        }else{
            isLive=false;
        }
    }
}
