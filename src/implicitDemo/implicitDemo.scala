package implicitDemo

/**
  * Created by Administrator on 2018/8/6.
  */
object implicitDemo extends App{
  implicit def String2IntHjd(str: String) = { //参数类型不能重复！，只能定义这一个
    str.toDouble
  }
  println("120"/12) //编译器一旦发现对于 String 类操作符 / 不可用，而 + 方法正好对应 Int 型的参数，且当前作用域存在 String 类型的隐式转换。  所以实际等价于print(String2Int("120") / 12)。
  println("120"+12 ) //显式操作先行原则：若编写的代码类型检查无误，则不会尝试任何隐式操作。

  implicit def Double2Int(num: Double) = num.toInt
  val x:Int = 12.0     //命名隐式转换,数据类型已经声明规定了
  print(x)
}
