package javax1;

/**
 * Created by Administrator on 2018/8/31.
 */
public class Test01 {
    public static void main(String[] args) {
        String m = String.format("%03d",Integer.parseInt(Integer.toHexString(23)));
        System.out.println(String.format("%04x",16));
        System.out.println(m);

         StringBuilder buffer = new StringBuilder("CW");
        buffer.append(123);
        System.out.println(buffer);

        System.out.println(String.format("%04d", 121));
    }
}
