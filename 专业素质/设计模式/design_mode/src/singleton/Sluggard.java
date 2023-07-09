package singleton;

public class Sluggard {
    // 懒汉式单例类.在第一次调用的时候实例化自己 
    private Sluggard() {}
    private static Sluggard single=null;
    //静态工厂方法 线程不安全的
    public static Sluggard getInstance() {
         if (single == null) {  
             single = new Sluggard();
         }  
        return single;
    }
    // 线程安全但是效率低
    public static synchronized  Sluggard getInstance0() {
        if (single == null) {  
            single = new Sluggard();
        }  
       return single;
   }
}