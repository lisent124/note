package abstract_factory;
/*
某界面可以使用不同风格的控件：
按钮Button有Window风格的子类 WindowButton 和 Motif 风格的子类树MotifButton；
文本框TextBox也有Windows风格文本框WindowsTextBox 和Motif风格的文本框MotifTextBox，
AbstractFactory 是抽象工厂接口，
请实现具体的Window风格工厂WindowFactory 及Motif风格的工厂Motifactory 
*/


abstract class Button{};
abstract class TextBox{};

class WindowButton extends Button {
    public WindowButton(){
        System.out.println("createWindowsButton");
    }
}
class MotifButton extends Button {
    public MotifButton(){
        System.out.println("createMotifButton");
    }
}
class WindowTextBox extends  TextBox{
    public WindowTextBox(){
        System.out.println("createWindowTextBox");
    }
}
class MotifTextBox extends  TextBox{
    public MotifTextBox(){
        System.out.println("createMotifTextBox");
    }
}
interface AbstractFactory {
    Button createButton();
    TextBox createTextBox();
}
class Dialog{
    AbstractFactory factory;
    Button button;
    TextBox textBox;
    public void setFactory(AbstractFactory factory) {
        this.factory = factory;
    }
    public void init(){
        button=factory.createButton();
        textBox=factory.createTextBox();
    }
}
//请提交WindowFactory  和 Motiffactory 
class WindowFactory implements AbstractFactory{

    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowTextBox();
    }


}
class Motiffactory implements AbstractFactory{

    @Override
    public Button createButton() {
        return new MotifButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MotifTextBox();
    }
}
public class Main{
    public static void main(String[] args){
        Dialog dialog=new Dialog();
        dialog.setFactory(new WindowFactory());
        dialog.init();
        dialog.setFactory(new Motiffactory());
        dialog.init();
    }
}
