package observer;
/*
 * 火灾报警器类FireWarner但当有火灾时能发布报警信息，
 * 广播Broadcast、电视TV等类可订阅报警信息，当有报警信息时得到通知，
 * 请用观察者设计模式解决该问题。
 */

import java.util.ArrayList;
import java.util.List;

interface FireWarner{
    public void warning();
}

class BroadCast implements FireWarner{
    @Override
    public void warning() {
        System.out.println("BroadCast: this is a fire!");
    }
}

class TV implements FireWarner{
    public void warning(){
        System.out.println("TV: this is a fire!");
    }
}

class Agency{
    List<FireWarner> warners = new ArrayList<>();
    public void attach(FireWarner fireWarner){
        warners.add(fireWarner);
    }
    public void detach(FireWarner fireWarner){
        warners.remove(fireWarner);
    }

    public void notifyFire(){
        for (FireWarner fireWarner : warners) {
            fireWarner.warning();
        }
    }
}

class TVAgency extends Agency{
    public void notifyFire(){
        System.out.println("XXTV Notify");
        super.notifyFire();
    }
}
class Government extends Agency{
    public void notifyFire(){
        System.out.println("XX Government Notify");
        super.notifyFire();
    }
}
public class Main {
    public static void main(String[] args) {
        FireWarner broadCast = new BroadCast();
        FireWarner tv = new TV();

        Agency tvAgency = new TVAgency();
        tvAgency.attach(tv);

        Agency government = new Government();
        government.attach(tv);
        government.attach(broadCast);

        System.out.println("fire is on.");

        government.notifyFire();
        tvAgency.notifyFire();

        return;
    }
}
