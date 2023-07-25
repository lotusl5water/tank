package tankgame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //���ڼ�¼�ҷ����ٵ���̹����
    private static int allEnemyTankNum = 0;
    //����IO����׼��д���ݵ��ļ���
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    //��ȡMyPanel�����еĵ���̹��Vector
    public static Vector<EnemyTank> enemyTanks = null;

    //����һ��Node��Vector,���ڱ��������Ϣnode
    private static Vector<Node> nodes = new Vector<>();


    public static String getRecordFile() {
        return recordFile;
    }

    public static void setRecordFile(String recordFile) {
        Recorder.recordFile = recordFile;
    }

    //����һ����ȡrecordFile,�ָ������Ϣ
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //ѭ����ȡ�ļ�������nodes����
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyz = line.split(" ");
                Node node = new Node(Integer.parseInt(xyz[0]),
                        Integer.parseInt(xyz[1]),
                        Integer.parseInt(xyz[2]));
                nodes.add(node);//����nodes Vector
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return nodes;
    }


    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //����Ϸ�˳�ʱ����allEnemyTankNum���浽recordFile
    //ͬʱ�������̹�˵�����ͷ���
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //�����з�̹�˵�Vector���ж��Ƿ���
            for (int i = 0; i < enemyTanks.size(); i++) {
                //ȡ������̹��
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    //�����enemyTank��Ϣ
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //д�뵽�ļ�
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //���ҷ�̹�˻���һ������̹�ˣ���allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
