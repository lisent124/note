package decorator;

/*
Java IO 流的设计使用了装饰器设计模式，
例如字符流输入流抽象类Reader 中有方法 public abstract int read()  读取一个字符 ；
StringRead是具体的字符输入流，实现read()方法，从字符串中读取一个字符。
FilterReader是抽象的装饰器类。请从FilterReader 类继承实现  LowerCaseStringReader ，
把输入字符流中大写字母都转换为小写。
代码如下：
*/
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
//只需要 补充完成 LowerCaseStringReader 类，且仅需提交 LowerCaseStringReader 类即可
class LowerCaseStringReader extends FilterReader {
    Reader in;
    protected LowerCaseStringReader(Reader in) {
        super(in);
        this.in=in;
    }
    @Override
    public  int read() throws IOException {
        // 请补充代码
        int c = in.read();
        if (c >= 'A' && c <= 'Z') 
            c = c - 'A' + 'a';
        return c;
    }
}
public class javaIO{
    public static void main(String[] args) throws IOException {
        LowerCaseStringReader r=new LowerCaseStringReader(new StringReader("Design Pattern"));
        int c;
        while( (c=r.read())>0){
            System.out.print((char)c);
        }
    }
}
/*
 * design pattern
 */