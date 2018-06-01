package lambdasinaction.a_myselfConclusion;

/**
 * Created with Chenquan.
 * Description:
 * Date: 2018-05-31
 * Time: 20:21
 */
public class MethodRefDemo {

    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {

        StringFunc sf = MyStringOps::strReverse;//这种写法就是实现接口

        String inStr = "lambda add power to Java";
        //MyStringOps::strReverse 相当于实现了接口方法func()
        // 并在接口方法func()中作了MyStringOps.strReverse()操作
        String outStr = stringOp(MyStringOps::strReverse, inStr);//strReverse方法传入的参数，与返回的参数相同就可以实现接口
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
    }

}