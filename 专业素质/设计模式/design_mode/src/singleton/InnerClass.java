package singleton;

public class InnerClass {
    // 创建静态类 并在内部类中加载实例
    private static class SingletoHolder{
        private static final InnerClass INSTANCE = new InnerClass();
    }
    private InnerClass(){}
    // 调用内部静态类加载实例
    public static InnerClass getInstance(){
        return SingletoHolder.INSTANCE;
    }
}
