package state;
/*
某大型商场内安装了多个简易的纸巾售卖机，自动出售2元钱一包的纸巾，且每次仅售出一包纸巾。
纸巾售卖机的状态图如下图所示：
采用状态（State）模式来实现该纸巾售卖机，得到如下图所示的类图。
其中类State为抽象类，定义了投币、退币、出纸巾等方法接口。
类SoldState、SoldOutState、NoQuarterState和HasQuarterState
分别对应图6-1中纸巾售卖机的4种状态：
售出纸巾、纸巾售完、没有投币、有2元钱。
*/

abstract class State {
    protected TissueMachine tissueMachine;
    public void insertQuarter()//投币
    {
    }
    public void ejectQuarter()//退币
    {
    }
    public void turnCrank() //按下“出纸巾”按钮
    {
    }
    public void dispense() //出纸巾
    {
    }
    public abstract void printState();
}
class TissueMachine {
    private State soldOutState, noQuarterState, hasQuarterState, soldState, state;
    private int count; //纸巾数
    public TissueMachine(int numbers) {
        count = numbers;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        state = noQuarterState;
    }
    public State getHasQuarterState() {
        return hasQuarterState;
    }
    public State getNoQuarterState() {
        return noQuarterState;
    }
    public State getSoldState() {
        return soldState;
    }
    public State getSoldOutState() {
        return soldOutState;
    }
    public int getCount() {
        return count;
    }
    public void setState(State state) {
        this.state = state;
    }
    public void printState() {
        this.state.printState();
    }
    public void insertQuarter()//投币
    {
        this.state.insertQuarter();
    }
    public void ejectQuarter()//退币
    {
        this.state.ejectQuarter();
    }
    public void turnCrank() //按下“出纸巾”按钮
    {
        this.state.turnCrank();
    }
    public void dispense() //出纸巾
    {
        this.state.dispense();
    }
}
//类SoldState、SoldOutState、NoQuarterState和HasQuarterState
class SoldState extends State{

    SoldState(TissueMachine tissueMachine){
        this.tissueMachine = tissueMachine;
    }
    
    public void dispense(){//出纸巾
        // tissueMachine.count--;
        tissueMachine.setState(tissueMachine.getNoQuarterState());
        if (tissueMachine.getCount()==0)
            tissueMachine.setState(tissueMachine.getSoldOutState());
    }

    @Override
    public void printState() {
        System.out.println("SoldState");
        
    }

}
class SoldOutState extends State{
    SoldOutState(TissueMachine tissueMachine){
        this.tissueMachine = tissueMachine;
    }
    @Override
    public void printState() {
        System.out.println("SoldOutState");
        
    }
}
class NoQuarterState extends State{
    NoQuarterState(TissueMachine tissueMachine){
        this.tissueMachine = tissueMachine;
    }
    public void insertQuarter(){//投币
        tissueMachine.setState(tissueMachine.getHasQuarterState());
    }
    @Override
    public void printState() {
          System.out.println("NoQuarterState");
        
    }
}
class HasQuarterState extends State{
    HasQuarterState(TissueMachine tissueMachine){
        this.tissueMachine = tissueMachine;
    }
    public void ejectQuarter(){//退币
        tissueMachine.setState(tissueMachine.getNoQuarterState());
    }
    public void turnCrank(){//按下“出纸巾”按钮
        tissueMachine.setState(tissueMachine.getSoldState());
    }
    @Override
    public void printState() {
        System.out.println("HasQuarterState");
    }
}

public class Main {
    public static void main(String[] args) {
        TissueMachine tissueMachine = new TissueMachine(10);
        tissueMachine.printState();
        tissueMachine.insertQuarter();
        tissueMachine.printState();
        tissueMachine.ejectQuarter();
        tissueMachine.printState();
        tissueMachine.insertQuarter();
        tissueMachine.printState();
        tissueMachine.turnCrank();
        tissueMachine.printState();
        tissueMachine.dispense();
        tissueMachine.printState();
    }
}
/*
NoQuarterState

HasQuarterState

NoQuarterState

HasQuarterState

SoldState

NoQuarterState
 */