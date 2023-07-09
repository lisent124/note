package combination;

/**
*采用组合设计模式实现价格计算： Part类是零件（叶子节点）零件的价格直接由构造方法传入；
*Composite是组合件，由Componet组成，价格由子节点累计；
*Componet是零件和组件共有的父类，des数据域是名称；
*请完成Composite类和Part类提交
*/

import java.util.*;


abstract class Component {
    String des="";
    public String getDes() {
        return des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public Component(String s) {
        this.des = s;
    }
    public abstract  double  computerPrice();
    public void addComponent(Component c){
        throw new UnsupportedOperationException("不能添加子节点");
    }
}

//请完成Composite类和Part类提交
class Composite extends Component{
    List<Component> list = new ArrayList<>();
    public Composite(String s) {
        super(s);
    }

    @Override
    public double computerPrice() {
        double sum = 0;
        for (Component component : list) {
            sum += component.computerPrice();
        }
        return sum;
    }

    @Override
    public void addComponent(Component c){
        list.add(c);
    }
}

class Part extends Component{
    private double price;
    List<Component> list = new ArrayList<>();
    public Part(double price,String s) {
        super(s);
        this.price = price;
    }
    @Override
    public double computerPrice() {
        return this.price;
    }
    @Override
    public void addComponent(Component c){
        list.add(c);
    }
}
public class Componet {
    public static void main(String[] args){
        Composite computer=new Composite("计算机");
        Part mouse=new Part(30,"鼠标");
        Part keyBoard=new Part(40,"键盘");
        Part screen=new Part(800,"显示器");
        computer.addComponent(mouse);
        computer.addComponent(keyBoard);
        computer.addComponent(screen);
        Composite box=new Composite("机箱");
        Part mainBoard=new Part(800,"主板");
        Part displayCard=new Part(500,"显卡");
        Part disk=new Part(400,"硬盘");
        box.addComponent(mainBoard);
        box.addComponent(displayCard);
        box.addComponent(disk);
        computer.addComponent(box);
        System.out.println(box.computerPrice());
        System.out.println(computer.computerPrice());
    }
}