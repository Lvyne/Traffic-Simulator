package kuruma;

public class car {
    int[] goCell;//進んだセル数
    int[] carspeed;//車の速さ
    int[] lapcount;//周回数
    int maxcell;//セルの長さ
    int[] laptime;//ラップタイム
    int[] lapold;//前回のラップタイム


    public car(int car_num, int maxcell) {
        this.carspeed = new int[car_num];
        this.goCell = new int[car_num];
        this.maxcell = maxcell;
        this.lapcount = new int[car_num];
        this.laptime = new int[car_num];
        this.lapold = new int[car_num];
    }


    public void carspeed(int carno, int speed) {
        carspeed[carno - 1] = speed;
    }


    public void gocell(int carno, int go, int time) {
        goCell[carno - 1] = goCell[carno - 1] + go;
        if (goCell[carno - 1] > ((lapcount[carno - 1] + 1) * maxcell)) {//goCell是现在的车辆行进格子数，超过maxcell记一周，超过两倍记两周
            lapcount[carno - 1]++;//计数器
            if (lapcount[carno - 1] == 1) {//一周回的时候
                laptime[carno - 1] = time;//记录旅行时间
                lapold[carno - 1] = time;//同时存入上周旅行时间
            } else {
                laptime[carno - 1] = (laptime[carno - 1] + (time - lapold[carno - 1])) / 2;//这次和上次的旅行时间合算计平均
                lapold[carno - 1] = time;//同时存入上周旅行时间
            }
        }
    }
	
	
	/*public void goCellCount(int position,int gocell,int time){
		goCell[position]=goCell[position]+gocell;
		
	}*/

}
