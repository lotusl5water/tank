package tankgame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //用于记录我方击毁敌人坦克书
    private static int allEnemyTankNum = 0;
    //定义IO对象，准备写数据到文件中
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    //获取MyPanel对象中的敌人坦克Vector
    public static Vector<EnemyTank> enemyTanks = null;

    //定义一个Node的Vector,用于保存敌人信息node
    private static Vector<Node> nodes = new Vector<>();


    public static String getRecordFile() {
        return recordFile;
    }

    public static void setRecordFile(String recordFile) {
        Recorder.recordFile = recordFile;
    }

    //定义一个读取recordFile,恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyz = line.split(" ");
                Node node = new Node(Integer.parseInt(xyz[0]),
                        Integer.parseInt(xyz[1]),
                        Integer.parseInt(xyz[2]));
                nodes.add(node);//放入nodes Vector
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

    //当游戏退出时，将allEnemyTankNum保存到recordFile
    //同时保存敌人坦克的坐标和方向
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //遍历敌方坦克的Vector，判断是否存活
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    //保存该enemyTank信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
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

    //当我方坦克击毁一个敌人坦克，就allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
