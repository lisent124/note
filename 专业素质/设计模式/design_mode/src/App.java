import java.io.Reader;
import java.io.StringReader;

public class App {
    public static void main(String[] args) throws Exception {
        Reader r =new StringReader("Design Pattern");
        int c;
        while( (c=r.read())>0){
            System.out.print((char)c);
        }
    }
}
