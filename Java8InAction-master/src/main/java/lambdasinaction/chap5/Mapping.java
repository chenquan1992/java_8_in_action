package lambdasinaction.chap5;

import lambdasinaction.chap4.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;

public class Mapping{

    public static void main(String...args){

        // map
        List<Integer> dishNames = menu.stream()
                                     .map(Dish::getName)  //调用Dish中的getName方法，获取到所有名字的流
                                     .map(String::length) //使用上面的流继续操作，调用String的length的方法获取到长度的流，
                                     .collect(toList()); //将这些流变成一个集合返回
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // flatMap
        List<String[]> collect = words.stream()
                .map(word -> word.split("")) //注意这里返回的是一个数组的集合，所以下面
//              .distinct()  // 在这里使用这个是没有效果的
                .collect(toList());

        // flatMap
        Stream<String> stringStream = words.stream()
                .map(word -> word.split("")) //注意这里返回的是一个数组的集合，所以下面
                .flatMap(Arrays::stream); // 注意加了这个之后就变成单个String的流，所以下面
//              .distinct()  // 这时候就可以使用，这就有效果了
//                .collect(toList());

        // Arrays.stream（）将数组变成各自单独的流
        // flatMap 将各自单独的流整合成一个流
        words.stream()
                 .flatMap((String line) -> Arrays.stream(line.split("")))
                 .distinct()
                 .forEach(System.out::println);

        // flatMap
        System.out.println("=========================================================================================");
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> { // 有流返回到这里    二
                                    System.out.println("i = "+i);
                                    return numbers2.stream()
                                                       .map((Integer j) ->{
                                                             System.out.println("j = "+j);
                                                             return new int[]{i, j}; //当这里有返回的时候，自然的就会运行下面的fillter  一
                                                       });
//                                                        .filter(pair -> {  //注销的这段与下面的效果相同
//                                                            System.out.println("pair 1 = (" + pair[0]+ ","+pair[1]+")");
//                                                            return (pair[0] + pair[1]) % 3 == 0;
//                                                        });
                                })
                                .filter(pair -> { //自然就会运行这里    三
                                    System.out.println("pair 2 = (" + pair[0]+ ","+pair[1]+")");
                                    return (pair[0] + pair[1]) % 3 == 0;
                                })
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
