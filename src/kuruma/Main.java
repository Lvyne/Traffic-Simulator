package kuruma;

public class Main {
    static boolean disp = true;//セル表示するtrue　しないfalse
    //static int bottle_start = 1998;
    //static int bottle_end = 1999;
    static int CarConfigRange = 2000;
    //static int l=10;
    static int k =5;
    static int t =100;
    static double total_car_num_g = 0;
    static double total_car_num_a = 300;
    static double total_car_num  = total_car_num_g + total_car_num_a;

    //static double p_bottle = 0.1;//ボトルネック区間のランダムブレーキ確率

    public static void main(String[] args) {
        int maxcell = 2000;
        //int travel_time = 0;//速度を持たせる時間
        int v_max = 5;//最高速度
        double sim_time = 10000;//シミュレーション試行回数
        //double rho = 0.1;
        //double gamma_rho = 0.1;//一般車の密度
        //double zensharo = 0.1;//全車両の密度
        double p = 0.1;//ランダムブレーキ確率

        Simulator simulator = new Simulator(maxcell, v_max, sim_time, p);
        System.out.println("全体平均旅行時間:" + simulator.getryokoujikan());
        //simulator.getryokoujikan2();
        //System.out.println("流量:"+simulator.getryuryo());
    }
}
