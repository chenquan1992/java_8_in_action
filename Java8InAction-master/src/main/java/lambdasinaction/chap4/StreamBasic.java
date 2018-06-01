package lambdasinaction.chap4;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() > 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d2, Dish d1){//排序：传进来的参数位置与比较的位置相同就是升序排序，反之，倒序。可自行更换d2与d1的位置顺序看结果
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getCalories()+"");
        }
        return lowCaloricDishesName;
    }

    public static List<Integer> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(d -> d.getCalories() > 400)
                .limit(3) // debug的时候看集合顺序，取的前三条
                .skip(2)  //  debug的时候看集合顺序，跳过了前两条不取，取剩下的
                .distinct()  //只保留不同元素的，根据流所生成元素的 hashCode 和 equals 方法实现
                .sorted(comparing(Dish::getCalories))//Dish::getCalories 方法应用，看做是调用Dish中的getCalories方法这样去理解
                .map(Dish::getCalories) // map 这个方法相当于 调用了 getCalories 的方法，将他返回的值存在map中，然后
                .collect(toList());// 在这里操作成一个集合可以返回
    }
}
