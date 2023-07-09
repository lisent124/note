package singleton;

public class DoubleChecked {
    private volatile static DoubleChecked instance;
    private DoubleChecked(){}

    // 这种方式采用双锁机制，安全且在多线程情况下能保持高性能
    public static DoubleChecked getInstance(){
        if(instance == null){
            synchronized (DoubleChecked.class){
                if(instance == null){
                    instance = new DoubleChecked();
                }
            }
        }
        return instance;
    }
}
