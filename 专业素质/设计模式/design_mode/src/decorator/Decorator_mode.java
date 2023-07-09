package decorator;

/*
Componet是界面组件中的抽象父类，其TextBox子类是文本框组件，Table子类是表格组件。
Decorator是抽象的装饰器父类，有两个具体的装饰器：ScrollDecorator给Componet画上滚动条
（简单地在被装饰对象draw()后System.out.print(" drawScroll")即可 ）
BorderDecorator给Componet画上边框
（简单地在被装饰对象draw()后System.out.print(" drawBorder");即可 ）
请完成ScrollDecorator  和 BorderDecorator 类并提交
*/
abstract class Component {
    abstract void draw();
}
class TextBox extends Component{
    @Override
    void draw() {
        System.out.print("drawTextBox");
    }
}
class Table extends Component{
    @Override
    void draw() {
        System.out.print("drawTable");
    }
}
abstract  class Decorator extends Component{
}
/**
    完成ScrollDecorator  和 BorderDecorator 类并提交
**/
class ScrollDecorator extends Decorator{
    public Component component;
    ScrollDecorator(Component component){
        this.component = component;
    }

    @Override
    void draw() {
        this.component.draw();
        System.out.print(" drawScroll");
    }
    
}
class BorderDecorator extends Decorator{
    public Component component;
    BorderDecorator(Component component){
        this.component = component;
    }

    @Override
    void draw() {
        this.component.draw();
        System.out.print(" drawBorder");
    }
    
}

public class Decorator_mode{
    public static void main(String[] args){
        Component t=new ScrollDecorator(new BorderDecorator(new Table()));
        Component text=new BorderDecorator(new TextBox());
        t.draw();
        System.out.println();
        text.draw();
    }
}
