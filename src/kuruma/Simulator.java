package kuruma;

import static kuruma.Main.total_car_num_g;
import static kuruma.Main.total_car_num_a;

public class Simulator {
    double ryuryo;
    double ryokoujikan;
    boolean disp;
    double sim_time;
    int count;
    double total_car_num;
    car carman;

    ScanCar scancar1 = new ScanCar (5,100);

    public int[][] rule1(int[][] cell, double p, int v_max, car car, int time) {
        int[][] new_cell = new int[4][cell[0].length];

        //考えること;
        double dice;//サイコロを用意する
        //速度を決定するループStep1~3
        for (int i = 0; i < cell[0].length; i++) {
            //車の有無を判断するand車種判断
            //車がいるand一般車両
            if (cell[0][i] == 1 && cell[2][i] == 3) {
                //              if (Main.bottle_start <= i && i <= Main.bottle_end) {
                //System.out.println("sharou"+cell[3][i]+":rule1");
                int position = i;//現在位置を記憶する
                int distance;//車頭距離カウント

                //車頭距離を計測
                for (int j = 1; j < cell[0].length; j++) {
//					//一般処理
                    if (cell[0][(i + j) % cell[0].length] == 1) {
                        if (time == Main.t) {
                            System.out.println(cell[3][i] + "," + j);
                        }
                        //System.out.println("車間距離計測離テストi"+i);
                        //System.out.println("車間距離計測離テスト"+(i+j)%cell[0].length);

                        distance = j;
                        rule_a(cell, v_max, i);//一般車両はNaSchモデルのみ
//						System.out.println("車間距離計測離テスト"+distance);
//						//step2
                        if (cell[1][i] >= distance) {
                            cell[1][i] = distance - 1;
                        }
                        //step3
                        //速度を見る
                        if (cell[1][i] > 0) {
                            dice = Math.random();
                            if (dice < p) {//ランダム化
                                cell[1][i] = cell[1][i] - 1;
                            }
                        }
                        //step4 速度を持つ車両の場合
                        if (cell[1][i] > 0) {
                            new_cell[0][(cell[1][i] + position) % cell[0].length] = 1;
                            new_cell[1][(cell[1][i] + position) % cell[0].length] = cell[1][i];
                            new_cell[2][(cell[1][i] + position) % cell[0].length] = 3;
                            new_cell[3][(cell[1][i] + position) % cell[0].length] = cell[3][i];
                        }
                        //step4　速度を持たない場合
                        else {
                            new_cell[0][position] = 1;
                            new_cell[1][position] = cell[1][i];
                            new_cell[2][position] = 3;
                            new_cell[3][position] = cell[3][i];
                        }
                        car.gocell(cell[3][i], cell[1][i], time);
                        break;
                    }
                }

            }
            if (cell[0][i] == 1 && cell[2][i] == 1) {
                //              if (Main.bottle_start <= i && i <= Main.bottle_end) {
                //System.out.println("sharou"+cell[3][i]+":rule1");
                int position = i;//現在位置を記憶する
                int distance;//車頭距離カウント

                for (int j = 1; j < cell[0].length; j++) {
                    //一般処理
                    if (cell[0][(i + j) % cell[0].length] == 1) {
                        if (time == Main.t) {
                            System.out.println(cell[3][i] + "," + j);
                        }
                        //System.out.println("車間距離計測離テストi"+i);
                        //System.out.println("車間距離計測離テスト"+(i+j)%cell[0].length);

                        distance = j;

                       /* if(distance <= 2 || cell[1][i] == 0){
                            rule_a(cell, v_max, i);
                        } else {*/

                        rule_b(cell,v_max,i,distance);


//						System.out.println("車間距離計測離テスト"+distance);
//						//step2
                        if (cell[1][i] >= distance) {
                            cell[1][i] = distance - 1;
                        }
                        //step3
                        //ACC車両はランダムブレーキしない

                        //step4 速度を持つ車両の場合
                        if (cell[1][i] > 0) {
                            new_cell[0][(cell[1][i] + position) % cell[0].length] = 1;
                            new_cell[1][(cell[1][i] + position) % cell[0].length] = cell[1][i];
                            new_cell[2][(cell[1][i] + position) % cell[0].length] = 1;
                            new_cell[3][(cell[1][i] + position) % cell[0].length] = cell[3][i];
                        }
                        //step4　速度を持たない場合
                        else {
                            new_cell[0][position] = 1;
                            new_cell[1][position] = cell[1][i];
                            new_cell[2][position] = 1;
                            new_cell[3][position] = cell[3][i];
                        }
                        car.gocell(cell[3][i], cell[1][i], time);
                        break;
                    }
                }
            }
        }

        return new_cell;
    }

    public int[][] rule_a(int[][] cell, int v_max, int i) {

        //step1
        if (cell[1][i] < v_max) {
            cell[1][i] = cell[1][i] + 1;
        }
        return cell;
    }

    public int[][] rule_b(int[][] cell, int v_max, int i, int distance) {

        //Step 1
            if (distance - 1 == Main.k) {
                cell[1][i] = cell[1][i];
            } else if (cell[1][i] > 0 && distance - 1 < Main.k) {
                cell[1][i] = cell[1][i] - 1;
            } else if (cell[1][i] < v_max && distance - 1 > Main.k) {
                if (cell[1][i] + 1 <= distance) {
                    cell[1][i] = cell[1][i] + 1;
                } else {
                    cell[1][i] = cell[1][i];
                }
            }
        return cell;
    }



    public Simulator(int maxcell,int v_max, double sim_time, double p) {
        total_car_num = Main.total_car_num;//maxcell*rho;車両の総数
        total_car_num = Main.total_car_num_g + Main.total_car_num_a;
        int[][] lane = new int[4][maxcell];//lane[0] 車の有無　lane[1]速度を記憶する　lane[2]車種を記憶する　1:ACC　2:CC 3:一般　lane[3]車の番号
        this.disp = Main.disp;//セルを表示させるか
        carman = new car((int) total_car_num, maxcell);//車のインスタンス
        lane laneclass = new lane();
        lane = laneclass.init_cell(lane, total_car_num_g,total_car_num_a, carman);//道路の車配置決定メソッド

        //主循环
        for (int t = 0; t <= (int) sim_time; t++) {
            scancar1.scan(lane[3][scancar1.getPosition()]);//调用scancar1的scan方法,并且插入位置参数
            if (t == 0) {//初期配置出力
                if (disp) option.simetime(t, lane);
            } else {

                lane = rule1(lane, p, v_max, carman, t);//lane情報を更新
                if (disp) option.simetime(t, lane);
                if (t >= 1) {
                    laneclass.SUM(lane, v_max);//通過台数をカウントするメソッド
                }
            }
        }

        this.count = laneclass.count;
        this.sim_time = sim_time;
    }

    public double getryuryo() {
        ryuryo = count / sim_time;
        return ryuryo;
    }

    public double getryokoujikan() {
        int counter = 0;
        for (int k = 0; k < total_car_num; k++) {
            if (carman.laptime[k] != 0) {
                counter = counter + carman.laptime[k];
                //System.out.println("No"+(k+1)+"の平均旅行時間:"+carman.laptime[k]+"秒");
            } else {
                System.out.println("No" + (k + 1) + "は一周してません");
            }
        }
        ryokoujikan = counter / total_car_num;
        return ryokoujikan;
    }

    public void getryokoujikan2() {
        int counter = 0;
        for (int k = 0; k < total_car_num; k++) {
            System.out.println("No" + (k + 1) + "の進んだセル:" + carman.goCell[k] + "");

        }

    }
}