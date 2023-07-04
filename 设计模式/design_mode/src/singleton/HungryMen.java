package singleton;

public class HungryMen {
    // 饿汉式 提前加载 多线程安全
    private HungryMen(){}
    // 类加载时就初始化，浪费内存
    private static HungryMen instance = new HungryMen();

    public static HungryMen getInstance(){
        return instance;
    }
}
