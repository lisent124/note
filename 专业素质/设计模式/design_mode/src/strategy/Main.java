package strategy;
/*
某软件公司欲开发一款汽车竞速类游戏，需要模拟长轮胎和短轮胎急刹车时在路面上留下的不同痕迹，
并考虑后续能模拟更多种轮胎急刹车时的痕迹。现采用策略(Strategy)设计模式来实现该需求，
所设计的类图如下所示：img
请编码实现模拟长轮胎和短轮胎急刹车时在路面上留下的不同痕迹。
完整程序结构如下，本题已经提供了接口BrakeBehavior的定义以及main函数部分，
请自行编写并提交红色提示部分代码！
*/


interface BrakeBehavior {
    public abstract void stop();
}
class LongWheelBrake implements BrakeBehavior{
    @Override
    public void stop() {
        System.out.println("Simulate long tire brake marks!");
    }
}
class ShortWheelBrake implements BrakeBehavior{
    @Override
    public void stop() {
        System.out.println("Simulate short tire brake marks!");
    }
}

class Car{
    BrakeBehavior behavior ;
    public void brake(){
        behavior.stop();
    }
}
class LongWheelCar extends Car{
    LongWheelCar(BrakeBehavior behavior){
        this.behavior = behavior;
    }
}
class ShortWheelCar extends Car{
    ShortWheelCar(BrakeBehavior behavior){
        this.behavior = behavior;
    }
}

public class Main {
    public static void main(String[] args) {
        BrakeBehavior brake1 = new ShortWheelBrake();
        BrakeBehavior brake2 = new LongWheelBrake();
        Car car = new ShortWheelCar(brake1);
        car.brake();
        car = new LongWheelCar(brake2);
        car.brake();
    }
}
/*
Simulate short tire brake marks!
Simulate long tire brake marks!
 */