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

//        /**
//         * 给定一个分数:x，判断该分数所属等级
//         * 1，等级分为三种：不及格（x<60）、良好(80> x >60)、优秀(90 > x >80)、非常优秀(x>90)
//         * 2,用if-else 语句完成实现并打印输出
//         * 注:结合之前学到的 '&&', '>' ,'<' 等操作符合完成
//         */
//
//        int score = (int) (Math.random() * 100);  //在0-100中随机产生一个数，判断这个数所处的等级
//        System.out.println("当前随机产生的分数值为："+score);
//        System.out.println("-----------------------------\t");
//        System.out.println("该分数值所处的等级为：");
//
//        if (score < 60) {
//            System.out.println("不及格!" );
//        }else if (...){
//
//        }else...


    }
}
