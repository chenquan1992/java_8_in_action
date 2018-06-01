package lambdasinaction.chap5;
import lambdasinaction.chap4.*;

import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

public class Finding{

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("anyMatch ：Vegetarian friendly");
        }

        System.out.println("allMatch："+isHealthyMenu());
        System.out.println("noneMatch："+isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));//ifPresent 如果包含一个值就运行里面的函数，否则什么都不做
    }
    
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);//任何匹配：只要条件有一个返回true，就是true
    }
    
    private static boolean isHealthyMenu(){//全部匹配：只有条件全部是true，才能是true
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }
    
    private static boolean isHealthyMenu2(){//没有匹配：只要条件全都是false，才是true
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }
    
    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
    
}
