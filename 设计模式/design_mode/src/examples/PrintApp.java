package examples;

import java.util.ArrayList;
import java.util.List;

/*若实现一个画图的小程序，可以画圆形、矩形、三角形等等
，也可以把多个图形组合起来等，还可以插入文本等，思考可以使用哪些设计模式？
（组合设计模式、状态设计模式、命令设计模式......)
 请将设计描述出来。
 */


class Geometry{
    protected String info;
    public void setInfo(String info) {
        this.info = info;
    }
    public void draw(){

    }
}

class Circular extends Geometry{
    @Override
    public void draw() {
        System.out.print("Circular");
        if (info != null)
            System.out.print("with "+info);
    }
}

class Retangle extends Geometry{
    @Override
    public void draw() {
        System.out.print("Retangle");
        if (info != null)
            System.out.print("with "+info);
    }
}

class Triangle extends Geometry{
    @Override
    public void draw() {
        System.out.print("Triangle");
        if (info != null)
            System.out.print("with "+info);
    }
}

class Frame{
    public void addFrame(Frame frame){
        System.out.println("Part is not allowed to addFrame.");
    }
    public void print(){

    }

}

class Part extends Frame{
    private Geometry geometry;

    Part(Geometry geometry){
        setGeometry(geometry);
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    @Override
    public void print() {
        System.out.print("<");
        geometry.draw();
        System.out.println(">");
    }
}

class Composite extends Frame{
    List<Frame> list = new ArrayList<>();

    @Override
    public void addFrame(Frame frame) {
        list.add(frame);
    }
    @Override
    public void print() {
        System.out.println("<");
        for (Frame frame : list) {
            frame.print();
        }
        System.out.println(">");
    }
}

public class PrintApp{
    public static void main(String[] args) {
        Frame mainFrame = new Composite();
        Frame part1 = new Part(new Circular());
        Geometry triangle = new Triangle();
        triangle.setInfo("Triangle say.");
        Frame part2  = new Part(triangle);

        mainFrame.addFrame(part1);
        mainFrame.addFrame(part2);

        Frame conposite = new Composite();
        Geometry reTangle = new Retangle();
        reTangle.setInfo("null");
        conposite.addFrame(new Part(reTangle));
        mainFrame.addFrame(conposite);

        mainFrame.print();


    }
}