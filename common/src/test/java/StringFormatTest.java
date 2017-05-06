/**
 * Created by ajkx
 * Date: 2017/5/6.
 * Time:14:31
 */
public class StringFormatTest {

    public static void main(String[] args) {
        String s = "from %s x where 1=1 ";
        System.out.println(String.format(s,"aaa"));
    }
}
