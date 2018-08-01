package kuruma;

public class ScanCar {
    private int jamCount = 0;//测量结束之前经过的汽车数量(车辆id变化次数)
    private int timeCount = 0;//汽车在测量格子内的停留时间
    private int counter = 0;//测量次数的计数器
    private int maxCounter;//可定义的测量次数
    private int oldID = -999;//上一个时间点经过的汽车id
    private int position;//测量器的位置
    private boolean jamCondition;//拥堵判定结果

    ScanCar(int maxCounter,int position) {//constructor
        this.maxCounter = maxCounter;//ScanCar的测量次数初始化
        this.position = position;
    }

    void scan(int newID) {
        System.out.println(newID);
        if (counter == maxCounter) {
            jamCondition();//呼叫拥堵判断条件method
            reset();
        }
        checkSameCar(newID);

        oldID = newID;
        counter++;

    }

    private void jamCondition() {
        /**
         * 如果该次测量内，经过车辆少于3辆（车辆id变化少于3次）那么判断为交通拥堵状态
         */
        if (jamCount >= 3){
            jamCondition = true;
        }
        System.out.println(jamCondition);
    }

    private void checkSameCar(int newID) {
        if (oldID == newID && newID != 0) {
                jamCount++;
            }
        }

    private void reset() {
        jamCount = 0;
        timeCount = 0;
        counter = 0;
        jamCondition = false;
    }

    //getter可以再外部调用private.
    public int getCarCount() {
        return jamCount;
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

    public int getPosition() { return position; }

    public boolean isJamCondition() { return jamCondition; }
}