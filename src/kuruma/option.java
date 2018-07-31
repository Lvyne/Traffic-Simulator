package kuruma;

public class option {
    public static void simetime(int time, int[][] lane) {
        if(time ==  Main.t) {
            System.out.println("sim_time");
            System.out.println(time + "秒");
            disp_array(lane);
        }
    }


    //配列の内容を出力するメソッド
    public static void disp_array(int[][] array) {
        int count = 0;
        //System.out.println("lane");
        //System.out.print("有無" + "\t");
        for (int i = 0; i < array[0].length; i++) {
            //System.out.print(array[0][i] + "\t");
            if (array[0][i] == 1) {
                count++;
            }
        }
        //System.out.println();
        //System.out.print("速度" + "\t");
        for (int i = 0; i < array[0].length; i++) {
            //System.out.print(array[1][i] + "\t");
        }
        //System.out.println();
        //System.out.print("車種" + "\t");
        for (int i = 0; i < array[0].length; i++) {
            //System.out.print(array[2][i] + "\t");
        }
        //System.out.println();
        //System.out.print("番号" + "\t");
        for (int i = 0; i < array[0].length; i++) {
            //System.out.print(array[3][i] + "\t");
        }
        //System.out.println();
        //System.out.println("車両数:" + count);
        //System.out.println();
    }
}
