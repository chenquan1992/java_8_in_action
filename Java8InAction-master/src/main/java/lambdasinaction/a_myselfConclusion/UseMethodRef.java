package lambdasinaction.a_myselfConclusion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with Chenquan.
 * Description:
 * Date: 2018-05-31
 * Time: 20:36
 */
public class UseMethodRef {

    public static int compareMC(MyClass a, MyClass b) {
        return a.getValue() - b.getValue();
    }

    public static void main(String[] args) {
        ArrayList<MyClass> a1 = new ArrayList<MyClass>();
        a1.add(new MyClass(1));
        a1.add(new MyClass(4));
        a1.add(new MyClass(2));
        a1.add(new MyClass(9));
        a1.add(new MyClass(3));
        a1.add(new MyClass(7));
        //UseMethodRef::compareMC生成了抽象接口Comparator定义的compare()方法的实例。
        MyClass maxValObj = Collections.max(a1, UseMethodRef::compareMC);
        System.out.println("Maximum value is: " + maxValObj.getValue());
    }

}