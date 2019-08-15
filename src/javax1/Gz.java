package javax1;

/**
 * Created by Administrator on 2018/11/5.
 */
class Qs{
    public Qs(){
        System.out.println("1213111111");
    }
}
public class Gz extends Qs {
    private String r1 = "a";
    private String r2 = "b";
    public Gz(){
        System.out.println(r1+r2);
    }
    public Gz(String a,String b){
        r1=a;
        r2=b;
        System.out.println("gouzao:"+r1+r2);
    }

    public static void main(String[] args){
        Gz gz = new Gz();
        Gz gz1 = new Gz("Q","W");
    }
}