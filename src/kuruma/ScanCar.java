package kuruma;

public class ScanCar {
    private int carCount = 0;//测量结束之前经过的汽车数量(车辆id变化次数)
    private int timeCount = 0;//汽车在测量格子内的停留时间
    private int counter = 0;//测量次数的计数器
    private int maxCounter;//可定义的测量次数
    private int oldID = -999;//上一个时间点经过的汽车id

    ScanCar(int maxCounter) {//constructor
        this.maxCounter = maxCounter;//ScanCar的测量次数初始化
    }

    void scan(int newID) {
        if (counter == maxCounter) {
            //呼叫拥堵判断条件method
            reset();
        }
        checkSameCar(newID);


        counter++;
    }

    private boolean jamCondition() {

        /**
         * 如果该次测量内，经过车辆少于3辆（车辆id变化少于3次）那么判断为交通拥堵状态
         */
        if (carCount < 3){
            return true;
        }
        return false;
    }

    private void checkSameCar(int newID) {
        if (oldID != newID) {
            if (true) {//少车和多车的判定条件
                carCount++;
            }
        }
    }

    private void reset() {
        carCount = 0;
        timeCount = 0;
        counter = 0;
        oldID = -999;
    }

    public int getCarCount() {
        return carCount;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public int getCounter() {
        return counter;
    }

    public int getMaxCounter() {
        return maxCounter;
    }

    public int getOldID() {
        return oldID;
    }
}