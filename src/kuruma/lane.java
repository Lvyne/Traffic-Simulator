package kuruma;

import java.util.Random;

public class lane {
    int count;

    public void SUM(int[][] lane, int v_max) {
        for (int i = 0; i < v_max; i++) {
            if (lane[1][lane[0].length / 2 + i] > i) {
                this.count = this.count + 1;
            }
        }
    }

    //与えられた配列に対して，車両をランダムに配置するメソッド

    public int[][] init_cell(int[][] lane,double total_car_num_g,double total_car_num_a,car car) {
        //考えること
        long seed = 1L;
        Random r = new Random(seed);
        int carno = 0;

        //一般車両を形成するループ
        for (int i = 0; i < total_car_num_g; i++) {
            int find_car = (int) (r.nextDouble() * Main.CarConfigRange);//配列初期は0であるため，総台数に合わせて，車を作る
            if (lane[0][find_car] == 0) {
                lane[0][find_car] = 1;
                lane[2][find_car] = 3;
            } else {
                i--;
            }
        }

        //ACC車両を形成するループ
        for (int i = 0; i < total_car_num_a; i++) {
            int find_car = (int) (r.nextDouble() * Main.CarConfigRange);//配列初期は0であるため，総台数に合わせて，車を作る
            if (lane[0][find_car] == 0) {
                lane[0][find_car] = 1;
                lane[2][find_car] = 1;
            } else {
                i--;
            }
        }

        for (int i = 0; i < lane[0].length; i++) {
            if (lane[0][i] == 1) {
                lane[3][i] = carno + 1;
                carno++;
                car.carspeed(carno, lane[1][i]);
            }
        }
        return lane;
    }
}
